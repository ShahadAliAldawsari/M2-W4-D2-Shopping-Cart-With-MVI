package com.example.m2w4d2_shoppingcartwithmvi.Presentation

import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem
import java.util.UUID

sealed class CartIntent {
    object LoadCart : CartIntent()
    data class AddItem(val item: CartItem) : CartIntent()
    data class RemoveItem(val itemId: UUID) : CartIntent()
    data class UpdateQuantity(val itemId: UUID, val newQuantity: Int) : CartIntent()
}