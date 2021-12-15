package xyz.javaee.study.Activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.R;

public class VideoLearnActivity extends AppCompatActivity {
    private JCVideoPlayerStandard player = null;
    private String videoURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learn);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://javaee.xyz/video.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //初始化播放器
                player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);

                String json = new String(responseBody, StandardCharsets.UTF_8);
                //FastJson
                System.out.println(json);
                List<Video> videos = JSON.parseArray(json, Video.class);

                System.out.println(videos);
                if (!videos.isEmpty()) {
                    Video video = videos.get(1);
                    //设置路径

                    videoURL = video.getURL();
                    boolean setUp = player.setUp(videoURL, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                    /*if (setUp) {
                        Glide.with(VideoLearnActivity.this).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(player.thumbImageView);
                    }*/

                    //直接进入全屏
//                    player.startFullscreen(this, JCVideoPlayerStandard.class, videoUrl, "");
                    //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
//                    player.startButton.performClick();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.err.println("shibai");
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