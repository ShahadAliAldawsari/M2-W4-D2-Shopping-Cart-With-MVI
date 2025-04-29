package com.example.m2w4d2_shoppingcartwithmvi.Domain

import java.util.UUID


class GetCartItemsUseCase(private val repository: CartRepository) {
    operator fun invoke() = repository.getCartItems()
}

class AddItemToCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(cartItem: CartItem) = repository.addItem(cartItem)
}

class UpdateItemQuantityUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(itemId: UUID, newQuantity: Int) {
        repository.updateItemQuantity(itemId, newQuantity)
    }
}

class RemoveItemFromCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(itemId: UUID) {
        repository.removeItem(itemId)
    }
}