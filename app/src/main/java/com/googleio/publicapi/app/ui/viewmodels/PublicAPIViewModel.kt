package com.googleio.publicapi.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PublicAPIViewModel {
    private val _clickedAPI : MutableLiveData<String> = MutableLiveData("")
    val clickedAPI : LiveData<String> = _clickedAPI


}