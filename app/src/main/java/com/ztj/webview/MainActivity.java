package com.ztj.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 隐藏标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 获取 WebView
        WebView web_view = findViewById(R.id.web_view);
        // 加载默认的资源
        web_view.loadUrl("http://web-view.html.ztj.xyz/JavaScript-Bridge.html");
        // 设置桥接对象
        web_view.addJavascriptInterface(this, "bridge");

        // 开发调试 - 清理缓存
        web_view.clearCache(true);

        // 获取 WebSettings
        WebSettings web_settings = web_view.getSettings();
        // 启用 JavaScript
        web_settings.setJavaScriptEnabled(true);
        // 不加载缓存
        web_settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 允许访问文件
        web_settings.setAllowFileAccess(true);
        // 启用 LocalStorage
        web_settings.setDomStorageEnabled(true);
        // 设置默认编码格式
        web_settings.setDefaultTextEncodingName("utf-8");
        // 缩放至屏幕的大小
        web_settings.setLoadWithOverviewMode(true);
        // 支持 ViewPort
        web_settings.setUseWideViewPort(true);
    }

    @JavascriptInterface
    public boolean debug() {
        CharSequence message = "原生调试方法";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }

    @JavascriptInterface
    public String init() {
        return "Android";
    }
}
