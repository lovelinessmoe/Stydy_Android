package xyz.javaee.study.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import xyz.javaee.study.R;
import xyz.javaee.study.View.ViewPagerFragment;

public class MyAdapter extends FragmentPagerAdapter {
    private int mCount = 2 ;
//    R.layout.discover_tab1_layout
    private int[] mLayouts = new int[]{R.layout.discover_tab1_layout,R.layout.discover_tab2_layout};
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(mLayouts[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "培训课程";
            case 1:
                return "新闻动态";
            default:return "0";
        }
    }
}