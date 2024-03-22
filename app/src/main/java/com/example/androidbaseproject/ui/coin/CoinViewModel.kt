package com.example.androidbaseproject.ui.coin

import android.util.Log
import androidx.lifecycle.bindCommonError
import androidx.lifecycle.bindLoading
import com.example.androidbaseproject.arch.extensions.FlowResult
import com.example.androidbaseproject.arch.extensions.onError
import com.example.androidbaseproject.arch.extensions.onSuccess
import com.example.androidbaseproject.data.datasource.CoinRemoteDataSource
import com.example.androidbaseproject.data.model.Coin
import com.example.androidbaseproject.data.repository.CoinRepository
import com.example.androidbaseproject.data.response.TrendingResponse
import com.example.androidbaseproject.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CoinViewModel(private val coinRepository: CoinRepository = CoinRepository(CoinRemoteDataSource.getInstance())) :
    BaseViewModel() {
    private val _coins: MutableStateFlow<List<Coin>> = MutableStateFlow(listOf())
    var coins: StateFlow<List<Coin>> = _coins

    fun getTrending(): Flow<FlowResult<TrendingResponse>> =
        coinRepository.getTrending().bindLoading(this)
            .bindCommonError(this)
            .onSuccess {
                Log.i("XXX", "getTrending: $it")
                _coins.value = it.coins
            }
            .onError {
                Log.i("XXX", "getTrending_Error:${it.message} ")
            }
}