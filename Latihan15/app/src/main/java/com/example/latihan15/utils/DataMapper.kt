package com.example.latihan15.utils

import com.example.latihan15.core.data.local.room.MakeupEntity
import com.example.latihan15.core.data.local.room.StoryEntity
import com.example.latihan15.core.data.remote.response.dicoding.getStory.ResponseStory
import com.example.latihan15.core.data.remote.response.makeup.ResponseItem
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.core.domain.model.Story

object DataMapper {
    //MakeUp
    fun mapMakeUpEntitiesToDomain(input: List<MakeupEntity>): List<Makeup> =
        input.map {
            Makeup(
                id = it.id,
                name = it.name,
                price = it.price,
                image = it.image,
                description = it.description,
                rating = it.rating,
                hexValue = it.hexValue,
                colourName = it.colourName
            )
        }

    fun mapResponseToMakeUpEntities(data: List<ResponseItem>): List<MakeupEntity> {
        val listData = ArrayList<MakeupEntity>()
        for (i in data) {
            var hexValue = ""
            var colourName = ""

            i.product_colors.map {
                hexValue = it.hex_value.toString()
                colourName = it.colour_name.toString()
            }

            val data = MakeupEntity(
                i.id,
                i.name,
                i.price,
                i.image_link,
                i.description,
                i.rating,
                hexValue,
                colourName
            )
            listData.add(data)
        }
        return listData
    }

    //Story
    fun mapStoryEntitiesToDomain(input: List<StoryEntity>): List<Story> =
        input.map {
            Story(
                id = it.id,
                image = it.image,
                name = it.name,
                description = it.description,
                date = it.date

            )
        }

    fun mapResponeToStoryEntities(data: ResponseStory): List<StoryEntity> {
        val listData = ArrayList<StoryEntity>()
        for(i in data.listStory){
            val data = StoryEntity(
                i.id,
                i.photoUrl,
                i.name,
                i.description,
                i.createdAt
            )
            listData.add(data)
        }
        return listData
    }
}