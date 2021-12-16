package xyz.javaee.study.Activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cz.msebera.android.httpclient.Header;
import xyz.javaee.study.Adapter.VideoListAdapter;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.R;

public class VideoLearnActivity extends AppCompatActivity {

    private String videoURL = "";
    private ExpandableListView listview = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learn);

        //初始化播放器
        JzvdStd jzvdStd = (JzvdStd) findViewById(R.id.jz_video);
        //设置路径
        videoURL = "http://vfile.9mededu.com/met_video/480P/20210721143724.mp4";
        jzvdStd.setUp(videoURL, "默认视频", JzvdStd.SCREEN_NORMAL);
        Glide.with(VideoLearnActivity.this).load("https://picb2.photophoto.cn/36/507/36507192_1.jpg").into(jzvdStd.posterImageView);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://javaee.xyz/video.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                jzvdStd.startButton.performClick();

                String json = new String(responseBody, StandardCharsets.UTF_8);
                //FastJson
                List<Video> videos = JSON.parseArray(json, Video.class);

                //树列表
                listview = (ExpandableListView) findViewById(R.id.classList);
                listview.setAdapter(new VideoListAdapter(getApplicationContext(), videos, jzvdStd));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.err.println("fail");
            }
        });
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