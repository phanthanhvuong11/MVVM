package com.example.androidbaseproject.arch.extensions

import android.view.View
import com.example.androidbaseproject.arch.util.DebounceOnClickListener

fun View.onClick(interval: Long = 400L, listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(interval, listenerBlock))
