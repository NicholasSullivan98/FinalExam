package sheridan.sullnich.exam.data

import sheridan.sullnich.exam.model.StudentData
import sheridan.sullnich.exam.network.CollegeApiService

interface CollegeRepository{
    suspend fun getStudentData(): StudentData
}

class NetworkCollegeRepository(
    private val collegeApiService: CollegeApiService
) : CollegeRepository {

    override suspend fun getStudentData(): StudentData = collegeApiService.getData()

}