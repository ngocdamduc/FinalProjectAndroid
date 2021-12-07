package com.example.wor.ui.workout.search_exercise_tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wor.MainActivity;
import com.example.wor.R;
import com.example.wor.room.AvailableExerciseItem;
import com.example.wor.room.ExerciseType;
import com.example.wor.room.TypeConverters;
import com.example.wor.ui.workout.WorkoutViewModel;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment {

    // Input fields
    private LocalDateTime mCurrentDateInput;
    private ExerciseType mExerciseTypeInput;

    // UI Fields
    private WorkoutViewModel mViewModel;
    private AvailableExerciseAdapter mAdapter;
    private RecyclerView mAvailableExerciseRV;

    public BrowseFragment() {
        // To enable menu for this fragment
        setHasOptionsMenu(true);
    }

    // New instance constructor
    static BrowseFragment newInstance(LocalDateTime currentDateInput, ExerciseType exerciseTypeInput) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle bundle = new Bundle();
        String dateInfo = TypeConverters.dateTimeToString(currentDateInput);
        bundle.putString(MainActivity.DATE_INFO, dateInfo);
        ArrayList<String> exerciseInfo = new ArrayList<>();
        exerciseInfo.add(Integer.toString(TypeConverters.exerciseTypeToInt(exerciseTypeInput)));
        bundle.putStringArrayList(MainActivity.EXERCISE_INFO, exerciseInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To enable menu for this fragment
        setHasOptionsMenu(true);
        // Parse through bundle
        if (getArguments() != null) {
            String dateInfo = getArguments().getString(MainActivity.DATE_INFO);
            mCurrentDateInput = TypeConverters.stringToDate(dateInfo);
            ArrayList<String> exerciseInfo = getArguments().getStringArrayList(MainActivity.EXERCISE_INFO);
            if (exerciseInfo != null)
                mExerciseTypeInput = TypeConverters.intToExerciseType(Integer.parseInt(exerciseInfo.get(0)));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize fields and variables
        View root = inflater.inflate(R.layout.fragment_browse, container, false);
        mAvailableExerciseRV = root.findViewById(R.id.browse_exercise_list_rv);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Setup adaptor
        mAvailableExerciseRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mAvailableExerciseRV.setHasFixedSize(true);
        mAdapter = new AvailableExerciseAdapter(new AvailableExerciseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                AvailableExerciseItem currentAvailableExercise = mAdapter.getExerciseItem(position);
                if (view.getId() == R.id.available_exercise_name_tv) {
                    Bundle bundle = new Bundle();
                    String dateInfo = TypeConverters.dateTimeToString(mCurrentDateInput);
                    bundle.putString(MainActivity.DATE_INFO, dateInfo);
                    ArrayList<String> exerciseInfo = new ArrayList<>();
                    exerciseInfo.add(Integer.toString(TypeConverters.exerciseTypeToInt(mExerciseTypeInput)));
                    exerciseInfo.add(currentAvailableExercise.getMExerciseName());
                    bundle.putStringArrayList(MainActivity.EXERCISE_INFO, exerciseInfo);
                    NavDestination currentDestination = Navigation.findNavController(view).getCurrentDestination();
                    if (currentDestination != null && currentDestination.getId() == R.id.navigation_search_exercise) {
                        Navigation.findNavController(view).navigate(R.id.to_session, bundle);
                    }
                } else if (view.getId() == R.id.available_exercise_favorite_iv) {
                    currentAvailableExercise.setFavorite(!currentAvailableExercise.isFavorite());
                    mViewModel.update(currentAvailableExercise);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onLongClick(View view, int position) {
                return false;
            }
        });
        mAvailableExerciseRV.setAdapter(mAdapter);

        // Observe live data
        mViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        mViewModel.getAllAvailableExercises(mExerciseTypeInput).observe(getViewLifecycleOwner(), (List<AvailableExerciseItem> availableExerciseItems) -> {
            mAdapter.setFullList(availableExerciseItems);
            mAdapter.submitList(availableExerciseItems);
        });
    }

    // Setup menu options
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_exercise_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_menu_item);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
