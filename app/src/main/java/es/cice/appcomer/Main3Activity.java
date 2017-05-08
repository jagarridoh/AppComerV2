package es.cice.appcomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main3Activity extends AppCompatActivity {
    private static final String TAG = "Main3Activ-Gest Comens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }


    public void anadirComensal(View view) {
        //todo mostrar de alguna manera la lista. Hay qye recuperar el resultado y meterlo en campo.
        Log.d(TAG, "abrirComensales");
//        finish();
    }

}
