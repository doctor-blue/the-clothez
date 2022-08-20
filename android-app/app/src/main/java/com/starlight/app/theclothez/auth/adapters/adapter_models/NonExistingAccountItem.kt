package com.starlight.app.theclothez.auth.adapters.adapter_models

import com.starlight.app.theclothez.others.Constant.CREATE_NEW_ACCOUNT_TYPE

data class NonExistingAccountItem(
    val id: Int = CREATE_NEW_ACCOUNT_TYPE
) : Equatable {
    override fun viewType(): Int {
        return id
    }
}