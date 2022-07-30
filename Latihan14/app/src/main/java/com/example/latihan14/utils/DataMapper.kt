package com.example.latihan14.utils

import com.example.latihan14.core.data.source.local.room.DestinasiEntity
import com.example.latihan14.core.data.source.remote.response.Response
import com.example.latihan14.core.domain.model.Destinasi

object DataMapper {
    /*fun mapResponseToDomain(data: Response): List<Destinasi> {
        val listDestinasi = ArrayList<Destinasi>()
        for (i in data.places) {
            val destinasi = Destinasi(
                i.id,
                i.image,
                i.name,
                i.address
            )
            listDestinasi.add(destinasi)
        }
        return listDestinasi
    }*/

    fun mapEntitiesToDomain(input: List<DestinasiEntity>): List<Destinasi> =
        input.map {
            Destinasi(
                id = it.id,
                image = it.image,
                name = it.name,
                address = it.address
            )
        }

    fun mapResponsetoEntities(data: Response): List<DestinasiEntity> {
        val listDestinasi = ArrayList<DestinasiEntity>()
        for(i in data.places){
            val destinasi = DestinasiEntity(
                i.id,
                i.address,
                i.description,
                i.image,
                i.latitude,
                i.like,
                i.longitude,
                i.name
            )
            listDestinasi.add(destinasi)
        }
        return listDestinasi
    }
}