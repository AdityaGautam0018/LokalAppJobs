package com.example.lokaljobs.screens

sealed class BottomBarScreen(
    val screen: String,

    ) {
    data object Jobs : BottomBarScreen("jobs")
    data object Bookmarks : BottomBarScreen("bookmarks")
}