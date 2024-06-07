package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroAltaUsuarioActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextCorreo;
    private Spinner spinnerCategoria;
    private Spinner spinnerArma;
    FirebaseCrud firebaseCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_registro_alta_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerArma = findViewById(R.id.spinnerArma);
        Button buttonRegistrar = findViewById(R.id.buttonRegistro);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        ArrayAdapter<CharSequence> adapterArma = ArrayAdapter.createFromResource(this,
                R.array.arma_array, android.R.layout.simple_spinner_item);
        adapterArma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArma.setAdapter(adapterArma);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String correo = editTextCorreo.getText().toString().trim();
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String arma = spinnerArma.getSelectedItem().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(correo)) {
                    Toast.makeText(RegistroAltaUsuarioActivity.this, "El nombre de usuario, contraseña y correo son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(username, password, correo, categoria, arma);
                }
            }
        });
    }

    private void registrarUsuario(String username, String password, String correo, String categoria, String arma) {
        Usuario usuario=new Usuario(username,password,correo,categoria,arma);
        firebaseCrud.crearNuevoUsuario(usuario);
        limpiarCampos();
        Intent intent = new Intent(RegistroAltaUsuarioActivity.this, LoguinActivity.class);
        startActivity(intent);

    }

    private void limpiarCampos() {
        editTextUsername.setText("");
        editTextPassword.setText("");
        editTextCorreo.setText("");
        spinnerCategoria.setSelection(0);
        spinnerArma.setSelection(0);
    }
}
