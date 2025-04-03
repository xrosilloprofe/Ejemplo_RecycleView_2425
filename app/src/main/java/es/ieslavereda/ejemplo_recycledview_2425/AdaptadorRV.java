package es.ieslavereda.ejemplo_recycledview_2425;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.ieslavereda.ejemplo_recycledview_2425.model.Pais;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {

    private LayoutInflater inflater;
    private List<Pais> paises;

    //constructor
    public AdaptadorRV(Context context, List<Pais> paises){
        this.paises = paises;
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //crear el viewholder como tipo View
    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_layout,
                parent,false);
        return new ViewHolder(view);
    }

    //Asociar vista con los datos de la posición
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {
        Pais pais = paises.get(position);
        holder.nombre.setText(pais.getNombre());
        //Picasso.get().load(url).into(imageview)
        Picasso.get().load(pais.getBanderaURL()).into(holder.bandera);
    }

    //devuelve el número de elementos de la lista
    @Override
    public int getItemCount() {
        return paises.size();
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
