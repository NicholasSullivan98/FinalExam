package sheridan.sullnich.exam.network

import retrofit2.http.GET
import sheridan.sullnich.exam.model.StudentData

interface CollegeApiService {

    @GET("data/college.json")
    suspend fun getData(): StudentData
}