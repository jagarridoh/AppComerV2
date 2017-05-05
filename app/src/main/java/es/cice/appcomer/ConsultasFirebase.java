package es.cice.appcomer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cice on 5/5/17.
 */

public class ConsultasFirebase {
    final static String TAG = "ConsultasFirebase";
    private static MainActivity mainActivity;
    static Comida comida;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        ConsultasFirebase.mainActivity = mainActivity;
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
}
