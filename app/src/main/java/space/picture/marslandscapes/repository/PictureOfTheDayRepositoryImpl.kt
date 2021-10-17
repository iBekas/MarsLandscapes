package space.picture.marslandscapes.repository

import retrofit2.Callback
import space.picture.marslandscapes.model.NasaDTO
import space.picture.marslandscapes.model.RemoteDataSource

class PictureOfTheDayRepositoryImpl(private val remoteDataSource: RemoteDataSource) : PictureOfTheDayRepository {
    override fun getMovieFromServerNow(apiKey: String, callback: Callback<NasaDTO>) =
        remoteDataSource.getPictureOfTheToday(apiKey, callback)

    override fun getMovieFromServerUpcoming(
        date: String,
        apiKey: String,
        callback: Callback<NasaDTO>
    ) = remoteDataSource.getPictureOfTheYesterday(date, apiKey, callback)
}