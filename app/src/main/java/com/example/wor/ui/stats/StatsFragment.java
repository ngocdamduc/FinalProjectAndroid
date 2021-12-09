package com.example.wor.ui.stats;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wor.MainActivity;
import com.example.wor.R;
import com.example.wor.room.Stat;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StatsFragment extends Fragment implements AddStatDialogFragment.StatListener {

    // Static fields
    private static final String TAG = "StatsFragment";

    // Input fields
    private String mWeightInput;
    private String mHeightInput;
    private boolean mShouldUpdate = false;

    // UI fields
    private MaterialTextView mWeightInputTV;
    private MaterialTextView mHeightInputTV;
    private MaterialButton mUpdateButton;
    private LineChart mWeightChart;
    private LineChart mHeightChart;

    // View model fields
    private StatsViewModel mViewModel;

    public StatsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize fields and variables
        View root = inflater.inflate(R.layout.fragment_stats, container, false);
        MaterialToolbar toolbar = root.findViewById(R.id.stats_tb);
        MaterialTextView currentDateTV = root.findViewById(R.id.stats_current_date_tv);
        mWeightInputTV = root.findViewById(R.id.stats_weight_tv);
        mHeightInputTV = root.findViewById(R.id.stats_height_tv);
        mUpdateButton = root.findViewById(R.id.stats_update_btn);
        mWeightChart = root.findViewById(R.id.weight_chart);
        mHeightChart = root.findViewById(R.id.height_chart);

        // Set current date
        String formattedCurrentDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        currentDateTV.setText(formattedCurrentDate);

        // Setup app tool bar
        if (getActivity() != null) {
            ((MainActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
            } else {
                Log.e(TAG, "onCreateView: Could not get reference to support action bar");
            }
        } else {
            Log.e(TAG, "onCreateView: Could not get reference to activity");
        }
        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // On click listeners
        mUpdateButton.setOnClickListener((View view1) -> startDialogFragment());
        // Observe live data
        mViewModel = new ViewModelProvider(this).get(StatsViewModel.class);
        mViewModel.getAllStats().observe(getViewLifecycleOwner(), (List<Stat> stats) -> {

            // No data formatting
            mWeightChart.setNoDataTextColor(getResources().getColor(R.color.evening_blue, null));
            mWeightChart.setNoDataTextTypeface(getResources().getFont(R.font.open_sans));
            mWeightChart.setNoDataText(getResources().getString(R.string.no_data));

            mHeightChart.setNoDataTextColor(getResources().getColor(R.color.evening_blue, null));
            mHeightChart.setNoDataTextTypeface(getResources().getFont(R.font.open_sans));
            mHeightChart.setNoDataText(getResources().getString(R.string.no_data));

            // If there is no data, show no data text
            if (stats.size() == 0) {
                mWeightInput = MainActivity.EMPTY + "";
                mWeightChart.invalidate();
                mHeightInput = MainActivity.EMPTY + "";
                mHeightChart.invalidate();
                return;
            }

            // Sort stat list
            stats.sort(Comparator.comparing(Stat::getMDate));

            // Check and set data for today
            Stat lastEntry = stats.get(stats.size() - 1);
            if (lastEntry.getMDate().isEqual(LocalDate.now())) {
                mShouldUpdate = true;
                mWeightInput = lastEntry.getMWeight() + "";
                if (lastEntry.getMWeight() != MainActivity.EMPTY) {
                    String displayWeight = mWeightInput + " kg";
                    mWeightInputTV.setText(displayWeight);
                } else {
                    mWeightInputTV.setText(getResources().getString(R.string.none));
                }
                mHeightInput = lastEntry.getMHeight() + "";
                if (lastEntry.getMHeight() != MainActivity.EMPTY) {
                    String displayFat = mHeightInput + "cm";
                    mHeightInputTV.setText(displayFat);
                } else {
                    mHeightInputTV.setText(getResources().getString(R.string.none));
                }
            } else {
                mWeightInput = MainActivity.EMPTY + "";
                mHeightInput = MainActivity.EMPTY + "";
                mWeightInputTV.setText(getResources().getString(R.string.none));
                mHeightInputTV.setText(getResources().getString(R.string.none));
            }

            // Y bounds for charts
            int yWeightMax = 0;
            int yWeightMin = 1000;
            int yHeightMax = 0;
            int yHeightMin = 1000;

            // Create entries
            List<Entry> weightEntries = new ArrayList<>();
            List<Entry> heightEntries = new ArrayList<>();
            List<Stat> weightOnlyList = new ArrayList<>();
            List<Stat> fatOnlyList = new ArrayList<>();

            for (Stat stat : stats) {
                if (stat.getMWeight() != MainActivity.EMPTY) {
                    weightOnlyList.add(stat);
                    weightEntries.add(new Entry(weightOnlyList.size(), stat.getMWeight()));
                    if (stat.getMWeight() > yWeightMax) yWeightMax = stat.getMWeight();
                    if (stat.getMWeight() < yWeightMin) yWeightMin = stat.getMWeight();
                }
                if (stat.getMHeight() != MainActivity.EMPTY) {
                    fatOnlyList.add(stat);
                    heightEntries.add(new Entry(fatOnlyList.size(), stat.getMHeight()));
                    if (stat.getMHeight() > yHeightMax) yHeightMax = stat.getMHeight();
                    if (stat.getMHeight() < yHeightMin) yHeightMin = stat.getMHeight();
                }
            }

            // Initialize integer formatter
            FloatToIntegerFormatter integerFormatter = new FloatToIntegerFormatter();

            // If not empty, set data for chart
            if (weightEntries.size() > 0) {

                // X bounds for chart
                float xWeightMin = weightEntries.get(0).getX() - 1;
                float xWeightMax = weightEntries.get(weightEntries.size() - 1).getX() + 1;
                mWeightChart.getXAxis().setAxisMinimum(xWeightMin);
                mWeightChart.getXAxis().setAxisMaximum(xWeightMax);

                // Create line data sets
                LineDataSet weightLineDataSet = new LineDataSet(weightEntries, "Weight (kg)");

                // Format body weight data set
                weightLineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                weightLineDataSet.setValueFormatter(integerFormatter);
                weightLineDataSet.setLineWidth(3f);
                weightLineDataSet.setValueTextSize(10f);
                weightLineDataSet.setColor(getResources().getColor(R.color.sailor_blue, null));
                weightLineDataSet.setCircleColor(getResources().getColor(R.color.sailor_blue, null));
                weightLineDataSet.setCircleHoleColor(getResources().getColor(R.color.sailor_blue, null));
                weightLineDataSet.setCircleRadius(4f);

                // Create list of data with line data sets
                List<ILineDataSet> weightListOfData = new ArrayList<>();
                weightListOfData.add(weightLineDataSet);

                // Create line data
                LineData weightLineData = new LineData(weightListOfData);

                // Format typeface for line data
                weightLineData.setValueTypeface(getResources().getFont(R.font.open_sans));

                // Apply axis label formatter
                mWeightChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                CustomDateFormatter weightDateFormatter = new CustomDateFormatter(weightOnlyList);
                mWeightChart.getXAxis().setValueFormatter(weightDateFormatter);
                mWeightChart.getXAxis().setTypeface(getResources().getFont(R.font.open_sans));
                mWeightChart.getXAxis().setGranularity(1f);

                // Set Y bounds
                mWeightChart.getAxisLeft().setAxisMinimum(Math.max(yWeightMin - 25, 0));
                mWeightChart.getAxisLeft().setAxisMaximum(yWeightMax + 25);

                // Set max visible range and initial view
                mWeightChart.setVisibleXRangeMaximum(8);
                mWeightChart.setVisibleXRangeMinimum(2);
                mWeightChart.moveViewToX(weightEntries.size() + 3);

                // Other styling
                // No grid lines
                mWeightChart.getAxisRight().setDrawGridLines(false);
                mWeightChart.getXAxis().setDrawGridLines(false);
                mWeightChart.getAxisLeft().setDrawGridLines(false);

                // No left and right axis lines
                mWeightChart.getAxisLeft().setDrawLabels(false);
                mWeightChart.getAxisLeft().setDrawAxisLine(false);
                mWeightChart.getAxisRight().setDrawLabels(false);
                mWeightChart.getAxisRight().setDrawAxisLine(false);

                // No description label
                mWeightChart.getDescription().setEnabled(false);

                // No legend
                mWeightChart.getLegend().setEnabled(false);

                // Disable double tap to zoom
                mWeightChart.setDoubleTapToZoomEnabled(false);

                // Set the data and refresh the chart
                mWeightChart.setData(weightLineData);
                mWeightChart.animateX(500, Easing.EaseInCubic);
            } else {
                mWeightChart.clear();
                mWeightChart.invalidate();
            }
            if (heightEntries.size() > 0) {

                // X bounds for chart
                float xHeightMin = heightEntries.get(0).getX() - 1;
                float xHeightMax = heightEntries.get(heightEntries.size() - 1).getX() + 1;
                mHeightChart.getXAxis().setAxisMinimum(xHeightMin);
                mHeightChart.getXAxis().setAxisMaximum(xHeightMax);

                // Create line data sets
                LineDataSet heightLineDataSet = new LineDataSet(heightEntries, "Height (cm)");

                // Format height data set
                heightLineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                heightLineDataSet.setValueFormatter(integerFormatter);
                heightLineDataSet.setLineWidth(3f);
                heightLineDataSet.setValueTextSize(10f);
                heightLineDataSet.setColor(getResources().getColor(R.color.reply_orange, null));
                heightLineDataSet.setCircleColor(getResources().getColor(R.color.reply_orange, null));
                heightLineDataSet.setCircleHoleColor(getResources().getColor(R.color.reply_orange, null));
                heightLineDataSet.setCircleRadius(4f);

                // Create list of data with line data sets
                List<ILineDataSet> heightListOfData = new ArrayList<>();
                heightListOfData.add(heightLineDataSet);

                // Create line data
                LineData heightLineData = new LineData(heightLineDataSet);

                // Format typeface for line data
                heightLineData.setValueTypeface(getResources().getFont(R.font.open_sans));

                // Apply axis label formatter
                mHeightChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                CustomDateFormatter fatDateFormatter = new CustomDateFormatter(fatOnlyList);
                mHeightChart.getXAxis().setValueFormatter(fatDateFormatter);
                mHeightChart.getXAxis().setTypeface(getResources().getFont(R.font.open_sans));
                mHeightChart.getXAxis().setGranularity(1f);

                // Set Y bounds
                mHeightChart.getAxisLeft().setAxisMinimum(Math.max(yHeightMax - 10, 0));
                mHeightChart.getAxisLeft().setAxisMaximum(yHeightMax + 10);

                // Set max visible range and initial view
                mHeightChart.setVisibleXRangeMaximum(8);
                mHeightChart.setVisibleXRangeMinimum(2);
                mHeightChart.moveViewToX(heightEntries.size() + 3);

                // Other styling
                // No grid lines
                mHeightChart.getAxisRight().setDrawGridLines(false);
                mHeightChart.getXAxis().setDrawGridLines(false);
                mHeightChart.getAxisLeft().setDrawGridLines(false);

                // No left and right axis lines
                mHeightChart.getAxisLeft().setDrawAxisLine(false);
                mHeightChart.getAxisLeft().setDrawLabels(false);
                mHeightChart.getAxisRight().setDrawAxisLine(false);
                mHeightChart.getAxisRight().setDrawLabels(false);

                // No description label
                mHeightChart.getDescription().setEnabled(false);

                // No legend
                mHeightChart.getLegend().setEnabled(false);

                // Disable double tap to zoom
                mHeightChart.setDoubleTapToZoomEnabled(false);

                // Set the data and refresh the chart
                mHeightChart.setData(heightLineData);
                mHeightChart.animateX(500, Easing.EaseInCubic);
            } else {
                mHeightChart.clear();
                mHeightChart.invalidate();
            }

        });
    }
    @Override
    public void sendStat(int weight, int height) {
        if (mShouldUpdate) {
            mViewModel.update(new Stat(LocalDate.now(), weight, height));
        } else {
            mViewModel.insert(new Stat(LocalDate.now(), weight, height));
        }
    }
    // Other methods

    private void startDialogFragment() {
        AddStatDialogFragment addStatDialogFragment = AddStatDialogFragment.newInstance(Integer.parseInt(mWeightInput), Integer.parseInt(mHeightInput));
        addStatDialogFragment.setTargetFragment(this, 1);
        requireActivity().getSupportFragmentManager();
        addStatDialogFragment.show(requireFragmentManager(), "Update Stats");
    }
}