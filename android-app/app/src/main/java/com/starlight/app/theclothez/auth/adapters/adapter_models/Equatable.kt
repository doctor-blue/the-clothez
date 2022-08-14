package com.starlight.app.theclothez.auth.adapters.adapter_models

interface Equatable {
    fun viewType(): Int
    override fun equals(other: Any?): Boolean
}