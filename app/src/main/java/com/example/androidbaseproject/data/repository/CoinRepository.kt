package com.example.androidbaseproject.data.repository

import com.example.androidbaseproject.arch.data.Repository
import com.example.androidbaseproject.arch.extensions.FlowResult
import com.example.androidbaseproject.arch.extensions.safeFlow
import com.example.androidbaseproject.data.datasource.CoinRemoteDataSource
import com.example.androidbaseproject.data.response.TrendingResponse
import kotlinx.coroutines.flow.Flow

class CoinRepository(private val coinRemoteDataSource: CoinRemoteDataSource) : Repository() {
    fun getTrending(): Flow<FlowResult<TrendingResponse>> = safeFlow {
        coinRemoteDataSource.getTrending()
    }
}