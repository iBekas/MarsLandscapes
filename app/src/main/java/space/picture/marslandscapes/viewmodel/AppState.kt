package space.picture.marslandscapes.viewmodel

import space.picture.marslandscapes.model.NasaDTO

sealed class AppState {
    data class SuccessToday(val dataNasa: NasaDTO) : AppState()
    data class SuccessYesterday(val dataNasa: NasaDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}