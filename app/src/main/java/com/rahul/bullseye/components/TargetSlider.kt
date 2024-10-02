package com.rahul.bullseye.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rahul.bullseye.R

@Composable
fun TargetSlider(modifier: Modifier = Modifier , value: Float =0.5f , valueChanged: (Float) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier= modifier) {

        Text(
            stringResource(id= R.string.Min_Value), textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 15.dp)
        )

        Slider(
            value = value, valueRange = 0.01f..1f,
            onValueChange = valueChanged,
            modifier = Modifier.weight(1f))
        Text(
            stringResource(R.string.Max_Value),
            textAlign = TextAlign.Center, // New Code
            modifier = Modifier.padding(start = 15.dp))
    }

}