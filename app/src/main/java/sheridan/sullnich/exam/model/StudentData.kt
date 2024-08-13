package sheridan.sullnich.exam.model

@Serializable
data class StudentData {

    val id: String,
    val name: String,
    val type: String,
    val established: String,
    val students: Students,
    val image: String,
    val location: String,



}