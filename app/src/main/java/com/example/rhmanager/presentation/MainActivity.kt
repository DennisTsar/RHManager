package com.example.rhmanager.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.rhmanager.BuildConfig
import com.example.rhmanager.data.remote.responses.HistoricalData
import com.example.rhmanager.presentation.ui.theme.RHManagerTheme
import com.example.rhmanager.util.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private val viewModel : TestViewModel by viewModels()
    //var data : HistoricalData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("qwer", BuildConfig.API_KEY)

        //However, you need to remember that collecting SharedFlow in the Fragment using lifecycleScope.launch{} is not lifecycle aware — you need to use launchWhenStarted or cancel the job when the app goes to background.
        viewModel.getCryptoData()

//        Log.d("qwer",data.toString())


        //data = viewModel.getStuff()
        Log.d("qwer",TestViewModel().getStuff().toString())

        setContent {
            RHManagerTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize(),
                ){
                    Navigation()
                }
                Graph(viewModel)
            }
        }
    }
}

@Composable
fun Graph(viewModel: TestViewModel) {
    Log.d("qwer","Asdf")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(25))
            .background(Color(Random.nextInt(), Random.nextInt(), Random.nextInt()))
    ){
        val data = viewModel.data.value.data
        Log.d("qwer",data.toString())
        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            if (data != null) {
                Log.d("qwer","Zxcv")
                data.dataPoints.forEach {
                    Log.d("Qwer","Asdzcxvf")
                    drawLine(
                        color=Color.Green,
                        start = Offset(size.width,y=0f),
                        end = Offset(x=0f,size.height)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RHManagerTheme {
        LazyColumn{
            itemsIndexed(
                listOf("a","b","c","d")
            ){ index, string ->
                ListItem()
                Text(index.toString() + string)
            }
        }
    }
}

@Composable
fun ListItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(25))
            .background(Color(Random.nextInt(), Random.nextInt(), Random.nextInt()))
    ){
        Row{
            Text("hi", textAlign = TextAlign.End)
            Spacer(Modifier.width(20.dp))
            Text(
                text = "hi",
            )
            Spacer(Modifier.width(20.dp))
        }
    }
}

//@Composable
//fun StoreScreen() {
//    val list = viewModel.categoriesList().collectAsState(emptyList())
//    Log.d("appDebug", list.value.toString()) // Showing always emptyList []
//}