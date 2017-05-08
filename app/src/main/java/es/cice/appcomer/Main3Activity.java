package es.cice.appcomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private static final String TAG = "Main3Activ-Gest Comens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListaComensalesAdapter(this, R.layout.comensal_row, (List<Usuario>) Usuario.getData()));
        Log.d(TAG, " despues de setAdapter ");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "click en item " + i);
                Toast.makeText(Main3Activity.this, "click en item " + i, Toast.LENGTH_LONG).show();
            }
        });
    }


    public void anadirComensal(View view) {
        //todo mostrar la lista de todos los usuarios en una nueva actividad o en la actual ocultando campos.
        // todo Hay qye recuperar el resultado y meterlo en campo.
        Log.d(TAG, "abrirComensales");
//        finish();
    }

}
