package xyz.javaee.study.Fragment.VideoLearn;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cn.jzvd.JzvdStd;
import cz.msebera.android.httpclient.Header;
import xyz.javaee.study.Adapter.VideoListAdapter;
import xyz.javaee.study.Entity.Video;
import xyz.javaee.study.R;

public class ContentFragment extends Fragment {

    private JzvdStd jzvdStd = null;

    public ContentFragment() {
        // Required empty public constructor
    }

    public ContentFragment(JzvdStd jzvdStd) {
        this.jzvdStd = jzvdStd;
    }

    public static Fragment newInstance(JzvdStd jzvdStd) {
        ContentFragment contentFragment = new ContentFragment(jzvdStd);
        return contentFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_learn_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://javaee.xyz/video.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String json = new String(responseBody, StandardCharsets.UTF_8);
                //FastJson
                List<Video> videos = JSON.parseArray(json, Video.class);

                ExpandableListView listview = null;
                //树列表
                listview = getActivity().findViewById(R.id.classList);
                //去除中间的分割线
                listview.setDivider(null);
                listview.setAdapter(new VideoListAdapter(getActivity().getApplicationContext(), videos, jzvdStd));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.err.println("fail");
            }
        });
    }
}