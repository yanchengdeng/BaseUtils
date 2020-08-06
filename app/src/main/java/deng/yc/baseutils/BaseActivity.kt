package deng.yc.baseutils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import deng.yc.baseutils.util.StatusBarUtils

abstract class BaseActivity : AppCompatActivity() {
    protected val FLAG_PULL_DOWN = 0x98

    protected val FLAG_PULL_UP = 0x99

    protected var rootView: View? = null

    protected var rightTvBtn: TextView? = null

    protected var titleTv: TextView? = null

    protected var mInflater: LayoutInflater? = null


    protected fun initLayoutInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val FRAGMENTS_TAG = "Android:support:fragments"
            savedInstanceState.remove(FRAGMENTS_TAG)
        }
        StatusBarUtils.setWindowStatusBarColor(this)
        super.onCreate(savedInstanceState)
        setContentView()
        rootView = getContentView()
        findView()
        init()
    }

    protected fun getContentView(): View? {
        return null
    }

    protected abstract fun setContentView()

    protected abstract fun findView()

    protected abstract fun init()
}