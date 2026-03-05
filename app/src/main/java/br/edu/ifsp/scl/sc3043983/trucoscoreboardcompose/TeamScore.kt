package br.edu.ifsp.scl.sc3043983.trucoscoreboardcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamScore(
    name: String,
    score: Int,
    onAddOne: () -> Unit,
    onAddThree: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = name, color = Color.White, fontSize = 20.sp)
        Text(
            text = score.toString(),
            color = Color.White,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            ScoreButton(text = "+1", onClick = onAddOne)
            ScoreButton(text = "+3", onClick = onAddThree)
        }
    }
}