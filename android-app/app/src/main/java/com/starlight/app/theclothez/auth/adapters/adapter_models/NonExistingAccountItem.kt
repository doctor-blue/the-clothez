package com.starlight.app.theclothez.auth.adapters.adapter_models

import com.starlight.app.theclothez.others.Constant.LOGIN_TO_OTHER_ACCOUNT_TYPE

data class NonExistingAccountItem(
    val id: Int = LOGIN_TO_OTHER_ACCOUNT_TYPE
) : Equatable {
    override fun viewType(): Int {
        return id
    }
}