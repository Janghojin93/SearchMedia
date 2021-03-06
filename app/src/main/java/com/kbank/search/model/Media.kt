package com.kbank.search.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbank.search.model.Media.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Media(
    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var author: String? = null,
    var body: String? = null,
    var imageUrl: String? = null
) {
    companion object {
        const val TABLE_NAME = "media"
    }
}


