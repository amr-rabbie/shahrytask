package com.example.shahrytask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShahryDao {

    @Insert(entity = Authorss::class)
    fun insertIntoAuthors(authorss: Authorss)

    @Query("delete from authors_table")
    fun deleteAllAuthors()

    @Query("select * from authors_table")
    fun getAllAuthorsOffline():LiveData<List<Authorss>>

    @Insert(entity = Post::class)
    fun insertPost(post: Post)

    @Query("delete from posts_table where authorId=:id")
    fun deleteAllPostsForAuthor(id: Int)

    @Query("select * from posts_table where authorId=:id")
    fun getPostsForAuthorOffline(id:Int):LiveData<List<Post>>
}