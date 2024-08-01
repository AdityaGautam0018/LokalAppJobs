package com.example.lokaljobs.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lokaljobs.JobEntity
import com.example.lokaljobs.model.JobsData
import com.example.lokaljobs.viewmodel.JobsViewModel

@Composable
fun BookmarksScreen(viewModel: JobsViewModel) {
    val bookmarkedJobs by viewModel.bookmarkedJobs.observeAsState(emptyList())
    Log.d("BookmarksScreen", "Bookmarked Jobs: $bookmarkedJobs")


    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bookmarkedJobs) { jobEntity ->
            JobItem(job = jobEntity.toJobsDataResult(), viewModel = viewModel)
        }
    }

}

fun JobEntity.toJobsDataResult(): JobsData.Result {
    return JobsData.Result(
        advertiser = null,
        amount = null,
        button_text = null,
        city_location = null,
        company_name = null,
        contact_preference = null,
        content = null,
        contentV3 = null,
        created_on = null,
        creatives = null,
        custom_link = null,
        enable_lead_collection = null,
        experience = null,
        expire_on = null,
        fb_shares = null,
        fee_details = null,
        fees_charged = null,
        fees_text = null,
        id = id,
        is_applied = null,
        is_bookmarked = true,
        is_job_seeker_profile_mandatory = null,
        is_owner = null,
        is_premium = null,
        job_category = null,
        job_category_id = null,
        job_hours = null,
        job_location_slug = job_location_slug,
        job_role = null,
        job_role_id = null,
        job_tags = null,
        job_type = null,
        locality = null,
        locations = null,
        num_applications = null,
        openings_count = null,
        other_details = null,
        premium_till = null,
        primary_details = null,
        qualification = null,
        question_bank_id = null,
        salary_max = null,
        salary_min = salary_min,
        screening_retry = null,
        shares = null,
        shift_timing = null,
        should_show_last_contacted = null,
        status = null,
        tags = null,
        title = title,
        translated_content = null,
        type = null,
        updated_on = null,
        videos = null,
        views = null,
        whatsapp_no = whatsapp_no
    )
}