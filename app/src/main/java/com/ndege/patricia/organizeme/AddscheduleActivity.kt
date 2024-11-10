package com.ndege.patricia.organizeme

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

class AddscheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addschedule)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_pickstart = findViewById<Button>(R.id.pickstart)
        val start_time = findViewById<TextView>(R.id.start_time)


        btn_pickstart.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                start_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val btn_pickend = findViewById<Button>(R.id.pickend)
        val end_time     = findViewById<TextView>(R.id.end_time)

        btn_pickend.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                end_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val btn_add_schedule: Button = findViewById(R.id.btn_add_schedule)
        val ed_activity_name: EditText = findViewById(R.id.activity_name)
        val ed_activity_desc: EditText = findViewById(R.id.activity_desc)
        val ed_start_time: TextView = findViewById(R.id.start_time)
        val ed_end_time:TextView = findViewById(R.id.end_time)

        btn_add_schedule.setOnClickListener {

            val activity_name = ed_activity_name.text.toString()
            val activity_desc = ed_activity_desc.text.toString()
            val  start_time = ed_start_time.text.toString()
            val end_time = ed_end_time.text.toString()

            val body = JSONObject()
            val prefs = getSharedPreferences("user", MODE_PRIVATE)
            val email = prefs.getString("email", "")

            body.put("activity_name",activity_name)
            body.put("activity_desc",activity_desc)
            body.put("start_time",start_time)
            body.put("end_time",end_time)
            body.put("email",email)

            val api = "https://PatriciaWachira.pythonanywhere.com/day_schedule"

            val helper = ApiHelper(applicationContext)
            helper.post(api,body,object : ApiHelper.CallBack{
                override fun onSuccess(result: String?) {

                }

                override fun onSuccess(result: JSONArray?) {

                }

                override fun onSuccess(result: JSONObject?) {
                    println(result.toString())
                        println(result.toString())
                        Log.d("response", "${result.toString()}")
                        Toast.makeText(applicationContext,"${result.toString()}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, DayscheduleActivity::class.java))

                }
            })
        }

    }
}