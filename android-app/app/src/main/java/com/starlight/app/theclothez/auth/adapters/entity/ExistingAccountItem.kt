package com.starlight.app.theclothez.auth.adapters.entity

import com.starlight.app.theclothez.others.Constant.LOGIN_TO_EXISTING_ACCOUNT_TYPE

data class ExistingAccountItem(
    val accountName: String
) : Equatable {
    override fun viewType() = LOGIN_TO_EXISTING_ACCOUNT_TYPE
}
