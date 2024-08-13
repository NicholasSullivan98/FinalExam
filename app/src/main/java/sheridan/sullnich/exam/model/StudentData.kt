package sheridan.sullnich.exam.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentData (
    val name: String,
    val type: String,
    val established: Int,
    val students: Students,
    val image: String,
    val location: String,
)