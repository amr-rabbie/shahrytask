package com.example.shahrytask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shahrytask.databinding.PostsItemBinding
import com.example.shahrytask.model.PostsResponseItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PostsAdapter(val context: Context , val list: List<PostsResponseItem>) : RecyclerView.Adapter<PostsAdapter.PostsViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostsViewModel {
        return PostsViewModel(PostsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostsViewModel, position: Int) {
        var post:PostsResponseItem=list.get(position)

        holder.binding.apply {
            title.text=post.title

            val mydate:String=post.date!!
            val dateFormat = SimpleDateFormat(
                "yyyy-MM-dd"
            )
            try {
                val d = dateFormat.parse(mydate)
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                val myday = sdf.format(d)
                date.text=myday
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            body.text=post.body

            postimage.load(post.imageUrl){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount()=
        list.size

    inner class PostsViewModel(val binding: PostsItemBinding):
            RecyclerView.ViewHolder(binding.root)
}