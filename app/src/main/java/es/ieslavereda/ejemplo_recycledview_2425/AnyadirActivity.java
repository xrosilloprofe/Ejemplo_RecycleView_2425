package es.ieslavereda.ejemplo_recycledview_2425;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import es.ieslavereda.ejemplo_recycledview_2425.model.Pais;

public class AnyadirActivity extends AppCompatActivity {
private EditText banderaURL, nombre, descripcion;
private Button cancelarBoton, confirmarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anyadir_layout);

        banderaURL = findViewById(R.id.banderaEditText);
        nombre = findViewById(R.id.nombreEditText);
        descripcion = findViewById(R.id.descripcionEditText);
        cancelarBoton = findViewById(R.id.cancelarBoton);
        confirmarBoton = findViewById(R.id.confirmarBoton);

        cancelarBoton.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED,intent);
            finish();
        });

        confirmarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Pais pais = new Pais(nombre.getText().toString(),
                        banderaURL.getText().toString(),
                        descripcion.getText().toString());
                intent.putExtra("pais", pais);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

}
