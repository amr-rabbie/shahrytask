package com.example.shahrytask.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Posts_Table")
@Parcelize
class Post(var date:String,var title:String,var body:String,var authorId:Int,var url:String) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id=0;

    /*@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    lateinit var imageUrl: ByteArray*/


    override fun toString(): String {
        return "Post(date='$date', title='$title')"
    }


}