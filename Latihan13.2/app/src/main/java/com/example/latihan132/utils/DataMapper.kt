package com.example.latihan132.utils

import com.example.latihan132.data.remote.response.ResponseKartun
import com.example.latihan132.domain.KartunEntity

object DataMapper {
    fun mapResponseToDomain(data: ResponseKartun): List<KartunEntity>{
        val listKartun = ArrayList<KartunEntity>()
        for (i in data.results){
            val kartun = KartunEntity(
                i.id,
                i.image,
                i.species,
                i.name,
                i.status,
                i.origin.name,
                i.created,
            )
            listKartun.add(kartun)
        }
        return listKartun
    }
}