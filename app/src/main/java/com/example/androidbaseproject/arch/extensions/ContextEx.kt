package com.example.androidbaseproject.arch.extensions

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager

internal fun Context.hideKeyboard(activity: Activity) {
    val focusedView = activity.currentFocus
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(focusedView?.windowToken, 0)
}

internal fun Context.convertDpToPx(dp: Int): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
        resources.displayMetrics
    ).toInt()