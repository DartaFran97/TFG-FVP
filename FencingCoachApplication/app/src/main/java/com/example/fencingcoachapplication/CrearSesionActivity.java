package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CrearSesionActivity extends AppCompatActivity {
    private EditText codigoSesion;
    private EditText responsableSesion;
    private DatePicker datePicker;
    private Button buttonCrearCode;
    private Button buttonCrearSesion;
    private Button buttonAtrasAdminMenu;
    private long codigoGenerado;
    FirebaseCrud firebaseCrud;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_crear_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        codigoSesion = findViewById(R.id.codigoSesion);
        responsableSesion = findViewById(R.id.ResponsableSesion);
        datePicker = findViewById(R.id.datePicker2);
        buttonCrearCode = findViewById(R.id.buttonCrearCode);
        buttonCrearSesion = findViewById(R.id.buttonCrearSesion);
        buttonAtrasAdminMenu = findViewById(R.id.buttonAtrasAdminMenu);

    }

    public void CrearCode(View view) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        calendar= Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());

        codigoGenerado = Long.parseLong(formattedDate);
        codigoSesion.setText(String.valueOf(codigoGenerado));

    }

    public void CrearSesion(View view) {
        String responsable = responsableSesion.getText().toString();
        List<Usuario> listaParticipantes= new ArrayList<>();
        Sesion sesion= new Sesion(codigoGenerado,calendar.getTime(),responsable,listaParticipantes);
        firebaseCrud.crearNuevaSesion(sesion);

        Toast.makeText(this, "Sesion creada", Toast.LENGTH_SHORT).show();

        Intent intent= new Intent(CrearSesionActivity.this, MenuAdministradorActivity.class);
        intent.putExtra("sesion", sesion);
        startActivity(intent);
        finish();

    }


    public void AtrasAdminMenu(View view) {
        startActivity(new Intent(CrearSesionActivity.this, MenuAdministradorActivity.class));
        finish();
    }
}