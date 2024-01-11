package com.enesas.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesas.newsapp.databinding.ItemArticleRowBinding
import com.enesas.newsapp.model.Article
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder (val binding : ItemArticleRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){ // OHA BUNU ŞUNUN İÇİN HER ZAMAN RECYCLER ADAPTER'E KOYMAK GEREK: Normalde val articleList = ArrayList<Article> diyip ve en aşağıda notifydatasetchange() çağırıp güncellerdim. Ama bu fonksiyon sayesinde bunu daha efficient yapabiliyorum.
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //Normalde api'dan çekerken id'si olsaydı article'ların direkt aşağıdaki gibi yapabilirdik ama yok. Biz kendimiz autogenerate = true diyerek room db'e aktarırken kullanacağımız bir id:Int? = null koyduk. O yüzden onu kullanamayız. Ama her article'ın unique bir url'i olduğu için onun kullanabiliriz.
            // return oldItem.id == newItem.id
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleList = differ.currentList

        holder.binding.tvSource.text = articleList[position].source?.name
        holder.binding.tvTitle.text = articleList[position].title
        holder.binding.tvDescription.text = articleList[position].description
        holder.binding.tvPublishedAt.text = articleList[position].publishedAt

        Picasso.get().load(articleList[position].urlToImage).into(holder.binding.ivArticleImage)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(articleList[position]) }
        }


    }

    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }


}