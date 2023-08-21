package com.example.juanromeronycschools20230821.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juanromeronycschools20230821.api.SchoolData
import com.example.juanromeronycschools20230821.repo.SchoolRepository
import com.example.juanromeronycschools20230821.repo.SchoolsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel @Inject constructor(
    private val repository: SchoolRepository
): ViewModel(){

    private val _list = MutableStateFlow(listOf<SchoolData>())
    val list = _list.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    var dbn = mutableStateOf("")

    fun getDbn(dbn: String){
        this.dbn.value = dbn
    }

    init {
        getSchools()
    }

    fun getSchools(){
        _loading.value = true
        viewModelScope.launch {
            repository.getSchools(object : SchoolsRepoImpl.SchoolsListener{
                override fun onSuccess(list: MutableList<SchoolData>) {
                    _loading.value = false
                    _list.value = list
                    _error.value = false

                    Log.i("jpr","${list.size}")

                }

                override fun onError(e: String) {
                    _error.value = true
                    _loading.value = false
                    Log.i("jpr","error $e")
                }
            })
        }
    }
}