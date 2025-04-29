package com.example.m2w4d2_shoppingcartwithmvi.Presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.m2w4d2_shoppingcartwithmvi.Data.CartLocalDataSource
import com.example.m2w4d2_shoppingcartwithmvi.Data.CartRepositoryImpl
import com.example.m2w4d2_shoppingcartwithmvi.Domain.AddItemToCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.CartItem
import com.example.m2w4d2_shoppingcartwithmvi.Domain.GetCartItemsUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.RemoveItemFromCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.UpdateItemQuantityUseCase

@Composable
fun CartScreen() {

    val dataSource = CartLocalDataSource()
    val repository = CartRepositoryImpl(dataSource)

    val viewModel = CartViewModel(
        GetCartItemsUseCase(repository),
        AddItemToCartUseCase(repository),
        RemoveItemFromCartUseCase(repository),
        UpdateItemQuantityUseCase(repository)
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CartIntent.LoadCart)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Generate a new CartItem
                    val newItem = CartItem(
                        name = "Item ${(1..100).random()}",
                        quantity = 1
                    )
                    viewModel.handleIntent(CartIntent.AddItem(newItem))
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    )
    { paddingValues ->

        when (state) {
            is CartState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(paddingValues))
            }
            is CartState.Success -> {
                val items = (state as CartState.Success).items

                if (items.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Your cart is empty, try to add some items",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        items(items) { item ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Text("${item.name} x${item.quantity}")
//                            Row {
//                                IconButton(onClick = {
//                                    viewModel.handleIntent(CartIntent.UpdateQuantity(item.id, item.quantity + 1))
//                                }) {
//                                    Text("+")
//                                }
//
//                                // (-) button
//                                IconButton(onClick = {
//                                    if (item.quantity > 1) {
//                                        viewModel.handleIntent(CartIntent.UpdateQuantity(item.id, item.quantity - 1))
//                                    } else {
//                                        viewModel.handleIntent(CartIntent.RemoveItem(item.id))
//                                    }
//                                }) {
//                                    Text("-")
//                                }
//                            }
//                        }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            item.name,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            "Qty: ${item.quantity}",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Row {
                                        IconButton(onClick = {
                                            viewModel.handleIntent(
                                                CartIntent.UpdateQuantity(
                                                    item.id,
                                                    item.quantity + 1
                                                )
                                            )
                                        }) {
                                            Icon(
                                                Icons.Default.KeyboardArrowUp,
                                                contentDescription = "Increase"
                                            )
                                        }

                                        IconButton(onClick = {
                                            if (item.quantity > 1) {
                                                viewModel.handleIntent(
                                                    CartIntent.UpdateQuantity(
                                                        item.id,
                                                        item.quantity - 1
                                                    )
                                                )
                                            } else {
                                                viewModel.handleIntent(CartIntent.RemoveItem(item.id))
                                            }
                                        }) {
                                            Icon(
                                                Icons.Default.KeyboardArrowDown,
                                                contentDescription = "Decrease"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            is CartState.Error -> {
                Text(
                    text = (state as CartState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}