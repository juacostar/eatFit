package com.example.eatfit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.Glide
import com.example.eatfit.model.dto.Receipe
import com.example.eatfit.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var recipes = mutableListOf<Receipe>()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
        setContent{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        viewModel.recipesList.observe(this, Observer{
            recipes.addAll(it)
            setContent{
                recyclerview()
            }
        })


    }

    @Preview
    @Composable
    fun recyclerview(
        viewModel: HomeViewModel = this.viewModel
    ){

       contentRecycler(recipes = recipes)
    }



    @Composable
    fun contentRecycler(recipes: List<Receipe>){

        LazyColumn (
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()

                ){

            items(
                items = recipes ,
                itemContent = {
                    recipeDetail(receipe = it)
                }
            )
        }
    }


    @Composable
    fun recipeDetail(receipe: Receipe){
        Row() {
           Card(

               modifier = Modifier
                   .padding(10.dp)
                   .clickable {
                       val intent = Intent(this@MainActivity, RecipeDetailActivity::class.java)
                       intent.putExtra("title", receipe.title)
                       intent.putExtra("image", receipe.image)
                       intent.putExtra("summary", receipe.summary)
                   }
           ) {
               Row {
                   Image(
                       painter = rememberAsyncImagePainter(receipe.image),
                       contentDescription = null,
                       modifier = Modifier.size(128.dp)
                   )
                   Text(text = receipe.title,
                       fontFamily = poppinsFontFamily,

                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(16.dp)
                       .size(20.dp))

//                   Text(text = receipe.)

               }
           }
//            Button(onClick = { /*TODO*/ },
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = Color.Black
//                )
//            ) {
//                Text("salga")
//                Icons.Rounded.KeyboardArrowRight
//            }
        }

    }


}

