/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.kbank.search.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.kbank.search.data.remote.model.DocumentImageResponse
import com.kbank.search.data.remote.model.SearchImageResponse
import com.kbank.search.data.remote.model.SearchVideoResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT, REQUEST, REQUEST2> {

    fun asFlow() = flow<Resource<List<DocumentImageResponse>>> {

        // Fetch latest posts from remote
        val apiResponseMedia = fetchFromRemoteImage()
        // Parse body
        val remotePostsMedias = apiResponseMedia.body()
        // Check for response validation
        if (apiResponseMedia.isSuccessful && remotePostsMedias != null) {
            // Save posts into the persistence storage
        } else {
            // Something went wrong! Emit Error state.
            emit(Resource.Failed(apiResponseMedia.message()))
        }

        emit(
            Resource.Success(remotePostsMedias!!.documents)
        )


    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error! Can't get latest posts."))
    }


    @WorkerThread
    protected abstract suspend fun fetchFromRemoteImage(): Response<SearchImageResponse>

    @WorkerThread
    protected abstract suspend fun fetchFromRemoteVideo(): Response<SearchVideoResponse>

}
