package xyz.javaee.study.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.javaee.study.Adapter.LearnAdapter;
import xyz.javaee.study.R;

public class LearnFragment extends Fragment {

    private View root;
    private RecyclerView rv_course;//课程目录RecyclerView
    private List<Integer> Data;//课程目录资源数组
    private int[] img;//课程目录资源数组_图片
    private LearnAdapter learnAdapter;

    //PopWindow
    private PopupWindow popupWindow;
    private int from = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_learn, container, false);
        return root;
    }
    /**
     * 自定义View在fragment中的调用异常问题：
     * 如果view是静态的，就可以在onCreateView中调用
     * 大多数的自定义view，初始化时都需要一个context。而activity是context的子类。
     * 所以在onCreateView方法中，非静态的view初始化调用可能出现异常；对于非静态的view，应该在onActivityCreated方法调用
     */

    private void initData() {//资源数组初始化
        Data = new ArrayList<>();
        for (int i = 1; i < 7; i++)
            Data.add(i);
        img = new int[]{
                R.drawable.study1itemphoto1,
                R.drawable.study1itemphoto2,
                R.drawable.study1itemphoto3,
                R.drawable.study1itemphoto4,
                R.drawable.study1itemphoto5,
                R.drawable.study1itemphoto6
        };
    }


    View.OnClickListener popClick = v -> {
        if (v.getId() == R.id.catalogue) {
            from = Location.BOTTOM.ordinal();
        }

        //调用此方法，menu不会顶置：popupWindow.showAsDropDown(v);
        initPopupWindow();
    };

    class popupDismissListener implements PopupWindow.OnDismissListener {//添加新笔记时弹出PopWindow关闭事件
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }//改回透明度
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void initPopupWindow() {
        View popupWindowView = getLayoutInflater().inflate(R.layout.study1pop, null);
        //内容，高度，宽度
        if (Location.BOTTOM.ordinal() == from) {
            popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        }
        //动画效果
        if (Location.BOTTOM.ordinal() == from) {
            popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        }
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        //宽度
        //popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        //高度
        //popupWindow.setHeight(LayoutParams.FILL_PARENT);
        //显示位置
        if (Location.BOTTOM.ordinal() == from) {
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.fragment_learn, null), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        //设置背景半透明
        backgroundAlpha(0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener((v, event) -> {
            /*if( popupWindow!=null && popupWindow.isShowing()){
                popupWindow.dismiss();
                popupWindow=null;
            }*/
            // 如果返回true，touch事件将被拦截
            // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            return false;
        });

        Button share = popupWindowView.findViewById(R.id.share);
        Button download = popupWindowView.findViewById(R.id.download);
        Button setting = popupWindowView.findViewById(R.id.setting);

        share.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "share", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
            /*
            hide:设置为不可见，不移除
            dismiss:销毁
            cancel:监听，调用dismiss
             */
        });

        download.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "download", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });

        setting.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "setting", Toast.LENGTH_LONG).show();
            popupWindow.dismiss();
        });
    }

    /*
    设置添加屏幕的背景透明度
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 枚举菜单弹出方向
     */
    public enum Location {
//        LEFT,
//        RIGHT,
//        TOP,
        BOTTOM
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //课程列表
        initData();
        rv_course = getActivity().findViewById(R.id.courseList);
        rv_course.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        /**
         * 设置组件垂直布局
         * rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
         * 参数为上下文，布局方向，是否反转
         *
         */
        learnAdapter = new LearnAdapter(getActivity().getApplicationContext(), img);
        rv_course.setAdapter(learnAdapter);
        //添加Android自带的分割线
        rv_course.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


        //跳转视频播放页
        ImageView imgPlay = root.findViewById(R.id.playButton);
        imgPlay.setOnClickListener(view -> {//fragment中onCreateView不能像activity一样地进行通信，设置监听器需要写在onActivityCreated方法中
            Intent intent = new Intent();
            intent.setAction("xyz.javaee.videoPlay");
            startActivity(intent);
        });


        //PopWindow
        ImageView imgCatalogue = getActivity().findViewById(R.id.catalogue);
        imgCatalogue.setOnClickListener(popClick);
    }
}
