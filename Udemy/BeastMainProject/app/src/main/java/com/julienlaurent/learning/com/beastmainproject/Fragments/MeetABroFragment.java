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

public class MeetABroFragment extends Fragment {
    public static MeetABroFragment newInstance(){
        return new MeetABroFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meet_a_bro, container, false);
        return rootView;
    }
}
