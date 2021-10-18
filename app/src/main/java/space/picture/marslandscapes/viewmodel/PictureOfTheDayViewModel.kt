package space.picture.marslandscapes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.picture.marslandscapes.model.NasaDTO
import space.picture.marslandscapes.model.RemoteDataSource
import space.picture.marslandscapes.repository.PictureOfTheDayRepository
import space.picture.marslandscapes.repository.PictureOfTheDayRepositoryImpl

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: PictureOfTheDayRepository = PictureOfTheDayRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {

    fun getLiveData() = liveDataForViewToObserve

    fun getPictureTodayFromRemoteSource(apiKey: String) {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        repository.getPictureOfTheToday(apiKey, callBackToday)
    }

    fun getPictureYesterdayFromRemoteSource(date: String, apiKey: String) {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        repository.getPictureOfTheYesterday(date, apiKey, callBackYesterday)
    }

    private val callBackToday = object : Callback<NasaDTO> {
        override fun onResponse(call: Call<NasaDTO>, response: Response<NasaDTO>) {
            val serverResponse: NasaDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataForViewToObserve.value = AppState.Success(serverResponse)
            } else {
                liveDataForViewToObserve.postValue(AppState.Error(NullPointerException()))
            }
        }
        override fun onFailure(call: Call<NasaDTO>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }

    private val callBackYesterday = object : Callback<NasaDTO> {
        override fun onResponse(call: Call<NasaDTO>, response: Response<NasaDTO>) {
            val serverResponse: NasaDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataForViewToObserve.value = AppState.Success(serverResponse)
            } else {
                liveDataForViewToObserve.postValue(AppState.Error(NullPointerException()))
            }
        }
        override fun onFailure(call: Call<NasaDTO>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }
}