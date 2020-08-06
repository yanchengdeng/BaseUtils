package deng.yc.baseutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_open_web_view.*

class OpenWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_web_view)



        webview.loadUrl("file:///android_asset/www/index.html")
    }
}