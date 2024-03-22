// Package set to androidx.lifecycle so we can have access to package private methods

@file:Suppress("PackageDirectoryMismatch")

package androidx.lifecycle

import com.example.androidbaseproject.arch.extensions.FlowResult
import com.example.androidbaseproject.arch.extensions.LoadingAware
import com.example.androidbaseproject.arch.extensions.ViewErrorAware
import com.example.androidbaseproject.arch.extensions.onError
import com.example.androidbaseproject.data.error.ErrorModel
import kotlinx.coroutines.flow.*

private const val ERROR_FLOW_KEY = "androidx.lifecycle.ErrorFlow"
private const val LOADING_FLOW_KEY = "androidx.lifecycle.LoadingFlow"

suspend fun <T> T.emitErrorModel(errorModel: ErrorModel) where T : ViewErrorAware, T : ViewModel {
    getErrorMutableSharedFlow().emit(errorModel)
}

val <T> T.viewErrorFlow: SharedFlow<ErrorModel> where T : ViewErrorAware, T : ViewModel
    get() {
        return getErrorMutableSharedFlow()
    }

val <T> T.loadingFlow: StateFlow<Boolean> where T : LoadingAware, T : ViewModel
    get() {
        return loadingMutableStateFlow
    }

private val <T> T.loadingMutableStateFlow: MutableStateFlow<Boolean> where T : LoadingAware, T : ViewModel
    get() {
        val flow: MutableStateFlow<Boolean>? = getTag(LOADING_FLOW_KEY)
        return flow ?: setTagIfAbsent(LOADING_FLOW_KEY, MutableStateFlow(false))
    }

private fun <T> T.getErrorMutableSharedFlow(): MutableSharedFlow<ErrorModel> where T : ViewErrorAware, T : ViewModel {
    val flow: MutableSharedFlow<ErrorModel>? = getTag(ERROR_FLOW_KEY)
    return flow ?: setTagIfAbsent(ERROR_FLOW_KEY, MutableSharedFlow())
}

fun <F, T> Flow<FlowResult<F>>.bindLoading(t: T): Flow<FlowResult<F>> where T : LoadingAware, T : ViewModel {
    return this
        .onStart {
            t.loadingMutableStateFlow.value = true
        }
        .onCompletion {
            t.loadingMutableStateFlow.value = false
        }
}

fun <F, T> Flow<FlowResult<F>>.bindCommonError(t: T): Flow<FlowResult<F>> where T : ViewErrorAware, T : ViewModel {
    return this
        .onError(commonAction = {
            t.emitErrorModel(it)
        })
}
