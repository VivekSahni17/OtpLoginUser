package com.techarion.otploginuser


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserResponse2(
    @SerializedName("result") val result: String?,
    @SerializedName("status code") val statusCode: Int?,



)