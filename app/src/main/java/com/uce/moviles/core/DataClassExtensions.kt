package com.uce.moviles.core

import com.uce.moviles.data.network.entities.jikan.anime.FullInfoAnime
import com.uce.moviles.logic.usercases.jikan.entities.FullInfoAnimeLG


fun FullInfoAnime.getFullInfoAnimeLG() = FullInfoAnimeLG(
    this.data.mal_id,
    this.data.title_english,
    this.data.images.jpg.small_image_url,
    this.data.images.jpg.large_image_url,
    this.data.synopsis
)
