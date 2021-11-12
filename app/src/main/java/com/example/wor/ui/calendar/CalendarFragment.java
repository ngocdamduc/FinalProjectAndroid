package com.example.wor.ui.calendar;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.wor.R;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.HashSet;
import java.util.Set;

public class CalendarFragment extends Fragment {

    // Input fields
    private final Set<LocalDate> mWorkoutDates = new HashSet<>();

    // UI fields
    private CalendarView mCalendarView;
    private DateTimeFormatter mMonthFormatter;
    private TextView mCalendarMonthTV;
    private TextView mCalendarYearTV;

    // Empty constructor
    public CalendarFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize fields and variables
        View root = inflater.inflate(R.layout.fragment_calendar, container,false);
        mCalendarView = root.findViewById(R.id.calendar_view);
        mCalendarMonthTV = root.findViewById(R.id.calendar_month_tv);
        mCalendarYearTV = root.findViewById(R.id.calendar_year_tv);
        mMonthFormatter = DateTimeFormatter.ofPattern("MMMM");

        // Set boundaries for calendar
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(36);
        YearMonth lastMonth = currentMonth.plusMonths(12);
        mCalendarView.setup(firstMonth, lastMonth, DayOfWeek.SUNDAY);
        mCalendarView.scrollToMonth(currentMonth);
        mCalendarView.setMaxRowCount(6);

        class DayViewContainer extends ViewContainer {

            // Fields
            private CalendarDay mCalendarDay;
            private final TextView mCalendarDayTV;
            private final View mCalendarDotV;
            private final FrameLayout mCalendarDayBackgroundFL;

            private DayViewContainer(@NonNull View view) {
                super(view);
                mCalendarDayBackgroundFL = view.findViewById(R.id.calendar_day_fl);
                mCalendarDayTV = view.findViewById(R.id.calendar_day_tv);
                mCalendarDotV = view.findViewById(R.id.calendar_dot_v);
            }
        }

        // Day binder
        mCalendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @NonNull
            @Override
            public DayViewContainer create(@NonNull View view) {
                return new DayViewContainer(view);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void bind(@NonNull DayViewContainer viewContainer, @NonNull CalendarDay calendarDay) {
                viewContainer.mCalendarDay = calendarDay;
                String day = calendarDay.getDate().getDayOfMonth() + "";
                viewContainer.mCalendarDayTV.setText(day);
                if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                    if (calendarDay.getDate().equals(LocalDate.now())) {
                        viewContainer.mCalendarDayBackgroundFL.setBackground(getResources().getDrawable(R.drawable.today_grid_background, null));
                    } else {
                        viewContainer.mCalendarDayBackgroundFL.setBackground(getResources().getDrawable(R.drawable.white_grid_background, null));
                    }
                } else {
                    if (calendarDay.getDate().equals(LocalDate.now())) {
                        viewContainer.mCalendarDayBackgroundFL.setBackground(getResources().getDrawable(R.drawable.today_grid_background2, null));
                    } else {
                        viewContainer.mCalendarDayBackgroundFL.setBackground(getResources().getDrawable(R.drawable.gray_grid_background, null));
                        viewContainer.mCalendarDayTV.setTextColor(getResources().getColor(R.color.gray, null));
                    }
                }
                if (mWorkoutDates.contains(calendarDay.getDate())) {
                    viewContainer.mCalendarDotV.setVisibility(View.VISIBLE);
                } else {
                    viewContainer.mCalendarDotV.setVisibility(View.GONE);
                }
            }
        });

        // Month scroll listener
        mCalendarView.setMonthScrollListener((CalendarMonth calendarMonth) -> {
            String year = calendarMonth.getYearMonth().getYear() + "";
            mCalendarYearTV.setText(year);
            mCalendarMonthTV.setText(mMonthFormatter.format(calendarMonth.getYearMonth()));
            return null;
        });
        return root;
    }
    // Other methods
}