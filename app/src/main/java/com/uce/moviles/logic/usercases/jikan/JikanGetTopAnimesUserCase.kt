package com.uce.moviles.logic.usercases.jikan

import android.util.Log
import com.uce.moviles.data.network.endpoints.anime.TopAnimesEndpoint
import com.uce.moviles.data.network.entities.jikan.top.TopAnime
import com.uce.moviles.data.network.repository.RetrofitBase
import com.uce.moviles.ui.core.Constants

class JikanGetTopAnimesUserCase {

    suspend fun getResponse(): Result<TopAnime> {
        var result: Result<TopAnime>? = null
        var infoAnime = TopAnime()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(TopAnimesEndpoint::class.java)
            val call = service.getAllTopAnimes()

            if (call.isSuccessful) {
                val a = call.body()!!
                infoAnime = a
                result = Result.success(infoAnime)
            } else {
                Log.e(Constants.TAG, "Error en el llamado a la API de Jikan")
                result = Result.failure(Exception("Error en el llamado a la API de Jikan"))
            }
        } catch (ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }

        return result!!
    }
}