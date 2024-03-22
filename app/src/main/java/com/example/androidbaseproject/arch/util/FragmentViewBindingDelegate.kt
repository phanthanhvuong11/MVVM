package com.example.androidbaseproject.arch.util

import android.view.View
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.example.androidbaseproject.arch.extensions.ensureMainThread
import com.example.androidbaseproject.ui.base.BaseFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: BaseFragment,
    private val viewBinder: (View) -> T,
    private val disposeEvents: T.() -> Unit = {}
) : ReadOnlyProperty<BaseFragment, T>, LifecycleObserver {

    private inline fun BaseFragment.observeLifecycleOwnerThroughLifecycleCreation(crossinline viewOwner: LifecycleOwner.() -> Unit) {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                viewLifecycleOwnerLiveData.observe(
                    this@observeLifecycleOwnerThroughLifecycleCreation,
                    Observer { viewLifecycleOwner ->
                        viewLifecycleOwner.viewOwner()
                    }
                )
            }
        })
    }

    private var fragmentBinding: T? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disposeBinding() {
        fragmentBinding?.disposeEvents()
        fragmentBinding = null
    }

    init {
        fragment.observeLifecycleOwnerThroughLifecycleCreation {
            lifecycle.addObserver(this@FragmentViewBindingDelegate)
        }
    }

    override fun getValue(thisRef: BaseFragment, property: KProperty<*>): T {
        ensureMainThread()
        val binding = fragmentBinding
        if (binding != null) {
            return binding
        }
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        check(lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) { "Fragment views are destroyed." }
        return viewBinder(thisRef.requireView()).also { fragmentBinding = it }
    }
}
