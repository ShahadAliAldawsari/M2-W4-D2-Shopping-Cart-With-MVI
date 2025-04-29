package com.example.m2w4d2_shoppingcartwithmvi.Domain

import java.util.UUID

data class CartItem(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val quantity: Int
)