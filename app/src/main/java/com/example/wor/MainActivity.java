package com.example.wor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity {
    // Fab state fields
    private boolean mFabVisible;
    private boolean mFabExpanded;
    private Animation fab_open, fab_close, fab_clockwise, fab_anticlockwise;
    // UI fields
    private FloatingActionButton mAddFAB;
    private FloatingActionButton mCalisthenicFAB;
    private FloatingActionButton mStrengthFAB;
    private FloatingActionButton mCardioFAB;
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);
        // Initialize fields and variables
        mBottomNav = findViewById(R.id.nav_view);
        mAddFAB = findViewById(R.id.workout_add_fab);
        mCalisthenicFAB = findViewById(R.id.calisthenics_session_fab);
        mStrengthFAB = findViewById(R.id.strength_session_fab);
        mCardioFAB = findViewById(R.id.cardio_session_fab);
        // Animations
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clockwise);
        fab_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlockwise);
        // Close fab submenus initially
        closeSubMenusFab();
        //Set up Bottom Nav
        // Navigation fields
        NavController mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);
        mNavController.addOnDestinationChangedListener((@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) ->{
            if (destination.getId() == R.id.navigation_to_workout_today || destination.getId() == R.id.navigation_to_workout_another_day){
                showFloatingActionButton();
            } else {
                if(mFabVisible) hideFloatingActionButton();
            }
            if (destination.getId() != R.id.navigation_to_workout_today && destination.getId() != R.id.navigation_to_stats && destination.getId()
                    != R.id.navigation_to_calendar && destination.getId() != R.id.navigation_to_workout_another_day) {
                hideBottomNavigationView();
            } else{
                showBottomNavigationView();
            }
        });
        //on Click Listener
        mAddFAB.setOnClickListener((View view) -> {
            if(mFabExpanded){
                mAddFAB.startAnimation(fab_anticlockwise);
                closeSubMenusFab();
            } else {
                mAddFAB.startAnimation(fab_clockwise);
                openSubMenusFab();
            }
        });
        }


    // Other main activity methods

    public void showFloatingActionButton() {
        if (mAddFAB != null) {
            mAddFAB.startAnimation(fab_anticlockwise);
            mAddFAB.setClickable(true);
            mAddFAB.show();
            mFabVisible = true;
        }
    }

    public void hideFloatingActionButton() {
        if (mAddFAB != null) {
            mAddFAB.startAnimation(fab_clockwise);
            if (mFabExpanded) closeSubMenusFab();
            mAddFAB.hide();
            mAddFAB.setClickable(false);
            mFabVisible = false;
        }
    }

    public void hideBottomNavigationView() {
        mBottomNav.clearAnimation();
        mBottomNav.animate().translationY(mBottomNav.getHeight()).setDuration(300);
    }

    public void showBottomNavigationView() {
        mBottomNav.clearAnimation();
        mBottomNav.animate().translationY(0).setDuration(300);
    }
    // Closes FAB submenus
    private void closeSubMenusFab(){
        mCalisthenicFAB.startAnimation(fab_close);
        mCardioFAB.startAnimation(fab_close);
        mStrengthFAB.startAnimation(fab_close);
        mCalisthenicFAB.setVisibility(View.GONE);
        mCardioFAB.setVisibility(View.GONE);
        mStrengthFAB.setVisibility(View.GONE);
        mCalisthenicFAB.setClickable(false);
        mCardioFAB.setClickable(false);
        mStrengthFAB.setClickable(false);
        mFabExpanded = false;
    }
    // Opens FAB submenus
    private void openSubMenusFab(){
        mCalisthenicFAB.startAnimation(fab_open);
        mCardioFAB.startAnimation(fab_open);
        mStrengthFAB.startAnimation(fab_open);
        mCalisthenicFAB.setVisibility(View.VISIBLE);
        mCardioFAB.setVisibility(View.VISIBLE);
        mStrengthFAB.setVisibility(View.VISIBLE);
        mCalisthenicFAB.setClickable(true);
        mCardioFAB.setClickable(true);
        mStrengthFAB.setClickable(true);
        mFabExpanded = true;
    }
}