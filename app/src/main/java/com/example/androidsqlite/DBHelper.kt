package com.example.androidsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    val DB_NAME = "todo.db"
    val DB_VERSION = 1
    val TB_NAME = "task"
    val COL_ID = "id"
    val COL_TASKNAME = "taskname"

    fun AddTask(newTask: Task){
        val values = ContentValues()
        values.put(COL_TASKNAME,newTask.taskname)

        val db = this.writableDatabase

        db.insert(TB_NAME,null,values)
        db.close()
    }
    fun GetAllTask(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM "+TB_NAME ,null)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val UPGRADE_TABLE = "DROP TABLE IF EXISTS " + TB_NAME
        db.execSQL(UPGRADE_TABLE)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE "+TB_NAME+
                " ( " + COL_ID + " INTEGER PRIMARY KEY,"+
                COL_TASKNAME + " TEXT )"
        db.execSQL(CREATE_TABLE)
    }
}