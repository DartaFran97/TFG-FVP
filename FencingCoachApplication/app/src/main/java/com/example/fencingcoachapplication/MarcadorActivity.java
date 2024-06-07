package com.example.fencingcoachapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fencingcoachapplication.pojo.Usuario;

import java.util.Locale;

public class MarcadorActivity extends AppCompatActivity {
    private EditText puntosTiradorVerde;
    private EditText puntosTiradorRojo;
    private EditText TimeCronometro;
    private Button TocadoVerde;
    private Button TocadoRojo;
    private Button menosTocadoVerde;
    private Button menosTocadoRojo;
    private Button ResetTiempo;
    private Button ResetMarcador;
    private  Usuario usuario;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 180000; // 3 minutos en milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_marcador);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        puntosTiradorVerde = findViewById(R.id.editTextNumberTiradorVerde);
        puntosTiradorRojo = findViewById(R.id.editTextNumberTiradorRojo);
        TimeCronometro = findViewById(R.id.editTextTimeCronometro);
        TocadoVerde = findViewById(R.id.buttonTocadoVerde);
        TocadoRojo = findViewById(R.id.buttonTocadoRojo);
        menosTocadoVerde = findViewById(R.id.buttonmenosTocadoVerde);
        menosTocadoRojo = findViewById(R.id.butttonmenosTocadoRojo);
        ResetTiempo = findViewById(R.id.buttonResetTiempo);
        ResetMarcador = findViewById(R.id.buttonResetMarcador);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void resetearMarcador(View view) {
        puntosTiradorVerde.setText("0");
        puntosTiradorRojo.setText("0");
    }

    public void menosrojo(View view) {
        int marcadorRojo = Integer.parseInt(puntosTiradorRojo.getText().toString());
        if (marcadorRojo > 0) {
            marcadorRojo--;
            puntosTiradorRojo.setText(String.valueOf(marcadorRojo));
        }
    }

    public void menosverde(View view) {
        int marcadorVerde = Integer.parseInt(puntosTiradorVerde.getText().toString());
        if (marcadorVerde > 0) {
            marcadorVerde--;
            puntosTiradorVerde.setText(String.valueOf(marcadorVerde));
        }
    }

    public void tocadorojo(View view) {
        int marcadorRojo = Integer.parseInt(puntosTiradorRojo.getText().toString());
        marcadorRojo++;
        puntosTiradorRojo.setText(String.valueOf(marcadorRojo));
    }

    public void tocadoverde(View view) {
        int marcadorVerde = Integer.parseInt(puntosTiradorVerde.getText().toString());
        marcadorVerde++;
        puntosTiradorVerde.setText(String.valueOf(marcadorVerde));
    }

    public void cronometro(View view) {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void StartTime(View view) {
        startTimer();
    }

    public void StopTime(View view) {
        stopTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();

        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        TimeCronometro.setText(timeLeftFormatted);
    }

    public void resetearTiempo(View view) {
        timeLeftInMillis = 180000; // 3 minutos en milisegundos
        updateTimer();
    }

    public void AtrasMenu(View view) {
        if (usuario.isRol()) {
            Intent intent = new Intent(MarcadorActivity.this, MenuAdministradorActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);

        } else {
            Intent intent = new Intent(MarcadorActivity.this, MainActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        }
    }
}
