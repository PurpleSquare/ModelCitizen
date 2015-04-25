package com.ps.citizen3.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawerRecyclerView extends RecyclerView {
    private NavigationDrawerCallbacks callbacks;

    public DrawerRecyclerView(Context context) {
        super(context);
    }

    public DrawerRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setUp(final Activity activity, final DrawerLayout drawerLayout) {
        try {
            callbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }

        // Setup drawer recyclerview
        List<String> tabs = new ArrayList<String>();
        tabs.add("Representatives");
        tabs.add("Elections");
        tabs.add("Bills");
        DrawerAdapter drawerAdapter = new DrawerAdapter(tabs);
        this.setAdapter(drawerAdapter);
        this.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        this.setLayoutManager(layoutManager);
        final GestureDetector gestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    drawerLayout.closeDrawers();
                    selectItem(rv.getChildPosition(child));
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });
    }

    private void selectItem(int position) {
        callbacks.onNavigationDrawerItemSelected(position);
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }
}
