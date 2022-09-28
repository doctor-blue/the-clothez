package com.starlight.module.data.repository

import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.cache.dao.CategoryDao
import com.starlight.module.data.datasource.mappers.CategoryMapper
import com.starlight.module.data.datasource.mappers.SubCategoryMapper
import com.starlight.module.data.datasource.remote.services.CategoryService
import com.starlight.module.domain.const.SUCCESS_CODE
import com.starlight.module.domain.model.Category
import com.starlight.module.domain.model.Status
import com.starlight.module.domain.model.SubCategory
import com.starlight.module.domain.repository.CategoryRepository
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.*

class CategoryRepositoryImpl(
    private val authCache: AuthCache,
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper,
    private val categoryDao: CategoryDao,
    private val subCategoryMapper: SubCategoryMapper
) : CategoryRepository {
    override suspend fun getCategoryByGender(gender: Int): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading())
        try {
            val category = categoryDao.getCategoryByGender(gender)
            category.collect {
                if (it.isNotEmpty()) {
                    emit(
                        DataState.Success(
                            categoryMapper.toDomainList(it),
                            Status(SUCCESS_CODE, "Success")
                        )
                    )
                    return@collect
                }
                refreshCategory().catch { ex ->
                    throw  ex
                }
                    .collect { refreshData ->
                        if (refreshData is DataState.Success)
                            emit(DataState.Success(data = refreshData.data?.filter { it.gender == gender }))
                        else
                            emit(refreshData)
                    }
            }

        } catch (e: Exception) {
            emit(
                DataState.Error(
                    status = Status(-1, e.message.toString())
                )
            )
        }
    }

    override suspend fun getSubCategoryByCategoryId(categoryId: String): Flow<DataState<List<SubCategory>>> =
        flow {
            emit(DataState.Loading())
            try {
                val subCategory = getSubCategoryFromLocal(categoryId)
                subCategory.collect {
                    if (it is DataState.Success && it.data.isNullOrEmpty()) {
                        refreshCategory().collect { refreshData ->
                            if (refreshData is DataState.Success)
                                getSubCategoryFromLocal(categoryId).collect { subCategoryState ->
                                    emit(subCategoryState)
                                }
                        }
                        return@collect
                    }
                    emit(it)
                }

            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        status = Status(-1, e.message.toString())
                    )
                )
            }
        }

    private suspend fun getSubCategoryFromLocal(categoryId: String) =
        flow {
            emit(DataState.Loading())
            try {
                val category = categoryDao.getSubCategoryByCategory(categoryId)
                category.collect {
                    if (it.isNotEmpty()) {
                        emit(
                            DataState.Success(
                                subCategoryMapper.toDomainList(it),
                                Status(SUCCESS_CODE, "Success")
                            )
                        )
                        return@collect
                    }
                }

            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        status = Status(-1, e.message.toString())
                    )
                )
            }

        }

    override suspend fun refreshCategory(): Flow<DataState<List<Category>>> = channelFlow {
        try {
            authCache.getToken {
                throw  it
            }.collectLatest { token ->
                if (token?.accessToken == null) {
                    send(
                        DataState.Error(
                            status = Status(-3, "Invalid token")
                        )
                    )
                    return@collectLatest
                }
                val response = categoryService.getCategoryDto(token.accessToken)

                if (response.data != null) {
                    categoryDao.insertCategories(response.data.categories)
                    categoryDao.insertSubCategories(response.data.subCategories)
                    send(DataState.Success(categoryMapper.toDomainList(response.data.categories)))
                    return@collectLatest
                }

                send(DataState.Error(status = response.status.toStatusModel()))
                return@collectLatest
            }


        } catch (e: Exception) {
            send(
                DataState.Error(
                    status = Status(-2, e.message.toString())
                )
            )
        }

    }
}