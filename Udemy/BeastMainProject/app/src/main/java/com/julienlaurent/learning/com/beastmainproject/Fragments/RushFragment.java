package com.julienlaurent.learning.com.beastmainproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julienlaurent.learning.com.beastmainproject.R;

/**
 * Created by djoum on 7/31/17.
 */

public class RushFragment extends Fragment {
    public static RushFragment newInstance(){
        return new RushFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);
        return rootView;
    }
}
