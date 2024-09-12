package com.example.marketplace.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun ProductItemShimmerEffect() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(30.dp))
            .shadow(elevation = 10.dp)
            .shimmerEffect(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .shimmerEffect(),
                contentAlignment = Alignment.Center
            ) {

            }
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(30.dp)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(30.dp)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(30.dp)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}