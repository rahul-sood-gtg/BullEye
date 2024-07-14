package com.rahul.bullseye

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.bullseye.ui.theme.BullsEyeTheme
import kotlin.math.abs
import kotlin.random.Random

@Composable
@Preview

fun GameScreen(){
    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableStateOf(0.5f) }
    val sliderToInt = (sliderValue * 100).toInt()
    val targetValue by rememberSaveable { mutableStateOf( Random.nextInt(1,100) ) }

    fun pointsForCurrentRound() :Int{
        val maxScore =100
        val difference = abs( targetValue-sliderToInt)

        return maxScore-difference
    }
    Log.i("ALERT VISIBLE", alertIsVisible.toString())

    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {Spacer(modifier = Modifier.weight(.5f))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(9f))
        {


           GamePrompt(targetValue= targetValue)
             TargetSlider( value = sliderValue,
                 valueChanged = { value ->
                     sliderValue = value}
             )
             Button(onClick = {
               alertIsVisible= true


              }) {
                Text(stringResource(R.string.btn_HitMe))

            }
        }
        Spacer(modifier = Modifier.weight(.5f))
        if(alertIsVisible){
            Log.i("ALERT VISIBLE", alertIsVisible.toString())

          ResultDialog( hideDialog = {alertIsVisible =false},
                  sliderValue = sliderToInt,
              points =pointsForCurrentRound()
           )


        }
    }


}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    BullsEyeTheme {
        GameScreen()
    }
}