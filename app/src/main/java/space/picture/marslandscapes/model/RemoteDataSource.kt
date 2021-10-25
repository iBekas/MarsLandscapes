package space.picture.marslandscapes.model


import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {
    private val moviesAPI = Retrofit.Builder()
        .baseUrl(NASA_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(NasaPhotoAPI::class.java)

    fun getPictureOfTheToday(apiKey: String, callback: Callback<NasaDTO>) {
        moviesAPI.getPictureOfTheToday(apiKey).enqueue(callback)
    }

    fun getPictureOfTheYesterday(date: String, apiKey: String, callback: Callback<NasaDTO>) {
        moviesAPI.getPictureOfTheYesterday(date, apiKey).enqueue(callback)
    }

    fun getPictureOfMarsRover(sol: Int, camera: String, apiKey: String, callback: Callback<AllPhotosByNasaRoverDTO>) {
        moviesAPI.getPictureOfMarsRover(sol, camera, apiKey).enqueue(callback)
    }
}