package com.survivalcoding.network_apps.feature_paging.domain.repository

import androidx.paging.PagingData
import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PagingData<Post>>
}