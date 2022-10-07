package cz.mendelu.pef.compose.petstore.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.RelativeLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import androidx.compose.ui.viewinterop.AndroidView
import cz.mendelu.pef.compose.petstore.R
import cz.mendelu.pef.compose.petstore.models.Data
import cz.mendelu.pef.compose.petstore.models.ScreenState
import cz.mendelu.pef.compose.petstore.navigation.INavigationRouter
import cz.mendelu.pef.compose.petstore.ui.elements.ErrorScreen
import cz.mendelu.pef.compose.petstore.ui.elements.LoadingScreen
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigation: INavigationRouter,
    viewModel: MainScreenViewModel = getViewModel()
) {
    
    val screenState: MutableState<ScreenState<Data>> = rememberSaveable{
        mutableStateOf(ScreenState.Loading())
    }
    
    
    viewModel.mainScreenUIState.value.let { 
        when(it){
            is MainScreenUiState.Error -> screenState.value = ScreenState.Error(it.error)
            is MainScreenUiState.Start -> {
                LaunchedEffect(it){
                    viewModel.loadData()
                }
            }
            is MainScreenUiState.Success -> {
                screenState.value = ScreenState.DataLoaded(it.data)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .weight(1.5f)
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colorScheme.background
            )
        },
        content = {
            ScreenContent(screenState = screenState.value)
        },
    )
}

@Composable
fun ScreenContent(screenState: ScreenState<Data>) {
    
    screenState.let { 
        when(it){
            is ScreenState.DataLoaded -> {
                Log.e("SOmttuu","somtu")
                content2(data = it.data)
            }
            is ScreenState.Error -> ErrorScreen(text = stringResource(it.error))
            is ScreenState.Loading -> LoadingScreen()
        }
    }
}


@Composable
fun content2(data:Data) {
    Text(text = "dkdk")
    Chart(data = data)
    
}

@Composable
fun Chart(
    data: Data,
) {

    Column {
        AndroidView(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 0.dp),
            factory = { context ->
                val lineChart = LineChart(context)
                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )
                lineChart.layoutParams = params

                lineChart.fitScreen()
                lineChart.setPadding(0, 0, 0, 0)

                lineChart.setBackgroundColor(android.graphics.Color.WHITE)
                lineChart.description.isEnabled = false
                lineChart.setTouchEnabled(false)
                lineChart.setOnChartValueSelectedListener(null)
                lineChart.setDrawGridBackground(false)
                lineChart.isDragEnabled = false
                lineChart.setScaleEnabled(false)
                lineChart.setPinchZoom(false)

                lineChart.axisLeft.isEnabled = false
                val yAxis = lineChart.axisRight
                yAxis.isEnabled = false

                val values: ArrayList<com.github.mikephil.charting.data.Entry> = ArrayList()
                var counter = 0

                var lowest = 50000.0f
                var highest = -500000.0f

                data.data!!.forEach {
                    // Todo spravte až budete vědět jména proměnných.
                    val value = it.value - 100.0f
                    //if (value > highest) highest = value.toFloat()
                    if (value < lowest) lowest = value.toFloat()
                    values.add(com.github.mikephil.charting.data.Entry(counter.toFloat(),
                        value.toFloat()
                    ))
                    counter++
                }

                yAxis.axisMaximum = roundUp(highest)
                yAxis.axisMinimum = roundDown(lowest)

                val lineDataSet = LineDataSet(values, null)
                lineDataSet.color = android.graphics.Color.BLACK
                lineDataSet.setDrawCircles(false)
                lineDataSet.lineWidth = 1f

                lineDataSet.setDrawFilled(true)
                lineDataSet.fillFormatter =
                    IFillFormatter { dataSet, dataProvider -> lineChart.axisLeft.axisMinimum }

                val dataSets: ArrayList<ILineDataSet> = ArrayList()
                dataSets.add(lineDataSet)

                val lineData = LineData(dataSets)

                lineChart.data = lineData
                lineChart.axisLeft.setDrawLabels(false)
                lineChart.axisRight.setDrawLabels(false)
                lineChart.xAxis.setDrawLabels(false)
                lineChart.axisRight.setDrawZeroLine(false)
                lineChart.axisLeft.setDrawZeroLine(false)
                lineChart.xAxis.setDrawAxisLine(false)
                lineChart.axisLeft.setDrawGridLines(false)
                lineChart.xAxis.setDrawGridLines(false)

                lineChart.legend.isEnabled = false
                lineChart.minOffset = 0.0f
                lineChart.animateX(0)
                lineChart
            }) {
        }
    }
}


private fun roundUp(toRound: Float): Float {
    if (toRound % 10 == 0.0f) return toRound
    return (10 - toRound % 10) + toRound
}

private fun roundDown(toRound: Float): Float {
    return toRound - toRound % 10.0f
}

