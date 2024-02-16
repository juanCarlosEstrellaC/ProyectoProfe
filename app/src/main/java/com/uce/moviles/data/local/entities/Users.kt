package com.uce.moviles.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    val username: String? = null,
    val password: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = -1
    var firstName: String = "No registrado"
    var lastName: String = "No registrado"
    var profile: String = ""

    constructor(username: String?, password: String?, userId: Int)
            : this(username, password) {
        this.userId = userId
    }

    constructor(
        id: Int, username: String?,
        password: String?, profile: String
    ) : this(
        username,
        password
    ) {
        this.profile = profile
    }

    constructor(
        username: String?,
        password: String?, userId: Int,
        firstName: String, lastName: String
    ) : this(
        username,
        password
    ) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
    }
}



