package com.knotenpunkt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.security.Key;

public class NewspaperFragment extends Fragment {
   private View webpagesave;
   private WebView mywebView;
   private boolean showNewspaperFragment = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View webpage = inflater.inflate(R.layout.fragment_newspaper, container, false);
        if (showNewspaperFragment != false) {
            mywebView=(WebView) webpage.findViewById(R.id.webview);
            mywebView.setWebViewClient(new WebViewClient());
            mywebView.loadUrl("https://app.knotenpunkt.info/appnews");
            mywebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
            });
            WebSettings webSettings=mywebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            showNewspaperFragment = false;
            webpagesave = webpage;
            return webpage;
        }
        else {
            return webpagesave;
        }
    }
}
