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

import cz.msebera.android.httpclient.Header;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xyz.javaee.study.Adapter.VideoListAdapter;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.R;

public class VideoLearnActivity extends AppCompatActivity {
    private JCVideoPlayerStandard player = null;
    private String videoURL = "";
    private ExpandableListView listview = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learn);

        AsyncHttpClient client = new AsyncHttpClient();
        //播放器
        client.get("http://javaee.xyz/video.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //初始化播放器
                player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);

                String json = new String(responseBody, StandardCharsets.UTF_8);
                //FastJson
                List<Video> videos = JSON.parseArray(json, Video.class);
                if (!videos.isEmpty()) {
                    //设置路径
                    videoURL = "http://vfile.9mededu.com/met_video/480P/20211130145638.mp4";
                    boolean setUp = player.setUp(videoURL, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                    if (setUp) {
                        Glide.with(VideoLearnActivity.this).load("https://picb2.photophoto.cn/36/507/36507192_1.jpg").into(player.thumbImageView);
                    }
                }

                //树列表
                listview = (ExpandableListView) findViewById(R.id.classList);
                listview.setAdapter(new VideoListAdapter(getApplicationContext(),videos,player));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.err.println("fail");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}