package xyz.javaee.study.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.Entity.VideoList;
import xyz.javaee.study.R;

public class VideoListAdapter extends BaseExpandableListAdapter {
    private List<VideoList> videosList = null;
    private Context context = null;                    //Context为ExpandableListView所在的Activity，通过构造方法的参数传递进来，可以获得该Activity对应的Inflater
    private LayoutInflater mInflater = null;     //获得Activity的Inflater
    private JCVideoPlayerStandard player;

    public VideoListAdapter(Context context, List<Video> videos, JCVideoPlayerStandard player) {
        //转为数据结构为树形
        ArrayList<VideoList> videoLists = new ArrayList<>();
        for (Video video : videos) {
            ArrayList<Video> child = new ArrayList<>();
            for (Video video1 : videos) {
                //如果他的父节点id和当前的id相同那么就是他的孩子
                if (video1.getP_id() == video.getId()) {
                    child.add(video1);
                }
            }
            //是父节点，插进去
            if (video.getP_id() == 0) {
                videoLists.add(new VideoList(video, child));
            }
        }
        this.videosList = videoLists;
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.player = player;
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return this.videosList.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int i) {
        return this.videosList.get(i).getChild().size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int i) {
        return this.videosList.get(i);
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int i, int i1) {
        return this.videosList.get(i).getChild().get(i1);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    //获得父列表项，与getChildView方法类似
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.video_group_item_layout, null);
        }

        //当前父节点
        VideoList group = (VideoList) this.getGroup(groupPosition);

        TextView className = convertView.findViewById(R.id.className);
        className.setText(group.getName());

        return convertView;
    }

    //获得子列表项
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //convertView is the old view to reuse，使用前需要对convertView判断是否为空
        if (convertView == null) {
            //如果第一次调用则convertView为null，需要获得对应的layout布局文件：子列表项的布局R.layout.child_item_layout
            convertView = mInflater.inflate(R.layout.video_child_item_layout, null);
        }
        //当前子类
        Video child = (Video) this.getChild(groupPosition, childPosition);

        TextView className = convertView.findViewById(R.id.className);
        className.setText(child.getName());

        className.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoURL;
                videoURL = child.getURL();
                player.setUp(videoURL, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                //直接进入全屏
//                player.startFullscreen(context, JCVideoPlayerStandard.class, videoURL, "");
                //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
                player.startButton.performClick();
            }
        });
        return convertView;
    }
}