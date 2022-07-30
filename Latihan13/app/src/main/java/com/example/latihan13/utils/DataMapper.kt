package com.example.latihan13.utils

import com.example.latihan13.core.data.source.local.entity.GameEntity
import com.example.latihan13.core.data.source.local.entity.KartunEntity
import com.example.latihan13.core.data.source.local.entity.MemeEntity
import com.example.latihan13.core.data.source.local.entity.NasaEntity
import com.example.latihan13.core.data.source.remote.response.game.ResponseGame
import com.example.latihan13.core.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan13.core.data.source.remote.response.meme.ResponseMeme
import com.example.latihan13.core.data.source.remote.response.nasa.ResponseNasaItem
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.core.domain.model.Nasa

object DataMapper {
    fun mapGameEntityToDomain(input: List<GameEntity>): List<Game> {
        return input.map {
            Game(
                it.id,
                it.name,
                it.released,
                it.background_image,
                it.rating,
                it.platforms,
                it.genres,
                it.minimum,
                it.recommended,
                it.favorite
            )
        }
    }

    fun mapDomainToGameEntity(it: Game): GameEntity {
        return GameEntity(
            it.id,
            it.name,
            it.released,
            it.background_image,
            it.rating,
            it.platforms,
            it.genres,
            it.minimum,
            it.recommended,
            it.favorite
        )
    }

    fun mapResponseGameToEntities(data: ResponseGame): List<GameEntity> {
        val listGame = ArrayList<GameEntity>()
        for (i in data.results) {
            val listPlatform = ArrayList<String>()
            val listGenre = ArrayList<String>()
            var recommended = ""
            var minimum = ""

            for (i in i.platforms) {
                if (i.platform.name == "PC") {
                    minimum = i.requirements_en?.minimum.toString()
                    recommended = i.requirements_en?.recommended.toString()
                }
                listPlatform.add(i.platform.name)
            }

            for (i in i.genres) {
                listGenre.add(i.name)
            }

            val game = GameEntity(
                i.id,
                i.name,
                i.released,
                i.background_image,
                i.rating.toString(),
                listPlatform.toString(),
                listGenre.toString(),
                minimum, recommended
            )
            listGame.add(game)
        }
        return listGame
    }

    fun mapKartunEntityToDomain(input: List<KartunEntity>): List<Kartun> {
        return input.map {
            Kartun(
                it.id,
                it.image,
                it.species,
                it.name,
                it.status,
                it.originName,
                it.created,
                it.favorite
            )
        }
    }

    /*fun mapDomainToKartunEntity(it: Kartun): KartunEntity {
        return KartunEntity(
            it.id,
            it.name,
            it.species,
            it.name,
            it.status,
            it.originName,
            it.created,
            it.favorite
        )
    }*/

    fun mapResponseKartunToEntities(data: ResponseKartun): List<KartunEntity> {
        val listKartun = ArrayList<KartunEntity>()

        for (i in data.results) {
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

    fun mapNasaEntityToDomain(input: List<NasaEntity>): List<Nasa> {
        return input.map {
            Nasa(
                it.id,
                it.title,
                it.hdurl,
                it.explanation,
                it.date,
                it.copyright,
                it.favorite
            )
        }
    }

    fun mapResponseNasaToEntities(data: List<ResponseNasaItem>): List<NasaEntity> {
        val listNasa = ArrayList<NasaEntity>()
        for (i in data) {
            val nasa = NasaEntity(
                title = i.title,
                copyright = i.copyright ?: "No Copyright",
                hdurl = i.hdurl ?: "No Image",
                date = i.date,
                explanation = i.explanation
            )
            listNasa.add(nasa)
        }
        return listNasa
    }

    fun mapMemeEntityToDomain(input: List<MemeEntity>): List<Meme> {
        return input.map {
            Meme(
                it.id,
                it.name,
                it.image
            )
        }
    }

    fun mapResponseMemeToEntities(data: ResponseMeme): List<MemeEntity>{
        val listMeme = ArrayList<MemeEntity>()
        for(i in data.data.memes){
            val meme = MemeEntity(
                i.id?:"No Data",
                i.name?:"No Data",
                i.url?:"No Data"
            )
            listMeme.add(meme)
        }
        return listMeme
    }
}