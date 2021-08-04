package dev.kibet.movetechtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kibet.movetechtest.data.repository.Repository
import dev.kibet.movetechtest.data.request.Response
import dev.kibet.movetechtest.others.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _sendSmsStatus = MutableLiveData<Resource<Response>>()
    val sendSmsStatus: LiveData<Resource<Response>> = _sendSmsStatus

    fun sendSms(
        username: String,
        api_key: String,
        sender: String,
        to: String,
        message: String,
        msgtype: String,
        dlr: String
    ) {
        viewModelScope.launch {
            val result = repository.sendSms(
                username = username,
                api_key = api_key,
                sender = sender,
                to = to,
                message = message,
                msgtype = msgtype,
                dlr = dlr
            )
            _sendSmsStatus.postValue(result)
        }
    }
}