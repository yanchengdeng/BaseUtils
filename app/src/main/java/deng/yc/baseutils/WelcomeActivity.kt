package deng.yc.baseutils

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.jaredrummler.android.widget.AnimatedSvgView


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class WelcomeActivity : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_activity)

        val svgViewGitBub =
            findViewById<View>(R.id.animated_svg_view_github) as AnimatedSvgView

        svgViewGitBub.postDelayed({
            svgViewGitBub.start()
        },1000)

        svgViewGitBub.setOnStateChangeListener{
            when(it){
//                AnimatedSvgView.STATE_FINISHED -> ToastUtils.showShort("动画结束")
            }
        }



        val svgViewGitGoogle =
            findViewById<View>(R.id.animated_svg_view_google) as AnimatedSvgView

        svgViewGitGoogle.postDelayed({
            svgViewGitGoogle.start()
        },1000)

        svgViewGitGoogle.setOnStateChangeListener{
            when(it){
//                AnimatedSvgView.STATE_FINISHED -> ToastUtils.showShort("动画结束")
            }
        }


    }


    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}