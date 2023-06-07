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
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
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
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import com.bumptech.glide.Glide
import com.example.eatfit.model.dto.ExtendedIngredient
import com.example.eatfit.model.dto.Receipe
import com.example.eatfit.viewmodel.HomeViewModel
import com.example.eatfit.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var recipes = mutableListOf<Receipe>()
    private var recipeDetail: Receipe? = null
    private val viewModel by viewModels<HomeViewModel>()
    private val detailViewModel by viewModels<RecipeDetailViewModel>()


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

    @Composable
    fun recyclerview(
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel = this.viewModel,
        navController: NavHostController = rememberNavController()
    ){

        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = "root"){

            composable("root"){
                contentRecycler(recipes = recipes, navHostController = navController)
            }
            
            composable("recipeDetail/{id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})
            ){
                activityDetailRecipe(id = it.arguments!!.getInt("id"))
            }

        }
    }



    @Composable
    fun contentRecycler(
        recipes: List<Receipe>,
        navHostController: NavHostController){

        LazyColumn (
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()

                ){

            items(
                items = recipes ,
                itemContent = {
                    recipeDetail(receipe = it, navController = navHostController)
                }
            )
        }
    }


    @Composable
    fun recipeDetail(
        receipe: Receipe,
        navController: NavHostController){

        var ingredientsRecipes = ""
        for (ingredient in receipe.extendedIngredients) ingredientsRecipes += ingredient.original

        Row() {
           Card(

               modifier = Modifier
                   .padding(10.dp)
                   .clickable {
                       navController.navigate("recipeDetail/" + receipe.id) {
                       }
                   }
           ) {
               Row {
                   Image(
                       painter = rememberAsyncImagePainter(receipe.image),
                       contentDescription = null,
                       modifier = Modifier.size(128.dp)
                   )
                   Spacer(modifier = Modifier.width(20.dp))
                   Column {
                       Spacer(modifier = Modifier.height(20.dp))
                       Text(text = receipe.title,
                           fontFamily = poppinsFontFamily,
                           modifier = Modifier
                               .fillMaxWidth()
                               .size(20.dp))
                       Spacer(modifier = Modifier.height(30.dp))
                       Row {
                           Column {
                               Icon( imageVector = Icons.Rounded.Favorite
                                   , contentDescription = receipe.aggregateLikes.toString(),
                                   modifier = Modifier
                                       .padding(0.dp)
                                       .size(20.dp))
                               Text(text = receipe.aggregateLikes.toString(),
                                   fontFamily = poppinsFontFamily,
                                   modifier = Modifier
                                       .padding(3.dp)
                                       .size(20.dp))
                           }
                           Column {
                               Icon( imageVector = Icons.Rounded.DateRange
                                   , contentDescription = receipe.readyInMinutes.toString(),
                                   modifier = Modifier
                                       .padding(0.dp)
                                       .size(20.dp))
                               Text(text = receipe.readyInMinutes.toString(),
                                   fontFamily = poppinsFontFamily,
                                   modifier = Modifier
                                       .padding(3.dp)
                                       .size(20.dp))
                           }
                       }
                       
                   }

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

    @Composable
    fun activityDetailRecipe(
        id: Int,
        viewModel: RecipeDetailViewModel = this.detailViewModel){
        viewModel.onCreate(id)

        //spinner
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }

        detailViewModel.recipe.observe(this, Observer(){
            this.recipeDetail = it
            setContent {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(model = recipeDetail!!.image),
                        contentDescription = null)
                    Text(text = recipeDetail!!.title)
                }
            }
        })

    }


}

