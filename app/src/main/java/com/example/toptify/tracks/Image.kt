package com.example.toptify.tracks

import java.io.Serializable

data class Image(
    val height: Int,
    val url: String,
    val width: Int
): Serializable