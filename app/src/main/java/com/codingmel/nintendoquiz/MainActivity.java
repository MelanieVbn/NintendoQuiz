package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.q42.android.scrollingimageview.ScrollingImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.mario_medley);
        mp.setLooping(true);
        mp.start();

        /*ObjectAnimator animator = ObjectAnimator.ofFloat(R.id.titleGameTextView,"translationY",-500f);
        animator.setDuration(5000);
        animator.start();*/

        setContentView(R.layout.activity_main);
        ScrollingImageView scrollingBackground = findViewById(R.id.bgImageView);
        scrollingBackground.stop();
        scrollingBackground.start();


        findViewById(R.id.aboutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choisir un niveau");
                final String[] difficultyLabels = {"Facile","Moyen","Dur"};
                builder.setSingleChoiceItems(difficultyLabels, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,GameActivity.class);
                        switch (which) {
                            case 0:
                                intent.putExtra("questions",getEasyQuestions());
                                intent.putExtra("difficulty","FACILE");
                                break;
                            case 1:
                                intent.putExtra("questions",getEasyQuestions());
                                intent.putExtra("difficulty","MOYEN");
                                break;
                            case 2:
                                intent.putExtra("questions",getEasyQuestions());
                                intent.putExtra("difficulty","DIFFICILE");
                                break;
                        }
                        mp.stop();
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

        findViewById(R.id.allQuestionsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<Question> getEasyQuestions() {
        Question questions[] = new Question[]{
                new Question("Quel est ce personnage ?", "FACILE", R.drawable.waluigi, 0, Arrays.asList("Luigi", "Wario", "Waluigi"), "Waluigi"),
                new Question("De quel jeu ce personnage est-il l'antagoniste ?", "FACILE", R.drawable.ganon, 0, Arrays.asList("The Legend of Zelda", "Pokemon", "Super Mario Bros"), "The Legend of Zelda"),
                new Question("Quel sont les noms de ces pokemons ?", "FACILE", R.drawable.pkm_starters, 0, Arrays.asList("Bulbizarre, Salamèche et Carapuce", "Germignon, Héricendre et Kaiminus", "Brindibou, Flamiaou et Otaquin"), "Bulbizarre, Salamèche et Carapuce"),
                new Question("De quel jeu vient cette musique ?", "FACILE", R.drawable.red_mushroom, R.raw.zelda_main_theme, Arrays.asList("The Legend of Zelda", "Mario Kart", "Splatoon"), "The Legend of Zelda"),
                new Question("De quel jeu vient cette musique ?", "FACILE", R.drawable.red_mushroom, R.raw.street_fighter_2_guile_theme, Arrays.asList("Super Smash Bros Melee", "Street Fighter II", "Arms"), "Street Fighter II"),
                new Question("De qui ce personnage est-il le personnage mirroir?", "FACILE", R.drawable.wario, 0, Arrays.asList("Mario", "Luigi", "Peach"), "Mario"),
                new Question("Dans quel lieu de Mario Bros retouve-t-on cette musique ?", "FACILE", R.drawable.red_mushroom, R.raw.mario_bros_wii_underground_theme, Arrays.asList("Les sous terrains", "Sous L'eau", "Dans les airs"), "Les sous terrains"),
                new Question("De quel jeu vient cette musique ?", "FACILE", R.drawable.red_mushroom, R.raw.wii_sports_theme, Arrays.asList("Wii Sport", "Mario Galaxy", "Pokemon"), "Wii Sport"),
                new Question("Quel est le nom de ce personnage ?", "FACILE", R.drawable.dr_mario, 0, Arrays.asList("Professeur Mario", "Mario le Toubib", "Dr.Mario"), "Dr.Mario"),
                new Question("Quelle console avait cette intoduction ?", "FACILE", R.drawable.red_mushroom, R.raw.gamecube_intro, Arrays.asList("Game Cube", "Wii", "Nintendo 64"), "Game Cube")
        };

        return new ArrayList<Question>(Arrays.asList(questions));
    }
}
