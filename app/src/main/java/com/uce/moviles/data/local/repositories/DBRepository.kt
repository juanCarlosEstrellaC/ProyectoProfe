package com.uce.moviles.data.local.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uce.moviles.data.local.dao.UsersDAO
import com.uce.moviles.data.local.entities.Users

@Database(
    entities = [Users::class],
    version = 1
)
abstract class DBRepository : RoomDatabase() {
    abstract fun getUsersDAO(): UsersDAO
}

class DBConnection() {
    fun getConnection(context: Context): DBRepository =
        Room.databaseBuilder(
            context,
            DBRepository::class.java,
            "DBTest"
        ).build()
}