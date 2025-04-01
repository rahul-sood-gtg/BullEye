package com.rahul.bullseye.components

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.bullseye.R

@Composable
fun GameDetail(totalScore: Int =0,
               modifier: Modifier = Modifier,
               round: Int = 1,
               onNavigateToAbout: () -> Unit,


               onStartOver:() -> Unit) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement =Arrangement.SpaceEvenly ) {


        FilledIconButton(
            onClick = { onStartOver() },
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            modifier = Modifier.size(50.dp)
                //.background(Color.Red)
              //  .border(2.dp, Color.Black)

        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = stringResource(id = R.string.start_over)

            )
        }

//        Button(onClick = {onStartOver()} ) {
//            Text(stringResource(R.string.start_over))
//        }
        GameInfo(label = stringResource(id = R.string.score_label), value =totalScore)
        GameInfo(label = stringResource(id = R.string.round), value =round)


       FilledIconButton(
           onClick = {  onNavigateToAbout() },
                   colors = IconButtonDefaults.filledIconButtonColors(
                   containerColor = MaterialTheme.colorScheme.tertiaryContainer
                   ),
           modifier = Modifier.size(50.dp)
       )


       {
           Icon(Icons.Filled.Info, contentDescription = stringResource(id = R.string.info))
       }
//        Button(onClick = {}) {
//            Text(stringResource(R.string.info))
//        }
    }

}


@Composable
fun GameInfo(label : String , value : Int =0){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.padding(horizontal = 32.dp)

)
    {
        Text(label)
        Text(value.toString(),style=MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp))

    }
}
@Preview (showBackground = true)
@Composable
fun GameDetailPreview() {
    GameDetail(onStartOver = {} , onNavigateToAbout = {})
}