package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
//GAME CLASS ACTIVITY
//This activity manage all the game part

public class GameActivity extends AppCompatActivity {
    //Here we initialize all useful variables, which have multiple use
    private int questionIndex = 0;
    private Intent srcIntent;
    private List<Question> questions;
    private Question[] questionsArray;
    private Question question;
    private Button nextButton;
    private RadioGroup radioGroup;
    private int goodAnswers;
    private MediaPlayer mp;
    private Button soundButton;
    private CountDownTimer cntr_aCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        srcIntent = getIntent();//Get parent Intent
        questions = srcIntent.getParcelableArrayListExtra("questions");//Here we get a list of Questions (ArrayList)

        Collections.shuffle(questions); //Question Order Randomization
        questionsArray = new Question[questions.size()];//We transform the ArrayList in a simple array

        final String difficulty = srcIntent.getStringExtra("difficulty");//Here we get the difficulty of the game

        nextButton = findViewById(R.id.nextButton);

        setQuestion(questionIndex);//Question Initialisation

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*--- All this part concerns the player button, to avoid music to continue playing in others questions ---*/
                //If there was music playing, we need to stop it
                if(mp != null){
                    mp.stop(); //Here we stop the current media player

                    //There is a counter used for only playing 10sec of a sound, we also need to stop it
                    if(cntr_aCounter != null){
                        cntr_aCounter.cancel();//Here we stop the counter
                    }
                    //When a sound is played, the sound button is disabled, here, we enabled it
                    if(!soundButton.isEnabled()){
                        soundButton.setEnabled(true);
                    }
                }
                /*--- End part ---*/

                ProgressBar progressBar = findViewById(R.id.progressBar);
                float progression = ((float)(questionIndex+1)/(float)questions.size())*100; //For the progress bar, we need a percentage of advancement
                progressBar.setProgress((int)progression);//Here we set the progression with the percentage calculation above


                if(questionIndex < questionsArray.length-1){
                    questionIndex++;
                    radioGroup.removeAllViews();//Here we remove all radio buttons in order to leave space for the next radio buttons
                    radioGroup.setVisibility(View.VISIBLE);
                    setQuestion(questionIndex); //We call one more time setQuestions for initialise activity with new data
                }else{
                    //When the game is over, we need to go to the result page
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    intent.putExtra("goodAnswerPercentage",((float)goodAnswers/(float)questions.size())*100);
                    intent.putExtra("totalNumberOfQuestions",questions.size());
                    intent.putExtra("difficulty",difficulty);
                    //For the result page, we need to give him the percentage of good answer, the total number of questions and the current difficulty
                    finish();//Here we kill the current activity, then we cannot back to the quiz from the result page
                    startActivity(intent);//Start the next Activity
                }
            }
        });
    }

    /*--Set Question take a int, the index of the current question in order to update the layout,
    this methos is called everytime next question's button is pressed--*/
    public void setQuestion(int index){
        question = questions.toArray(questionsArray)[index];//We get the curent question with index in current Array
        /*---All next items  will be set to invisible*/
        nextButton.setVisibility(View.INVISIBLE);
        ImageView questionImg = findViewById(R.id.questionImage);
        questionImg.setVisibility(View.INVISIBLE);

        soundButton = findViewById(R.id.soundButton);
        soundButton.setVisibility(View.INVISIBLE);

        final TextView resultTitle = findViewById(R.id.answerResultTitleTextView);
        resultTitle.setVisibility(View.INVISIBLE);

        final TextView answerResult = findViewById(R.id.answerResultTextView);
        answerResult.setVisibility(View.INVISIBLE);
        /*--- End Part ---*/

        /*---This part manage the display of an image or a sound button---*/
        if(question.getSoundId() != 0){
            //SOUND MANAGEMENT
            soundButton.setVisibility(View.VISIBLE); //The playable button is showed
            soundButton.setOnClickListener(new View.OnClickListener() {
                boolean isPlaying;
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(GameActivity.this, question.getSoundId());//We give to the media player the id of the current sound
                    if(!isPlaying){
                        //We initialize the counter for only playing 10sec of a sound
                        cntr_aCounter = new CountDownTimer(10000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                mp.start();//Sound start at the same time of the timer
                                soundButton.setEnabled(false);//Disable player button to not play again the sound while it's always playing
                            }

                            public void onFinish() {
                                //code fire after finish
                                mp.stop();//When timer end, the sound end
                                soundButton.setEnabled(true);//When the timer end, the button is enabled for re-playing the sound
                                isPlaying = false;
                            }
                        };
                        cntr_aCounter.start();//When the play button is pressed, the timer stard
                        isPlaying = true;
                    }
                }
            });
        }else{
            //IMAGE MANAGEMENT
            questionImg.setImageResource(question.getImgId());//Set image with the current img Id
            questionImg.setVisibility(View.VISIBLE); //The image is show
            //OnClick on the image
            questionImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When img clicked, we can watch id bigger in a new Activity
                    Intent intent = new Intent(GameActivity.this, FullScreenImgActivity.class);
                    intent.putExtra("imgId",question.getImgId());//We give to the next Activity the current img Id
                    startActivity(intent);//Go to the next Activity
                }
            });
        }
        /*--- End Part ---*/

        TextView questionText = findViewById(R.id.questionTextView);
        questionText.setText(question.getQuestion()); //Set the text of the current question

        /*--- This part manage the radio button group ---*/
        radioGroup = findViewById(R.id.answersRadioGroup);
        Collections.shuffle(question.getAnswers());// Randomize answers order

        for (String answer: question.getAnswers()){
            //For each answer, a radio button is created//
            final RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);//Set the text of the radio buttton
            radioButton.setOnClickListener(new RadioButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.setEnabled(false);
                    radioGroup.setVisibility(View.INVISIBLE);//When we click on a radio button, the radio group is disabled and became invisile
                    if(radioButton.getText().equals(question.getRightAnswers())){
                        resultTitle.setText("VRAI !");//The text of the result is set
                        goodAnswers++; //If it's a good answers, the goodAnswers counter is incremented
                    }else{
                        resultTitle.setText("FAUX ! :( "); //The text of the result is set
                    }
                    answerResult.setText("La réponse était : " + question.getRightAnswers()); //The text of the result is set
                    resultTitle.setVisibility(View.VISIBLE);
                    answerResult.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    //All the texts for result are showed instead of the radio button group

                    //Set the text display depending on the current gameplay
                    if(questionIndex < questionsArray.length-1){
                        nextButton.setText("Question Suivante"); //If there is another one question, set "Next Question"
                    }else if (questionIndex == questionsArray.length -1){
                        nextButton.setText("Voir les réuslats"); //If the game is over, set "Show Results"
                    }
                }
            });
            radioGroup.addView(radioButton);//Show the new radio button group
        }
        /*--- End Part ---*/
    }
}
