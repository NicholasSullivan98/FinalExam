package sheridan.sullnich.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import sheridan.sullnich.exam.ui.CollegeApp
import sheridan.sullnich.exam.ui.theme.ExamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CollegeApp()
                }
            }
        }
    }
}