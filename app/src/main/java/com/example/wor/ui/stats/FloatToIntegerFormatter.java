package com.example.wor.ui.stats;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class FloatToIntegerFormatter extends ValueFormatter {
    // Constructor
    FloatToIntegerFormatter() {
    }
    @Override
    public String getFormattedValue(float value) {
        return "" + ((int) value);
    }
}
