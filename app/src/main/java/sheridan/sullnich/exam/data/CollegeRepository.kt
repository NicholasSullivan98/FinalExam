package sheridan.sullnich.exam.data

interface CollegeRepository{
    suspend fun getStudentData(): StudentData
}

class NetworkCollegeRepository(
    private val collegeApiService: CollegeApiService
) : CollegeRepository {

    override suspend fun getStudentData(): StudentData = collegeApiService.getData()

}