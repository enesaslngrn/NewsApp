package com.enesas.newsapp.repository

import com.enesas.newsapp.database.ArticleDatabase
import com.enesas.newsapp.model.Article
import com.enesas.newsapp.service.NewsAPIService

// Bu Repository'nin amacı, Database'den ve Api'dan verileri almaktır.
// Yani burada direkt Api'dan verileri almak için @Query'ler olacak.
// Çünkü NewsAPIService içerisinde herhangi bir function ile veri almadık.
// Ardından NewsViewModel içerisinde burada oluşturduğumuz fonksiyonları çağırıp gerekli response'ları alacağız.
// Ve ViewModel içerisinde ilgili liveData<> objelerini bu response'lara karşılık güncelleyip. UI kısmına gönderip Fragment'larda göreceğiz.


class NewsRepository (
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        NewsAPIService.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchForNews(searchQuery: String, pageNumber: Int) =
        NewsAPIService.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}