package ne.subgrupo_catorce.proyecto_final.ui.events;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.databinding.FragmentEventOverviewBinding;

public class EventOverviewFragment extends Fragment {
    FragmentEventOverviewBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_overview, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }

    public void goBackToEventList() {
        NavHostFragment.findNavController(this).navigate(R.id.action_eventOverview_to_eventsListFragment);
    }

    public void goToEventDetails() {
        NavHostFragment.findNavController(this).navigate(R.id.action_eventOverview_to_eventDetailFragment);
    }

}