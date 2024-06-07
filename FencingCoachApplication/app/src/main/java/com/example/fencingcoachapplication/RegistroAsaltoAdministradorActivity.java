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
import com.example.fencingcoachapplication.pojo.Usuario;

public class RegistroAsaltoAdministradorActivity extends AppCompatActivity {
    private TextView textViewNombreTirador1;
    private TextView textViewNombreTirador2;
    private TextView textViewTocadosTirador1;
    private TextView textViewTocadosTirador2;
    private TextView textViewIdSesion;
    private Button buttonRegistrarAsalto;
    private Button buttonAtrasAdminMenu;
    Usuario usuario;
    FirebaseCrud firebaseCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_asalto_administrador);
        Intent intent = getIntent();
        usuario= (Usuario) intent.getSerializableExtra("usuario");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewNombreTirador1 = findViewById(R.id.textViewNombreTirador1);
        textViewNombreTirador2 = findViewById(R.id.textViewNombreTirador2);
        textViewTocadosTirador1 = findViewById(R.id.textViewTocadosTirador1);
        textViewTocadosTirador2 = findViewById(R.id.textViewTocadosTirador2);
        textViewIdSesion = findViewById(R.id.textViewidSesion);
        buttonRegistrarAsalto = findViewById(R.id.buttonRegistrarAsalto);
        buttonAtrasAdminMenu = findViewById(R.id.buttonAtrasAdminMenu);

    }
    public void AtrasAdminMenu(View view) {
        Intent intent= new Intent(this, MenuAdministradorActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);


    }

    public void RegistrarAsaltoAdmin(View view) {
        String nombreTirador1 = textViewNombreTirador1.getText().toString();
        String nombreTirador2 = textViewNombreTirador2.getText().toString();
        int tocadosTirador1 = Integer.parseInt(textViewTocadosTirador1.getText().toString());
        int tocadosTirador2 = Integer.parseInt(textViewTocadosTirador2.getText().toString());
        int idSesion = Integer.parseInt(textViewIdSesion.getText().toString());

        Asalto asalto= new Asalto(nombreTirador1,nombreTirador2,tocadosTirador1,tocadosTirador2);
        String Vencedor= String.valueOf(Asalto.getVencedor());
        String IdAsalto= nombreTirador1+nombreTirador2+String.valueOf(tocadosTirador1)+String.valueOf(tocadosTirador2)+String.valueOf(DATE);;

        asalto.setIdAsalto(IdAsalto);
        asalto.setVencedor(Vencedor);
        asalto.setSesionId(idSesion);
        firebaseCrud.crearNuevoAsalto(idSesion,asalto);
        limpiarCampos();



    }
    private void limpiarCampos() {
        textViewNombreTirador1.setText("");
        textViewNombreTirador2.setText("");
        textViewTocadosTirador1.setText("");
        textViewTocadosTirador2.setText("");
        textViewIdSesion.setText("");
    }
}