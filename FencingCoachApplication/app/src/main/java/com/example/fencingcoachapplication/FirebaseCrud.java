package com.example.fencingcoachapplication;
import com.example.fencingcoachapplication.pojo.Asalto;
import com.example.fencingcoachapplication.pojo.Sesion;
import com.example.fencingcoachapplication.pojo.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class FirebaseCrud {
    private DatabaseReference databaseReference;

    public FirebaseCrud() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://fencingcoachapplication-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
    }

    public void crearNuevoUsuario(Usuario usuario) {
        String userId = databaseReference.child("usuarios").push().getKey();
        usuario.setId(Long.parseLong(userId));
        databaseReference.child("usuarios").child(userId).setValue(usuario);
    }

    public void compararUsuario(String correo, String contrasena, final UserCallback callback) {
        databaseReference.child("usuarios").orderByChild("correo").equalTo(correo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                            callback.onCallback(usuario);
                            return;
                        }
                    }
                }
                callback.onCallback(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onCallback(null);
            }
        });
    }

    public void crearNuevaSesion(Sesion sesion) {
        String sesionId = databaseReference.child("sesiones").push().getKey();
        sesion.setId(Long.parseLong(sesionId));
        databaseReference.child("sesiones").child(sesionId).setValue(sesion);
    }

    public void actualizarSesion(long sesionId, Asalto asalto) {
        databaseReference.child("sesiones").child(String.valueOf(sesionId)).child("asaltos").push().setValue(asalto);
    }

    public void crearNuevoAsalto(long sesionId, Asalto asalto) {

        String asaltoId = databaseReference.child("asaltos").push().getKey();
        if (asaltoId != null) {
            asalto.setIdAsalto(asaltoId);

            databaseReference.child("sesiones").child(String.valueOf(sesionId)).child("asaltos").child(asaltoId).setValue(asalto);
        } else {

            throw new IllegalStateException("No se pudo generar una clave para el asalto.");
        }
    }


    public void listarYFiltrarAsaltos(String nombreVencedor, String nombrePerdedor, final AsaltosCallback callback) {
        databaseReference.child("asaltos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Asalto> asaltos = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Asalto asalto = snapshot.getValue(Asalto.class);
                    if (asalto != null && (nombreVencedor.equals(asalto.getVencedor()) || nombrePerdedor.equals(asalto.getUsuarioLocal()) || nombrePerdedor.equals(asalto.getUsuarioVisitante()))) {
                        asaltos.add(asalto);
                    }
                }
                callback.onCallback(asaltos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onCallback(new ArrayList<>());
            }
        });
    }
    public void getSessionById(Long id, final SesionCallback callback) {
        DatabaseReference sessionRef = databaseReference.child(String.valueOf(id));
        sessionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Sesion sesion = dataSnapshot.getValue(Sesion.class);
                    callback.onCallback(sesion);
                } else {
                    callback.onCallback(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onCallback(null);
            }
        });
    }

    //callback
    public interface SesionCallback {
        void onCallback(Sesion sesion);
    }
    public interface UserCallback {
        void onCallback(Usuario usuario);
    }

    public interface AsaltosCallback {
        void onCallback(List<Asalto> asaltos);
    }
}

