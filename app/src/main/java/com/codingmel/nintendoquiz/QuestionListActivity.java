package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private List<Question> questions;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_questions);
        Intent srcIntent = getIntent();
        ArrayList<Question> questionList = srcIntent.getParcelableArrayListExtra("questions");

        questions = new ArrayList<>();
        for (Question question:questionList){
            questions.add(question);
        }
        adapter = new QuestionAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
