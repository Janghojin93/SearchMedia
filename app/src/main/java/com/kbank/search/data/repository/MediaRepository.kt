package com.kbank.search.data.repository

import com.kbank.search.data.local.media.MediaDao
import com.kbank.search.data.remote.ApiServices
import com.kbank.search.model.Media
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface MediaRepository {
    fun getAllMedias(): Flow<Resource<List<Media>>>
}

@ExperimentalCoroutinesApi
class DefaultMediaRepository @Inject constructor(
    private val mediaService: ApiServices
) : MediaRepository {

    override fun getAllMedias(): Flow<Resource<List<Media>>> {
        return object : NetworkBoundRepository<List<Media>, List<Media>>() {


            override suspend fun fetchFromRemoteImage(response: List<Media>) {
                mediaService.getImages("집",1,50)
            }

            override suspend fun fetchFromRemoteVideo(response: List<Media>) {
                mediaService.getVideos("집",1,50)
            }

            override suspend fun fetchFromRemoteMedia(): Response<List<Media>> = mediaService.getPosts()

        }.asFlow()
    }


}
