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

import xyz.javaee.study.Activity.MainActivity;
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
        for (int i = 0; i < 15; i++){
            mData.add("数据"+i);
        }
        initView();
    }

    List<String> networkImage = new ArrayList<>();
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
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
        //Glide.with(context).load(data.getImgUrl()).into(imageView);
        Log.d("imgUrl", "UpdateUI: "+data);
        Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher_round).into(imageView);
    }
}
