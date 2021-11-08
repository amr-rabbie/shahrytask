package com.example.shahrytask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorsResponse(

	@field:SerializedName("AuthorsResponse")
	val authorsResponse: List<AuthorsResponseItem?>? = null
) : Parcelable

@Parcelize
data class Address(

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
) : Parcelable

@Parcelize
data class AuthorsResponseItem(

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("avatarUrl")
	val avatarUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
