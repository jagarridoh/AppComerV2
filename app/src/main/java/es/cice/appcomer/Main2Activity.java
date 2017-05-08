package es.cice.appcomer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activ-Crear Comida";
    EditText editText1, editText2, editText3, editText4, editText5;
    private static final int REQUEST_PLACE_PICKER = 0;

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

        Button button = (Button) findViewById(R.id.buttonMaps);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    PlacePicker.IntentBuilder intentBuilder =
//                            new PlacePicker.IntentBuilder();
//                    LatLng bernabeu = new LatLng(40.45306, -3.68835);
//                    LatLng bernabeu2 = new LatLng(40.46, -3.67);
//                    intentBuilder.setLatLngBounds(new LatLngBounds(bernabeu, bernabeu2));
//                    Intent intent = intentBuilder.build(activity);
//                    //Abre la actividad de google places, que no está definida en el proyecto
//                    //  si no que viene con la librería.
//                    startActivityForResult(intent, REQUEST_PLACE_PICKER);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
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

    public void abrirGooglePlaces(View view) {
        Log.w(TAG, "abrirGooglePlaces");
        try {
            PlacePicker.IntentBuilder intentBuilder =
                new PlacePicker.IntentBuilder();
            LatLng bernabeu = new LatLng(40.45306, -3.68835);
            LatLng bernabeu2 = new LatLng(40.46, -3.67);
            intentBuilder.setLatLngBounds(new LatLngBounds(bernabeu, bernabeu2));
            Intent intent = intentBuilder.build(this);
            //Abre la actividad de google places, que no está definida en el proyecto
            //    si no que viene con la librería.
            startActivityForResult(intent, REQUEST_PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
//        Intent i = new Intent(this, Main2Activity.class);
////        i.putExtra("Campo", usuario);
//        startActivity(i);
    }

    public void abrirComensales(View view) {
        //Abrir actividad de comensales
        Log.d(TAG, "abrirComensales");
        Intent i = new Intent(this, Main3Activity.class);
        //todo hay que recuperar el resultado y meterlo en campo.
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
//            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
//                    .show();
            Log.d(TAG, "Busqueda en places cancelada");
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
//            String resultado = data.getExtras().getString("RESULTADO");
            // Y tratamos el resultado
            Toast.makeText(this, "ResultCode=" + resultCode, Toast.LENGTH_SHORT)
                    .show();
            if (requestCode == REQUEST_PLACE_PICKER
                    && resultCode == Activity.RESULT_OK) {
                final Place place = PlacePicker.getPlace(this, data);
                final CharSequence name = place.getName();
                final CharSequence address = place.getAddress();
//                Toast.makeText(this, "Place: name=" + name + ", address=" + address, Toast.LENGTH_SHORT)
//                        .show();
                Log.d(TAG, "Place: name=" + name + ", address=" + address);
                editText2.setText(name);
                editText3.setText(address);
            }
        }
    }

}
