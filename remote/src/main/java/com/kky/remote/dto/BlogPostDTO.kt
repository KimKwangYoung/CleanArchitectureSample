package com.kky.remote.dto

import com.google.gson.annotations.SerializedName
import com.kky.domain.data.Post

data class BlogPostDTO(
    @SerializedName("title")
    val title: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("postdate")
    val postDate: String,

    @SerializedName("bloggername")
    val blogger: String
) {
    fun toDomainModel(): Post {
        return Post(
            title = title,
            link = link,
            postDate = postDate,
            content = description,
            writer = blogger,
            source = SOURCE
        )
    }

    companion object {
        const val SOURCE = "네이버 블로그"
    }
}
