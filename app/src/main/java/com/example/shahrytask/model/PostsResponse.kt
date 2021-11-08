package com.example.shahrytask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsResponse(

	@field:SerializedName("PostsResponse")
	val postsResponse: List<PostsResponseItem?>? = null
) : Parcelable

@Parcelize
data class PostsResponseItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("authorId")
	val authorId: Int? = null
) : Parcelable
