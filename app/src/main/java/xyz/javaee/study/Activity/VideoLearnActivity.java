package xyz.javaee.study.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import xyz.javaee.study.Fragment.VideoLearn.ContentFragment;
import xyz.javaee.study.Fragment.VideoLearn.IntroduceFragment;
import xyz.javaee.study.Fragment.VideoLearn.NoteFragment;
import xyz.javaee.study.Fragment.VideoLearn.QandAFragment;
import xyz.javaee.study.R;

public class VideoLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learn);

        //初始化播放器
        JzvdStd jzvdStd = findViewById(R.id.jz_video);
        //设置路径
        String videoURL = "http://vfile.9mededu.com/met_video/480P/20210721143724.mp4";
        jzvdStd.setUp(videoURL, "默认视频", JzvdStd.SCREEN_NORMAL);
        Glide.with(VideoLearnActivity.this).load("https://picb2.photophoto.cn/36/507/36507192_1.jpg").into(jzvdStd.posterImageView);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(ContentFragment.newInstance(jzvdStd));
        fragments.add(QandAFragment.newInstance());
        fragments.add(NoteFragment.newInstance());
        fragments.add(IntroduceFragment.newInstance());

        TabLayout mTabLayout = findViewById(R.id.mTabLayout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中某个tab
                replaceFragment(fragments.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //当tab从选择到未选择
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //已经选中tab后的重复点击tab
            }

            private void replaceFragment(Fragment fragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.tab_container, fragment).commit();
            }
        });

        // 2.添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("目录"), true);
        mTabLayout.addTab(mTabLayout.newTab().setText("问答"));
        mTabLayout.addTab(mTabLayout.newTab().setText("笔记"));
        mTabLayout.addTab(mTabLayout.newTab().setText("介绍"));
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }


}