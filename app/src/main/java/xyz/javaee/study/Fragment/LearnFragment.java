package xyz.javaee.study.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import xyz.javaee.study.R;

public class LearnFragment extends Fragment {

    private View root;
    private RecyclerView rv_course;
    private List<Integer> Data;
    private LearnAdapter learnAdapter;
    private int [] img;

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

    class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.MyViewHolder> {
        private final Context context;

        public LearnAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_study,parent,false));
            return holder;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tv.setText("测试"+Data.get(position).toString());
            holder.iv.setImageResource(img[position]);
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.photo_item);
                iv = itemView.findViewById(R.id.message_item);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //跳转视频播放页
        ImageView imgPlay = root.findViewById(R.id.playButton);
        imgPlay.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("xyz.javaee.videoPlay");
            startActivity(intent);
        });

        initData();
        rv_course = getActivity().findViewById(R.id.courseList);
        rv_course.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        learnAdapter = new LearnAdapter(getContext());
        rv_course.setAdapter(learnAdapter);
    }
}
