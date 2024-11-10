package com.ndege.patricia.organizeme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ndege.basil_empapp.ApiHelper
import org.json.JSONArray
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ed_username: EditText = findViewById(R.id.username)
        val ed_email: EditText = findViewById(R.id.email)
        val ed_phone_no: EditText = findViewById(R.id.phone_no)
        val ed_password1: EditText = findViewById(R.id.password1)
        val ed_password2: EditText = findViewById(R.id.password2)

        val btn_register: Button = findViewById(R.id.btn_register)
        btn_register.setOnClickListener {
            val username = ed_username.text.toString()
            val email = ed_email.text.toString()
            val phone_no = ed_phone_no.text.toString()
            val password1 = ed_password1.text.toString()
            val password2 = ed_password2.text.toString()

            if (username == "" || email == "" || phone_no == "" || password1 == "" || password2 == "") {
                Toast.makeText(
                    applicationContext,
                    "You haven't provided all the credentials",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(applicationContext, RegisterActivity::class.java))

//
            } else if (password1 == password2) {

                val body = JSONObject()
                body.put("username",username)
                body.put("email",email)
                body.put("phone_no",phone_no)
                body.put("password",password1)
                val api = "https://PatriciaWachira.pythonanywhere.com/register"

                val helper = ApiHelper(applicationContext)

                helper.post(api,body,object :ApiHelper.CallBack{
                    override fun onSuccess(result: String?) {

                    }

                    override fun onSuccess(result: JSONArray?) {

                    }

                    override fun onSuccess(result: JSONObject?) {
                            val prefs = getSharedPreferences("storage", MODE_PRIVATE)
                            val editor = prefs.edit()
                            editor.putString("username", username)
                            editor.putString("email", email)
                            editor.putString("phone_no", phone_no)
                            editor.putString("password", password1)
                            editor.apply()

                            Toast.makeText(applicationContext, "Successfully registered!Please login", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
//                        get user data and save in a shared prefs
//                        Intent to where the user should go after logging in

                    }
                })

            } else {
                Toast.makeText(
                    applicationContext,
                    "Your passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            }


        }
    }
}
