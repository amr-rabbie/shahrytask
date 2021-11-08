package com.example.shahrytask.network

import com.example.shahrytask.model.AuthorsResponse
import com.example.shahrytask.model.AuthorsResponseItem
import com.example.shahrytask.model.PostsResponse
import com.example.shahrytask.model.PostsResponseItem
import com.example.shahrytask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShahryApiService {

    @GET(Constants.Authors_Endpoint)
    suspend fun getAllAuthors(

    ):Response<List<AuthorsResponseItem>>

    @GET(Constants.Posts_Endpoint)
    suspend fun getPostsForAuthor(
        @Query("authorId") authorId:Int
    ):Response<List<PostsResponseItem>>
}