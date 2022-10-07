package com.kky.remote.dto

import com.google.gson.annotations.SerializedName
import com.kky.domain.data.BlogPost

data class BlogPostDTO(
    @SerializedName("title")
    val title: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("postdate")
    val postDate: String
) {
    fun toDomainModel(): BlogPost {
        return BlogPost(
            title = title,
            link = link,
            postDate = postDate
        )
    }
}
