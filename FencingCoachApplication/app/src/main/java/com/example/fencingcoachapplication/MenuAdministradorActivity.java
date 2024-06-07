package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;

public class MenuAdministradorActivity extends AppCompatActivity {
    Usuario usuario;
    Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        sesion = (Sesion) intent.getSerializableExtra("sesion");
        setContentView(R.layout.activity_menu_administrador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void crearSesion(View view) {
        startActivity(new Intent(this, CrearSesionActivity.class));
    }

    public void resultadosSesionAdmin(View view) {
        Intent intent = new Intent(this, AccesoCodigoSesionActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("desvio", 2);
        if (sesion != null) {
            intent.putExtra("sesion", sesion);
        }
        startActivity(intent);
    }

    public void AcesoMarcadorAdmin(View view) {
        startActivity(new Intent(this, MarcadorActivity.class));
    }

    public void RegistrarAsaltoAdmin(View view) {
        Intent intent = new Intent(this, AccesoCodigoSesionActivity.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("desvio", 1);
        if (sesion != null) {
            intent.putExtra("sesion", sesion);
        }
        startActivity(intent);
    }
}