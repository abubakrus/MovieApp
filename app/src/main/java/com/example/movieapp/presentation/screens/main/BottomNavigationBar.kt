package com.example.movieapp.presentation.screens.main

import com.example.movieapp.R

sealed class BottomNavigationBar(
    val route: String, val title: String, val icon: Int, val iconFocused: Int

) {
    object Main : BottomNavigationBar(
        route = "main",
        title = "Home",
        icon = R.drawable.ic_bottom_home,
        iconFocused = R.drawable.ic_bottom_home_focused
    )

    object Search : BottomNavigationBar(
        route = "search",
        title = "Search",
        icon = R.drawable.search,
        iconFocused = R.drawable.seacrh_focused
    )

    object WatchList : BottomNavigationBar(
        route = "watch_list",
        title = "WatchList",
        icon = R.drawable.ic_bottom_list,
        iconFocused = R.drawable.ic_bottom_list_focused
    )
}