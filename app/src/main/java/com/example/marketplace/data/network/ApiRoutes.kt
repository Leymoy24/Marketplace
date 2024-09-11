package com.example.marketplace.data.network

object ApiRoutes {

    const val BASE_URL = "https://fakeshopapi-l2ng.onrender.com"
    const val PRODUCTS = "/products"
    const val PRODUCTS_CATEGORY = "/products?category="
    const val PRODUCTS_PRICE = "/products?price="
    const val PRODUCTS_PAGINATION_FIRST = "/products?limit="
    const val PRODUCTS_PAGINATION_SECOND = "&page="
    const val PRODUCTS_SELECTED_FIELDS = "/products?fields="
    const val PRODUCTS_SORT = "/products?sort="

    const val USERS = "/users"
    const val USERS_AUTH_LOGIN = "/users/auth/login"
    const val USER_REGISTER = "/app/v1/users"

    const val CARTS = "/carts"
}