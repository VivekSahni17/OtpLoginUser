package com.techarion.otploginuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class OtpRepository @Inject constructor(private val userAPi: UserAPi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse2>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse2>>
        get() = _userResponseLiveData


    suspend fun sendOtp(user: User){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPi.sendOtp(user)
        handleResponse(response)
    }


    private fun handleResponse(response: Response<UserResponse2>) {
        try {
            if (response.isSuccessful && response.body() != null) {
                _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            } else {
                _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e:Exception){

        }
    }
}