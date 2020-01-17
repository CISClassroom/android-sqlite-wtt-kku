package com.example.androidsqlite

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB_NAME = "todo.db"

        bt_add.setOnClickListener{
            val dbHelper = DBHelper(this,"todo.db",null,1)
            val newTask:Task = Task(editText.text.toString())
            dbHelper.AddTask(newTask)

            Toast.makeText(this,editText.text.toString()+" Add To DB",Toast.LENGTH_SHORT).show()
        }

        bt_search.setOnClickListener{
            val dbHelper = DBHelper(this,DB_NAME,null,1)
            val data:Cursor? = dbHelper.GetAllTask()

            data!!.moveToFirst()
            TextView.text = ""
            TextView.append(data.getString(data.getColumnIndex("taskname")))

            while (data.moveToNext()){
                TextView.append("\n")
                TextView.append(data.getString(data.getColumnIndex("taskname")))
            }
            data.close()
        }
    }
}
