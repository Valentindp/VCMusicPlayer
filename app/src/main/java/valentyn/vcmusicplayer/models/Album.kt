package com.example.valentin.valentinmusicplayer.models

data class Album (
    val id: Long = -1,
    val title: String = "",
    val artistName: String = "",
    val artistId: Long = -1,
    val songCount: Int = -1,
    val year: Int = -1
) {}