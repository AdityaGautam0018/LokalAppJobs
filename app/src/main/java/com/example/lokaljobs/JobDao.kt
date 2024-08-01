package com.example.lokaljobs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getBookmarkedJobs(): LiveData<List<JobEntity>>

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun deleteJob(jobId: Int)
}

// JobEntity.kt
@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val job_location_slug: String,
    val salary_min: Int?,
    val whatsapp_no: String?
)
