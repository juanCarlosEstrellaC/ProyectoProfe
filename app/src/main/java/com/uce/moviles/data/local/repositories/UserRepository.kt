package com.uce.moviles.data.local.repositories

import com.uce.moviles.data.local.entities.Users

class UserRepository {

    fun getUserList(): List<Users> {
        return listOf<Users>(
            Users(
                "bayron", "bayron", 1,
                "Bayron", "Torres"
            ),
            Users("juan", "juan", 2),
            Users("marco", "marco", 3),
            Users("maria", "maria", 4)
        )
    }
}