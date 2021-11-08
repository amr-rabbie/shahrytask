package com.example.shahrytask.repostry

import androidx.lifecycle.LiveData
import com.example.shahrytask.db.Authorss
import com.example.shahrytask.db.Post
import com.example.shahrytask.db.ShahryDao
import com.example.shahrytask.network.ShahryApiService
import javax.inject.Inject

class ShahryRepostry
@Inject
constructor(private val shahryApiService: ShahryApiService ,private val shahryDao: ShahryDao)
{

    suspend fun getAllAuthors()=
        shahryApiService.getAllAuthors()

    suspend fun getPostsForAuthor(authorId:Int)=
        shahryApiService.getPostsForAuthor(authorId)

    fun insertAuthor(author: Authorss)=
        shahryDao.insertIntoAuthors(author)

    fun deleteAllAuthors()=
        shahryDao.deleteAllAuthors()

    fun getAllAuthorsOffline():LiveData<List<Authorss>>{
        return shahryDao.getAllAuthorsOffline()
    }

    fun insertPost(post: Post)=
        shahryDao.insertPost(post)

    fun  deleteAllPostsForAuthor(id:Int)=
        shahryDao.deleteAllPostsForAuthor(id)

    fun getPostsForAuthorOffline(id: Int):LiveData<List<Post>>{
        return shahryDao.getPostsForAuthorOffline(id)
    }
}