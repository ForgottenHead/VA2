package cz.mendelu.pef.compose.todo.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.compose.todo.R
import cz.mendelu.pef.compose.todo.ui.theme.getTintColor

@Composable
fun InfoElement(
    value: String?,
    hint: String,
    leadingIcon: Int,
    onClick: () -> Unit,
    onClearClick: () -> Unit){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    // Je nutné kvůli správnému chování labelu. Jinak po kliknutí na křížek zůstane nahoře.
    val focusManager = LocalFocusManager.current

    if (isPressed) {
        LaunchedEffect(isPressed){
            onClick()
        }
    }

    OutlinedTextField(
        value = if (value != null) value else "",
        onValueChange = {},
        interactionSource = interactionSource,
        leadingIcon = {Icon(
            painter = painterResource(id = leadingIcon),
            tint = getTintColor(),
            contentDescription = null
        )}
        ,
        trailingIcon = if (value != null) {
            {
                IconButton(onClick = {
                    onClearClick()
                    focusManager.clearFocus()
                }) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Clear),
                        tint = getTintColor(),
                        contentDescription = stringResource(R.string.clear)
                    )
                }
            }

        } else {
            null
        },
        label = {Text(text = hint)},
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}