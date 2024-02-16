package com.uce.moviles.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uce.moviles.data.network.entities.nobel.NobelPrizeX
import com.uce.moviles.logic.usercases.nobel.GetAllNobelPrizesUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class PrincipalViewModels : ViewModel() {

    val listItems = MutableLiveData<List<NobelPrizeX>>()
    val error = MutableLiveData<String>()

    fun getAllNobelPrizes() {
        viewModelScope.launch(Dispatchers.IO) {

            val userCase = GetAllNobelPrizesUserCase()
            val nobelFlow = userCase.invoke(1)

            nobelFlow
                .collect { nobel ->
                    nobel.onSuccess {
                        listItems.postValue(it.toList())
                    }
                    nobel.onFailure {
                        error.postValue(it.message.toString())
                    }
                }
        }
    }
}
