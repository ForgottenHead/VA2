package cz.mendelu.pef.compose.arapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.mendelu.pef.compose.arapp.ui.theme.ARAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ARAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()){

                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {
                            startActivity(Intent(this@MainActivity, ARActivity::class.java))
                        }) {
                            Text(text = "Run AR")
                        }

                    }
                }
            }
        }
    }
}