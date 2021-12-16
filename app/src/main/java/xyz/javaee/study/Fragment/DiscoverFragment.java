package xyz.javaee.study.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import xyz.javaee.study.Adapter.DiscoverAdapter;
import xyz.javaee.study.R;


public class DiscoverFragment extends Fragment {
    private DiscoverAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTableLayout;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new DiscoverAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) getActivity().findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(mAdapter);
        mTableLayout = getActivity().findViewById(R.id.main_tab);
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);

    }

}
