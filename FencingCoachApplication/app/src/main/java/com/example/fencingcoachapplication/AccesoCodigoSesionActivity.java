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

public class AccesoCodigoSesionActivity extends AppCompatActivity {
    private EditText editTextSesionCode;
    private Button buttonAccesoSesion;
    FirebaseCrud firebaseCrud;
    Usuario usuario;
    int desvio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acceso_codigo_sesion);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        desvio= (int) intent.getSerializableExtra("desvio");

        editTextSesionCode = findViewById(R.id.editTextSesionCode);
        buttonAccesoSesion = findViewById(R.id.buttonAccesoSesion);
        firebaseCrud= new FirebaseCrud();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonAccesoSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accesoSesion();
            }
        });
    }

    public void CerrarSesion(View view) {
        startActivity(new Intent(this, LoguinActivity.class));
        finish();
    }

    public void accesoSesion() {
        Long codigoSesion =Long.parseLong(editTextSesionCode.getText().toString()) ;
        if (codigoSesion.equals(null)) {
            Toast.makeText(this, "Por favor, introduce el código de sesión", Toast.LENGTH_SHORT).show();
        } else {
            firebaseCrud.getSessionById(codigoSesion,new FirebaseCrud.SesionCallback() {
                @Override
                public void onCallback(Sesion sesion) {

                    if (sesion != null) {
                        if (usuario.isRol()) {
                            if (desvio == 1) {
                                Intent intent = new Intent(AccesoCodigoSesionActivity.this, RegistroAsaltoAdministradorActivity.class);
                                intent.putExtra("usuario", usuario);
                                intent.putExtra("sesion",codigoSesion);
                                startActivity(intent);
                            } else if (desvio == 2) {
                                Intent intent = new Intent(AccesoCodigoSesionActivity.this, ResultadoAdministradorActivity.class);
                                intent.putExtra("usuario", usuario);
                                intent.putExtra("sesion",codigoSesion);
                                startActivity(intent);
                            }
                        }else{
                        Intent intent = new Intent(AccesoCodigoSesionActivity.this, EntradaSesionActivity.class);
                        intent.putExtra("usuario", usuario);
                        intent.putExtra("sesion",codigoSesion);
                        startActivity(intent);
                        }
                    } else {
                        Toast.makeText(AccesoCodigoSesionActivity.this, "codigo no valido", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            Toast.makeText(this, "Acceso con código: " + codigoSesion, Toast.LENGTH_SHORT).show();
        }
    }
}
