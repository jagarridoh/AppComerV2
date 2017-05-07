package es.cice.appcomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activ-Crear Comida";
    EditText editText1, editText2, editText3, editText4, editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);

//        Button buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
//        Button buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
    }

    public void guardar(View view) {
        Log.d(TAG, "guardar");
        ConsultasFirebase.CrearComida(editText1.getText().toString(),
                editText2.getText().toString(),
                editText3.getText().toString(),
                editText4.getText().toString(),
                editText5.getText().toString());
    }

    public void cancelar(View view) {
        Log.d(TAG, "cancelar");
        finish();
    }
}
