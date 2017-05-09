package es.cice.appcomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private static final String TAG = "Main4Activ-Add Comens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ListView listView = (ListView) findViewById(R.id.listViewTodos);
        listView.setAdapter(new ListaComensalesAdapter(this, R.layout.comensal_row, (List<Usuario>) Usuario.getData()));
        Log.d(TAG, " despues de setAdapter ");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "click en item " + i);
                Toast.makeText(Main4Activity.this, "click en item " + i, Toast.LENGTH_SHORT).show();
                ConsultasFirebase.getListaUsuarios().add(Usuario.getData().get(i));
                Toast.makeText(Main4Activity.this, "añadido comensal " + Usuario.getData().get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
