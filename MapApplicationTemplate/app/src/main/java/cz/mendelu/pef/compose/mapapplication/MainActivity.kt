package cz.mendelu.pef.compose.mapapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import cz.mendelu.pef.compose.mapapplication.navigation.Destination
import cz.mendelu.pef.compose.mapapplication.navigation.NavGraph
import cz.mendelu.pef.compose.mapapplication.ui.theme.MapApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapApplicationTheme {
                NavGraph(startDestination = Destination.MainScreen.route)
            }
        }
    }
}