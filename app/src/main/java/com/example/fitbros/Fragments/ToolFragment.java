package com.example.fitbros.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitbros.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment {

    ViewPager toolsViewPager;

    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tool, container, false);

        toolsViewPager = view.findViewById(R.id.toolsViewPager);

        CustomViewPageAdapter viewPageAdapter = new CustomViewPageAdapter(getChildFragmentManager());
        toolsViewPager.setAdapter(viewPageAdapter);

        return view;
    }

    public class CustomViewPageAdapter extends FragmentPagerAdapter {

        public CustomViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: return new Tool_IntentsFragment();
                case 1: return new Tool_RFMFragment();
                case 2: return new Tool_BMIFragment();
                default: return new Tool_IntentsFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
