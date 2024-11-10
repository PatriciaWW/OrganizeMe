package com.ndege.patricia.organizeme

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.textfield.TextInputLayout
import com.ndege.basil_empapp.ApiHelper
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class CreatetaskActivity : AppCompatActivity() {
    private lateinit var btn_datepicker: Button
    private lateinit var edit_date: EditText
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_createtask)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Assigning id of RadioGroup



        btn_datepicker = findViewById(R.id.btn_datepicker)
        edit_date = findViewById(R.id.edit_date)

        btn_datepicker.setOnClickListener {
            showDatePickerDialog()
        }

        val btn_add_task: Button = findViewById(R.id.btn_add_task)
        val ed_task_name:EditText= findViewById(R.id.task_name)
        val ed_task_desc:EditText= findViewById(R.id.task_desc)
        val ed_due_date:EditText= findViewById(R.id.edit_date)
        radioGroup = findViewById(R.id.task_state)

        btn_add_task.setOnClickListener {

            // Getting the checked radio button id
            // from the radio group
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId

            // Assigning id of the checked radio button
            radioButton = findViewById(selectedOption) as RadioButton

            val task_name = ed_task_name.text.toString()
            val task_desc = ed_task_desc.text.toString()
            val due_date = ed_due_date.text.toString()


            val body = JSONObject()

            val prefs = getSharedPreferences("user", MODE_PRIVATE)
            val email = prefs.getString("email", "")
            body.put("task_name",task_name)
            body.put("task_desc",task_desc)
            body.put("due_date",due_date)
            body.put("task_state",radioButton.text.toString())
            body.put("email",email)

            val api = "https://PatriciaWachira.pythonanywhere.com/tasks"

            val helper = ApiHelper(applicationContext)
            helper.post(api,body,object : ApiHelper.CallBack{
                override fun onSuccess(result: String?) {

                }

                override fun onSuccess(result: JSONArray?) {

                }

                override fun onSuccess(result: JSONObject?) {
                    println(result.toString())
                    Log.d("response", "${result.toString()}")
                    Toast.makeText(applicationContext,"${result.toString()}",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, TaskActivity::class.java))

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