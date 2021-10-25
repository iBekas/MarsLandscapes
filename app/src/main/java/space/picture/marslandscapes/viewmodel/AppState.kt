package space.picture.marslandscapes.viewmodel

import space.picture.marslandscapes.model.NasaDTO
import space.picture.marslandscapes.model.PhotosByNasaRoverDTO

sealed class AppState {
    data class SuccessRoverPhoto(val dataNasa: PhotosByNasaRoverDTO) : AppState()
    data class Success(val dataNasa: NasaDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}