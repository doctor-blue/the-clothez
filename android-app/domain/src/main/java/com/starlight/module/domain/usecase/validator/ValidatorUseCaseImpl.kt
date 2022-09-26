package com.starlight.module.domain.usecase.validator

import android.text.TextUtils
import android.util.Patterns

class ValidatorUseCaseImpl : ValidatorUseCase {

    override fun invoke(email: String, password: String): Boolean {
        return validateEmail(email) && validatePassword(password)
    }

    private fun validateEmail(email: String): Boolean {
        return TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return TextUtils.isEmpty(password)
    }
}