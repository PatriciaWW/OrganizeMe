package com.ndege.patricia.organizeme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {

    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // initializing variables of
        // list view with their ids.
        dateTV = findViewById<TextView>(R.id.idTVDate)
        calendarView = findViewById<CalendarView>(R.id.calendarView)

        // on below line we are adding set on
        // date change listener for calendar view.
        calendarView
            .setOnDateChangeListener(
                CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                    // In this Listener we are getting values
                    // such as year, month and day of month
                    // on below line we are creating a variable
                    // in which we are adding all the variables in it.
                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)

                    // set this date in TextView for Display
                    dateTV.setText(Date)
                })


        calendarView.isClickable = true
        calendarView.setOnClickListener {
            Log.e("event", "click")
        }
        calendarView.setOnLongClickListener {
            Log.e("event", "long click")
            false
        }
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Log.e("event", "datachanged")
        }

        val card_tasks:CardView= findViewById(R.id.card_tasks)
        card_tasks.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, TaskActivity::class.java))
        }

        val card_events:CardView= findViewById(R.id.card_events)
        card_events.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, EventActivity::class.java))
        }

        val card_appointment:CardView= findViewById(R.id.card_appointments)
        card_appointment.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, AppointmentsActivity::class.java))
        }

        val card_schedule:CardView= findViewById(R.id.card_schedule)
        card_schedule.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, DayscheduleActivity::class.java))
        }

        val bottom_sheet: FrameLayout = findViewById(R.id.bottom_sheet)
        BottomSheetBehavior.from(bottom_sheet).apply {
            peekHeight = 330
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val btn_register:Button= findViewById(R.id.btn_register)
        btn_register.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        val btn_login:Button= findViewById(R.id.btn_login)
        btn_login.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }





//        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//            .getBoolean("isFirstRun", true)

//        if (isFirstRun) {
//            //show start activity
//            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
//            Toast.makeText(this@MainActivity, "First Run", Toast.LENGTH_LONG)
//                .show()
//        }
//
//
//        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
//            .putBoolean("isFirstRun", false).commit()

    }
}