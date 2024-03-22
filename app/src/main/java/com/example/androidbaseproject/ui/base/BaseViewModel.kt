package com.example.androidbaseproject.ui.base

import androidx.lifecycle.ViewModel
import com.example.androidbaseproject.arch.extensions.LoadingAware
import com.example.androidbaseproject.arch.extensions.ViewErrorAware

/**
 *
 * @author vuongphan
 */
open class BaseViewModel : ViewModel(), ViewErrorAware, LoadingAware
