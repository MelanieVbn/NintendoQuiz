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

public class GameActivity extends AppCompatActivity {
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
    CountDownTimer cntr_aCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        srcIntent = getIntent();
        questions = srcIntent.getParcelableArrayListExtra("questions");
        Collections.shuffle(questions);
        questionsArray = new Question[questions.size()];

        final String difficulty = srcIntent.getStringExtra("difficulty");

        nextButton = findViewById(R.id.nextButton);
        setQuestion(questionIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.stop();
                    if(cntr_aCounter != null){
                        cntr_aCounter.cancel();
                    }
                    if(!soundButton.isEnabled()){
                        soundButton.setEnabled(true);
                    }
                }

                ProgressBar progressBar = findViewById(R.id.progressBar);
                float progression = ((float)(questionIndex+1)/(float)questions.size())*100;
                progressBar.setProgress((int)progression);

                if(questionIndex < questionsArray.length-1){
                    questionIndex++;
                    radioGroup.removeAllViews();
                    radioGroup.setVisibility(View.VISIBLE);
                    setQuestion(questionIndex);
                }else{
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    intent.putExtra("goodAnswerPercentage",((float)goodAnswers/(float)questions.size())*100);
                    intent.putExtra("totalNumberOfQuestions",questions.size());
                    intent.putExtra("difficulty",difficulty);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
    public void setQuestion(int index){
        question = questions.toArray(questionsArray)[index];

        nextButton.setVisibility(View.INVISIBLE);

        ImageView questionImg = findViewById(R.id.questionImage);
        questionImg.setVisibility(View.INVISIBLE);

        soundButton = findViewById(R.id.soundButton);
        soundButton.setVisibility(View.INVISIBLE);

        final TextView resultTitle = findViewById(R.id.answerResultTitleTextView);
        resultTitle.setVisibility(View.INVISIBLE);

        final TextView answerResult = findViewById(R.id.answerResultTextView);
        answerResult.setVisibility(View.INVISIBLE);

        if(question.getSoundId() != 0){
            soundButton.setVisibility(View.VISIBLE);
            soundButton.setOnClickListener(new View.OnClickListener() {
                boolean isPlaying;
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(GameActivity.this, question.getSoundId());
                    //mp.seekTo(50000);
                    if(!isPlaying){
                        Log.i("Music", "onClick: Playing ");
                        mp.start();
                        cntr_aCounter = new CountDownTimer(10000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                mp.start();
                                soundButton.setEnabled(false);
                            }

                            public void onFinish() {
                                //code fire after finish
                                mp.stop();
                                soundButton.setEnabled(true);
                                isPlaying = false;
                            }
                        };
                        cntr_aCounter.start();
                        isPlaying = true;
                    }
                }
            });
        }else{
            questionImg.setImageResource(question.getImgId());
            questionImg.setVisibility(View.VISIBLE);
            questionImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameActivity.this, FullScreenImgActivity.class);
                    intent.putExtra("imgId",question.getImgId());
                    startActivity(intent);
                }
            });
        }

        TextView questionText = findViewById(R.id.questionTextView);
        questionText.setText(question.getQuestion());

        radioGroup = findViewById(R.id.answersRadioGroup);
        Collections.shuffle(question.getAnswers());
        for (String answer: question.getAnswers()){
            final RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);
            radioButton.setOnClickListener(new RadioButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.setEnabled(false);
                    radioGroup.setVisibility(View.INVISIBLE);
                    if(radioButton.getText().equals(question.getRightAnswers())){
                        resultTitle.setText("VRAI !");
                        goodAnswers++;
                        Log.i("Pourcentage", "goodAnswers: "+goodAnswers);
                    }else{
                        resultTitle.setText("FAUX ! :( ");
                    }
                    answerResult.setText("La réponse était : " + question.getRightAnswers());
                    resultTitle.setVisibility(View.VISIBLE);
                    answerResult.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    if(questionIndex < questionsArray.length-1){
                        nextButton.setText("Question Suivante");
                    }else if (questionIndex == questionsArray.length -1){
                        nextButton.setText("Voir les réuslats");
                    }
                }
            });
            radioGroup.addView(radioButton);
        }
    }
}
