package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent srcIntent = getIntent();
        List<Question> questions = srcIntent.getParcelableArrayListExtra("questions");
        Question[] questionsArray = new Question[questions.size()];
        Question question = questions.toArray(questionsArray)[2];

        ImageView questionImg = findViewById(R.id.questionImage);
        questionImg.setImageResource(question.getImgId());

        TextView questionText = findViewById(R.id.questionTextView);
        questionText.setText(question.getQuestion());

        RadioGroup radioGroup = findViewById(R.id.answersRadioGroup);
        for (String answer: question.getAnswers()){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);
            radioGroup.addView(radioButton);
        }


        //Early Return
        /*if(isEmpty()){
            Toast.makeText(GameActivity.this, "Aucune réponse n'a été séléctionnée",Toast.LENGTH_SHORT).show();
            return;
        }*/
    }
}
