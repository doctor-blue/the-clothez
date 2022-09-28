package com.starlight.app.theclothez.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starlight.module.domain.model.Category
import com.starlight.module.domain.repository.CategoryRepository
import com.starlight.module.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _categoryLiveData: MutableLiveData<DataState<List<Category>>> =
        MutableLiveData(DataState.Loading())

    val categoryLiveData: LiveData<DataState<List<Category>>> = _categoryLiveData

    fun getCategory(gender: Int) {
        viewModelScope.launch {
            categoryRepository.getCategoryByGender(gender).onEach {
                _categoryLiveData.value = it
            }.collect()
        }
    }

}