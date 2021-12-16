package xyz.javaee.study.Fragment;

import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.javaee.study.Adapter.LearnAdapter;
import xyz.javaee.study.R;

public class LearnFragment extends Fragment {

    private View root;
    private RecyclerView rv_course;
    private List<Integer> Data;
    private int[] img;
    private LearnAdapter learnAdapter;


    //popwindow
    private LearnFragment context = null;
    private PopupWindow popupWindow;
    private int from = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_learn, container, false);
        return root;
    }

    private void initData() {
        Data = new ArrayList<>();
        for (int i = 1; i < 11; i++)
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


    View.OnClickListener popClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.catalogue: {
                    from = Location.BOTTOM.ordinal();
                    break;
                }
            }

            //调用此方法，menu不会顶置
            //popupWindow.showAsDropDown(v);
            initPopupWindow();
        }
    };

    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

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

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
				/*if( popupWindow!=null && popupWindow.isShowing()){
					popupWindow.dismiss();
					popupWindow=null;
				}*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        Button open = (Button) popupWindowView.findViewById(R.id.open);
        Button save = (Button) popupWindowView.findViewById(R.id.save);
        Button close = (Button) popupWindowView.findViewById(R.id.close);


        open.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Open", Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 菜单弹出方向
     */
    public enum Location {

        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        rv_course = getActivity().findViewById(R.id.courseList);
        rv_course.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        learnAdapter = new LearnAdapter(getActivity().getApplicationContext(), img);
        rv_course.setAdapter(learnAdapter);

        //跳转视频播放页
        ImageView imgPlay = root.findViewById(R.id.playButton);
        imgPlay.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("xyz.javaee.videoPlay");
            startActivity(intent);
        });

        //popwindow
        context = this;
        ImageView imgCatalogue = (ImageView) getActivity().findViewById(R.id.catalogue);
        imgCatalogue.setOnClickListener(popClick);
    }
}
