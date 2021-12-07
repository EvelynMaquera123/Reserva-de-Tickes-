package ne.subgrupo_catorce.proyecto_final.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ne.subgrupo_catorce.proyecto_final.Adaptadores.EventsAdapter;
import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.databinding.FragmentEventsListBinding;
import ne.subgrupo_catorce.proyecto_final.db.DbEventos;
import ne.subgrupo_catorce.proyecto_final.entidades.Eventos;

public class EventsListFragment extends Fragment {
    private FragmentEventsListBinding binding;

    ArrayList<Eventos> listaEventos;
    RecyclerView recyclerViewEventos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events_list, container, false);
        View vista = inflater.inflate(R.layout.fragment_events_list, container, false);

        listaEventos = new ArrayList<>();
        recyclerViewEventos = (RecyclerView) vista.findViewById(R.id.recycler_Events);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(getContext()));

        DbEventos dbEventos = new DbEventos(getContext());

        EventsAdapter adapter = new EventsAdapter(dbEventos.mostrarEventos());
        recyclerViewEventos.setAdapter(adapter);
        return vista;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
    }

    public void goToEventOverview() {
        NavHostFragment.findNavController(this).navigate(R.id.action_eventsListFragment_to_eventOverview);
    }
}