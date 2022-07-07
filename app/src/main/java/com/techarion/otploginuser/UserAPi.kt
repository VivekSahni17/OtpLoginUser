package com.techarion.otploginuser



import retrofit2.Response
import retrofit2.http.*


interface UserAPi {

   // @FormUrlEncoded
    //@Headers("Accept: application/json")
    @Headers("Content-Type:application/json")
    @POST("user-phone-check/")
    //suspend fun sendOtp(@Field("phone") phone:String): Response<UserResponse2>
    suspend fun sendOtp(@Body user: User):Response<UserResponse2>



}