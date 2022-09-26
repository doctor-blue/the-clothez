package com.starlight.module.domain.usecase.validator

interface ValidatorUseCase {
    fun invoke(email: String, password: String): Boolean
}