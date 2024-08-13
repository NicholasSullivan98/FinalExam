package sheridan.sullnich.exam.model

import kotlinx.serialization.Serializable

@Serializable
data class Students (
    val fullTime: Int,
    val partTime: Int
)