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
    private MediaPlayer mp;
    ArrayList<Question> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mp = MediaPlayer.create(MainActivity.this, R.raw.mario_medley);
        mp.setLooping(true);
        mp.start();

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
                final String[] difficultyLabels = {"Facile","Moyen","Difficile"};
                builder.setSingleChoiceItems(difficultyLabels, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,GameActivity.class);
                        switch (which) {
                            case 0:
                                intent.putExtra("questions",getQuestionsByDifficulty("FACILE"));
                                intent.putExtra("difficulty","FACILE");
                                break;
                            case 1:
                                intent.putExtra("questions",getQuestionsByDifficulty("MOYEN"));
                                intent.putExtra("difficulty","MOYEN");
                                break;
                            case 2:
                                intent.putExtra("questions",getQuestionsByDifficulty("DIFFICILE"));
                                intent.putExtra("difficulty","DIFFICILE");
                                break;
                        }
                        mp.stop();
                        dialog.cancel();
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
                intent.putExtra("questions",getQuestions());
                mp.stop();
                startActivity(intent);
            }
        });

    }

    private ArrayList<Question> getQuestions() {
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
                new Question("Quelle console avait cette intoduction ?", "FACILE", R.drawable.red_mushroom, R.raw.gamecube_intro, Arrays.asList("Game Cube", "Wii", "Nintendo 64"), "Game Cube"),

                new Question("Qui est cet homme ?", "MOYEN", R.drawable.shigeru_miyamot, 0, Arrays.asList("Shigeru Miyamoto", "Saturo Iwata", "Hayao Miyazaki"), "Shigeru Miyamoto"),
                new Question("Quel est le rôle de Marie dans Animal Crossing ?", "MOYEN", R.drawable.marie, 0,Arrays.asList("Elle est l'assistante du maire", "Elle est le maire", "Elle travaille dans une boutique"), "Elle est l'assistante du maire"),
                new Question("De quelle firme venait Sonic avant d'apparaitre aux côté de Mario ?", "MOYEN", R.drawable.sonic, 0, Arrays.asList("Sega", "Il a toujours été chez Nintendo", "DreamWorks"), "Sega"),
                new Question("Quel est le nom de ce personnage ?", "MOYEN", R.drawable.kirby, 0, Arrays.asList("Kirby", "Rondoudou", "Mrs.PacMan"), "Kirby"),
                new Question("Quel est le nom du royaume d'où vient la princesse Zelda ?", "MOYEN", R.drawable.hyrule, 0, Arrays.asList("Hyrule", "Royaume Champignon", "Narnia"), "Hyrule"),
                new Question("De quel jeu vient cette musique ?", "MOYEN", R.drawable.red_mushroom, R.raw.animal_crossing_wild_world_theme, Arrays.asList("Animal Crossing", "Pokemon", "Zelda"), "Animal Crossing"),
                new Question("De quel effet vient cette musique (Mario Bros) ?", "MOYEN", R.drawable.red_mushroom, R.raw.mario_bros_star_theme, Arrays.asList("Quand une étoile est récupérée", "Quand un champignon est récupéré", "Quand un drapeau est passé"), "Quand une étoile est récupérée"),
                new Question("A quel moment cette musique est-elle jouée (Zelda) ?", "MOYEN", R.drawable.red_mushroom, R.raw.zelda_chest_opening, Arrays.asList("Quand un coffre est ouvert", "Quand l'épée de légende est récupérée", "Quand un pouvoir est acquis"), "Quand un coffre est ouvert"),
                new Question("De quel jeu Smash Bros vient cette musique ?", "MOYEN", R.drawable.red_mushroom, R.raw.ssbu_main_theme, Arrays.asList("Super Smash Bros Ultimate", "Super Smash Bros Melee", "Supe Smash Bros Brawl"), "Super Smash Bros Ultimate"),
                new Question("Quelle console avait cette intoduction ?", "MOYEN", R.drawable.red_mushroom, R.raw.gameboy_startup, Arrays.asList("La GameBoy Color", "La DS", "La Wii"), "La GameBoy Color"),

                new Question("Quel était le premier nom de Mario ?", "DIFFICILE", R.drawable.jumpman, 0, Arrays.asList("JumpMan", "Il s'est toujours appelé Mario", "Luigi"), "JumpMan"),
                new Question("Quel est le nom de cette console ?", "DIFFICILE", R.drawable.famicom, 0, Arrays.asList("Famicom", "MegaDrive", "Nintendo 64"), "Famicom"),
                new Question("Qui se cache sous cette armure ?", "DIFFICILE", R.drawable.samus, 0, Arrays.asList("Samus", "Luna", "Megaman"), "Samus"),
                new Question("Au départ, que vendait Nintendo ?", "DIFFICILE", R.drawable.nintendo_first_logo, 0, Arrays.asList("Des jeux de cartes", "Des maquettes de voitures", "Des figurines"), "Des jeux de cartes"),
                new Question("Qui a developpé les jeux Megaman ?", "DIFFICILE", R.drawable.megaman_title_screen, 0, Arrays.asList("Capcom", "Nintendo", "Sega"), "Capcom"),
                new Question("De quel niveau de Mario Kart NES vient cette musique ?", "DIFFICILE", R.drawable.red_mushroom, R.raw.mk_8_rainbow_road_snes, Arrays.asList("Route Arc-en-ciel", "Circuit Mario", "Parc Baby"), "Route Arc-en-ciel"),
                new Question("De quel jeu vient cette musique ?", "DIFFICILE", R.drawable.red_mushroom, R.raw.megaman2_dr_wilys_castle, Arrays.asList("Megaman", "Wii", "Nintendo 64"), "Megaman"),
                new Question("Qui a composé l'ost des jeux a licence Zelda ?", "DIFFICILE", R.drawable.red_mushroom, R.raw.gerudo_valley_zelda_oot, Arrays.asList("Koji Kondo", "Asuka Ota", "Junichi Masuda"), "Koji Kondo"),
                new Question("Quelle console avait cette intoduction ?", "DIFFICILE", R.drawable.red_mushroom, R.raw.intro_nes, Arrays.asList("La Nes", "La Nintendo 64", "Les premières versions de la GameBoy"), "La Nes")
                //Il manque une question
        };

        return new ArrayList<Question>(Arrays.asList(questions));
    }

    private ArrayList<Question> getQuestionsByDifficulty(String difficulty){
        ArrayList<Question> fetchedQuestions = new ArrayList<>();
        for (Question question:getQuestions()) {
            if(question.getDifficulty() == difficulty)
            fetchedQuestions.add(question);
        };
        return fetchedQuestions;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mp != null && !mp.isPlaying()){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
