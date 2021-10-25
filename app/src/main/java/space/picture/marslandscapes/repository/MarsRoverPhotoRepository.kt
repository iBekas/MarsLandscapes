package space.picture.marslandscapes.repository

import retrofit2.Callback
import space.picture.marslandscapes.model.PhotosByNasaRoverDTO

interface MarsRoverPhotoRepository {
    fun getPictureOfMarsRover(sol: Int, camera: String, apiKey: String, callback: Callback<PhotosByNasaRoverDTO>)
}