package com.example.yvypora.navbar


import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yvypora.ScreenClients.FairsActivity


@Composable
fun NavigationHost(navController: NavHostController){
   NavHost(navController = navController,
       startDestination = ItemsMenu.Pantalla1.rota,){
       composable(ItemsMenu.Pantalla1.rota){
           Home()
       }
       composable(ItemsMenu.Pantalla2.rota){
           Live()
       }
       composable(ItemsMenu.Pantalla3.rota){
           Fair()
       }
       composable(ItemsMenu.Pantalla4.rota){
           Profile()
       }

   }
}