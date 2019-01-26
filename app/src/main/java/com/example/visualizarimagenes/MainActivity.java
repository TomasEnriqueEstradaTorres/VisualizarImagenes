package com.example.visualizarimagenes;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView reloj, opcion1, opcion2, opcion3, opcion4, referencia; //opciones de juego
    private ImageView inter1, inter2; // imagenes pequenyas en movimiento
    private AnimationDrawable  animacionReloj, animacion1, animacion2; //animacion por fotograma
    private Animation anim1, anim2; // servira para la animacion por interpolacion
    private Bitmap referenciaBitmap, bitmap1, bitmap2, bitmap3, bitmap4; // para obtener referencia de comparacion de imagenes
    private TextView mensaje;  //mostrara el mensaje de aviso si se gana o pierde
    private Button reinicioJuego; // boton para reinicioar el juego
    private final String mensajeGanador = "¡¡¡Haz Acertado Enhorabuena!!!";
    private final String mensajePerdedor = "¡¡¡Que Pena, sigue intentando!!!";
    private final String mensajeFinJuego = "¡¡¡FIN DE JUEGO!!!";
    private final String mensajeError = "¡¡¡FAIL!!!";
    private final String mensajeReinicio= "JUEGO REINICIADO";
    private static final Random rgenerador = new Random();//servira par rotar las imagenes de las opciones
    private static final Integer[] imagenesID = {R.drawable.aventurero12, R.drawable.sombiea18, R.drawable.sombieb24};//lista de imagenes
    private int resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sera para la animacion del reloj
        reloj = findViewById(R.id.imageViewReloj);
        //Seran las opciones de juego
        opcion1 = findViewById(R.id.imageViewOpcion1);
        opcion2 = findViewById(R.id.imageViewOpcion2);
        opcion3 = findViewById(R.id.imageViewOpcion3);
        opcion4 = findViewById(R.id.imageViewOpcion4);
        referencia = findViewById(R.id.imageViewReferencia);
        //seran las pequenas animaciones por fotograma e interpolacion
        inter1 = findViewById(R.id.imageViewInter1);
        inter2 = findViewById(R.id.imageViewInter2);
        //mostrara el mensaje si acertaste al elejir
        mensaje = findViewById(R.id.textViewMensaje);
        //Boton para reiniciar el juego
        reinicioJuego = findViewById(R.id.buttonReinicio);


        //imagenes de sombie vigilante femenino
        animacion1 = (AnimationDrawable)inter1.getDrawable();// movimiento por fotograma
        animacion1.start();
        anim1 = AnimationUtils.loadAnimation(this, R.anim.caminar); //movimiento por interpolacion
        inter1.startAnimation(anim1);
        //imagenes de sombie vigilante masculino
        animacion2 = (AnimationDrawable)inter2.getDrawable();// movimiento por fotograma
        animacion2.start();
        anim2 = AnimationUtils.loadAnimation(this, R.anim.caminar); //movimiento por interpolacion
        inter2.startAnimation(anim2);
        //Esto es la animacion del reloj por medio de fotogramas
        animacionReloj = (AnimationDrawable)reloj.getDrawable();
        animacionReloj.start();
        //Servira para poder realizar la comparacion de imagenes para saber si acertamos.
        referencia.setVisibility(View.INVISIBLE);
        referenciaBitmap = ((BitmapDrawable)referencia.getDrawable()).getBitmap();
        //Se encargara de rotal las imagenes de la opciones
        Integer q = imagenesID[rgenerador.nextInt(imagenesID.length)];
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();
        //Todo esto seran los botones en la pantalla
        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource = imagenesID[rgenerador.nextInt(imagenesID.length)]; // rota la imagen cada vez que se hace un click
                opcion1.setImageResource(resource);// recibe el resultado del ramdon de imagenes
                bitmap1 = ((BitmapDrawable)opcion1.getDrawable()).getBitmap();//obtiene la referencia 'bitmap' de la imagen
                comprobarImagen(referenciaBitmap, bitmap1); //comprueba si las imagenes son iguales
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource = imagenesID[rgenerador.nextInt(imagenesID.length)];
                opcion2.setImageResource(resource);
                bitmap2 = ((BitmapDrawable)opcion2.getDrawable()).getBitmap();
                comprobarImagen(referenciaBitmap, bitmap2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource = imagenesID[rgenerador.nextInt(imagenesID.length)];
                opcion3.setImageResource(resource);
                bitmap3 = ((BitmapDrawable)opcion3.getDrawable()).getBitmap();
                comprobarImagen(referenciaBitmap, bitmap3);
            }
        });

        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resource = imagenesID[rgenerador.nextInt(imagenesID.length)];
                opcion4.setImageResource(resource);
                bitmap4 = ((BitmapDrawable)opcion4.getDrawable()).getBitmap();
                comprobarImagen(referenciaBitmap, bitmap4);
            }
        });

        reinicioJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarJuego();
            }
        });
    }


    public void onRestart() {
        super.onRestart();

    }

    public void onPause() {
        super.onPause();

    }

    public void onStop() {
        super.onStop();

    }

    public void onDestroy() {
        super.onDestroy();

    }

    //funcion para reiniciar el juego
    public void reiniciarJuego(){
        //Retorna a la imagen por defecto de inicio
        opcion1.setImageResource(R.drawable.regalo);
        opcion2.setImageResource(R.drawable.regalo);
        opcion3.setImageResource(R.drawable.regalo);
        opcion4.setImageResource(R.drawable.regalo);
        //hace visible las otras imagenes
        opcion1.setVisibility(View.VISIBLE);
        opcion2.setVisibility(View.VISIBLE);
        opcion3.setVisibility(View.VISIBLE);
        opcion4.setVisibility(View.VISIBLE);
        //oculta el boton de reinicio
        reinicioJuego.setVisibility(View.INVISIBLE);
        toastPersonalizado(mensajeReinicio);//muestra un mensaje de reinicio de juego
        //iniciara las animaciones
        animacion1.start();
        animacion2.start();
        animacionReloj.start();
        inter1.startAnimation(anim1);
        inter2.startAnimation(anim2);
        mensaje.setText(R.string.adivina); // reinicia el mensaje del juego
        //reactivara los botones imagenes de las obciones
        opcion1.setEnabled(true);
        opcion2.setEnabled(true);
        opcion3.setEnabled(true);
        opcion4.setEnabled(true);
    }

    //Funcion para comprobar si la imagen elegida es la correcta
    public void comprobarImagen(Bitmap imagenReferencia, Bitmap imagenAcomparar){
        if(imagenReferencia.equals(imagenAcomparar)){
            mensaje.setText(mensajeGanador);
            mensaje.setTextColor(Color.BLUE);
            toastPersonalizado(mensajeFinJuego);
            //ocultara las imagenes si se acierta y gana
            if (referenciaBitmap.equals(bitmap1)){
                opcion2.setVisibility(View.INVISIBLE);
                opcion3.setVisibility(View.INVISIBLE);
                opcion4.setVisibility(View.INVISIBLE);
                opcion1.setEnabled(false); // desactivara el boton imagen para que no cambie de nuevo
            }
            else if (referenciaBitmap.equals(bitmap2)){
                opcion1.setVisibility(View.INVISIBLE);
                opcion3.setVisibility(View.INVISIBLE);
                opcion4.setVisibility(View.INVISIBLE);
                opcion2.setEnabled(false);// desactivara el boton imagen para que no cambie de nuevo
            }
            else if (referenciaBitmap.equals(bitmap3)){
                opcion1.setVisibility(View.INVISIBLE);
                opcion2.setVisibility(View.INVISIBLE);
                opcion4.setVisibility(View.INVISIBLE);
                opcion3.setEnabled(false);// desactivara el boton imagen para que no cambie de nuevo
            }
            else if (referenciaBitmap.equals(bitmap4)){
                opcion1.setVisibility(View.INVISIBLE);
                opcion2.setVisibility(View.INVISIBLE);
                opcion3.setVisibility(View.INVISIBLE);
                opcion4.setEnabled(false);// desactivara el boton imagen para que no cambie de nuevo
            }
            reinicioJuego.setVisibility(View.VISIBLE); //mostrara el boton de reinicio
            //detendra las animaciones
            animacion1.stop();
            animacion2.stop();
            animacionReloj.stop();
            inter1.clearAnimation();
            inter2.clearAnimation();
        }else{
            mensaje.setText(mensajePerdedor);
            mensaje.setTextColor(Color.RED);
            toastPersonalizado(mensajeError);
            retornarImagen();
        }
    }

    //funcion para retornar a la anterior imagen si no se acierta en el juego
    public void retornarImagen(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //esto comprueba si la imagen es la ganadora y si lo es no retornara a la imagen por defecto de inicio
                if (referenciaBitmap != bitmap1 || referenciaBitmap != bitmap2 || referenciaBitmap != bitmap3 || referenciaBitmap != bitmap4){
                    opcion1.setImageResource(R.drawable.regalo);
                    opcion2.setImageResource(R.drawable.regalo);
                    opcion3.setImageResource(R.drawable.regalo);
                    opcion4.setImageResource(R.drawable.regalo);
                }
                mensaje.setText(R.string.adivina); // reinicia el mensaje
                mensaje.setTextColor(Color.BLUE); //reinicia el color de texto
            }
        }, 2000); // retardo de dos segundos
    }

    //Funcion que personaliza el mensaje del toast
    public void toastPersonalizado(String mensaje){
        Toast toast = new Toast(this);
        //usamos cualquier layout como Toast
        View toast_layout = getLayoutInflater().inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.lytLayout));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toast_layout);
        //se podría definir el texto en el layout si es invariable pero lo hacemos programáticamente
        //tenemos acceso a cualquier widget del layout del Toast
        TextView textView = (TextView) toast_layout.findViewById(R.id.mensajeToast);
        textView.setText(mensaje);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
