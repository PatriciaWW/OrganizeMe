package com.ndege.patricia.organizeme

data class Appointment (
    var email: String,
    var appointment_name: String,
    var appointment_desc: String,
    var location: String,
    var date: String,
    var time: String,
    var appointment_id:Int
)