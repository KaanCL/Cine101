package com.example.cine101;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.cine101.util.Credentials;

public class VideoActivity extends AppCompatActivity {

    View view;
    String video_Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        WebView webView = findViewById(R.id.webView);

        System.out.println(Credentials.getVideo_url() + " ");
        video_Url = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+Credentials.getVideo_url()+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-black; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.loadData(video_Url , "text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());



    }
}