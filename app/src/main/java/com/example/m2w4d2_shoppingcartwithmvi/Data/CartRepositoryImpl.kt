package com.example.m2w4d2_shoppingcartwithmvi.Data

import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem
import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class CartRepositoryImpl(private val dataSource: CartLocalDataSource) : CartRepository {
    override fun getCartItems(): Flow<List<CartItem>> = dataSource.getCartItems()

    override suspend fun addItem(cartItem: CartItem) = dataSource.addItem(cartItem)

    override suspend fun removeItem(itemId: UUID) = dataSource.removeItem(itemId)

    override suspend fun updateItemQuantity(itemId: UUID, newQuantity: Int) = dataSource.updateItemQuantity(itemId, newQuantity)
}