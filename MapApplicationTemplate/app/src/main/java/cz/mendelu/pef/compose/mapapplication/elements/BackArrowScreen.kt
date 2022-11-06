@file:OptIn(ExperimentalMaterial3Api::class)

package cz.mendelu.pef.compose.mapapplication.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.compose.mapapplication.R

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
            SmallTopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = topBarText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .weight(1.5f)
                        )
                    }
                },
                actions = actions,
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        content = {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = Color.Black
                            )
                        }) }
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