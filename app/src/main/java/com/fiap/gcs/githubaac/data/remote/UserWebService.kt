package com.fiap.gcs.githubaac.data.remote

import com.fiap.gcs.githubaac.data.local.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface UserWebService{

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>
}