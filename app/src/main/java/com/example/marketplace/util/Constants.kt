package com.example.marketplace.util

import com.example.marketplace.data.model.ProductUiModel

object Constants {
    const val LIMIT_PRODUCTS = 20
    val errorProduct = ProductUiModel(
        name = "error",
        description = "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with fancy lace adnored and stylish handle also.(set of 2 piece),Specifications of Angelfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material Fabric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Color Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
        category = listOf(
            "bags, wallets & belts",
            "bags",
            "pouches and potlis",
            "angelfish pouches and potlis",
            "angelfish silk potali potli (multicolor)"
        ),
        price = 999,
        discounted_price = 399,
        images = listOf(
            "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli-silk-potali-original-imaeeprygdj223es.jpeg",
            "http://img5a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli-silk-potali-original-imaeepryxh8xez64.jpeg",
            "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli-silk-potali-original-imaeeprypmwscfp9.jpeg"
        )
    )
}