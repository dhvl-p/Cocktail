package com.e.cocktil.presentation.screen

import com.e.cocktil.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Search: BottomNavItem("Search",R.drawable.ic_search,"search")
    object Account: BottomNavItem("Account",R.drawable.ic_account,"account")
}