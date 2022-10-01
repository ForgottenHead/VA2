package cz.mendelu.pef.compose.todo.architecture

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel


/**
 * The base compose activity.
 */
abstract class BaseActivity<out VM : ViewModel>(private val viewModelClass: Class<VM>) : ComponentActivity() {
    protected abstract val viewModel: VM
}