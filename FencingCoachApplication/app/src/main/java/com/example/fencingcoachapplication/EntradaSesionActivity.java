package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;

public class EntradaSesionActivity extends AppCompatActivity {
    private Button buttonResultadoSesionUsuario;
    private Button buttonMarcador;
    private Button buttonRegistroAsalto;
    private Button buttonAtrasSesion;
    FirebaseCrud firebaseCrud;
    Usuario usuario;
    Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrada_sesion);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        sesion= (Sesion) intent.getSerializableExtra("sesion");
        firebaseCrud= new FirebaseCrud();

        buttonResultadoSesionUsuario = findViewById(R.id.buttonResultadoUsuario);
        buttonMarcador = findViewById(R.id.buttonMarcador);
        buttonRegistroAsalto = findViewById(R.id.buttonRegistroAsalto);
        buttonAtrasSesion = findViewById(R.id.buttonAtrasSesion);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

    }


    public void resultadoSesion(View view) {
        Intent intent = new Intent(this, ResultadoSesionUsuarioActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("sesion",sesion);
        startActivity(intent);
    }

    public void AcesoMarcador(View view) {
        Intent intent = new Intent(this, MarcadorActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void RegistrarAsalto(View view) {
        Intent intent = new Intent(this, RegistroAsaltoUsuarioActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("sesion",sesion);
        startActivity(intent);
    }
    public void volverAtrasSesion(View view) {
        Intent intent = new Intent(this, AccesoCodigoSesionActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("sesion",sesion);
        startActivity(intent);
    }
}