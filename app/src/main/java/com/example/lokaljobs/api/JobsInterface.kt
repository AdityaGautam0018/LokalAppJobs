package com.example.lokaljobs.api

import com.example.lokaljobs.model.JobsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JobsInterface {
    @GET("/common/jobs?page=1")
    suspend fun getJobs(): Response<JobsData>

    @GET("/common/jobs/{id}")
    suspend fun getJobById(@Path("id") jobId: Int): Response<JobsData.Result>
}