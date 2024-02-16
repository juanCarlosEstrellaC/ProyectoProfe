package com.uce.moviles.data.network.endpoints.anime

import com.uce.moviles.data.network.entities.jikan.anime.FullInfoAnime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeEndPoint {

    @GET("anime/{id}/full")
    fun getAnimeFullInfo(@Path("id") id: Int): Response<FullInfoAnime>

}