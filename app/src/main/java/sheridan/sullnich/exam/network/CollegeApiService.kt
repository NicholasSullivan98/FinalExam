package sheridan.sullnich.exam.network

interface CollegeApiService {

    @GET("data/college.json")
    suspend fun getData(): StudentData
}