package com.uce.moviles.logic.usercases.nobel

import android.util.Log
import com.uce.moviles.data.network.endpoints.nobel.NobelPrizeEndPoint
import com.uce.moviles.data.network.entities.nobel.NobelPrizeX
import com.uce.moviles.data.network.repository.RetrofitBase
import com.uce.moviles.ui.core.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNobelPrizesUserCase {

    suspend fun invoke(limit: Int): Flow<Result<List<NobelPrizeX>>> = flow {

        var result: Result<List<NobelPrizeX>>? = null
        var newLimit = limit

        val baseService = RetrofitBase.getNobelPrizesConnection()
        val service = baseService.create(NobelPrizeEndPoint::class.java)

        while (newLimit < 25) {
            val call = service.getAllNobelPrizes(newLimit)
            try {
                if (call.isSuccessful) {
                    val a = call.body()!!
                    val nobelPrizes = a.nobelPrizes
                    result = Result.success(nobelPrizes)
                } else {
                    val msg = "Error en el llamado a la API de Jikan"
                    result = Result.failure(Exception(msg))
                    Log.d(Constants.TAG, msg)
                }
            } catch (ex: Exception) {
                Log.e(Constants.TAG, ex.stackTraceToString())
                result = Result.failure(ex)
            }
            emit(result!!)
            delay(2000)
            newLimit++
        }
    }
}