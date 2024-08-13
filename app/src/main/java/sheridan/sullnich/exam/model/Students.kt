package sheridan.sullnich.exam.model

import kotlinx.serialization.Serializable

@Serializable
data class Students (
    val fullTime: String,
    val partTime: String
)