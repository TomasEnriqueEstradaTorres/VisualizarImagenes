package com.example.visualizarimagenes;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView reloj;
    private ImageView opcion1;
    private ImageView opcion2;
    private ImageView opcion3;
    private ImageView opcion4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reloj = findViewById(R.id.imageViewReloj);
        opcion1 = findViewById(R.id.imageViewOpcion1);
        opcion2 = findViewById(R.id.imageViewOpcion2);
        opcion3 = findViewById(R.id.imageViewOpcion3);
        opcion4 = findViewById(R.id.imageViewOpcion4);


        AnimationDrawable animacionReloj = (AnimationDrawable)reloj.getDrawable();
        animacionReloj.start();

        AnimationDrawable animacionOpcion1 = (AnimationDrawable)opcion1.getDrawable();
        animacionOpcion1.start();

        AnimationDrawable animacionOpcion2 = (AnimationDrawable)opcion2.getDrawable();
        animacionOpcion2.start();

        AnimationDrawable animacionOpcion3 = (AnimationDrawable)opcion3.getDrawable();
        animacionOpcion3.start();

        AnimationDrawable animacionOpcion4 = (AnimationDrawable)opcion4.getDrawable();
        animacionOpcion4.start();








    }
}
