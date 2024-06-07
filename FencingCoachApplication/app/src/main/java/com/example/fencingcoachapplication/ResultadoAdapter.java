package com.example.fencingcoachapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fencingcoachapplication.pojo.Resultado;
import java.util.List;

public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.ResultadoViewHolder> {
    private List<Resultado> resultados;

    public ResultadoAdapter(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado, parent, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        Resultado resultado = resultados.get(position);
        holder.tvUsuario.setText(String.valueOf(resultado.getUsuario()));  // Supongamos que quieres mostrar el ID del usuario
        holder.tvVictorias.setText("Victorias: " + resultado.getVictorias());
        holder.tvDerrotas.setText("Derrotas: " + resultado.getDerrotas());
        holder.tvTocadosDados.setText("TD: " + resultado.getTocadosDados());
        holder.tvTocadosRecibidos.setText("TR: " + resultado.getTocadosRecibidos());
        holder.tvCoeficiente.setText("Coeficiente: " + resultado.getCoeficienteTocados());
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    static class ResultadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsuario;
        TextView tvVictorias;
        TextView tvDerrotas;
        TextView tvTocadosDados;
        TextView tvTocadosRecibidos;
        TextView tvCoeficiente;

        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvVictorias = itemView.findViewById(R.id.tvVictorias);
            tvDerrotas = itemView.findViewById(R.id.tvDerrotas);
            tvTocadosDados = itemView.findViewById(R.id.tvTocadosDados);
            tvTocadosRecibidos = itemView.findViewById(R.id.tvTocadosRecibidos);
            tvCoeficiente = itemView.findViewById(R.id.tvCoeficiente);
        }
    }
}
