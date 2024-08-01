package com.example.lokaljobs.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ktx.R
import androidx.navigation.NavHostController
import com.example.lokaljobs.JobDetailsActivity
import com.example.lokaljobs.model.JobsData
import com.example.lokaljobs.viewmodel.CustomResult
import com.example.lokaljobs.viewmodel.JobsViewModel

@Composable
fun JobsScreen(viewModel: JobsViewModel) {
    val jobsResult by viewModel.jobs.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchJobs()
    }

    when (jobsResult) {
        is CustomResult.Success -> {
            val jobs = (jobsResult as CustomResult.Success<JobsData>).data.results.orEmpty()
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(jobs.orEmpty()) { job ->
                    job?.let { JobItem(it, viewModel) }
                }
            }
        }

        is CustomResult.Failure -> {
            val exception = (jobsResult as CustomResult.Failure).exception
            Text(
                text = "Error: ${exception.message}",
                modifier = Modifier.padding(16.dp)
            )
        }

        null -> {

        }
    }
}

@Composable
fun JobItem(job: JobsData.Result, viewModel: JobsViewModel) {
    val context = LocalContext.current
    val bookmarkedJobs by viewModel.bookmarkedJobs.observeAsState(emptyList())
    val isBookmarked = remember { mutableStateOf(bookmarkedJobs.any { it.id == job.id }) }

    LaunchedEffect(job.id) {
        isBookmarked.value = bookmarkedJobs.any { it.id == job.id }
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            val intent = Intent(context, JobDetailsActivity::class.java).apply {
                putExtra("jobTitle", job.title)
                putExtra("job_location_slug", job.job_location_slug)
                putExtra("Salary", job.primary_details?.Salary)
                putExtra("whatsapp_no", job.whatsapp_no)
                putExtra("Qualification", job.primary_details?.Qualification)
                putExtra("company_name", job.company_name)
                putExtra("job_hours", job.job_hours)
                putExtra("job_role", job.job_role)


            }
            context.startActivity(intent)
        }) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = job.title ?: "No Title",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = job.job_location_slug ?: "No Location",
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = job.primary_details?.Salary?.let { "Salary: $it" } ?: "No Salary",
                        modifier = Modifier.padding(top = 10.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = job.whatsapp_no.let { "Phone no: $it" } ?: "No Phone Data",
                        modifier = Modifier.padding(top = 10.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconToggleButton(
                    checked = isBookmarked.value,
                    onCheckedChange = {
                        isBookmarked.value = it
                        if (it) {
                            viewModel.bookmarkJob(job.id ?: -1)
                        } else {
                            viewModel.removeBookmark(job.id ?: -1)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isBookmarked.value) {
                                com.example.lokaljobs.R.drawable.ic_bookmarkfilled
                            } else {
                                com.example.lokaljobs.R.drawable.ic_bookmarkout
                            }
                        ),
                        contentDescription = "Bookmark Icon",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun Bookmark(job: JobsData.Result, viewModel: JobsViewModel, modifier: Modifier = Modifier) {
    val isBookmarked = job.is_bookmarked ?: false
    val checkedState = remember { mutableStateOf(isBookmarked) }
    LaunchedEffect(job.is_bookmarked) {
        checkedState.value = job.is_bookmarked ?: false
    }

    IconToggleButton(
        checked = checkedState.value,
        onCheckedChange = { newState ->
            checkedState.value = newState
            if (newState) {
                job.id?.let { viewModel.bookmarkJob(it) }
            } else {
                job.id?.let { viewModel.removeBookmark(it) }
            }
        },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(
                id = if (checkedState.value) {
                    com.example.lokaljobs.R.drawable.ic_bookmarkfilled
                } else {
                    com.example.lokaljobs.R.drawable.ic_bookmarkout
                }
            ),
            contentDescription = "Bookmark Icon",
            tint = Color.Black
        )
    }
}