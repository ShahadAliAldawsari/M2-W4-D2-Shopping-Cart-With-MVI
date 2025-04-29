package com.example.m2w4d2_shoppingcartwithmvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.m2w4d2_shoppingcartwithmvi.Data.CartLocalDataSource
import com.example.m2w4d2_shoppingcartwithmvi.Data.CartRepositoryImpl
import com.example.m2w4d2_shoppingcartwithmvi.Domain.AddItemToCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.GetCartItemsUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.RemoveItemFromCartUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Domain.UpdateItemQuantityUseCase
import com.example.m2w4d2_shoppingcartwithmvi.Presentation.CartScreen
import com.example.m2w4d2_shoppingcartwithmvi.Presentation.CartViewModel
import com.example.m2w4d2_shoppingcartwithmvi.ui.theme.M2W4D2_ShoppingCartWithMVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            M2W4D2_ShoppingCartWithMVITheme {
                CartScreen()
            }
        }
    }
}