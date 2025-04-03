package es.ieslavereda.ejemplo_recycledview_2425;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ieslavereda.ejemplo_recycledview_2425.model.Pais;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {

    //constructor
    public AdaptadorRV(Context context, List<Pais> paises){

    }

    //crear el viewholder como tipo View
    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //Asociar vista con los datos de la posición
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {

    }

    //devuelve el número de elementos de la lista
    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView bandera;
        private TextView nombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bandera = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.textView);
        }
    }

}
