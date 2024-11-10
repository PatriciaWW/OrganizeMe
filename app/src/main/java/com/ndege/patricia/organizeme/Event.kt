package com.ndege.patricia.organizeme

data class Event (
    var email: String,
    var event_name: String,
    var event_desc: String,
    var location: String,
    var date: String,
    var time: String,
    var event_id:Int
)
