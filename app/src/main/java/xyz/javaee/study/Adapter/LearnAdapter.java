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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_study, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(imgs[position]);
        holder.name.setText("[1 - "+position + "]"+"课程列表测试样例");
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.photo_item);
            name = view.findViewById(R.id.message_item);
        }

    }
}