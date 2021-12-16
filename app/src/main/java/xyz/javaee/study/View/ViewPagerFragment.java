package xyz.javaee.study.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import xyz.javaee.study.R;


public class ViewPagerFragment extends Fragment {

    private int layoutID;

    public ViewPagerFragment(int layoutID) {
        this.layoutID = layoutID;
    }

    public static ViewPagerFragment newInstance(int layoutID) {
        System.out.println(layoutID);
        System.out.printf("R.layout.discover_tab1_layout:"+R.layout.discover_tab1_layout);
        ViewPagerFragment fragment = new ViewPagerFragment(layoutID);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutID, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}