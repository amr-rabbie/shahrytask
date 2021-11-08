package com.example.shahrytask.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shahrytask.databinding.AuthorItemBinding
import com.example.shahrytask.databinding.AuthorItemOfflineBinding
import com.example.shahrytask.db.Authorss
import com.example.shahrytask.model.AuthorsResponseItem
import com.example.shahrytask.ui.DetailsActivity

class AuthorsAdapterOffline (val context: Context, val list :List<Authorss>) : RecyclerView.Adapter<AuthorsAdapterOffline.AuthorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsAdapterOffline.AuthorsViewHolder {
        return AuthorsViewHolder(AuthorItemOfflineBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AuthorsAdapterOffline.AuthorsViewHolder, position: Int) {
        var item: Authorss =list.get(position)

        holder.binding.apply {
            authorname.text=item.name
            authoremail.text=item.email


        }

        holder.itemView.setOnClickListener {mview->
            var intent: Intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("Data",item)
            intent.putExtra("Type","Offline")
            context.startActivity(intent)
        }
    }

    override fun getItemCount()=
        list.size

    inner class AuthorsViewHolder(val binding: AuthorItemOfflineBinding):
        RecyclerView.ViewHolder(binding.root)
}