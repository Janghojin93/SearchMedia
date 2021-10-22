package com.kbank.search.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchImageResponse(
    @SerializedName("meta") val meta: MetaImageResponse,
    @SerializedName("documents") val documents: MutableList<DocumentImageResponse>
)

data class MetaImageResponse(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int
)

data class DocumentImageResponse(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("display_sitename") val display_sitename: String,
    @SerializedName("doc_url") val doc_url: String,
    @SerializedName("datetime") val datetime: String,
)