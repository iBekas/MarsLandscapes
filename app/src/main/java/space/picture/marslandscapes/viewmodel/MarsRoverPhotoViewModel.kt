package space.picture.marslandscapes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.picture.marslandscapes.model.PhotosByNasaRoverDTO
import space.picture.marslandscapes.model.RemoteDataSource
import space.picture.marslandscapes.repository.MarsRoverPhotoRepository
import space.picture.marslandscapes.repository.MarsRoverPhotoRepositoryImpl

class MarsRoverPhotoViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: MarsRoverPhotoRepository = MarsRoverPhotoRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {

    fun getLiveData() = liveDataForViewToObserve

    fun getPictureOfMarsRover(sol: Int, camera: String, apiKey: String) {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        repository.getPictureOfMarsRover(sol, camera, apiKey, callBackRoverPhoto)
    }

    private val callBackRoverPhoto = object : Callback<PhotosByNasaRoverDTO> {
        override fun onResponse(call: Call<PhotosByNasaRoverDTO>, response: Response<PhotosByNasaRoverDTO>) {
            val serverResponse: PhotosByNasaRoverDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataForViewToObserve.value = AppState.SuccessRoverPhoto(serverResponse)
            } else {
                liveDataForViewToObserve.postValue(AppState.Error(NullPointerException()))
            }
        }
        override fun onFailure(call: Call<PhotosByNasaRoverDTO>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }
}