package com.example.susanasantosmoreno.reproductordemusica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISION_REQUEST = 1;
    private ListView songList;
    private Spinner spinerOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songList = findViewById(R.id.listaCanciones);
        spinerOpciones = findViewById(R.id.spinnerOrden);

        final String [] datosSpinner = new String[]{"Ordenar por Nombre", "Ordenar por Artista"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datosSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerOpciones.setAdapter(spinnerAdapter);

        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.portraid);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());
        roundedDrawable.setCornerRadius(12f);

        //ImageView imageView = (ImageView) findViewById(R.id.ImageMusic);
        //imageView.setImageDrawable(roundedDrawable);

        //AQUI PREGUNTA SI TIENEN LOS PERMISOS DE LECTURA.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISION_REQUEST);
            return;
        }

        final ArrayList<Songs> songs = getSong();

        spinerOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){//nombres
                            Collections.sort(songs);

                        }else{//apellidos
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent){}
                }
        );

        SongAdapter adapter = new SongAdapter(this, songs);
        songList.setAdapter(adapter);

    }

    //ESTE METODO SE EJECUTA SI NO LOS HA PEDIDO ANTERIORMENTE
    //A PARTIR DE ANDROID 6 SOLO TE LOS PIDE LA PRIMERA VEZ, SE QUEDAN ACTIVOS.
    //EN EL PERMISION GRANTED ES DONDE TIENES QUE EJECUTAR EL CODIGO QUE NECESITE ACCEDER A ESE PERMISO
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*MY_PERMISION_REQUEST ES UNA CONSTANTE QUE SE INICIALIZA ARRIBA DEL TO-DO CON VALOR 1 */
        if (requestCode == MY_PERMISION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getSong();
            } else {
                // User refused to grant permission.
            }
        }
    }

    public ArrayList<Songs> getSong(){
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
        };

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);

        ArrayList<Songs> songs = new ArrayList<Songs>();
        while(cursor.moveToNext()){
            songs.add(new Songs(cursor.getString(2), cursor.getString(1), 1));
        }

        return songs;
    }

}
