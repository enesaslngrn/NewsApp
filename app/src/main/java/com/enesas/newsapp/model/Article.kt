package com.enesas.newsapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enesas.newsapp.model.Source
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity("articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable {

    override fun hashCode(): Int {
        var result = id.hashCode()
        if(url!!.isEmpty()){
            result = result + url.hashCode()
        }
        return result
    }
}