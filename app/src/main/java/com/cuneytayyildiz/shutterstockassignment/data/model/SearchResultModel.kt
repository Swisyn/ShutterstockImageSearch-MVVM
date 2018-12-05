package com.cuneytayyildiz.shutterstockassignment.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultModel(
    @SerializedName("page") var page: Int = 0,
    @SerializedName("per_page") var perPage: Int = 0,
    @SerializedName("total_count") var totalCount: Int = 0,
    @SerializedName("search_id") var searchId: String = "",
    @SerializedName("data") var data: MutableList<DataEntity> = arrayListOf()
) {
    data class DataEntity(
        @SerializedName("id") var id: String = "",
        @SerializedName("aspect") var aspect: Double = 0.toDouble(),
        @SerializedName("assets") var assets: AssetsEntity? = null,
        @SerializedName("contributor") var contributor: ContributorEntity? = null,
        @SerializedName("description") var description: String = "",
        @SerializedName("image_type") var imageType: String = "",
        @SerializedName("has_model_release") var isHasModelRelease: Boolean = false,
        @SerializedName("media_type") var mediaType: String = ""
    ) {

        class AssetsEntity(
            @SerializedName("preview") var preview: PreviewEntity? = null,
            @SerializedName("small_thumb") var smallThumb: SmallThumbEntity? = null,
            @SerializedName("large_thumb") var largeThumb: LargeThumbEntity? = null,
            @SerializedName("huge_thumb") var hugeThumb: HugeThumbEntity? = null
        ) {

            data class PreviewEntity(
                @SerializedName("height") var height: Int = 0,
                @SerializedName("url") var url: String = "",
                @SerializedName("width") var width: Int = 0
            )

            data class SmallThumbEntity(
                @SerializedName("height") var height: Int = 0,
                @SerializedName("url") var url: String = "",
                @SerializedName("width") var width: Int = 0
            )

            data class LargeThumbEntity(
                @SerializedName("height") var height: Int = 0,
                @SerializedName("url") var url: String = "",
                @SerializedName("width") var width: Int = 0
            )

            data class HugeThumbEntity(
                @SerializedName("height") var height: Int = 0,
                @SerializedName("url") var url: String = "",
                @SerializedName("width") var width: Int = 0
            )
        }

        class ContributorEntity {
            @SerializedName("id")
            var id: String = ""
        }
    }
}