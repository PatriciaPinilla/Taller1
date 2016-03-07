package com.example.winchester.taller1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends AppCompatActivity implements OnClickListener {

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

        Button piedra  = (Button) findViewById(R.id.piedra);
        Button papel = (Button) findViewById(R.id.papel);
        Button tijera = (Button) findViewById(R.id.tijera);
        Button inicio = (Button) findViewById(R.id.Inicio);

        piedra.setOnClickListener(this);
        papel.setOnClickListener(this);
        tijera.setOnClickListener(this);
        inicio.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnswerUser);
        boolean play = true;

        switch (v.getId()) {
            case R.id.piedra:
                userSelection = Option.piedra;
                imageView.setImageResource(R.drawable.piedra);
                break;
            case R.id.papel:
                userSelection = Option.papel;
                imageView.setImageResource(R.drawable.papel);
                break;
            case R.id.tijera:
                userSelection = Option.tijeras;
                imageView.setImageResource(R.drawable.tijera);
                break;
            case R.id.Inicio:
                startActivity(new Intent(MainActivity.this, MainActivity.class)); // ir al inicio.
                play = false;
                break;
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
            }
        });

        // mostrar resultado.
        if(gameResult == Result.PERDISTE) {
            builder.setMessage("PERDISTE!");
        } else if(gameResult == Result.GANASTE) {
            builder.setMessage("GANASTE!");
        } else if(gameResult == Result.EMPATAMOS) {
            builder.setMessage("EMPATE!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play() {
        // Opciones de la máquina.
        int rand =  ((int)(Math.random() * 10)) % 3;
        Option androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.ImageViewAnswerAndroid);

        switch (rand) {
            case 0:
                androidSelection = Option.piedra;
                imageView.setImageResource(R.drawable.piedra);
                break;
            case 1:
                androidSelection = Option.papel;
                imageView.setImageResource(R.drawable.papel);
                break;
            case 2:
                androidSelection = Option.tijeras;
                imageView.setImageResource(R.drawable.tijera);
                break;
        }

        if(androidSelection == userSelection) {
            gameResult = Result.PERDISTE;
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