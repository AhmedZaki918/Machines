package com.example.machines.utils

interface OnItemClick {
    fun <T> onClicked(model: T)
    fun <T> onDeleted(model: T)
}