package com.uce.moviles.core

import android.app.Application
import com.uce.moviles.data.local.repositories.DBConnection
import com.uce.moviles.data.local.repositories.DBRepository
import com.uce.moviles.logic.usercases.local.LoginUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class My_Application : Application() {

    override fun onCreate() {
        super.onCreate()

        con = DBConnection().getConnection(applicationContext)

        GlobalScope.launch(Dispatchers.IO) {
            LoginUserCase(con).insertUsers()
        }
    }

    companion object {
        private lateinit var con: DBRepository

        fun getConnectionDB(): DBRepository? {
            return con
        }
    }
}