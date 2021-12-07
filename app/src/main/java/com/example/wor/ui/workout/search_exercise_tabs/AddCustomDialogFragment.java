package com.example.wor.ui.workout.search_exercise_tabs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wor.MainActivity;
import com.example.wor.R;
import com.example.wor.room.AvailableExerciseItem;
import com.example.wor.room.ExerciseType;
import com.example.wor.room.TypeConverters;
import com.example.wor.ui.workout.WorkoutViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class AddCustomDialogFragment extends AppCompatDialogFragment {

    // Input fields
    private Context mContext;
    private ExerciseType mExerciseTypeInput;

    // UI fields
    private WorkoutViewModel mViewModel;
    private TextInputEditText mExerciseNameTIET;

    // Empty constructor
    public AddCustomDialogFragment() {
    }

    // New instance constructor
    static AddCustomDialogFragment newInstance(ExerciseType exerciseTypeInput) {
        AddCustomDialogFragment fragment = new AddCustomDialogFragment();
        Bundle bundle = new Bundle();
        ArrayList<String> exerciseInfo = new ArrayList<>();
        exerciseInfo.add(Integer.toString(TypeConverters.exerciseTypeToInt(exerciseTypeInput)));
        bundle.putStringArrayList(MainActivity.EXERCISE_INFO, exerciseInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Initialize fields and variables
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = View.inflate(mContext, R.layout.fragment_add_custom_dialog,null);
        if (getArguments() != null) {
            ArrayList<String> exerciseInfo = getArguments().getStringArrayList(MainActivity.EXERCISE_INFO);
            if (exerciseInfo != null) mExerciseTypeInput = TypeConverters.intToExerciseType(Integer.parseInt(exerciseInfo.get(0)));
        }
        mViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        mExerciseNameTIET = view.findViewById(R.id.add_custom_exercise_name_tiet);
        MaterialButton saveButton = view.findViewById(R.id.save_custom_btn);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_custom_btn);

        // On click listeners
        saveButton.setOnClickListener((View saveButtonView) -> {
            if (Objects.requireNonNull(mExerciseNameTIET.getText()).toString().equals("")) {
                mExerciseNameTIET.setError(getString(R.string.toast_error_message));
            } else {
                mViewModel.insert(new AvailableExerciseItem(mExerciseTypeInput, mExerciseNameTIET.getText().toString(), false, true));
                dismiss();
            }
        });
        cancelButton.setOnClickListener((View cancelButtonView) -> dismiss());

        // Create view
        builder.setView(view)
                .setTitle(getResources().getString(R.string.add_exercise));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}