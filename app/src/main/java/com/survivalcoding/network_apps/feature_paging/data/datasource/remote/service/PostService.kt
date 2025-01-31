package com.survivalcoding.network_apps.feature_paging.data.datasource.remote.service

import com.survivalcoding.network_apps.feature_paging.domain.model.Post
import com.survivalcoding.network_apps.feature_paging.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("/posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") pageSize: Int
    ): List<Post>

    @GET("/users")
    suspend fun getUsers(
        @Query("id") id: List<Int>
    ): List<User>
}