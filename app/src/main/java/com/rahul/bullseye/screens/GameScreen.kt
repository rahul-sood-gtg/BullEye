package com.rahul.bullseye.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rahul.bullseye.components.GamePrompt
import com.rahul.bullseye.R
import com.rahul.bullseye.components.GameDetail
import com.rahul.bullseye.components.ResultDialog
import com.rahul.bullseye.components.TargetSlider
import com.rahul.bullseye.ui.theme.BullsEyeTheme
import kotlin.math.abs
import kotlin.random.Random

@Composable
@Preview

fun GameScreen(onNavigateToAbout:() -> Unit){
    fun newTargetValue() = Random.nextInt(1,100)
    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableStateOf(0.5f) }
    val sliderToInt = (sliderValue * 100).toInt()
    var targetValue by rememberSaveable { mutableStateOf( newTargetValue())}
    var totalScore by rememberSaveable { mutableStateOf(0) }
    var currentRound by rememberSaveable { mutableStateOf(1) }

    fun differenceAmount() : Int = abs( targetValue-sliderToInt)



    fun pointsForCurrentRound() :Int{
        var bonus =0
        val maxScore =100

        val difference = differenceAmount()
        if(difference==0){
            bonus =100
        }
        else if(difference ==1){
            bonus += 50
        }

        return (maxScore-difference) + bonus
    }
fun startGameOver() {
    totalScore =0
    currentRound=1
    sliderValue = 0.5f
    targetValue = newTargetValue()
}

    fun alertTitle() : Int{
        val difference = differenceAmount()
        val title: Int = if(difference ==0){
            R.string.alert_title_1
        }
        else if (difference <5){
            R.string.alert_title_2
        }
        else if(difference <=10) {
            R.string.alert_title_3
        }

        else {
            R.string.alert_title_4
        }

        return title
    }



    Log.i("ALERT VISIBLE", alertIsVisible.toString())




    Box {
        Image(modifier = Modifier.fillMaxSize(),
           painter = painterResource(id =R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.background_image)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(9f)
            )
            {


                GamePrompt(targetValue = targetValue)
                TargetSlider(value = sliderValue,
                    valueChanged = { value ->
                        sliderValue = value
                    }
                )
                Button(
                    onClick = {
                        alertIsVisible = true
                        totalScore += pointsForCurrentRound()


                    },

                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(16.dp)


                ) {
                    Text(stringResource(R.string.btn_HitMe))
                }
                GameDetail(
                    totalScore = totalScore,
                    round = currentRound,
                    modifier = Modifier.fillMaxWidth(),
                    onNavigateToAbout = onNavigateToAbout,
                    onStartOver = { startGameOver() })
            }
            Spacer(modifier = Modifier.weight(.5f))
            if (alertIsVisible) {
                Log.i("ALERT VISIBLE", alertIsVisible.toString())


                ResultDialog(hideDialog = { alertIsVisible = false },
                    sliderValue = sliderToInt, dialogTitle = alertTitle(),
                    points = pointsForCurrentRound(),
                    onRoundIncrement = {
                        currentRound += 1
                        targetValue = newTargetValue()
                    }
                )


            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    BullsEyeTheme {
        GameScreen(onNavigateToAbout = {})
    }
}