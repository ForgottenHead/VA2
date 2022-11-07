package com.mendelu.xstast12.homework2.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker



@Composable
fun MapCard(marker: Marker) {
    Card(modifier = Modifier
        .padding(bottom = 40.dp)
        .fillMaxWidth(0.7f)
        .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(0.5.dp, Color.Black)
    ) {


        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            Text(text = marker.title!!,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.labelLarge)
            Text(text = marker.snippet!!,
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelMedium)
        }
    }

}

