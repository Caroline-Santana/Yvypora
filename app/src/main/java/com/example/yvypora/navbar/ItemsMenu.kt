package com.example.yvypora.navbar

import com.example.yvypora.R

sealed class ItemsMenu(
    val icon: Int,
    val rota: String
){
    object Pantalla1: ItemsMenu(R.drawable.home,"pantalla1")
    object Pantalla2: ItemsMenu(R.drawable.video,"pantalla2")
    object Pantalla3: ItemsMenu(R.drawable.icon_feira,"pantalla4")
    object Pantalla4: ItemsMenu(R.drawable.profile_icon,"pantalla5")
}
