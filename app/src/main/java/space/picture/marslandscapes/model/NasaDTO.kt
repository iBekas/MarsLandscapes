package space.picture.marslandscapes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NasaDTO(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
): Parcelable