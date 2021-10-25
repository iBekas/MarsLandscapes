package space.picture.marslandscapes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NasaDTO(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
) : Parcelable

data class AllPhotosByNasaRoverDTO(val photos:List<PhotosByNasaRoverDTO>)

@Parcelize
data class PhotosByNasaRoverDTO(
    val id: Int,
    val sol: Int,
    val camera: Camera,
    val img_src: String,
    val earth_date: String,
    val rover: Rover
): Parcelable

@Parcelize
data class Rover(
    val id: Int,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String
): Parcelable

@Parcelize
data class Camera(
    val id: Int,
    val name: String,
    val rover_id: Int,
    val full_name: String
): Parcelable