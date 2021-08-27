package dev.kibet.movetechtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kibet.movetechtest.data.repository.Repository
import dev.kibet.movetechtest.others.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _sendSmsStatus = MutableLiveData<Resource<ResponseBody>>()
    val sendSmsStatus: LiveData<Resource<ResponseBody>> = _sendSmsStatus

    fun sendSms(
        username: String,
        api_key: String,
        sender: String,
        sendTo: String,
        message: String,
        msgtype: String,
        delivery: String
    ) {
        _sendSmsStatus.postValue(Resource.loading(null))
        if (username.isNullOrEmpty() || api_key.isNullOrEmpty() || sender.isNullOrEmpty() ||
            sendTo.isNullOrEmpty() || message.isNullOrEmpty() || msgtype.isNullOrEmpty() ||
            delivery.isNullOrEmpty()
        ) {
            _sendSmsStatus.postValue(Resource.error("Please fill all the fields", null))
            return
        }
        viewModelScope.launch {
            val result = repository.sendSms(
                username = username,
                api_key = api_key,
                sender = sender,
                to = sendTo,
                message = message,
                msgtype = msgtype,
                dlr = delivery
            )
            _sendSmsStatus.postValue(result)
        }
    }
}