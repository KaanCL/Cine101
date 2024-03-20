package com.example.cine101;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.example.cine101.util.Credentials;

public class VideoFragment extends Fragment {
    View view;
    String video_Url;
    String  url;
    private Credentials credentials = new Credentials();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        WebView webView = view.findViewById(R.id.webView);
        url = credentials.getVideo_url();
        System.out.println("Video Url"+url);
        showVideo(url,webView);

        view.findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().remove(VideoFragment.this).commit();
                FrameLayout frameLayout=v.findViewById(R.id.fragmanWidget);
                if (frameLayout != null) {
                    frameLayout.setVisibility(View.INVISIBLE);
                }
            }
        });


        return view;
    }

    public void showVideo(String Url ,WebView webView){
        url = "<html><body style=\"background-color:black; border:none;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + url + "\" title=\"YouTube video oynatıcı\" frameborder=\"0\" style=\"border: none; border-radius: 20px;\" allow=\"accelerometer; autoplay;  encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>";
        webView.loadData(url , "text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }



}