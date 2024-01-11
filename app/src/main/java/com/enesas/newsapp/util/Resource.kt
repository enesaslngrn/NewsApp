package com.enesas.newsapp.util


// Bunu her projede kullanabilirsin. Ve success, error, loading durumlarını ui'da gösterebilirsin. <T> : Type demek yani Bu <A>'da olabilir aslında ama böyle kullanılıyor.
sealed class Resource<T> (val data: T? = null,
                          val message: String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}