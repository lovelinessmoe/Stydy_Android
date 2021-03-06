package xyz.javaee.study.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jzvd.JzvdStd;
import xyz.javaee.study.Activity.MainActivity;
import xyz.javaee.study.Activity.VideoLearnActivity;
import xyz.javaee.study.Adapter.HomePhotoAdapter;
import xyz.javaee.study.R;

public class HomeFragment extends Fragment implements OnItemClickListener {
    private View mRootView;

    ConvenientBanner mBanner;

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    HomePhotoAdapter myAdapter;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startTurning(3000);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mData = new ArrayList<>();
        mData.add("推荐");
        initView();

    }

    List<String> networkImage = new ArrayList<>();
    private String[] images =
            {"https://picb9.photophoto.cn/40/165/40165839_1.jpg",
            "https://picb1.photophoto.cn/40/165/40165891_1.jpg",
            "https://picb1.photophoto.cn/40/165/40165891_1.jpg",
            "https://picb3.photophoto.cn/40/289/40289883_1.jpg",
            "https://picb8.photophoto.cn/40/242/40242528_1.jpg",
            "https://picb6.photophoto.cn/40/235/40235256_1.jpg",
            "https://picb8.photophoto.cn/40/098/40098498_1.jpg"
    };

    private List<String> mData;

    private void initView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.rv_header_banner, null);
        mBanner = (ConvenientBanner) header.findViewById(R.id.banner);
        //设置高度是屏幕1/4
        mBanner.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight()/3));

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_content);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initBanner();

        myAdapter = new HomePhotoAdapter();
        myAdapter.addDatas(mData);

        myAdapter.setmHeaderView(mBanner);
        mRecyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new HomePhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
            }
        });


        //初始化播放器
        JzvdStd jzvdStd = getActivity().findViewById(R.id.jz_video);
        //设置路径
        String videoURL = "http://vfile.9mededu.com/met_video/480P/20210721143724.mp4";
        jzvdStd.setUp(videoURL, "默认视频", JzvdStd.SCREEN_NORMAL);
        Glide.with(getActivity().getApplicationContext()).load("https://picb2.photophoto.cn/36/507/36507192_1.jpg").into(jzvdStd.posterImageView);

    }

    private void initBanner(){
        networkImage = Arrays.asList(images);

        mBanner.setPages(new CBViewHolderCreator<NetWorkImageHolderView>() {
            @Override
            public NetWorkImageHolderView createHolder() {
                return new NetWorkImageHolderView();
            }
        }, networkImage)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT) //设置指示器的方向（左、中、右）
                .setPageIndicator(new int[] { R.drawable.indicator_gray, R.drawable.indicator_red })//设置指示器样式
                .setOnItemClickListener(this)//点击事件
                .setScrollDuration(1500);//滑动的时间
    }

    @Override
    public void onItemClick(int position) {//Banner 点击事件
        System.out.printf("你干尼玛呢");
    }
}

class NetWorkImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_header_img, null);
        imageView = (ImageView) view.findViewById(R.id.iv_head);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher_round).into(imageView);
    }
}
