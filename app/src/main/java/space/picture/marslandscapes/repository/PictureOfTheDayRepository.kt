package space.picture.marslandscapes.repository

import retrofit2.Callback
import space.picture.marslandscapes.model.NasaDTO

interface PictureOfTheDayRepository {
    fun getPictureOfTheToday(apiKey: String, callback: Callback<NasaDTO>)
    fun getPictureOfTheYesterday(date: String, apiKey: String, callback: Callback<NasaDTO>)
}