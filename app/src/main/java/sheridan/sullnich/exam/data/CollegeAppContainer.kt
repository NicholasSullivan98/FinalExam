package sheridan.sullnich.exam.data

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

    override val collegeRepository: CollegeReposiotry by lazy {
        NetworkCollegeRepository(retrofitService)
    }
}