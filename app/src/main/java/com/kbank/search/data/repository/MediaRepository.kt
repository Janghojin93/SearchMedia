package com.kbank.search.data.repository

import PAGE_SIZE
import com.kbank.search.data.local.media.MediaDao
import com.kbank.search.data.remote.ApiServices
import com.kbank.search.data.remote.model.DocumentImageResponse
import com.kbank.search.data.remote.model.SearchImageResponse
import com.kbank.search.data.remote.model.SearchVideoResponse
import com.kbank.search.model.Media
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface MediaRepository {
    fun getAllMedias(query: String): Flow<Resource<List<DocumentImageResponse>>>
}

@ExperimentalCoroutinesApi
class DefaultMediaRepository @Inject constructor(
    private val mediaService: ApiServices
) : MediaRepository {

    override fun getAllMedias(query: String): Flow<Resource<List<DocumentImageResponse>>> {
        return object :
            NetworkBoundRepository<List<DocumentImageResponse>, SearchImageResponse, SearchVideoResponse>() {

            //50 페이지 이하
            override suspend fun fetchFromRemoteImage(): Response<SearchImageResponse> =
                mediaService.getImages(query, 1, PAGE_SIZE)

            //15 페이지 이하, 30사이즈 이하
            override suspend fun fetchFromRemoteVideo(): Response<SearchVideoResponse> =
                mediaService.getVideos(query, 1, PAGE_SIZE)

        }.asFlow()
    }


}
