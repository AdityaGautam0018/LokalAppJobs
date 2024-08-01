package com.example.lokaljobs.model

data class JobsData(
    val results: List<Result?>?
) {
    data class Result(
        val advertiser: Int?,
        val amount: String?,
        val button_text: String?,
        val city_location: Int?,
        val company_name: String?,
        val contact_preference: ContactPreference?,
        val content: String?,
        val contentV3: ContentV3?,
        val created_on: String?,
        val creatives: List<Creative?>?,
        val custom_link: String?,
        val enable_lead_collection: Boolean?,
        val experience: Int?,
        val expire_on: String?,
        val fb_shares: Int?,
        val fee_details: FeeDetails?,
        val fees_charged: Int?,
        val fees_text: String?,
        val id: Int?,
        val is_applied: Boolean?,
        val is_bookmarked: Boolean?,
        val is_job_seeker_profile_mandatory: Boolean?,
        val is_owner: Boolean?,
        val is_premium: Boolean?,
        val job_category: String?,
        val job_category_id: Int?,
        val job_hours: String?,
        val job_location_slug: String?,
        val job_role: String?,
        val job_role_id: Int?,
        val job_tags: List<JobTag?>?,
        val job_type: Int?,
        val locality: Int?,
        val locations: List<Location?>?,
        val num_applications: Int?,
        val openings_count: Int?,
        val other_details: String?,
        val premium_till: String?,
        val primary_details: PrimaryDetails?,
        val qualification: Int?,
        val question_bank_id: Any?,
        val salary_max: Int?,
        val salary_min: Int?,
        val screening_retry: Any?,
        val shares: Int?,
        val shift_timing: Int?,
        val should_show_last_contacted: Boolean?,
        val status: Int?,
        val tags: List<Any?>?,
        val title: String?,
        val translated_content: TranslatedContent?,
        val type: Int?,
        val updated_on: String?,
        val videos: List<Any?>?,
        val views: Int?,
        val whatsapp_no: String?
    ) {
        data class ContactPreference(
            val preference: Int?,
            val preferred_call_end_time: String?,
            val preferred_call_start_time: String?,
            val whatsapp_link: String?
        )

        data class ContentV3(
            val V3List: List<V3?>?
        ) {
            data class V3(
                val field_key: String?,
                val field_name: String?,
                val field_value: String?
            )
        }

        data class Creative(
            val creative_type: Int?,
            val `file`: String?,
            val image_url: String?,
            val order_id: Int?,
            val thumb_url: String?
        )

        data class FeeDetails(
            val V3: List<Any?>?
        )

        data class JobTag(
            val bg_color: String?,
            val text_color: String?,
            val value: String?
        )

        data class Location(
            val id: Int?,
            val locale: String?,
            val state: Int?
        )

        data class PrimaryDetails(
            val Experience: String?,
            val Fees_Charged: String?,
            val Job_Type: String?,
            val Place: String?,
            val Qualification: String?,
            val Salary: String?
        )

        class TranslatedContent
    }
}