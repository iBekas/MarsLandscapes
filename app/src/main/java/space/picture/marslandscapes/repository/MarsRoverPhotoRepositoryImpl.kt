package space.picture.marslandscapes.repository

import retrofit2.Callback
import space.picture.marslandscapes.model.PhotosByNasaRoverDTO
import space.picture.marslandscapes.model.RemoteDataSource

class MarsRoverPhotoRepositoryImpl(private val remoteDataSource: RemoteDataSource): MarsRoverPhotoRepository {
    override fun getPictureOfMarsRover(
        sol: Int,
        camera: String,
        apiKey: String,
        callback: Callback<PhotosByNasaRoverDTO>
    ) {
        remoteDataSource.getPictureOfMarsRover(sol, camera, apiKey, callback)
    }
}