package com.example.latihan131.utils

import com.example.latihan131.data.datasource.local.entity.KartunEntity
import com.example.latihan131.data.datasource.remote.response.kartun.ResponseKartun
import com.example.latihan131.domain.model.Kartun

object DataMapper {
    fun mapResponseToDomain(input: List<KartunEntity>): List<Kartun> {
        return input.map {
            Kartun(
                it.id,
                it.name,
                it.species,
                it.name,
                it.status,
                it.originName,
                it.created,
                it.favorite
            )
        }
    }

    fun mapResponseKartunToEntities(data: ResponseKartun): List<KartunEntity> {
        val listKartun = ArrayList<KartunEntity>()

        for(i in data.results){
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
        return  listKartun
    }
}