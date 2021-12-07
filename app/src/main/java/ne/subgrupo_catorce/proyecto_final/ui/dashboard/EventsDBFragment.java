package ne.subgrupo_catorce.proyecto_final.ui.dashboard;

import static androidx.navigation.Navigation.findNavController;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ne.subgrupo_catorce.proyecto_final.Adaptadores.ListaEventosAdapter;
import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.db.DbEventos;
import ne.subgrupo_catorce.proyecto_final.db.DbHelper;
import ne.subgrupo_catorce.proyecto_final.entidades.Eventos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsDBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsDBFragment extends Fragment implements SearchView.OnQueryTextListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventsDBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsDBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsDBFragment newInstance(String param1, String param2) {
        EventsDBFragment fragment = new EventsDBFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    SearchView txtBuscar;
    RecyclerView listaEventos;
    ArrayList<Eventos> listaArrayEventos;
    Button btnCrear;
    ListaEventosAdapter adapter;
    FloatingActionButton btnAgregar;

    //Comunicar fragments
    //Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_events_d_b, container, false);
        txtBuscar = vista.findViewById(R.id.buscar);
        listaEventos =vista. findViewById(R.id.lista);
        listaEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        DbEventos dbEventos = new DbEventos(getContext());

        listaArrayEventos = new ArrayList<>();
        adapter = new ListaEventosAdapter(dbEventos.mostrarEventos(),communication);
        listaEventos.setAdapter(adapter);

        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        btnAgregar = vista.findViewById(R.id.mas);
        //txtBuscar.setOnQueryTextListener(this);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.action_eventsDBFragment_to_newEventFragment);
            }
        });

        txtBuscar.setOnQueryTextListener(this);

        return vista;
    }
    ListaEventosAdapter.FragmentCommunication communication = new ListaEventosAdapter.FragmentCommunication() {
        @Override
        public void respond(int position,long id,String lugar, String titulo, String descripcion, String fecha, Double latitud, Double longitud) {
            EditEventFragment fragmentB=new EditEventFragment();
            Bundle bundle=new Bundle();
            bundle.putLong("ID",id);
            bundle.putString("LUGAR",lugar);
            bundle.putString("TITULO",titulo);
            bundle.putString("DESCRIPCION",descripcion);
            bundle.putString("FECHA",fecha);
            bundle.putDouble("LATITUD",latitud);
            bundle.putDouble("LONGITUD",longitud);
            fragmentB.setArguments(bundle);
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment,fragmentB).commit();
        }

    };

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}