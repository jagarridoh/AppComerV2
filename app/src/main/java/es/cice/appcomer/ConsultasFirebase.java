package es.cice.appcomer;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cice on 5/5/17.
 */

public class ConsultasFirebase {
    final static String TAG = "ConsultasFirebase";
    private static MainActivity mainActivity;
    static Comida comida;
    static String ultimoIdComidaGuardado;
    static String nombreUsuario;
    static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

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


    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(List<Usuario> listaUsuarios) {
        ConsultasFirebase.listaUsuarios = listaUsuarios;
    }

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
        ultimoIdComidaGuardado = comida.getId();
    }

    static void CrearComidaComensales(String nombre, String restaurante, String ubicacion, String fecha, String hora, List<Usuario> listaUsuarios) {
        Log.d(TAG, "Inicio de CrearComida");
        // Write objects to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Comidas");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
        String contador = nombreUsuario.replace(" ", "_") + " " + dateFormat.format(date);
        Comida comida = new Comida(nombre, restaurante, ubicacion, fecha + " " + hora, contador, listaUsuarios);
        myRef.child(comida.getId()).setValue(comida);
        ultimoIdComidaGuardado = comida.getId();
    }

    static void CrearUsuario() {
        Log.d(TAG, "Inicio de CrearUsuario");
        // Write objects to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Usuarios");
        Usuario usuario = new Usuario(getNombreUsuario());
        myRef.child(usuario.getNombre()).setValue(usuario);
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

    private static void addUserToList(Usuario u) {
        if (! listaUsuarios.contains(u)) listaUsuarios.add(u);
    }


    public static void obtenerUsuariosDeFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Usuarios");

        // Attach a listener to read the data at our posts reference
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String cadema) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                Log.d(TAG, "Leido Usuario: " + usuario.toString());
                Log.d(TAG, "Leido Datasnapshot: " + dataSnapshot.toString());
                //Añadir en la lista, antes mira que el usuario no esté ya.
                addUserToList(usuario);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "The read failed: " + databaseError.getCode());
            }
        });
    }



    public static void obtenerComensalesDeComida(String idComida) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Comidas").child(idComida);

        // Attach a listener to read the data at our posts reference
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String cadema) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                Log.d(TAG, "Leido Usuario: " + usuario.toString());
                Log.d(TAG, "Leido Datasnapshot: " + dataSnapshot.toString());
                //Añadir en la lista, antes mira que el usuario no esté ya.
                addUserToList(usuario);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "The read failed: " + databaseError.getCode());
            }
        });
    }



//        // Attach a listener to read the data at our posts reference
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Usuario usuario = dataSnapshot.getValue(Usuario.class);
//                Log.d(TAG, "Leido Usuario: " + usuario.toString());
//                Log.d(TAG, "Leido Datasnapshot: " + dataSnapshot.toString());
//                //Añadir en la lista, antes mira que el usuario no esté ya.
//                addUserToList(usuario);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(TAG, "The read failed: " + databaseError.getCode());
//            }
//        });

                /*
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Post post = dataSnapshot.getValue(Post.class);
        System.out.println(post);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
    }
});
                 */

}
