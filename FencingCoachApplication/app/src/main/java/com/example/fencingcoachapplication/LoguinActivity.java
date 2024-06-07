package com.example.fencingcoachapplication;

import static androidx.core.content.ContextCompat.startActivity;

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

import com.example.fencingcoachapplication.pojo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoguinActivity extends AppCompatActivity {
    EditText textCorreo;
    EditText textPassword;
    Button loginButton;
    Button crearUsuario;
    FirebaseCrud firebaseCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loguin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textCorreo = findViewById(R.id.editTextSesionCode);
        textPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonRegistrar);
        crearUsuario = findViewById(R.id.crearUsuario);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textCorreo.getText().toString();
                String password = textPassword.getText().toString();
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        firebaseCrud.compararUsuario(email, password, new FirebaseCrud.UserCallback() {
            @Override
            public void onCallback(Usuario usuario) {
                if (usuario != null) {
                    if (usuario.isRol()) {
                        // Usuario con rol administrador
                        Toast.makeText(LoguinActivity.this, "Usuario Administrador.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoguinActivity.this, MenuAdministradorActivity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoguinActivity.this, "Usuario.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoguinActivity.this, EntradaSesionActivity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoguinActivity.this, "usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show(); // Login failed, show error message
                    textCorreo.setText("");
                    textPassword.setText("");
                }
            }
        });
    }

    public void crearUsuario(View view) {
        Intent intent = new Intent(LoguinActivity.this, RegistroAltaUsuarioActivity.class);
        startActivity(intent);
    }
}
