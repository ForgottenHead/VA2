package cz.mendelu.pef.compose.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val PrimaryColor = Color(0xFF606c38)
val PrimaryDarkColor = Color(0xFF283618)
val SecondaryColor = Color(0xFFbc6c25)

// Dark mode primary colors
val PrimaryColorDark = Color(0xFFA1B94E)
val PrimaryDarkColorDark = Color(0xFF53752B)
val SecondaryColorDark = Color(0xFFF59138)


// Basic colors
val LightTextColor = Color(0xFF000000)
val DarkTextColor = Color(0xFFFFFFFF)

/**
 * The methods for returning colors. Usually, the color is return
 * based on dark mode.
 */
@Composable
fun getTintColor() = if (isSystemInDarkTheme()) Color.White else Color.Black

@Composable
fun getBasicTextColor(): Color = if (isSystemInDarkTheme()) DarkTextColor else LightTextColor
