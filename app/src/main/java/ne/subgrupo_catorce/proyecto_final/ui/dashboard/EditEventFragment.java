package ne.subgrupo_catorce.proyecto_final.ui.dashboard;

import static androidx.navigation.Navigation.findNavController;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.db.DbEventos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditEventFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditEventFragment newInstance(String param1, String param2) {
        EditEventFragment fragment = new EditEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    TextInputLayout txtNombre, txtTitulo, txtFecha, txtDescripcion, txtImagen, txtLatitud, txtLongitud;

    int position;
    long id;
    String fecha, lugar, titulo, descripcion;
    double latitud, longitud;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        id=getArguments().getLong("ID");
        lugar=getArguments().getString("LUGAR");
        titulo=getArguments().getString("TITULO");
        descripcion=getArguments().getString("DESCRIPCION");
        fecha=getArguments().getString("FECHA");
        latitud=getArguments().getDouble("LATITUD");
        longitud=getArguments().getDouble("LONGITUD");
    }

    Button btnActualizar;
    Button btnEliminar;
    boolean correcto = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_edit_event, container, false);
        txtNombre = vista.findViewById(R.id.edit_nombre);
        txtTitulo = vista.findViewById(R.id.edit_titulo);
        //txtFecha = vista.findViewById(R.id.edit_fecha);
        txtDescripcion = vista.findViewById(R.id.edit_descripcion);
        //txtLatitud = vista.findViewById(R.id.edit_latitud);
        //txtLongitud = vista.findViewById(R.id.edit_longitud);

        txtNombre.getEditText().setText(lugar);
        txtTitulo.getEditText().setText(titulo);
        //txtFecha.getEditText().setText(fecha);
        txtDescripcion.getEditText().setText(descripcion);
        //txtLatitud.getEditText().setText(String.valueOf(latitud) );
        //txtLongitud.getEditText().setText(String.valueOf(longitud) );




        btnActualizar = vista.findViewById(R.id.actualizar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lugar = txtNombre.getEditText().getText().toString();
                titulo = txtTitulo.getEditText().getText().toString();
                //fecha = txtFecha.getEditText().getText().toString();
                descripcion = txtDescripcion.getEditText().getText().toString();
                //latitud = Double.parseDouble(txtLatitud.getEditText().getText().toString());
                //longitud = Double.parseDouble(txtLongitud.getEditText().getText().toString());
                Log.d("EDIT",String.valueOf(lugar));

                DbEventos dbEventos = new DbEventos(getContext());
                correcto = dbEventos.editarEvento(id,lugar,titulo,"",descripcion,0.0,0.0);
                if(correcto){
                    Toast.makeText(getContext(),"RESGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                    redirectEventsList();
                }else{
                    Toast.makeText(getContext(),"RESGISTRO NO MODIFICADO",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar = vista.findViewById(R.id.eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEventos dbEventos = new DbEventos(getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Desea eliminar este evento?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean correcto = dbEventos.eliminarEvento(id);
                        if(correcto){
                            redirectEventsList();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

        return vista;
    }
    public void redirectEventsList(){
        EventsDBFragment eventsDBFragment = new EventsDBFragment();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment,eventsDBFragment).commit();
    }
}