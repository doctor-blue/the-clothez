package com.starlight.app.theclothez.auth.adapters.entity

interface Equatable {
    fun viewType(): Int
    override fun equals(other: Any?): Boolean
}