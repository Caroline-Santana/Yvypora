package com.example.yvypora.navbar

import com.example.yvypora.R

sealed class ItemsMenu(
    val icon: Int,
    val title: String,
    val rota: String
){
    object Pantalla1: ItemsMenu(R.drawable.home,R.string.home.toString(),"pantalla1")
    object Pantalla2: ItemsMenu(R.drawable.video,R.string.live.toString(),"pantalla2")
    object Pantalla3: ItemsMenu(R.drawable.fair,R.string.fair.toString(),"pantalla3")
    object Pantalla4: ItemsMenu(R.drawable.profile_icon,R.string.profile.toString(),"pantalla4")


}
