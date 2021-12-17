package xyz.javaee.study.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.javaee.study.R;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ViewHolder> {

    private final Context context;
    private final int[] imgs;

    public LearnAdapter(Context context, int[] img) {
        this.context = context;
        this.imgs = img;
    }

    @NonNull
    @Override
    public LearnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_study, parent, false);//将xml布局添加到View对象
        ViewHolder holder = new ViewHolder(view);//将View对象添加到holder
        /*
        ViewHolder:RecyclerView滚动的时候快速设置值，而不必每次都重新创建很多对象，从而提升性能
        */
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnAdapter.ViewHolder holder, int position) {//将资源添加到holder中的控件对象
        holder.img.setImageResource(imgs[position]);
        holder.name.setText("[1 - " + position + "]" + "课程列表测试样例");
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {//将xml布局中的控件添加到ViewHolder
        ImageView img;
        TextView name;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.photo_item);
            name = view.findViewById(R.id.message_item);
        }

    }
}
