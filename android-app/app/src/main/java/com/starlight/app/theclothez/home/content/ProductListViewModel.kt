package com.starlight.app.theclothez.home.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starlight.module.domain.model.Product
import com.starlight.module.domain.repository.CategoryRepository
import com.starlight.module.domain.repository.ProductRepository
import com.starlight.module.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _productLiveData: MutableLiveData<DataState<List<Product>>> = MutableLiveData()
    private val productLiveData: LiveData<DataState<List<Product>>> = _productLiveData

    fun refreshProduct() {
        viewModelScope.launch {
            productRepository.getAllProduct().onEach {
                _productLiveData.value = it
            }.collect()
        }
    }

    fun getProductBySubCategory(
        subCategoryId: String,
        onComplete: (products: List<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val list = _productLiveData.value?.data ?: listOf()
            onComplete(
                list.filter { it.subCategoryId == subCategoryId }
            )
        }
    }

    fun getProductByCategory(
        categoryId: String,
        onComplete: (products: List<Product>) -> Unit
    ) {
        viewModelScope.launch {
            categoryRepository.getSubCategoryByCategoryId(categoryId).collectLatest {
                if (it is DataState.Success) {
                    val list = _productLiveData.value?.data ?: listOf()
                }
                if (it is DataState.Error) {
                    onComplete(listOf())
                }
            }
        }
    }


}