package com.example.shahrytask.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androiddeveloper.amrrabbie.kotlinapidb.utils.Network
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shahrytask.R
import com.example.shahrytask.adapters.PostsAdapter
import com.example.shahrytask.adapters.PostsAdapterOffline
import com.example.shahrytask.databinding.ActivityDetailsBinding
import com.example.shahrytask.db.Authorss
import com.example.shahrytask.db.Post
import com.example.shahrytask.model.AuthorsResponseItem
import com.example.shahrytask.viewmodel.ShahryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    val viewmodel:ShahryViewModel by viewModels()
    lateinit var madapter: PostsAdapter
    lateinit var madapteroffline: PostsAdapterOffline

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        bindAuthorandPostsData()
    }

    private fun bindAuthorandPostsData() {
        var intent=intent
        if(intent.hasExtra("Type")) {
            var type:String= intent.getStringExtra("Type")!!
            if(type == "Online") {
                if (intent.hasExtra("Data")) {
                    var author: AuthorsResponseItem? = intent.getParcelableExtra("Data")
                    if (author != null) {
                        binding.txtname.text = author.name
                        binding.txtemail.text = author.email

                        if (Network.isNetworkAvailable(this)) {
                            bindPostsData(author.id)
                        } else {
                            //Toast.makeText(this,"Please check your network connection", Toast.LENGTH_LONG).show()
                            val snack = Snackbar.make(
                                findViewById(android.R.id.content),
                                "Please check your network connection",
                                Snackbar.LENGTH_LONG
                            )
                            snack.setTextColor(Color.WHITE)
                            snack.setBackgroundTint(Color.RED)
                            snack.show()
                        }
                    }
                }
            }else{
                var author: Authorss? = intent.getParcelableExtra("Data")
                if (author != null) {
                    binding.txtname.text = author.name
                    binding.txtemail.text = author.email

                    if (Network.isNetworkAvailable(this)) {

                    } else {
                        bindPostsDataoffline(author.authorid)
                        //Toast.makeText(this,"Please check your network connection", Toast.LENGTH_LONG).show()
                        val snack = Snackbar.make(
                            findViewById(android.R.id.content),
                            "Please check your network connection",
                            Snackbar.LENGTH_LONG
                        )
                        snack.setTextColor(Color.WHITE)
                        snack.setBackgroundTint(Color.RED)
                        snack.show()
                    }
                }
            }
        }
    }

    private fun bindPostsDataoffline(id: Int) {
        viewmodel.getPostsForAuthorOffline(id)

        viewmodel.postslistoffline.observe(this, Observer { postslist->
            if(postslist != null){
                Log.d("TAG", "onCreatePosts: ${postslist.toString()}")
                madapteroffline= PostsAdapterOffline(this,postslist)

                binding.postsrecycler.apply {
                    layoutManager=LinearLayoutManager(this@DetailsActivity)
                    hasFixedSize()
                    adapter=madapteroffline
                    visibility=View.VISIBLE
                }
                binding.pbar.visibility=View.GONE
            }
        })
    }

    private fun bindPostsData(id: Int?) {
        viewmodel.getPostsForAuthor(id!!)

        viewmodel.postslist.observe(this, Observer { postslist->
            if(postslist != null){
                madapter= PostsAdapter(this,postslist)

                binding.postsrecycler.apply {
                    layoutManager=LinearLayoutManager(this@DetailsActivity)
                    hasFixedSize()
                    adapter=madapter
                    visibility=View.VISIBLE
                }
                binding.pbar.visibility=View.GONE

                viewmodel.deleteAllPostsForAuthor(id)

                for(item in postslist) {
                    Log.d("TAG", "bindPostsData: ${item.toString()}")
                    var post:Post= Post(item.date!!,item.title!!,item.body!!,item.authorId!!,item.imageUrl!!)
                    viewmodel.insertPost(post)
                }
            }
        })

    }
}