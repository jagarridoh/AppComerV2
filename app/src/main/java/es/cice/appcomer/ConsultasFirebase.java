package es.cice.appcomer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cice on 5/5/17.
 */

public class ConsultasFirebase {
    final static String TAG = "ConsultasFirebase";
    private static MainActivity mainActivity;
    static Comida comida;
    static String nombreUsuario;

//    static Contador contadorComidasComun;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        ConsultasFirebase.mainActivity = mainActivity;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        ConsultasFirebase.nombreUsuario = nombreUsuario;
    }
//    static void RegistrarUsuarioActual() {
//
//    }

    static void buscarComidaPorId(String id) {
//        Comida comida = null;
        comida = null;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Comidas");
        myRef.child(id).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        comida = dataSnapshot.getValue(Comida.class);
                        Log.d(TAG, "Se ha recuperado de firebase: " + comida.toString());
                        ConsultasFirebase.getMainActivity().setResultadoConsulta(comida);
                        Log.d(TAG,"comida en la otra clase:" + ConsultasFirebase.getMainActivity().getResultadoConsulta());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
//        return comida;
    }

    static void CrearComida(String nombre, String restaurante, String ubicacion, String fecha, String hora) {
        //todo programar envio
        Log.d(TAG, "Inicio de CrearComida");
        // Write objects to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Comidas");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
        String contador = nombreUsuario.replace(" ", "_") + " " + dateFormat.format(date);
        Comida comida = new Comida(nombre, restaurante, ubicacion, fecha + " " + hora, contador);
        myRef.child(comida.getId()).setValue(comida);
    }

//    static String obtenerContadorComidas() {
//        Log.d(TAG, "Inicio de obtenerContadorComidas");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Contadores");
//
//
//    }

//    static Contador contadorContadorComidasComun() {
//
//    }
    /*

        private void escribirObjetosPrueba() {
        // Write objects to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
        DatabaseReference myRef = database.getReference("Comidas");
        Comida comida = new Comida("Comida generada desde Java", "Restaurante 1", "01/01/2018 14:00", "Comida00001");
//        myRef.setValue("Hello, World!");
        myRef.child(comida.getId()).setValue(comida);
        /*
        User user = new User(name, email);
    mDatabase.child("users").child(userId).setValue(user);

}
     */

}
