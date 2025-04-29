package com.example.m2w4d2_shoppingcartwithmvi.Domain

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addItem(cartItem: CartItem)
    suspend fun removeItem(itemId: UUID)
    suspend fun updateItemQuantity(itemId: UUID, newQuantity: Int)
}