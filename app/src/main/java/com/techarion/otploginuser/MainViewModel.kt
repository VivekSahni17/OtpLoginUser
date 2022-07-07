package com.techarion.otploginuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techarion.otploginuser.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val otpRepository: OtpRepository):ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse2>>
        get() = otpRepository.userResponseLiveData


    fun sendOtp(user: User){
        viewModelScope.launch {
            otpRepository.sendOtp(user)
        }
    }
}