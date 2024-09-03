package com.example.notyapp.feature_note.domain.util

sealed class OrderNote(val orderType: OrderType) {
    class Title(orderType: OrderType): OrderNote(orderType)
    class Date(orderType: OrderType): OrderNote(orderType)
    class Color(orderType: OrderType): OrderNote(orderType)

    fun copy(orderType: OrderType): OrderNote {
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}