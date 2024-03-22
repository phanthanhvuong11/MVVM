package com.example.androidbaseproject.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.androidbaseproject.arch.extensions.LoadingAware
import com.example.androidbaseproject.arch.extensions.ViewErrorAware
import com.example.androidbaseproject.arch.extensions.collectFlow
import com.example.androidbaseproject.arch.extensions.hideKeyboard
import com.example.androidbaseproject.data.error.ErrorModel
import com.example.androidbaseproject.ui.widget.CustomProgressDialog
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow

/**
 *
 * @author vuongphan
 */
abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {

    protected open val viewModel: ViewModel? = null

    private val progressDialog by lazy {
        CustomProgressDialog.newInstance()
    }

    internal fun handleCommonError(errorModel: ErrorModel) {
        //Handle Logic
    }

    abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                hideKeyboardWhenTapOutsideEditText(ev).run {
                    return if (this) {
                        this
                    } else {
                        super.dispatchTouchEvent(ev)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboardWhenTapOutsideEditText(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view is EditText) {
            val rect = Rect()
            view.getGlobalVisibleRect(rect)

            val bound = IntArray(2)
            view.getLocationOnScreen(bound)
            val x = ev.rawX + view.getLeft() - bound[0]
            val y = ev.rawY + view.getTop() - bound[1]

            if (!rect.contains(ev.x.toInt(), ev.y.toInt())
                || x < view.getLeft() || x > view.getRight()
                || y < view.getTop() || y > view.getBottom()
            ) {
                hideKeyboard(this)
                view.clearFocus()
                return true
            }
        }
        return false
    }

    protected fun handleLoading(viewModel: ViewModel?) {
        viewModel?.let {
            if (it is ViewErrorAware) {
                collectFlow(it.viewErrorFlow) { errorModel ->
                    handleCommonError(errorModel)
                }
            }
            if (it is LoadingAware) {
                collectFlow(it.loadingFlow) { isShow ->
                    handleProgressDialogStatus(isShow)
                }
            }
        }
    }

    protected fun handleProgressDialogStatus(isShow: Boolean) {
        if (isShow) {
            progressDialog.show(
                supportFragmentManager,
                CustomProgressDialog::class.java.simpleName
            )
        } else {
            progressDialog.dismissAllowingStateLoss()
        }
    }
}
