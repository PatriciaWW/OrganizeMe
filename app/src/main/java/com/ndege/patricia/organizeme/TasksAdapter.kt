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
import org.json.JSONArray
import org.json.JSONObject

//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions


class TasksAdapter( val context: Context) :
    RecyclerView.Adapter<TasksAdapter.ItemViewHolder>() {
    private var itemList: List<Task> = listOf()

    private fun deleteTask(taskId: Int) {
        val body = JSONObject()
        body.put("task_id", taskId)

        val api = "https://PatriciaWachira.pythonanywhere.com/tasks/$taskId"
        val helper = ApiHelper(context)
        helper.delete(api, body, object : ApiHelper.CallBack {
            override fun onSuccess(result: String?) {
                // Remove the task from the list
                itemList = itemList.filter { it.task_id != taskId }
                // Notify the adapter that the data has changed
                notifyDataSetChanged()
            }

            override fun onSuccess(result: JSONArray?) {
                // Handle success with JSONArray
            }

            override fun onSuccess(result: JSONObject?) {
                // Handle success with JSONObject
            }

            fun onError(error: String?) {
                // Handle error
            }
        })
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task_name: TextView = itemView.findViewById(R.id.task_name)
        val task_desc: TextView = itemView.findViewById(R.id.task_desc)
        val due_date: TextView = itemView.findViewById(R.id.due_date)
        val task_state: TextView = itemView.findViewById(R.id.task_state)
        val edit: ImageView = itemView.findViewById(R.id.edit_task)
        val delete: ImageView = itemView.findViewById(R.id.delete_task)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.taskspage, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var currentItem = itemList[position]

        holder.task_name.text = currentItem.task_name
        holder.task_desc.text = currentItem.task_desc
        holder.due_date.text = currentItem.due_date
        holder.task_state.text = currentItem.task_state
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

            val prefs: SharedPreferences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("task_name", currentItem.task_name)
            editor.putString("task_id",currentItem.task_id.toString())
            editor.putString("task_desc", currentItem.task_desc)
            editor.putString("due_date", currentItem.due_date)
            editor.putString("task_state", currentItem.task_state)
            editor.apply()

            holder.edit.setOnClickListener {
                Toast.makeText(
                    context,
                    "Loading page",
                    Toast.LENGTH_SHORT
                ).show()
                val x = Intent(context, Edit_tasksActivity::class.java)
                x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(x)

            }

            holder.delete.setOnClickListener {
                val helper = ApiHelper(context)
                val api = "https://PatriciaWachira.pythonanywhere.com/tasks"
                val body = JSONObject()
                body.put("task_id", currentItem.task_id)
                helper.delete(api, body, object : ApiHelper.CallBack{
                    override fun onSuccess(result: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(result: JSONObject?) {

                        val prefs = context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                        val email = prefs.getString("email", "")
                        Log.d("user", email.toString())

                        val api = "https://PatriciaWachira.pythonanywhere.com/tasks?email=${email.toString()}"
                        helper.get(api, object : ApiHelper.CallBack{
                            override fun onSuccess(result: JSONArray?) {
                                println(result.toString())
                                // gson library: To convert JSONArray response(result) to List<Item>
                                val gson = GsonBuilder().create()
                                val list = gson.fromJson(result.toString(), Array<Task>::class.java).toList()

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

            //create SharedPreference and Save the data of the View Holder
            // On SingleActivity, we get data the saved data from SPreferences



            // Intent to the SingleActivity

//            val intent = Intent(context, SingleActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setRoomItems(itemList: List<Task>){
        this.itemList = itemList
        notifyDataSetChanged()
    }

}