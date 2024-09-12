package com.example.marketplace.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marketplace.data.model.ProductUiModel
import com.example.marketplace.ui.screen.main.components.CategoriesLazyRow
import com.example.marketplace.ui.screen.main.components.EndlessGrid
import com.example.marketplace.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onNavProductScreen: () -> Unit,
    viewModel: MainViewModel
) {
    val uiState by viewModel.uiScreenState.collectAsState()
    val categoriesUiState by viewModel.categoriesUiState.collectAsState()
    val listOfProducts by viewModel.listOfProducts.collectAsState()
    val listOfSearchedProducts by viewModel.listOfSearchedProducts.collectAsState()
    val listOfCategories by viewModel.listOfCategories.collectAsState()
    val currentCategory by viewModel.currentCategory.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val lazyGridState = rememberLazyGridState()

    var selectedChipIndex by rememberSaveable { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    when (uiState) {
        is MainUiState.Success<*> -> {
            viewModel.changeMainUiState(MainUiState.Initial)
        }

        is MainUiState.Error -> {
            LaunchedEffect(uiState) {
                val result = snackbarHostState
                    .showSnackbar(
                        message = "Произошла ошибка при загрузке данных!",
                        actionLabel = "Повторить",
                        duration = SnackbarDuration.Indefinite,
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.getAllProducts()
                        viewModel.getAllCategories()
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
        }

        else -> {}
    }

    when (categoriesUiState) {
        is CategoriesUiState.Success<*> -> {
            viewModel.changeCategoriesUiState(CategoriesUiState.Initial)
        }

        is CategoriesUiState.Error -> {
            LaunchedEffect(uiState) {
                val result = snackbarHostState
                    .showSnackbar(
                        message = "Произошла ошибка при загрузке данных!",
                        actionLabel = "Повторить",
                        duration = SnackbarDuration.Indefinite,
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.getAllProducts()
                        viewModel.getAllCategories()
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
        }

        else -> {}
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(15.dp),
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    actionColor = Blue
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 1.dp)
        ) {
            CategoriesLazyRow(
                listOfCategories = listOfCategories ?: listOf(),
                isLoading = categoriesUiState is CategoriesUiState.Loading && listOfCategories == null
                        || categoriesUiState is CategoriesUiState.Error,
                selectedChipIndex = selectedChipIndex,
                modifier = Modifier.padding(top = 10.dp),
                onSelectChip = { category, index ->
                    selectedChipIndex = index

                    if (selectedChipIndex == 0) {
                        viewModel.clearListOfSearchedProducts()
                        viewModel.clearCurrentCategory()

                    } else {
                        viewModel.searchProductsByCategory(category = category)
                    }

                    scope.launch {
                        lazyGridState.scrollToItem(index = 0)
                    }
                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(top = 10.dp)
            )

            EndlessGrid(
                lazyGridState = lazyGridState,
                listOfProducts = if (currentCategory == null) listOfProducts else listOfSearchedProducts,
                isLoading = (uiState is MainUiState.Loading && listOfProducts == null)
                        || (categoriesUiState is CategoriesUiState.Loading) || uiState is MainUiState.Error,
                onReachedBottom = {
                    viewModel.getMoreProducts(category = currentCategory)
                },
                onProductClicked = { product: ProductUiModel ->
                    viewModel.setCurrentProduct(product)
                    onNavProductScreen()
                }
            )
        }
    }
}