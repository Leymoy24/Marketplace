package com.example.marketplace.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.marketplace.data.model.ProductUiModel

@Composable
fun ProductItem(
    productUiModel: ProductUiModel,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onClick()
            }
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = productUiModel.images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 20.dp, start = 6.dp, end = 6.dp, bottom = 20.dp)
            ) {
                Text(
                    text = productUiModel.name,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "${productUiModel.discounted_price} ₽",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${productUiModel.price} ₽",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        ProductUiModel(
            name = "Angelfish Silk Potali Potli",
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
        ),
        {}
    )
}