package com.ndege.patricia.organizeme

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ndege.basil_empapp.ApiHelper
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar

class Create_eventActivity : AppCompatActivity() {

    private lateinit var btn_datepicker: Button 
    private lateinit var edit_date: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_event)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mPickTimeBtn = findViewById<Button>(R.id.pickTimeBtn)
        val textView     = findViewById<TextView>(R.id.timeTv)

        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        btn_datepicker = findViewById(R.id.btn_datepicker)
        edit_date = findViewById(R.id.edit_date)

        btn_datepicker.setOnClickListener {
            showDatePickerDialog()
        }

        val btn_create_event: Button = findViewById(R.id.btn_create_event)
        val ed_event_name:EditText= findViewById(R.id.event_name)
        val ed_event_desc:EditText= findViewById(R.id.event_desc)
        val ed_location: EditText =findViewById(R.id.location)
        val ed_date:EditText= findViewById(R.id.edit_date)
        val ed_time:TextView = findViewById(R.id.timeTv)

        btn_create_event.setOnClickListener {

            val event_name = ed_event_name.text.toString()
            val event_desc = ed_event_desc.text.toString()
            val location = ed_location.text.toString()
            val date = ed_date.text.toString()
            val time = ed_time.text.toString()

            val body = JSONObject()

            val prefs = getSharedPreferences("user", MODE_PRIVATE)
            val email = prefs.getString("email", "")

            body.put("event_name",event_name)
            body.put("event_desc",event_desc)
            body.put("location",location)
            body.put("date",date)
            body.put("time",time)
            body.put("email",email)

            val api = "https://PatriciaWachira.pythonanywhere.com/events"

            val helper = ApiHelper(applicationContext)
            helper.post(api,body,object : ApiHelper.CallBack{
                override fun onSuccess(result: String?) {

                }

                override fun onSuccess(result: JSONArray?) {

                }

                override fun onSuccess(result: JSONObject?) {
                        println(result.toString())
                        Log.d("response", "${result.toString()}")
                        Toast.makeText(applicationContext,"${result.toString()}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, EventActivity::class.java))

                }
            })
        }

    }

    private fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                edit_date.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}