package com.example.fencingcoachapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fencingcoachapplication.pojo.Asalto;
import com.example.fencingcoachapplication.pojo.Resultado;
import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAdministradorActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ResultadoAdapter resultadoAdapter;
    private List<Usuario> UsuarioList;
    Usuario usuario;
    Sesion sesion;
    FirebaseCrud firebaseCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_administrador);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        sesion = (Sesion) intent.getSerializableExtra("sesion");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recycler = findViewById(R.id.reciView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Crear datos de ejemplo
        UsuarioList = new ArrayList<>();
        UsuarioList = sesion.getUsuarios();
        List<Resultado> listaResultado = new ArrayList<>();

        for (Usuario u : UsuarioList) {
            firebaseCrud.listarYFiltrarAsaltos(u.getNombreUsuario(), u.getNombreUsuario(), new FirebaseCrud.AsaltosCallback() {
                @Override
                public void onCallback(List<Asalto> asaltos) {
                    int numVictorias = 0;
                    int numDerrotas = 0;
                    int totalTocadosDados = 0;
                    int totalTocadosRecibidos = 0;
                    int numAsaltos = asaltos.size();
                    int coeficiente = 0;
                    String idResultado = u.getNombreUsuario() + sesion.getIdSesion();

                    for (Asalto a : asaltos) {
                        if (a.getVencedor().equals(usuario.getNombreUsuario())) {
                            numVictorias++;
                        } else {
                            numDerrotas++;
                        }
                        totalTocadosDados += a.getTocadosLocal();
                        totalTocadosRecibidos += a.getTocadosVisitante();
                        coeficiente = totalTocadosDados / totalTocadosRecibidos;
                    }


                    Resultado resultado = new Resultado(usuario, idResultado, asaltos.size(), numVictorias, numDerrotas, totalTocadosDados, totalTocadosRecibidos, coeficiente);
                    listaResultado.add(resultado);
                }
            });
        }

        resultadoAdapter = new ResultadoAdapter(listaResultado);
        recycler.setAdapter(resultadoAdapter);

    }


    public void volverRecicler(View view) {
        Intent intent = new Intent(this, MenuAdministradorActivity.class);
        startActivity(intent);
    }

}