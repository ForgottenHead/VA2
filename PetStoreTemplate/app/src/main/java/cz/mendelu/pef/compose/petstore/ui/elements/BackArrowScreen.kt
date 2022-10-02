package cz.mendelu.pef.compose.petstore.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.compose.petstore.R

@Composable
fun BackArrowScreen(
    topBarText: String,
    disablePadding: Boolean = false,
    drawFullScreenContent: Boolean = false,
    content: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    onBackClick: () -> Unit) {

    Scaffold(
        drawerGesturesEnabled = false,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)

                    ) {
                        Text(
                            text = topBarText,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 0.dp, end = 5.dp)
                                //.weight(1.5f)
                        )

                        androidx.compose.material3.Icon(
                            painter = painterResource(id = R.drawable.ic_pet_cat),
                            tint = Color.White,
                            contentDescription = "icon?"
                        )
                    }
                },
                actions = actions,
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    } },
                elevation = 0.dp,
                backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
        }
    ) {
        if (!drawFullScreenContent) {
            LazyColumn {
                item {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .padding(if (!disablePadding) 16.dp else 0.dp)
                    ) {
                        content()
                    }
                }
            }
        } else {
            content()
        }
    }
}