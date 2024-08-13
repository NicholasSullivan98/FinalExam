package sheridan.sullnich.exam.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import sheridan.sullnich.exam.network.CollegeApiService

interface CollegeAppContainer{
    val collegeRepository: CollegeRepository
}

class defaultCollegeAppContainer : CollegeAppContainer {
    private val baseUrl = "https://my-course-exams.web.app/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CollegeApiService by lazy {
        retrofit.create(CollegeApiService::class.java)
    }

    override val collegeRepository: CollegeRepository by lazy {
        NetworkCollegeRepository(retrofitService)
    }
}