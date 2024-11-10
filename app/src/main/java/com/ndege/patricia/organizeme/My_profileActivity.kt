package com.ndege.patricia.organizeme

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import android.app.Activity
//import android.content.ActivityNotFoundException
//import android.content.Intent
//import android.graphics.Bitmap
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import coil.load
//import coil.transform.CircleCropTransformation
//import android.provider.MediaStore
//import android.provider.Settings
//import android.util.Log
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.AsyncHttpResponseHandler
//import com.loopj.android.http.RequestParams
//import cz.msebera.android.httpclient.Header
//import cz.msebera.android.httpclient.entity.StringEntity
//import org.json.JSONObject
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import java.util.Base64

class My_profileActivity : AppCompatActivity() {

//    private val CAMERA_REQUEST_CODE = 1
//    private  val GALLERY_REQUEST_CODE = 2
//    private lateinit var imagepath: String
//    private lateinit var imagefile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val imageView : ImageView = findViewById(R.id.imageView)
//        imageView.setOnClickListener {
//            val pictureDialog = AlertDialog.Builder(this)
//            pictureDialog.setTitle("Select Action")
//            val pictureDialogItem = arrayOf("Select photo from Gallery ","Capture photo using Camera")
//            pictureDialog.setItems(pictureDialogItem){dialog , which ->
//
//                when(which){
//                    0 -> gallery()
//                    1 -> camera()
//                }
//            }
//            pictureDialog.show()
//        }
    }

//    private fun gallery(){
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, GALLERY_REQUEST_CODE)
//    }
//
//    private fun camera(){
//        val intent  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent , CAMERA_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == Activity.RESULT_OK){
//            when(requestCode){
//                CAMERA_REQUEST_CODE -> {
//
//                    val bitmap = data?.extras?.get("data") as Bitmap
//
//                    val btnImage : Button = findViewById(R.id.btnImage)
//                    btnImage.setOnClickListener {
//                        postImageToApi(bitmap)
//                        var imagePath = saveImageToFile(bitmap)
//
////                        PrefsHelper.savePrefs(this,"image",imagePath)
//                        imagepath = imagePath
//                        println(imagepath)
//                    }
//                    // we are using coroutine image loader  (coil)
//
//                    val imageView : ImageView = findViewById(R.id.imageView)
//                    imageView.load(bitmap){
//                        crossfade(true)
//                        crossfade(1000)
//                        transformations(CircleCropTransformation())
//
//                    }
//                }
//
//                GALLERY_REQUEST_CODE -> {
//
//                    val uri: Uri? = data?.data
//                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
//
//                    val btnImage : Button = findViewById(R.id.btnImage)
//                    btnImage.setOnClickListener {
//                        postImageToApi(bitmap)
//                        val imagePath = saveImageToFile(bitmap)
//                        imagepath = imagePath
//
//                        println(imagepath)
////                        PrefsHelper.savePrefs(this,"image",imagePath)
//                    }
//
//
//                    val imageView : ImageView = findViewById(R.id.imageView)
//                    imageView.load(data?.data){
//                        crossfade(true)
//                        crossfade(1000)
//                        transformations(CircleCropTransformation())
//                    }
//                }
//            }
//        }
//    }
//
//    private fun showRotationalDialogForPermission(){
//        AlertDialog.Builder(this)
//            .setMessage("You have turned off Permissions for this feature . Turn on Permissions in App Settings")
//            .setPositiveButton("Go to settings"){_,_ ->
//                try {
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                    val uri = Uri.fromParts("package", packageName , null)
//                    intent.data = uri
//                    startActivity(intent)
//
//                }catch (e: ActivityNotFoundException){
//                    e.printStackTrace()
//                }
//
//
//            }
//            .setNegativeButton("Cancel"){dialog,_ ->
//                dialog.dismiss()
//            }.show()
//    }
//
//    private fun postImageToApi(bitmap: Bitmap){
//        val progressbar : ProgressBar = findViewById(R.id.progressbar)
//        progressbar.visibility = View.VISIBLE
//        val client = AsyncHttpClient(true, 80, 443)
//        val requestParams = RequestParams()
//        val api = "https://ndegeapi.pythonanywhere.com/products"
////        val api ="http://192.168.88.237:5000/products"
//        val body = JSONObject()
//
//        try {
//            // Convert the bitmap to a file and add it to the request params
//            val imageFile = createImageFile(bitmap)
//            val encodedString = encodeImageToBase64(imageFile)
//
//
//            val prod_name : EditText = findViewById(R.id.name)
//            val prod_cost : EditText = findViewById(R.id.cost)
//            val prod_desc : EditText = findViewById(R.id.desc)
//            body.put("prod_name",prod_name.text.toString())
//            body.put("prod_cost",prod_cost.text.toString())
//            body.put("prod_desc",prod_desc.text.toString())
//            body.put("image", encodedString)
//            imagefile = imageFile
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        val con_body = StringEntity(body.toString())
//        Log.d("res", con_body.toString())
//
//        client.post(this, api, con_body,"application/json", object : AsyncHttpResponseHandler() {
//
//
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?
//            ) {
//                Log.d("res", responseBody.toString())
//                progressbar.visibility = View.GONE
//                Toast.makeText(applicationContext, "You have a successfully uploaded product", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//                Log.d("res", error.toString())
//                progressbar.visibility = View.GONE
//                Toast.makeText(applicationContext, "An error occurred. $error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    @Throws(IOException::class)
//    private fun createImageFile(bitmap: Bitmap): File {
//        val cacheDir = applicationContext.cacheDir
//        val imageFile = File.createTempFile("image", ".jpg", cacheDir)
//        val outputStream = FileOutputStream(imageFile)
//
//        // Compress bitmap to JPEG with 100% quality
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//        outputStream.flush()
//        outputStream.close()
//
//        return imageFile
//    }
//    private fun saveImageToFile(bitmap: Bitmap): String {
//        try {
//            val imageFile = createImageFile(bitmap)
//            return imageFile.absolutePath
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return ""
//    }
//
//    fun encodeImageToBase64(file: File): String {
//        val bytes = file.readBytes()
//        return Base64.getEncoder().encodeToString(bytes)
//    }

}