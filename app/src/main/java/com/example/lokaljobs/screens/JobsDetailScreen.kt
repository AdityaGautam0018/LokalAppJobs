package com.example.lokaljobs.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun JobsDetailScreen(
    jobTitle: String,
    job_location_slug: String,
    Salary: String,
    whatsapp_no: String,
    Qualification: String,
    company_name: String,
    job_hours: String,
    job_role: String
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = jobTitle ?: "No Title",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = job_location_slug.let { "Location: $it" },
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = Salary.let { "Salary: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = whatsapp_no.let { "Phone no: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = Qualification.let { "Qualification: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = company_name.let { "Company Name: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = job_hours.let { "Job Hours: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = job_role.let { "Job Role: $it" },
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}