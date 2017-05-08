package es.cice.appcomer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jagar on 08/05/2017.
 */

public class ListaComensalesAdapter extends ArrayAdapter {
    private Context ctx;
    private List<Usuario> data;



    public ListaComensalesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        ctx = context;
        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View row = inflater.inflate(R.layout.comensal_row, null);
        TextView nombre = (TextView) row.findViewById(R.id.nombreUsuario);
        nombre.setText(data.get(position).getNombre());
        return row;
//        return null;
        /*
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View row = inflater.inflate(R.layout.customer_row, null);
        ImageView fotoGender = (ImageView) row.findViewById(R.id.imageViewGender);
        TextView nombre = (TextView) row.findViewById(R.id.textViewName);
        TextView phone = (TextView) row.findViewById(R.id.textViewPhone);
        // pillar imagenes de sexo h y m
        if (data.get(position).getGender() == 'H') {
            fotoGender.setImageResource(R.mipmap.ic_male);
        } else {
            fotoGender.setImageResource(R.mipmap.ic_female);
        }
        nombre.setText(data.get(position).getName());
        phone.setText(data.get(position).getPhone());
        return row;
         */
    }
}
