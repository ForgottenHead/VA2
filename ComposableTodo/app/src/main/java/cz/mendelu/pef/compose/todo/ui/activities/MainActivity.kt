package cz.mendelu.pef.compose.todo.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import cz.mendelu.pef.compose.todo.architecture.BaseActivity
import cz.mendelu.pef.compose.todo.navigation.Destination
import cz.mendelu.pef.compose.todo.navigation.NavGraph
import cz.mendelu.pef.compose.todo.ui.activities.viewmodels.MainActivityViewModel
import cz.mendelu.pef.compose.todo.ui.theme.ComposableToDoTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainActivityViewModel>(MainActivityViewModel::class.java) {

    override val viewModel: MainActivityViewModel by viewModel()

    companion object {
        /**
         * Creates intent for the activity.
         * @param context the context to run the intent
         */
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableToDoTheme {
                // jen spustím NavGraph, nic víc.
                NavGraph(startDestination = Destination.TaskListScreen.route)
            }
        }
    }
}