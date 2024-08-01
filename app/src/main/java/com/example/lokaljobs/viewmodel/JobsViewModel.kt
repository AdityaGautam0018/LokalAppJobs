package com.example.lokaljobs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobs.JobEntity
import com.example.lokaljobs.model.JobsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(private val repository: JobsRepository) : ViewModel() {
    private val _jobs = MutableLiveData<CustomResult<JobsData>>()
    val jobs: LiveData<CustomResult<JobsData>> = _jobs
    val bookmarkedJobs: LiveData<List<JobEntity>> = repository.getBookmarkedJobs()

    init {
        fetchJobs()
    }

    fun fetchJobs() {
        viewModelScope.launch {
            _jobs.value = try {
                val response = repository.getJobs()
                CustomResult.Success(response)
            } catch (e: Exception) {
                CustomResult.Failure(e)
            }
        }
    }


    fun bookmarkJob(jobId: Int) {
        viewModelScope.launch {
            repository.addBookmark(jobId)
        }
    }

    fun removeBookmark(jobId: Int) {
        viewModelScope.launch {
            repository.removeBookmark(jobId)
        }
    }
}
