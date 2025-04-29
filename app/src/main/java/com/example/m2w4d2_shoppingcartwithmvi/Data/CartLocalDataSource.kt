package com.example.m2w4d2_shoppingcartwithmvi.Data

import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class CartLocalDataSource {
    private val cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    fun getCartItems(): Flow<List<CartItem>> = cartItems

    suspend fun addItem(cartItem: CartItem) {
        val updatedList = cartItems.value.toMutableList()
        updatedList.add(cartItem)
        cartItems.emit(updatedList)
    }

    suspend fun removeItem(itemId: UUID) {
        val updatedList = cartItems.value.filter { it.id != itemId }
        cartItems.emit(updatedList)
    }

    suspend fun updateItemQuantity(itemId: UUID, newQuantity: Int) {
        val updatedList = cartItems.value.map {
            if (it.id == itemId) it.copy(quantity = newQuantity) else it
        }
        cartItems.emit(updatedList)
    }
}