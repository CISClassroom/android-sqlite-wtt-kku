package com.example.androidsqlite

class Task{
    var id = 0
    var taskname:String = ""

    constructor(id:Int,taskname:String){
        this.id = id
        this.taskname = taskname
    }

    constructor(taskname:String){
        this.taskname = taskname
    }
}