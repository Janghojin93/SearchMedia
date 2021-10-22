package com.kbank.search.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchVideoResponse(
    @SerializedName("meta") val meta: MetaVideoResponse,
    @SerializedName("documents") val documents: MutableList<DocumentVideoResponse>
)
data class MetaVideoResponse(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int
)

data class DocumentVideoResponse(
    @SerializedName("title") val title: String,
    @SerializedName("play_time") val play_time: Int,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("url") val url: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("author") val author: String)