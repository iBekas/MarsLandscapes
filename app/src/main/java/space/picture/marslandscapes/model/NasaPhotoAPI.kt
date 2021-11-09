package space.picture.marslandscapes.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NasaPhotoAPI {
    @GET(NASA_API_URL_END_POINTER)
    fun getPictureOfTheToday(
        @Header(NASA_API_KEY_NAME) token:String,
        @Query("api_key") apiKey: String
    ): Call<NasaDTO>

    @GET(NASA_API_URL_END_POINTER)
    fun getPictureOfTheYesterday(
        @Header(NASA_API_KEY_NAME) token:String,
        @Query("date") date: String,
        @Query("api_key") apiKey: String
    ): Call<NasaDTO>

    @GET(NASA_ROVER_PHOTO_URL_END_POINTER)
    fun getPictureOfMarsRover(
        @Header(NASA_API_KEY_NAME) token:String,
        @Query("sol") sol: Int,
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String
    ): Call<AllPhotosByNasaRoverDTO>

}