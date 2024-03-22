package com.example.androidbaseproject.arch.extensions

import android.app.Activity
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.androidbaseproject.arch.util.ActivityViewBindingDelegate
import com.example.androidbaseproject.arch.util.FragmentViewBindingDelegate
import com.example.androidbaseproject.arch.util.GlobalViewBindingDelegate
import com.example.androidbaseproject.ui.base.BaseActivity
import com.example.androidbaseproject.ui.base.BaseFragment

inline fun <T : ViewBinding> Activity.viewBinder(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T : ViewBinding> BaseActivity.viewBinding(
    bindingInflater: (LayoutInflater) -> T,
    beforeSetContent: () -> Unit = {}
) = ActivityViewBindingDelegate(this, bindingInflater, beforeSetContent)

fun <T : ViewBinding> BaseFragment.viewBinding(
    viewBindingFactory: (View) -> T,
    disposeEvents: T.() -> Unit = {}
) = FragmentViewBindingDelegate(this, viewBindingFactory, disposeEvents)


fun <T : ViewBinding> viewBinding(viewBindingFactory: (View) -> T) =
    GlobalViewBindingDelegate(viewBindingFactory)

internal fun ensureMainThread() {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        throw IllegalThreadStateException("View can be accessed only on the main thread.")
    }
}
