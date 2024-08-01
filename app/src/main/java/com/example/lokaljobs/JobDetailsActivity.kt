package com.example.lokaljobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.lokaljobs.screens.JobsDetailScreen
import com.example.lokaljobs.ui.theme.LokalJobsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jobTitle = intent.getStringExtra("jobTitle") ?: ""
        val job_location_slug = intent.getStringExtra("job_location_slug") ?: ""
        val Salary = intent.getStringExtra("Salary") ?: ""
        val whatsapp_no = intent.getStringExtra("whatsapp_no") ?: ""
        val Qualification = intent.getStringExtra("Qualification") ?: ""
        val company_name = intent.getStringExtra("company_name") ?: ""
        val job_hours = intent.getStringExtra("job_hours") ?: ""
        val job_role = intent.getStringExtra("job_role") ?: ""


        setContent {
            LokalJobsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    JobsDetailScreen(jobTitle, job_location_slug, Salary, whatsapp_no, Qualification, company_name, job_hours, job_role)
                }
            }
        }
    }
}
