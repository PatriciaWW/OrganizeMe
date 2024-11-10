package com.ndege.patricia.organizeme

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.ndege.basil_empapp.ApiHelper
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject

//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions


class ScheduleAdapter( val context: Context) :
    RecyclerView.Adapter<ScheduleAdapter.ItemViewHolder>() {
    private var itemList: List<DaySchedule> = listOf()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activity_name: TextView = itemView.findViewById(R.id.activity_name)
        val activity_desc: TextView = itemView.findViewById(R.id.activity_desc)
        val start_time: TextView = itemView.findViewById(R.id.start_time)
        val end_time: TextView = itemView.findViewById(R.id.end_time)
        val edit: ImageView = itemView.findViewById(R.id.edit_activity)
        val delete: ImageView = itemView.findViewById(R.id.delete_activity)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedulepage, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var currentItem = itemList[position]

        holder.activity_name.text = currentItem.activity_name
        holder.activity_desc.text = currentItem.activity_desc
        holder.start_time.text = currentItem.start_time
        holder.end_time.text = currentItem.end_time

//        Log.d("image", currentItem.image_name)
//        var imageurl = "https://ndegeapi.pythonanywhere.com/static/images/"+currentItem.image_name
//        Picasso.get()
//            .load(imageurl)
//            .fit()
//            .into(holder.imageView);


//        Glide.with(context)
//            .load(imageurl)
//            .into(holder.imageView)

        holder.itemView.setOnClickListener {

            //create SharedPreference and Save the data of the View Holder
            // On SingleActivity, we get data the saved data from SPreferences

            val prefs: SharedPreferences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("activity_name", currentItem.activity_name)
            editor.putString("activity_desc", currentItem.activity_desc)
            editor.putString("start_time",currentItem.start_time)
            editor.putString("end_time", currentItem.end_time)
            editor.apply()

            holder.edit.setOnClickListener {
                Toast.makeText(
                    context,
                    "Loading page",
                    Toast.LENGTH_SHORT
                ).show()
                val x = Intent(context, Edit_scheduleActivity::class.java)
                x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(x)

            }

            holder.delete.setOnClickListener {
                val helper = ApiHelper(context)
                val api = "https://PatriciaWachira.pythonanywhere.com/events"
                val body = JSONObject()
                body.put("schedule_id", currentItem.schedule_id)
                helper.delete(api, body, object : ApiHelper.CallBack{
                    override fun onSuccess(result: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(result: JSONObject?) {

                        val prefs = context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                        val email = prefs.getString("email", "")
                        Log.d("user", email.toString())

                        val api = "https://PatriciaWachira.pythonanywhere.com/dayschedule?email=${email.toString()}"
                        helper.get(api, object : ApiHelper.CallBack{
                            override fun onSuccess(result: JSONArray?) {
                                println(result.toString())
                                // gson library: To convert JSONArray response(result) to List<Item>
                                val gson = GsonBuilder().create()
                                val list = gson.fromJson(result.toString(), Array<DaySchedule>::class.java).toList()

                                setRoomItems(list)
                            }

                            override fun onSuccess(result: String?) {

                            }

                            override fun onSuccess(result: JSONObject?) {

                            }

                            fun onFailure(result: String?) {

                            }
                        })
                    }

                    override fun onSuccess(result: JSONArray?) {
                        TODO("Not yet implemented")
                    }

                })
            }

            // Intent to the SingleActivity

//            val intent = Intent(context, SingleActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setRoomItems(itemList: List<DaySchedule>){
        this.itemList = itemList
        notifyDataSetChanged()
    }

}