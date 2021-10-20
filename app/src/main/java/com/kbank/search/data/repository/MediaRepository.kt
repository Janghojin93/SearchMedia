package com.kbank.search.data.repository

import androidx.annotation.MainThread
import com.kbank.search.data.local.media.MediaDao
import com.kbank.search.data.remote.ApiServices
import com.kbank.search.model.Media
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface PostRepository {
    fun getAllPosts(): Flow<Resource<List<Media>>>
}

@ExperimentalCoroutinesApi
class DefaultPostRepository @Inject constructor(
    private val postsDao: MediaDao,
    private val foodiumService: ApiServices
) : PostRepository {

    override fun getAllPosts(): Flow<Resource<List<Media>>> {
        return object : NetworkBoundRepository<List<Media>, List<Media>>() {
            override suspend fun fetchFromRemote(): Response<List<Media>> = foodiumService.getPosts()
        }.asFlow()
    }


}
