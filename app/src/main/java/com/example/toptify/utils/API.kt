package com.example.toptify.utils

import com.example.toptify.tracks.Tracks
import retrofit.*
import retrofit.http.*


interface API {
//    @GET("/authorize")
//    fun login(@Query("client_id") client_id:String,
//              @Query("response_type") response_type:String,
//              @Query("redirect_uri")redirect_uri:String,
//              @Query("scope") scope:String
//    ):Call<LoginResult>

//    @Headers("Content-Type: application/json")
//    @GET("/v1/me/top/artists")
//    fun getArtists(
//        @Query("limit") limit: Int,
//        @Query("offset") offset:Int,
//        @Query("time_range")time_range:String
//        ):Call<Tracks>

    @Headers("Accept: application/json",
        "Content-Type: application/json")
    @GET("/v1/me/top/tracks")
    fun getTracks(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int,
        @Query("offset") offset:Int,
        @Query("time_range")time_range:String
    ):Call<Tracks>

    @POST("/api/token")
    @FormUrlEncoded
    fun requestToken(
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String,
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") uri: String
    ): Call<Token>
}