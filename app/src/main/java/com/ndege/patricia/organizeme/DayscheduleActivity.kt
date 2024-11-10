package com.ndege.patricia.organizeme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.ndege.basil_empapp.ApiHelper
import org.json.JSONArray
import org.json.JSONObject

class DayscheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dayschedule)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_add_activity: Button = findViewById(R.id.btn_add_schedule)
        btn_add_activity.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Loading page",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(applicationContext, AddscheduleActivity::class.java))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val itemAdapter = ScheduleAdapter(applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val helper = ApiHelper(applicationContext)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)
        val email = prefs.getString("email", "")
        Log.d("user", email.toString())


        val api = "http://PatriciaWachira.pythonanywhere.com/day_schedule?email=${email.toString()}"

        Log.d("user", api)

        helper.get(api, object : ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {
                // gson library: To convert JSONArray response(result) to List<Item>
                val gson = GsonBuilder().create()
                val list = gson.fromJson(result.toString(), Array<DaySchedule>::class.java).toList()
                itemAdapter.setRoomItems(list)
            }

            override fun onSuccess(result: String?) {

            }

            override fun onSuccess(result: JSONObject?) {

            }

            fun onFailure(result: String?) {

            }
        })

        recyclerView.adapter = itemAdapter
    }
}