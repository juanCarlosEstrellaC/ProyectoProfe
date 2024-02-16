package com.uce.moviles.logic.usercases.local

import com.uce.moviles.data.local.entities.Users
import com.uce.moviles.data.local.repositories.DBRepository
import com.uce.moviles.data.local.repositories.UserRepository

class LoginUserCase(val connection: DBRepository) {

    fun checkLogin(username: String, password: String): Int {
        var ret = -1
        val users = UserRepository().getUserList()
        val lstUsers = users.filter {
            it.password == password && it.username == username
        }
        if (lstUsers.isNotEmpty()) {
            ret = lstUsers.first().userId
        }
        return ret
    }

    suspend fun getUserName(userId: Int): Users =
        connection.getUsersDAO().getOneUser(userId)

    suspend fun insertUsers() =
        if (connection.getUsersDAO().getAllUsers().isEmpty()) {
            connection.getUsersDAO().insertUser(
                UserRepository().getUserList()
            )
        } else {
            null
        }

    suspend fun getAllUsers(): List<Users> =
        connection.getUsersDAO().getAllUsers()

}