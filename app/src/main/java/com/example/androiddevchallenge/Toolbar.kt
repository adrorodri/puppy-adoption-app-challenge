package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(title: String) {
    Row(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.Pets,
            "Pets",
            Modifier
                .size(80.dp)
                .padding(0.dp, 0.dp, 20.dp, 0.dp)
        )
        Text(
            text = title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}