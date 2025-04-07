package es.ieslavereda.ejemplo_recycledview_2425;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import es.ieslavereda.ejemplo_recycledview_2425.model.Pais;

public class DetailActivity extends AppCompatActivity {
    private ImageView bandera;
    private TextView nombre, detalles;
    private FloatingActionButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Bundle extra = getIntent().getExtras();
        Pais pais = (Pais) extra.getSerializable("pais");

        bandera = findViewById(R.id.banderaDetailImage);
        nombre = findViewById(R.id.detailPaisTextView);
        detalles = findViewById(R.id.detailsTextView);
        atras = findViewById(R.id.floatingActionButtonAtras);

        nombre.setText(pais.getNombre());
        detalles.setText(pais.getDetalle());
        Picasso.get().load(pais.getBanderaURL()).into(bandera);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
