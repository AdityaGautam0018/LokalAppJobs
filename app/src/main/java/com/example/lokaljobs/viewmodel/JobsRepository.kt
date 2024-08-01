package com.example.lokaljobs.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.lokaljobs.JobDao
import com.example.lokaljobs.JobEntity
import com.example.lokaljobs.api.JobsInterface
import com.example.lokaljobs.model.JobsData
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val api: JobsInterface,
    private val jobDao: JobDao
) {
    suspend fun getJobs(): JobsData {
        val response = api.getJobs()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error: ${response.message()}")
        }
    }


    suspend fun updateJob(job: JobsData.Result) {
        val jobEntity = JobEntity(
            id = job.id ?: -1,
            title = job.title ?: "",
            job_location_slug = job.job_location_slug ?: "",
            salary_min = job.salary_min,
            whatsapp_no = job.whatsapp_no ?: ""
        )
        jobDao.insertJob(jobEntity)
    }

    fun getBookmarkedJobs(): LiveData<List<JobEntity>> {
        return jobDao.getBookmarkedJobs()
    }

    suspend fun addBookmark(jobId: Int) {
        val response = api.getJobById(jobId)
        if (response.isSuccessful) {
            val job = response.body()
            if (job != null) {
                val jobEntity = JobEntity(
                    id = job.id ?: -1,
                    title = job.title ?: "",
                    job_location_slug = job.job_location_slug ?: "",
                    salary_min = job.salary_min,
                    whatsapp_no = job.whatsapp_no ?: ""
                )
                jobDao.insertJob(jobEntity)
                Log.d("JobsRepository", "Inserting JobEntity: $jobEntity")
            } else {
                throw Exception("Job not found")
            }
        } else {
            throw Exception("Error fetching job details")
        }
    }

    suspend fun removeBookmark(jobId: Int) {
        jobDao.deleteJob(jobId)
    }
}
