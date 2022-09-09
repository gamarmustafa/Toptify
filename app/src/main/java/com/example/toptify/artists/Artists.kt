package com.example.toptify.artists

import java.io.Serializable

data class Artists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
):Serializable