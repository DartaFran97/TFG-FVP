package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Asalto;
import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ResultadoSesionUsuarioActivity extends AppCompatActivity {
    private EditText nAsaltos, victorias, derrotas, tocadosDados, tocadosRecibidos, coeficiente;
    private TextView textNAsaltos, textNVictorias, textNDerrotas, textNTocadosDados, textNTocadosRecibidos, textCoeficiente;
    Usuario usuario;
    Sesion sesion;
    FirebaseCrud firebaseCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_sesion_usuario);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        sesion = (Sesion) intent.getSerializableExtra("sesion");

        nAsaltos = findViewById(R.id.NAsaltos);
        victorias = findViewById(R.id.editVictorias);
        derrotas = findViewById(R.id.editDerrotas);
        tocadosDados = findViewById(R.id.editTocadosDados);
        tocadosRecibidos = findViewById(R.id.editTocadosRecibidos);
        coeficiente = findViewById(R.id.editCoeficiente);

        textNAsaltos = findViewById(R.id.editTextNAsaltos);
        textNVictorias = findViewById(R.id.editTextNVictorias);
        textNDerrotas = findViewById(R.id.editTextNDerotas);
        textNTocadosDados = findViewById(R.id.editTextNTocadosDados);
        textNTocadosRecibidos = findViewById(R.id.editTextNTocadosRecibidos);
        textCoeficiente = findViewById(R.id.editTextCoeficiente);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseCrud = new FirebaseCrud();

        calcularResultados();
    }

    private void calcularResultados() {
        firebaseCrud.listarYFiltrarAsaltos(usuario.getNombreUsuario(), usuario.getNombreUsuario(), new FirebaseCrud.AsaltosCallback() {
            @Override
            public void onCallback(List<Asalto> asaltos) {
                int numVictorias = 0;
                int numDerrotas = 0;
                int totalTocadosDados = 0;
                int totalTocadosRecibidos = 0;
                int numAsaltos = asaltos.size();

                for (Asalto a : asaltos) {
                    if (a.getVencedor().equals(usuario.getNombreUsuario())) {
                        numVictorias++;
                    } else {
                        numDerrotas++;
                    }
                    totalTocadosDados += a.getTocadosLocal();
                    totalTocadosRecibidos += a.getTocadosVisitante();
                }


                nAsaltos.setText(String.valueOf(numAsaltos));
                victorias.setText(String.valueOf(numVictorias));
                derrotas.setText(String.valueOf(numDerrotas));
                tocadosDados.setText(String.valueOf(totalTocadosDados));
                tocadosRecibidos.setText(String.valueOf(totalTocadosRecibidos));
                coeficiente.setText(String.valueOf(totalTocadosDados/totalTocadosRecibidos));

            }
        });
    }


    public void AtrasMenuUsuario(View view) {
        Intent intent = new Intent(this,EntradaSesionActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("sesion",sesion);
        startActivity(intent);
        finish();
    }
}
