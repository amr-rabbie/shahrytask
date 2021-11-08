package com.example.shahrytask.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "authors_table")
@Parcelize
class Authorss (var name:String,var email:String, var url:String,var authorid:Int) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id=0

    /*@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    lateinit var avatarUrl: ByteArray*/

    @Ignore
    var userName:String=""


    override fun toString(): String {
        return "Author(name='$name', email='$email')"
    }


}