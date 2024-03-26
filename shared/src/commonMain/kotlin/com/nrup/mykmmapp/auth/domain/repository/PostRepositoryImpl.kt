package com.nrup.mykmmapp.auth.domain.repository

import PostService
import com.nrup.mykmmapp.auth.data.AuthService
import com.nrup.mykmmapp.auth.domain.model.PostResultData
import com.nrup.mykmmapp.common.util.DispatcherProvider
import com.nrup.mykmmapp.common.util.Result
import kotlinx.coroutines.withContext
import toPostResultData

internal class PostRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val postService: PostService
) : PostRepository {
    override suspend fun getAllPosts(): Result<List<PostResultData>> {
        return withContext(dispatcher.io) {
            try {
                val postsResponse = postService.getAllPosts()
                if (postsResponse.data.isEmpty()) {
                    Result.Error(
                        message = postsResponse.errorMessage!!
                    )
                } else {
                    val list = postsResponse.data.map {
                        it.toPostResultData()
                    }
                    Result.Success(data = list)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(message = "We couldn't send your request, try later")
            }
        }
    }
}