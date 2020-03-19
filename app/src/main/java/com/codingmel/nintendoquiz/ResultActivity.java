package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent srcIntent = getIntent();
        float percent = srcIntent.getFloatExtra("goodAnswerPercentage",0);
        TextView percentageText = findViewById(R.id.percentageTextView);
        percentageText.setText((int)percent+" %");
        int questionsTotal = srcIntent.getIntExtra("totalNumberOfQuestions",0);
        TextView resultPhraseTextView = findViewById(R.id.resultPhraseTextView);
        resultPhraseTextView.setText("Tu as répondu juste à "+(int)percent/10+" questions sur "+questionsTotal);

        TextView comment = findViewById(R.id.commentTextView);
        String difficulty = srcIntent.getStringExtra("difficulty");
        comment.setText(setComment((int)percent,difficulty));
    }

    private String setComment(int percent, String difficulty){
        String comment;
        switch (percent){
            case 0:
                comment = "Aucune bonne réponse... ";
                if(difficulty.equals("FACILE")){
                    comment += "pense a revoir tes classiques !";
                }else if(difficulty.equals("MOYEN")){
                    comment = "je suis sur que tu peux le faire !  !";
                }else{
                    comment = "tu t'es peut être sur-estimé!";
                }
                break;
            case 1-30:
                comment= "Il y à encore quelques éléments a revoir...";
                break;
            case 30-60:
                comment="Dans la moyenne, Bravo !";
                break;
            case 60-100:
                comment="Tu t'y connais bien, je suis sur que tu peux arriver a faire un sans faute!";
                break;
            case 100:
                comment = "Un sans faute ! ";
                if(difficulty.equals("FACILE") || difficulty.equals("MOYEN")){
                    comment += "tu devrais songer a passer au niveau supérieur !";
                }else{
                    comment = "tu est incolable sur Nintendo, bravo à toi !";
                }
                break;
            default:
                comment = "BRAVO !";
        }
        return comment;
    }
}
