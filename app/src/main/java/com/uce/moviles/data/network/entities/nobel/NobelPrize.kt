package com.uce.moviles.data.network.entities.nobel

data class NobelPrize(
    val links: Links,
    val meta: Meta,
    val nobelPrizes: List<NobelPrizeX>
)