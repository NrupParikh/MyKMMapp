package com.nrup.mykmmapp.auth.domain.repository

import com.nrup.mykmmapp.auth.domain.model.PostResultData


internal interface PostRepository {
    suspend fun getAllPosts(
    ): com.nrup.mykmmapp.common.util.Result<List<PostResultData>>
}