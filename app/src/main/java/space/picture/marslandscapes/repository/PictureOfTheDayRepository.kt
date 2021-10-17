package space.picture.marslandscapes.repository

import retrofit2.Callback
import space.picture.marslandscapes.model.NasaDTO

interface PictureOfTheDayRepository {
    fun getMovieFromServerNow(apiKey: String, callback: Callback<NasaDTO>)
    fun getMovieFromServerUpcoming(date: String, apiKey: String, callback: Callback<NasaDTO>)
}