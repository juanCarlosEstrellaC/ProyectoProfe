package com.uce.moviles.logic.usercases.jikan

import android.util.Log
import com.uce.moviles.core.getFullInfoAnimeLG
import com.uce.moviles.data.network.endpoints.anime.AnimeEndPoint
import com.uce.moviles.data.network.repository.RetrofitBase
import com.uce.moviles.logic.usercases.jikan.entities.FullInfoAnimeLG
import com.uce.moviles.ui.core.Constants
import java.lang.Exception

class JikanAnimeUserCase() {

     fun getResponse(nameAnime: Int): FullInfoAnimeLG {
        var infoAnime = FullInfoAnimeLG()

         try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(AnimeEndPoint::class.java)
            val call = service.getAnimeFullInfo(nameAnime)

            if (call.isSuccessful) {
                val a = call.body()!!
                infoAnime = a.getFullInfoAnimeLG()
            } else {
                Log.d(Constants.TAG, "Error en el llamado a la API de Jikan")
            }
        }
        catch (ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
        }

        return infoAnime
    }
}