package ne.subgrupo_catorce.proyecto_final.Adaptadores;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.entidades.Eventos;
import ne.subgrupo_catorce.proyecto_final.ui.dashboard.EditEventFragment;

public class ListaEventosAdapter extends RecyclerView.Adapter<ListaEventosAdapter.ContactoViewHolder> {

    ArrayList<Eventos> listaEventos;
    ArrayList<Eventos> listaOriginal;
    FragmentCommunication mCommunicator;

    public ListaEventosAdapter(ArrayList<Eventos> listaEventos, FragmentCommunication communication){
        this.listaEventos = listaEventos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaEventos);
        mCommunicator = communication;
    }

    // Crea la interfaz que implementaras en la actividad
    public interface FragmentCommunication {
        void respond(int position,long id,String lugar, String titulo, String descripcion, String fecha, Double latitud, Double longitud);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
        ContactoViewHolder holder = new ContactoViewHolder(view, mCommunicator);
        return new ContactoViewHolder(view, mCommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {

        holder.viewId.setText(listaEventos.get(position).getId().toString());

        holder.viewNombre.setText(listaEventos.get(position).getNombre());
        holder.viewTitulo.setText(listaEventos.get(position).getTitulo());
        //holder.viewFecha.setText(listaEventos.get(position).getFecha().toString());
        holder.viewDescripcion.setText(listaEventos.get(position).getDescripcion());
        //holder.viewLongitud.setText(listaEventos.get(position).getLongitud().toString());
        //holder.viewLatitud.setText(listaEventos.get(position).getLatitud().toString());

        String data = listaEventos.get(position).getId().toString();

        EditEventFragment editEventFragment = new EditEventFragment();
        Bundle bundle=new Bundle();
        bundle.putString("ID",data);
        editEventFragment.setArguments(bundle);

    }

    //Clase para buscar Eventos por titulo
    public void filtrado(String txtBuscar){

        int longitud = txtBuscar.length();
        if(longitud==0){
            listaEventos.clear();
            listaEventos.addAll(listaOriginal);
        }else{
            List<Eventos> collec = listaEventos.stream().filter(i->i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
            listaEventos.clear();
            listaEventos.addAll(collec);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return listaEventos.size();
    }
    public class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView viewId, viewNombre, viewTitulo, viewFecha, viewDescripcion, viewLongitud, viewLatitud;

        public ContactoViewHolder(@NonNull View itemView, FragmentCommunication communication) {
            super(itemView);
            viewId = itemView.findViewById(R.id.viewID);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTitulo = itemView.findViewById(R.id.viewTitulo);
            //viewFecha = itemView.findViewById(R.id.viewFecha);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            //viewLongitud = itemView.findViewById(R.id.viewLongitud);
            //viewLatitud = itemView.findViewById(R.id.viewLatitud);
            mCommunicator = communication;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //mCommunicator.respond(getAdapterPosition(),listaEventos.get(getAdapterPosition()).getNombre(),listaEventos.get(getAdapterPosition()).getTitulo());
            mCommunicator.respond(getAdapterPosition(),listaEventos.get(getAdapterPosition()).getId(),listaEventos.get(getAdapterPosition()).getNombre(),listaEventos.get(getAdapterPosition()).getTitulo(),listaEventos.get(getAdapterPosition()).getDescripcion(),listaEventos.get(getAdapterPosition()).getFecha(),listaEventos.get(getAdapterPosition()).getLatitud(),listaEventos.get(getAdapterPosition()).getLongitud());
        }
    }
}
