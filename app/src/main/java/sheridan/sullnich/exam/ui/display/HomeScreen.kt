package sheridan.sullnich.exam.ui.display

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import sheridan.sullnich.exam.R
import sheridan.sullnich.exam.model.StudentData
import sheridan.sullnich.exam.model.Students
import sheridan.sullnich.exam.ui.theme.ExamTheme

@Composable
fun HomeScreen(
    collegeUiState: CollegeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (collegeUiState) {
        is CollegeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is CollegeUiState.Success -> PhotosGridScreen(
            collegeUiState.data, modifier = modifier.fillMaxWidth()
        )
        is CollegeUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PhotosGridScreen(
    data: StudentData,
    modifier: Modifier = Modifier
) {
    MarsPhotoCard(
        data,
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1.5f)
    )
}

@Composable
fun MarsPhotoCard(data: StudentData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data("https://my-course-exams.web.app/images/"+data.image)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.college_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(250.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "College Name: " + data.name)
                Text(text = "School Type: " + data.type)
                Text(text = "Established: " + data.established)
                Text(text = "Full Time Students: " + data.students.fullTime)
                Text(text = "Part Time Students: " + data.students.partTime)
                Text(text = "Location: " + data.location)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    ExamTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ExamTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    ExamTheme {
        val mockStudents = Students(40000, 32000)
        val mockData = StudentData("Sheridan College", type = "Public", established = 1967, students = mockStudents, image = " ", location = "Ontario, Canada")
        PhotosGridScreen(mockData)
    }
}