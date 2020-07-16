package deng.yc.baseutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_trans_top.*

class TransTopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans_top)
        var isToolbarShown = false


        val image = "https://publish-pic-cpu.baidu.com/2c8eee82-f791-46d8-b4ca-786f9f604a23.jpeg@q_90,w_450"

        Glide.with(this).load(image).into(detail_image)

        // scroll change listener begins at Y = 0 when image is fully collapsed
        plant_detail_scrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    toolbar_layout.isTitleEnabled = shouldShowToolbar
                }
            }
        )
    }
}