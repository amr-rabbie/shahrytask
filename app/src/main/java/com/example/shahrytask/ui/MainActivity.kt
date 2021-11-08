package com.example.shahrytask.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androiddeveloper.amrrabbie.kotlinapidb.utils.Network
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shahrytask.adapters.AuthorsAdapter
import com.example.shahrytask.adapters.AuthorsAdapterOffline
import com.example.shahrytask.databinding.ActivityMainBinding
import com.example.shahrytask.db.Authorss
import com.example.shahrytask.viewmodel.ShahryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewmodel:ShahryViewModel by viewModels()
    lateinit var madapter:AuthorsAdapter
    lateinit var madapteroffline:AuthorsAdapterOffline

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(Network.isNetworkAvailable(this)){
            bindAuthorsData()
        }else{
            //Toast.makeText(this,"Please check your network connection",Toast.LENGTH_LONG).show()
            val snack = Snackbar.make(findViewById(android.R.id.content),"Please check your network connection",Snackbar.LENGTH_LONG)
            snack.setTextColor(Color.WHITE)
            snack.setBackgroundTint(Color.RED)
            snack.show()

            viewmodel.getAllAuthorsOffline()
            viewmodel.authorslistoffline.observe(this, Observer { authorslist->

                if(authorslist != null){
                    Log.d("TAG", "onCreate: ${authorslist.toString()}")

                    madapteroffline= AuthorsAdapterOffline(this,authorslist)

                    binding.authorsrecycler.apply {
                        layoutManager=GridLayoutManager(this@MainActivity,2)
                        hasFixedSize()
                        adapter=madapteroffline
                        visibility=View.VISIBLE
                    }
                    binding.pbar.visibility=View.GONE
                }

            })

        }
    }

    private fun bindAuthorsData() {
        viewmodel.getAllAuthors()

        viewmodel.authorslist.observe(this, Observer { authorslist->
            if(authorslist != null){
                madapter= AuthorsAdapter(this,authorslist)

                binding.authorsrecycler.apply {
                    layoutManager=GridLayoutManager(this@MainActivity,2)
                    hasFixedSize()
                    adapter=madapter
                    visibility=View.VISIBLE
                }
                binding.pbar.visibility=View.GONE

                viewmodel.deleteAllAuthors()

                for( item in authorslist){

                    Log.d("TAG", "bindAuthorsData: ${item.toString()}")

                    var author: Authorss =Authorss(item.name!!,item.email!!,item.avatarUrl!!,item.id!!)
                    viewmodel.insertAuthor(author)
                }
            }
        })
    }



}