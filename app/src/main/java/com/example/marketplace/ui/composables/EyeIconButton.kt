package com.example.marketplace.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.marketplace.R

@Composable
fun EyeIconButton(
    isVisibleEye: Boolean,
    onCLick: () -> Unit
) {
    IconButton(
        onClick = {
            onCLick()
        }
    ) {
        if (isVisibleEye) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.eye_visibility
                ),
                contentDescription = null
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.eye_invisibility
                ),
                contentDescription = null
            )
        }
    }
}