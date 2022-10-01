package cz.mendelu.pef.compose.todo.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.compose.todo.ui.theme.smallMargin

@Composable
fun TextInputField(value: String,
                   hint: String,
                   onValueChange: ((String) -> Unit),
                   errorMessage: String){
    OutlinedTextField(
        value = value,
        label = { Text(text = hint) },
        onValueChange = onValueChange,
        isError = false,
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp))

    Text(
        text = errorMessage,
        modifier = Modifier
            .alpha(if (errorMessage.isNotEmpty()) 100f else 0f)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, smallMargin()),
        color = Red,
        textAlign = TextAlign.Start,
        fontSize = 11.sp
    )

}