package com.example.yvypora.navbar


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHost

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yvypora.MarketerScreens.InicialMarketerMain


@Composable
fun NavigationHost(navController: NavHostController){
    val context = LocalContext.current
   NavHost(navController = navController,
       startDestination = ItemsMenu.Pantalla1.rota,){
       composable(ItemsMenu.Pantalla1.rota){
           Home()
       }
       composable(ItemsMenu.Pantalla2.rota){
           Live()
       }
       composable(ItemsMenu.Pantalla3.rota){
//           context.startActivity(Intent(context, Fair::class.java))
           Fair()
       }
       composable(ItemsMenu.Pantalla4.rota){
           Profile()
       }
   }
}

@Composable
fun NavegationMarketer(navController: NavHostController){
    NavHost(navController= navController,
    startDestination = ItemsMenu.Pantalla5.rota){

        composable(ItemsMenu.Pantalla5.rota){
            HomeMarketer()
        }
        composable(ItemsMenu.Pantalla6.rota){
            Live()
        }
        composable(ItemsMenu.Pantalla7.rota){
            FairMarketer()
        }
        composable(ItemsMenu.Pantalla8.rota){
            ProfileMarketer()
        }
    }
}