package br.edu.ifsp.scl.sc3043983.trucoscoreboardcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
}