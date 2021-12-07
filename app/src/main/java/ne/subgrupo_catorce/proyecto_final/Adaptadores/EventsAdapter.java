package ne.subgrupo_catorce.proyecto_final.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.entidades.Eventos;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventosViewHolder> {
    ArrayList<Eventos> listaEventos;
    private View.OnClickListener listener;

    public EventsAdapter(ArrayList<Eventos> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @Override
    public EventosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);
        return new EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventosViewHolder holder, int position) {
        holder.txtLugar.setText(listaEventos.get(position).getNombre());
        holder.txtFecha.setText(String.valueOf(listaEventos.get(position).getFecha()));
        holder.txtTitulo.setText(listaEventos.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class EventosViewHolder extends RecyclerView.ViewHolder {
        TextView txtFecha, txtTitulo, txtLugar;
        Button seeOverviewButton;

        public EventosViewHolder(View itemView) {
            super(itemView);
            txtFecha = (TextView) itemView.findViewById(R.id.e_fecha);
            txtLugar = (TextView) itemView.findViewById(R.id.e_lugar);
            txtTitulo = (TextView) itemView.findViewById(R.id.e_titulo);
            seeOverviewButton = itemView.findViewById(R.id.see_overview_button);

            seeOverviewButton.setOnClickListener((view) -> {
                        Navigation.findNavController(view).navigate(R.id.action_eventsListFragment_to_eventOverview);
                    }
            );
        }
    }
}
