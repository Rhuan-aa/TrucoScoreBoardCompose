package br.edu.ifsp.scl.sc3043983.trucoscoreboardcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3043983.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrucoScoreScreen()
        }
    }
}

@Composable
fun TrucoScoreScreen() {
    var scoreA by remember { mutableIntStateOf(0) }
    var scoreB by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    fun processPoint(currentScore: Int, added: Int, onUpdate: (Int) -> Unit, teamName: String) {
        if (scoreA >= 12 || scoreB >= 12) return

        val newScore = (currentScore + added).coerceAtMost(12)
        onUpdate(newScore)

        if (newScore == 11) {
            Toast.makeText(context, "$teamName na Mão de 11!", Toast.LENGTH_SHORT).show()
        } else if (newScore == 12) {
            Toast.makeText(context, "$teamName VENCEU!", Toast.LENGTH_LONG).show()
        }

    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E7D32))
            .padding(24.dp) ,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        TeamScore(
            name = "EQUIPE A",
            score = scoreA,
            onAddOne = { processPoint(scoreA, 1, { scoreA = it }, "EQUIPE A") },
            onAddThree = { processPoint(scoreA, 3, { scoreA = it }, "EQUIPE A") }
        )

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
        Spacer(modifier = Modifier.height(32.dp))

        TeamScore(
            name = "EQUIPE B",
            score = scoreB,
            onAddOne = { processPoint(scoreB, 1, { scoreB = it }, "EQUIPE B") },
            onAddThree = { processPoint(scoreB, 3, { scoreB = it }, "EQUIPE B") }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { scoreA = 0; scoreB = 0 },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD32F2F)
            )
        ) {
            Text("REINICIAR PARTIDA", color = Color.White)
        }
    }
}