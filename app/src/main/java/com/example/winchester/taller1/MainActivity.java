package com.example.winchester.taller1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;;

    public class MainActivity extends AppCompatActivity {

    public enum Option {
      piedra, papel, tijeras
    }

    public enum Result {
     GANASTE, PERDISTE, EMPATAMOS
    }

private Option userSelection;
private Result gameResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button piedra;
        piedra = (Button) findViewById(R.id.ImgPiedra);
        Button papel;
        papel = (Button) findViewById(R.id.ImgPapel);
        Button tijera;
        tijera = (Button) findViewById(R.id.ImgTijera);

        // Set click listening event for all buttons.
        piedra.setOnClickListener((OnClickListener) this);
        papel.setOnClickListener((OnClickListener)this);
        tijera.setOnClickListener((OnClickListener)this);
    }

    public void onClick(View v) {

        ImageView imageView = (ImageView) findViewById(R.id.ImgPiedra);
        boolean play = true;

        switch (v.getId()) {
            case R.id.ImgPiedra:
                userSelection = Option.piedra;
              // imageView.setImageResource(R.drawable.piedra);
                break;
            case R.id.ImgPapel:
                userSelection = Option.papel;
              //  imageView.setImageResource(R.drawable.papel);
                break;
            case R.id.tijera:
                userSelection = Option.tijeras;
             //   imageView.setImageResource(R.drawable.tijera);
                break;
          //  case R.id.imageButtonHome:
           //     startActivity(new Intent(activity_main.this, ChooseActivity.class)); // To go home.
            //    play = false;
              //  break;
        }

        if(play) {
            play();
            showResults();
        }
    }

    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });

        // Sets the right message according to result.
        if(gameResult == Result.PERDISTE) {
            builder.setMessage("You Loose!");
        } else if(gameResult == Result.GANASTE) {
            builder.setMessage("You Win!");
        } else if(gameResult == Result.EMPATAMOS) {
            builder.setMessage("It's a draw!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play() {
        // Generates a random play.
        int rand =  ((int)(Math.random() * 10)) % 3;
        Option androidSelection = null;
     //   ImageView imageView = (ImageView) findViewById(R.id.ImageViewAnswerAndroid);

        // Sets the right image according to random selection.
        switch (rand) {
            case 0:
                androidSelection = Option.piedra;
                //imageView.setImageResource(R.drawable.ImgPiedra);
                break;
            case 1:
                androidSelection = Option.papel;
                //imageView.setImageResource(R.drawable.papel);
                break;
            case 2:
                androidSelection = Option.tijeras;
               // imageView.setImageResource(R.drawable.tijera);
                break;
        }
        // Determine game result according to user selection and Android selection.
        if(androidSelection == userSelection) {
            gameResult = Result.EMPATAMOS;
        }
        else if(androidSelection == Option.piedra && userSelection == Option.tijeras) {
            gameResult = Result.PERDISTE;
        }
        else if(androidSelection == Option.papel && userSelection == Option.piedra) {
            gameResult = Result.PERDISTE;
        }
        else if(androidSelection == Option.tijeras && userSelection == Option.papel) {
            gameResult = Result.PERDISTE;
        } else {
            gameResult = Result.GANASTE;
        }
    }
}