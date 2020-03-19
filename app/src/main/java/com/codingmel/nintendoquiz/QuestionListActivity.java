package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        questions = new ArrayList<>();
        for (Question question:getEasyQuestions()){
            questions.add(question);
        }
        adapter = new QuestionAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Question> getEasyQuestions(){
        Question questions[] = new Question[]{
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Luigi","Wario","Waluigi"),"Waluigi"),
                new Question("De quel jeu ce personnage est-il l'antagoniste ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("The Legend of Zelda","Pokemon","Super Mario Bros"),"The Legend of Zelda"),
                new Question("Quel sont les noms de ces pokemons ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Bulbizarre, Salamèche et Carapuce","Germignon, Héricendre et Kaiminus","Brindibou, Flamiaou et Otaquin"),"Bulbizarre, Salamèche et Carapuce"),
                new Question("De quel jeu vient cette musique ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("The Legend of Zelda","Mario Kart","Splatoon"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("De qui se personnage est-il le personnage mirroir?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Peach"),"Mario"),
                new Question("Dans quel lieu de Mario Bros retouve-t-on cette musique ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Les sous terrains","Sous L'eau","Dans les airs"),"Les sous terrains"),
                new Question("De quel jeu vient cette musique ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Wii Sport","Mario Galaxy","Pokemon"),"Wii Sport"),
                new Question("Quel est le nom de ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Professeur Mario","Mario le Toubib","Dr.Mario"),"Dr.Mario"),
                new Question("Quelle console avait cette intoduction ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Game Cube","Wii","Nintendo 64"),"Game Cube")
        };
        return Arrays.asList(questions);
    }

    private List<Question> getMediumQuestions(){
        Question questions[] = new Question[]{
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi")
        };
        return Arrays.asList(questions);
    }

    private List<Question> getHardQuestions(){
        Question questions[] = new Question[]{
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi"),
                new Question("Quel est ce personnage ?","FACILE",R.drawable.red_mushroom,0, Arrays.asList("Mario","Luigi","Wario"),"Luigi")
        };
        return Arrays.asList(questions);
    }
}
