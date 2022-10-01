package cz.mendelu.pef.compose.todo.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import cz.mendelu.pef.compose.todo.R
import cz.mendelu.pef.compose.todo.ui.activities.viewmodels.AppIntroViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppIntroActivity : AppIntro() {

    companion object {
        /**
         * Creates intent for the activity.
         * @param context the context to run the intent
         */
        fun createIntent(context: Context): Intent {
            return Intent(context, AppIntroActivity::class.java)
        }
    }

    private val viewModel: AppIntroViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showAppIntro()
    }

    private fun showAppIntro() {
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.welcome_to) + " " + getString(R.string.app_name),
                description = getString(R.string.intro_text_1),
                titleColor = ContextCompat.getColor(this, R.color.textColor),
                descriptionColor = ContextCompat.getColor(this, R.color.textColor),
                imageDrawable = R.drawable.ic_intro_first
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.compose),
                description = getString(R.string.intro_text_2),
                titleColor = ContextCompat.getColor(this, R.color.textColor),
                descriptionColor = ContextCompat.getColor(this, R.color.textColor),
                imageDrawable = R.drawable.ic_intro_second
            )
        )

        showSeparator(true)
        setSeparatorColor(ContextCompat.getColor(this, R.color.textColor))
        setColorDoneText(ContextCompat.getColor(this, R.color.textColor))
        setDoneText(getString(R.string.continue_to_app))
        setIndicatorColor(
            ContextCompat.getColor(this, R.color.textColor),
            ContextCompat.getColor(this, android.R.color.darker_gray))
        setNextArrowColor(ContextCompat.getColor(this, R.color.textColor))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        continueToMainActivity()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        continueToMainActivity()
    }

    private fun continueToMainActivity() {
        lifecycleScope.launch {
            viewModel.setFirstRun()
        }.invokeOnCompletion {
            startActivity(MainActivity.createIntent(this))
            finish()
        }
    }
}