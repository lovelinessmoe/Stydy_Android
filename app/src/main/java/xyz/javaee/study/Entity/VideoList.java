package xyz.javaee.study.Entity;

import java.util.ArrayList;

public class VideoList extends Video{
    //子节点
    private ArrayList<Video> child;

    public VideoList(Video video,ArrayList<Video> child){
        super(video.getLevel(),video.getId(),video.getP_id(),video.getName(),video.getURL());
        this.child = child;
    }

    public ArrayList<Video> getChild() {
        return child;
    }

    public void setChild(ArrayList<Video> child) {
        this.child = child;
    }
}
