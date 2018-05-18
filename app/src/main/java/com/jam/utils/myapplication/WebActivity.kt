package com.jam.utils.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.webkit.WebView
import kotlinx.android.synthetic.main.web_activity.*

class WebActivity : AppCompatActivity() {

    private val webView by lazy { WebView(this) }

    private val webSettings by lazy { webView.settings }

    private val urlPath = "http://39.107.82.207/wechat/index/xq/img/UHVibGljL3VwbG9hZC8yMDE4LTA0LTA5LzJBQTM3NUQ1QzZCQTExMkM4MzZBNEYwODE0REFDQkI0LmpwZw=="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)

        fl_webContainer.addView(webView,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))


        webSettings.run {
            javaScriptEnabled = true
        }

        webView.run {
            loadUrl(urlPath)
        }
    }


    override fun onPause() {
        if (isFinishing) {
            fullReleaseWebView(webView)
        }
        super.onPause()
    }


    /**
     * 完全释放WebView的内存，完全解决WebView 内存泄漏问题，兼容Android 5.0
     */
    private fun fullReleaseWebView(webView: WebView?) {
        if (webView != null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            if (webView.parent != null) {
                val parent = webView.parent as ViewGroup
                parent?.removeView(webView)
                webView.stopLoading()
                // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                webView.settings.javaScriptEnabled = false
                webView.clearHistory()
                webView.clearView()
                webView.removeAllViews()
                webView.destroy()
            }


        }
    }


}