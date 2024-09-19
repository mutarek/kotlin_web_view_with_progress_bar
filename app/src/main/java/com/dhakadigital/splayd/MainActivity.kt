package com.dhakadigital.splayd

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize the WebView and ProgressBar
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Load a webpage
        webView.loadUrl("https://splayd.com.bd")

        // Handle the progress of WebView
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    progressBar.visibility =
                        View.GONE // Hide progress bar when page is fully loaded
                } else {
                    progressBar.visibility = View.VISIBLE // Show progress bar while loading
                    progressBar.progress = newProgress // Update progress
                }
            }
        }

        // Handle WebView navigation (make sure links open in the WebView, not in a browser)
        webView.webViewClient = WebViewClient()
    }

        // Back button handling for WebView history
        override fun onBackPressed() {
            if (webView.canGoBack()) {
                webView.goBack() // Navigate back within the WebView
            } else {
                super.onBackPressed() // Exit app if no history left
            }
        }
}