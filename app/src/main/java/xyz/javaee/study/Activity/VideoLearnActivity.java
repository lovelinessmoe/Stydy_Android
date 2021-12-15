package xyz.javaee.study.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.R;
import xyz.javaee.study.View.MyIjkVideoView;

public class VideoLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_learn);
        //初始化播放器
        MyIjkVideoView ijkVideoView = findViewById(R.id.ijk_video);
        //加载本地库
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        //监听
        ijkVideoView.setListener(new MyIjkVideoView.VideoPlayerListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("video", "完成");
            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("video", "失败");
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.d("video", "准备");

            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://javaee.xyz/video.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody, StandardCharsets.UTF_8);
                //FastJson
                System.out.println(json);
                List<Video> videos = JSON.parseArray(json, Video.class);

                System.out.println(videos);
                if (!videos.isEmpty()) {
                    Video video = videos.get(1);
                    //设置路径
                    ijkVideoView.setVideoPath(video.getURL());
                    //开始加载
                    ijkVideoView.start();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.err.println("shibai");
            }
        });

    }
}