package com.example.wor.ui.stats;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.wor.MainActivity;
import com.example.wor.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class AddStatDialogFragment extends AppCompatDialogFragment {

    // Input fields
    private Context mContext;
    private String mWeightInput;
    private String mHeightInput;

    // UI fields
    private TextInputEditText mWeightTIET;
    private TextInputEditText mHeightTIET;

    // Listener
    private StatListener mListener;

    // Empty constructor
    public AddStatDialogFragment() {
    }

    // New instance constructor
    static AddStatDialogFragment newInstance(int weight, int height) {
        AddStatDialogFragment fragment = new AddStatDialogFragment();
        Bundle bundle = new Bundle();
        ArrayList<String> statInfo = new ArrayList<>();
        if (weight == MainActivity.EMPTY) {
            statInfo.add("");
        } else {
            statInfo.add("" + weight);
        }
        if (height == MainActivity.EMPTY) {
            statInfo.add("");
        } else {
            statInfo.add(height+"");
        }
        bundle.putStringArrayList(MainActivity.STAT_INFO, statInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Initialize fields and variables
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = View.inflate(mContext, R.layout.fragment_add_stat_dialog,null);
        if (getArguments() != null) {
            ArrayList<String> statInfo = getArguments().getStringArrayList(MainActivity.STAT_INFO);
            if (statInfo != null) {
                mWeightInput = statInfo.get(0);
                mHeightInput = statInfo.get(1);
            }
        }
        mWeightTIET = view.findViewById(R.id.weight_input_tiet);
        mHeightTIET = view.findViewById(R.id.fat_input_tiet);
        MaterialButton saveButton = view.findViewById(R.id.stat_save_btn);
        MaterialButton cancelButton = view.findViewById(R.id.stat_cancel_btn);

        // Update note if there is already input
        if (!mWeightInput.equals("")) {
            mWeightTIET.setText(mWeightInput);
        }
        if (!mHeightInput.equals("")) {
            mHeightTIET.setText((mHeightInput));
        }

        
        // On click listeners
        saveButton.setOnClickListener((View saveButtonView) -> {
            int weight, fat;
            if (Objects.requireNonNull(mWeightTIET.getText()).toString().equals("")) {
                weight = MainActivity.EMPTY;
            } else {
                weight = Integer.parseInt(mWeightTIET.getText().toString());
            }
            if (Objects.requireNonNull(mHeightTIET.getText()).toString().equals("")) {
                fat = MainActivity.EMPTY;
            } else {
                fat = Integer.parseInt(mHeightTIET.getText().toString());
            }
            mListener.sendStat(weight, fat);
            dismiss();
        });
        cancelButton.setOnClickListener((cancelButtonView) -> dismiss());

        // Create view
        builder.setView(view)
                .setTitle(getResources().getString(R.string.update_stat_title));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        try {
            mListener = (StatListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    // Note listener interface
    public interface StatListener {
        void sendStat(int weight, int height);
    }
}
