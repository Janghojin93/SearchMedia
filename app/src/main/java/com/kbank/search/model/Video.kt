package com.kbank.search.model

import androidx.room.PrimaryKey

data class Video(
    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var author: String? = null,
    var body: String? = null,
    var imageUrl: String? = null
) {
}

