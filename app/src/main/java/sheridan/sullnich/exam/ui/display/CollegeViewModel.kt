package sheridan.sullnich.exam.ui.display

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import sheridan.sullnich.exam.CollegeApplication
import sheridan.sullnich.exam.data.CollegeRepository
import sheridan.sullnich.exam.model.StudentData
import java.io.IOException

sealed interface CollegeUiState {
    data class Success(val data: StudentData) : CollegeUiState
    object Error : CollegeUiState
    object Loading : CollegeUiState
}

class CollegeViewModel(private val collegeRepository: CollegeRepository) : ViewModel() {

    var collegeUiState: CollegeUiState by mutableStateOf(CollegeUiState.Loading)
        private set


    init {
        getStudentData()
    }

    fun getStudentData() {
        viewModelScope.launch {
            collegeUiState = CollegeUiState.Loading
            collegeUiState = try {
                CollegeUiState.Success(collegeRepository.getStudentData())
            } catch (e: IOException) {
                CollegeUiState.Error
            } catch (e: HttpException) {
                CollegeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CollegeApplication)
                val collegeRepository = application.container.collegeRepository
                CollegeViewModel(collegeRepository = collegeRepository)
            }
        }
    }
}