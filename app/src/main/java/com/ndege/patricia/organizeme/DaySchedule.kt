package com.ndege.patricia.organizeme

data class DaySchedule (
    var email: String,
    var activity_name: String,
    var activity_desc: String,
    var start_time: String,
    var end_time: String,
    var schedule_id:Int
)