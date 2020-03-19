package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private boolean isItRightAnswer;
    private int questionIndex = 0;
    private Intent srcIntent;
    private List<Question> questions;
    private Question[] questionsArray;
    private Question question;
    private Button nextButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        srcIntent = getIntent();
        questions = srcIntent.getParcelableArrayListExtra("questions");
        Collections.shuffle(questions);
        questionsArray = new Question[questions.size()];

        setQuestion(questionIndex);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Early Return
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(GameActivity.this, "Aucune réponse n'a été séléctionnée",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(questionIndex < questionsArray.length-1){
                    questionIndex++;
                    radioGroup.removeAllViews();
                    setQuestion(questionIndex);
                }else{
                    Intent intent = new Intent(GameActivity.this, QuestionListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void setQuestion(int index){
        question = questions.toArray(questionsArray)[index];

        ImageView questionImg = findViewById(R.id.questionImage);
        questionImg.setVisibility(View.INVISIBLE);

        Button soundButton = findViewById(R.id.soundButton);
        soundButton.setVisibility(View.INVISIBLE);

        if(question.getSoundId() != 0){
            soundButton.setVisibility(View.VISIBLE);
        }else{
            questionImg.setImageResource(question.getImgId());
            questionImg.setVisibility(View.VISIBLE);
        }

        TextView questionText = findViewById(R.id.questionTextView);
        questionText.setText(question.getQuestion());

        radioGroup = findViewById(R.id.answersRadioGroup);
        for (String answer: question.getAnswers()){
            final RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);
            radioButton.setOnClickListener(new RadioButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radioButton.getText().equals(question.getRightAnswers())){
                        Toast.makeText(GameActivity.this,"C'est la bonne réponse !",Toast.LENGTH_SHORT).show();
                        isItRightAnswer = true;
                    }else{
                        Toast.makeText(GameActivity.this,"Mauvaise réponse :(",Toast.LENGTH_SHORT).show();
                        isItRightAnswer = false;
                    }
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
