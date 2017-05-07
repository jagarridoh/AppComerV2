package es.cice.appcomer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    private String mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "MainActivity";
    private Object resultadoConsulta;

    public Object getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(Object resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Ini del oncreate.");

//        Button button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Resultado consulta=" + getResultadoConsulta(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Resultado2 consulta2=" + ConsultasFirebase.comida, Toast.LENGTH_SHORT).show();
//                // funciona: Comida comida2 = (Comida) getResultadoConsulta();
//            }
//        });
        // Set default username is anonymous.
        mUsername = ANONYMOUS;
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            Log.d(TAG, "Not signed in, launch the Sign In activity.");
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            Log.d(TAG, "Después de start activity");
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            Log.d(TAG, "Signed in: " + mUsername);
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

        Log.d(TAG, "oncreate: antes de GoogleApiClientBuilder.");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
//        escribirDatosPrueba();
        escribirObjetosPrueba();
//        leerPrueba();
        ConsultasFirebase.setMainActivity(this);
//        ConsultasFirebase.setNombreUsuario("Test User");
        ConsultasFirebase.setNombreUsuario(mFirebaseUser.getDisplayName());
//        Comida comida = ConsultasFirebase.buscarComidaPorId("Comida00001");
//        ConsultasFirebase.buscarComidaPorId("Comida00001");
//        Log.d(TAG, "comida=" + comida);
//        Toast.makeText(this, "Fin del oncreate.", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Fin del oncreate.");
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }


    private void escribirDatosPrueba() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

    private void escribirObjetosPrueba() {
        // Write objects to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
        DatabaseReference myRef = database.getReference("Comidas");
        Comida comida = new Comida("Comida generada desde Java", "Restaurante 1", "", "01/01/2018 14:00", "Comida00001");
//        myRef.setValue("Hello, World!");
        myRef.child(comida.getId()).setValue(comida);
        /*
        User user = new User(name, email);
    mDatabase.child("users").child(userId).setValue(user);
         */
    }

    private void leerPrueba() {
        // todo primero buscamos la Comida00001
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Comidas");
        myRef.child("Comida00001").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Comida comida = dataSnapshot.getValue(Comida.class);
                        Log.d(TAG, "Se ha recuperado de firebase: " + comida.toString());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }

    public void crearComida(View view) {
        Log.w(TAG, "crearComida");
        Intent i = new Intent(this, Main2Activity.class);
//        i.putExtra("Campo", usuario);
        startActivity(i);
    }

    private void leerTodosDatosFirebase() {

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // old: return super.onOptionsItemSelected(item);
//        switch (item.getItemId()) {
//            case 12345678: //to do antes había: R.id.sign_out_menu:
//                mFirebaseAuth.signOut();
//                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//                mUsername = ANONYMOUS;
//                startActivity(new Intent(this, SignInActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        //to do añadir el menu: inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }

}
