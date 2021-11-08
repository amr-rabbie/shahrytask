package com.example.shahrytask.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shahrytask.databinding.AuthorItemBinding
import com.example.shahrytask.model.AuthorsResponseItem
import com.example.shahrytask.ui.DetailsActivity

class AuthorsAdapter(val context: Context,val list :List<AuthorsResponseItem>) : RecyclerView.Adapter<AuthorsAdapter.AuthorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsAdapter.AuthorsViewHolder {
        return AuthorsViewHolder(AuthorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AuthorsAdapter.AuthorsViewHolder, position: Int) {
        var item:AuthorsResponseItem=list.get(position)

        holder.binding.apply {
            authorname.text=item.name

            authorimage.load(item.avatarUrl){
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener {mview->
            var intent:Intent= Intent(context,DetailsActivity::class.java)
            intent.putExtra("Data",item)
            intent.putExtra("Type","Online")
            context.startActivity(intent)
        }
    }

    override fun getItemCount()=
        list.size

    inner class AuthorsViewHolder(val binding: AuthorItemBinding):
        RecyclerView.ViewHolder(binding.root)
}