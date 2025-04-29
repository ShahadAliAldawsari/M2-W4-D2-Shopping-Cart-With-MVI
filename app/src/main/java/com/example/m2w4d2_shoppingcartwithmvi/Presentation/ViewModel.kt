package com.example.m2w4d2_shoppingcartwithmvi.Presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m2w4d2_shoppingcartwithmvi.Data.CartRepositoryImpl
import com.example.m2w4d2_shoppingcartwithmvi.Domain.AddItemToCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem
import com.example.m2w4d2_shoppingcartwithmvi.Domain.GetCartItemsUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.RemoveItemFromCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.UpdateItemQuantityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
    private val updateItemQuantityUseCase: UpdateItemQuantityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state

    private var currentCartItems: List<CartItem> = emptyList()

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.LoadCart -> loadCartItems()
            is CartIntent.AddItem -> addItemToCart(intent.item)
            is CartIntent.RemoveItem -> removeItemFromCart(intent.itemId)
            is CartIntent.UpdateQuantity -> updateItemQuantity(intent.itemId, intent.newQuantity)
        }
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            getCartItemsUseCase().collect { items ->
                currentCartItems = items
                _state.value = CartState.Success(items)
            }
        }
    }

    private fun addItemToCart(item: CartItem) {
        viewModelScope.launch {
            try {
                addItemToCartUseCase(item)
                reloadCart() // reload after adding
            } catch (e: Exception) {
                _state.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun removeItemFromCart(itemId: UUID) {
        viewModelScope.launch {
            try {
                removeItemFromCartUseCase(itemId)
                reloadCart()
            } catch (e: Exception) {
                _state.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun updateItemQuantity(itemId: UUID, newQuantity: Int) {
        viewModelScope.launch {
            try {
                updateItemQuantityUseCase(itemId, newQuantity)
                reloadCart()
            } catch (e: Exception) {
                _state.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun reloadCart() {
        viewModelScope.launch {
            getCartItemsUseCase().collect { items ->
                _state.value = CartState.Success(items)
            }
        }
    }
}