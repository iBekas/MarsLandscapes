package space.picture.marslandscapes.model


import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.picture.marslandscapes.BuildConfig


class RemoteDataSource {
    private val moviesAPI = Retrofit.Builder()
        .baseUrl(NASA_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(NasaPhotoAPI::class.java)

    fun getPictureOfTheToday(apiKey: String, callback: Callback<NasaDTO>) {
        moviesAPI.getPictureOfTheToday(BuildConfig.NASA_API_KEY, apiKey).enqueue(callback)
    }

    fun getPictureOfTheYesterday(date: String, apiKey: String, callback: Callback<NasaDTO>) {
        moviesAPI.getPictureOfTheYesterday(BuildConfig.NASA_API_KEY, date, apiKey).enqueue(callback)
    }

    fun getPictureOfMarsRover(sol: Int, camera: String, apiKey: String, callback: Callback<AllPhotosByNasaRoverDTO>) {
        moviesAPI.getPictureOfMarsRover(BuildConfig.NASA_API_KEY, sol, camera, apiKey).enqueue(callback)
    }
}