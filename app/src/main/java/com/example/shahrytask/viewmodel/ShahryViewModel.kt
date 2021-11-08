package com.example.shahrytask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shahrytask.db.Authorss
import com.example.shahrytask.db.Post
import com.example.shahrytask.model.AuthorsResponseItem
import com.example.shahrytask.model.PostsResponseItem
import com.example.shahrytask.repostry.ShahryRepostry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShahryViewModel
@Inject
constructor(private val shahryRepostry: ShahryRepostry):ViewModel()
{
     var authorslist=MutableLiveData<List<AuthorsResponseItem>>()

     var postslist=MutableLiveData<List<PostsResponseItem>>()

    lateinit var authorslistoffline:LiveData<List<Authorss>>

    lateinit var postslistoffline:LiveData<List<Post>>

    fun getAllAuthors()=viewModelScope.launch {
        shahryRepostry.getAllAuthors().let { authorsresponse->
            if(authorsresponse.isSuccessful){
                authorslist.postValue(authorsresponse.body()!!)
            }else{
                Log.d("TAG", "getAllAuthors: ${authorsresponse.message()}")
            }
        }
    }

    fun getPostsForAuthor(authorId:Int)=viewModelScope.launch {
        shahryRepostry.getPostsForAuthor(authorId).let { postsresponse->
            if(postsresponse.isSuccessful){
                postslist.postValue(postsresponse.body()!!)
            }else{
                Log.d("TAG", "getPostsForAuthor: "+postsresponse.message())
            }
        }
    }

    fun insertAuthor(author: Authorss)=
        shahryRepostry.insertAuthor(author)

    fun deleteAllAuthors()=
        shahryRepostry.deleteAllAuthors()

    fun getAllAuthorsOffline(){
        authorslistoffline=shahryRepostry.getAllAuthorsOffline()
    }

    fun insertPost(post: Post)=
        shahryRepostry.insertPost(post)

    fun deleteAllPostsForAuthor(id:Int)=
        shahryRepostry.deleteAllPostsForAuthor(id)

    fun getPostsForAuthorOffline(id: Int){
        postslistoffline=shahryRepostry.getPostsForAuthorOffline(id)
    }


}