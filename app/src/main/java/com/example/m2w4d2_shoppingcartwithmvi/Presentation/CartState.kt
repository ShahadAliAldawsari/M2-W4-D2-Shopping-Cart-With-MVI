package com.example.m2w4d2_shoppingcartwithmvi.Presentation

import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem

sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<CartItem>) : CartState()
    data class Error(val message: String) : CartState()
}