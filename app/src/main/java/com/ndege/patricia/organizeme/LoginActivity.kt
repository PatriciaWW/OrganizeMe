package com.ndege.patricia.organizeme

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ed_email: EditText = findViewById(R.id.email)
        val ed_password: EditText = findViewById(R.id.password)

        val btn_login:Button = findViewById(R.id.btn_login)
        btn_login.setOnClickListener {
            val email = ed_email.text.toString()
            val password = ed_password.text.toString()

            val body = JSONObject()
            body.put("email",email)
            body.put("password",password)
            val api = "https://PatriciaWachira.pythonanywhere.com/login"

            val helper = ApiHelper(applicationContext)
            helper.post(api,body,object :ApiHelper.CallBack{
                override fun onSuccess(result: String?) {

                }

                override fun onSuccess(result: JSONArray?) {

                }

                override fun onSuccess(result: JSONObject?) {
                    println(result.toString())
                    if(result!!.has("user")){
                        val prefs = getSharedPreferences("user", MODE_PRIVATE)
                        val editor = prefs.edit()
                        val user_data = result.getJSONObject("user")
                        editor.putString("email",user_data.getString("email"))
                        editor.putString("phone_no",user_data.getString("phone_no"))
                        editor.putString("username",user_data.getString("username"))
                        editor.apply()

                        Toast.makeText(applicationContext, "Successfully logged in to your virtual PA", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
//                        get user data and save in a shared prefs
//                        Intent to where the user should go after logging in

                    } else {
                        println(result.toString())
                        Log.d("response", "${result.toString()}")
                        Toast.makeText(applicationContext,"${result.toString()}",Toast.LENGTH_SHORT).show()

                    }
                }
            })


//            val prefs = this.getSharedPreferences("storage", MODE_PRIVATE)
//            val sp_username = prefs.getString("username","")
//            val sp_password = prefs.getString("password","")
//            if (username == "" || password ==""){
//                Toast.makeText(applicationContext,"You haven't provided all the credentials", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(applicationContext,LoginActivity::class.java))
//
//            }
//
//            else if(sp_username != username ){
//                Toast.makeText(applicationContext,"Username not found in the system try again!", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(applicationContext,LoginActivity::class.java))
//            }
//
//            else if (sp_password != password){
//                Toast.makeText(applicationContext,"Incorrect password", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(applicationContext,LoginActivity::class.java))
//            }
//
//
//            else {
//                val names = prefs.getString("name","")
//                Toast.makeText(applicationContext,"Hello $names welcome to organizeme",Toast.LENGTH_SHORT).show()
//                startActivity(Intent(applicationContext,MainActivity::class.java))
//
//            }
        }

    }
}