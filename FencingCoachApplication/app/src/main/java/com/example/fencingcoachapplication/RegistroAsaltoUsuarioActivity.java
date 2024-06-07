package com.example.fencingcoachapplication;

import static java.util.Calendar.DATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Asalto;
import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;

public class RegistroAsaltoUsuarioActivity extends AppCompatActivity {
    private TextView textViewNombreRival;
    private TextView textViewTocadosRival;
    private TextView textViewTocadosPropios;
    private Button buttonRegistrarAsalto;
    private Button buttonAtrasMenu;
    Usuario usuario;
    Sesion sesion;
    FirebaseCrud firebaseCrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        sesion = (Sesion) intent.getSerializableExtra("sesion");
        setContentView(R.layout.activity_registro_asalto_usuario);
        textViewNombreRival = findViewById(R.id.textViewNombreRival);
        textViewTocadosRival = findViewById(R.id.textViewTocadosRival);
        textViewTocadosPropios = findViewById(R.id.textViewTocadosPropios);
        buttonRegistrarAsalto = findViewById(R.id.buttonRegistrarAsalto);
        buttonAtrasMenu = findViewById(R.id.buttonAtrasMenu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Registrar(View view) {
        String nombreUsuario= usuario.getNombreUsuario();
        String nombreRival = textViewNombreRival.getText().toString();
        int tocadosRival = Integer.parseInt(textViewTocadosRival.getText().toString());
        int tocadosPropios = Integer.parseInt(textViewTocadosPropios.getText().toString());

        Asalto asalto= new Asalto(nombreUsuario,nombreRival,tocadosPropios,tocadosRival);
        String Vencedor= String.valueOf(Asalto.getVencedor());
        String IdAsalto= nombreUsuario+nombreRival+String.valueOf(tocadosPropios)+String.valueOf(tocadosRival)+String.valueOf(DATE);;

        asalto.setIdAsalto(IdAsalto);
        asalto.setVencedor(Vencedor);
        asalto.setSesionId(sesion.getIdSesion());
        firebaseCrud.crearNuevoAsalto(sesion.getIdSesion(), asalto);
        limpiarCampos();



    }
    private void limpiarCampos() {
        textViewNombreRival.setText("");
        textViewTocadosRival.setText("");
        textViewTocadosPropios.setText("");
    }

    public void AtrasMenu(View view) {
        Intent intent= new Intent(this, EntradaSesionActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}