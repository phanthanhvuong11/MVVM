package com.example.androidbaseproject.data.datasource

import com.example.androidbaseproject.arch.extensions.apiCall
import com.example.androidbaseproject.arch.modules.ApiClient
import com.example.androidbaseproject.data.APIService
import com.example.androidbaseproject.data.response.TrendingResponse

/**
 *
 * @author vuongphan
 */
class CoinRemoteDataSource(
    private val api: APIService,
) {
    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance(): CoinRemoteDataSource {
            if (instance == null) {
                val api: APIService = ApiClient.getInstance().apiService
                instance = CoinRemoteDataSource(
                    api
                )
            }

            return instance!!
        }
    }

    suspend fun getTrending(): TrendingResponse = apiCall {
        api.getTrending()
    }
}
