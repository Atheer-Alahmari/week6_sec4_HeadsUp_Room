package com.example.week6_sec4_headsuproom.editDB

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week6_sec4_headsuproom.R
import com.example.week6_sec4_headsuproom.models.Celebrity
import com.example.week6_sec4_headsuproom.models.CelebrityDataBase
import kotlinx.android.synthetic.main.activity_add_data.*

class Add_Data : AppCompatActivity() {
    lateinit var myDBRoom:CelebrityDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        btn_Add.setOnClickListener {

            var name = name_ED.text.toString()
            var tabo1 = t1_ED.text.toString()
            var tabo2 = t2_ED.text.toString()
            var tabo3 = t3_ED.text.toString()

            if (name.isNotEmpty() && tabo1.isNotEmpty() && tabo2.isNotEmpty() && tabo3.isNotEmpty()) {
                //----------------Save DB--------------
                myDBRoom=CelebrityDataBase.getInstance(this)
                  myDBRoom.CelebrityDao().insertCelebrity(Celebrity(0,name, tabo1, tabo2, tabo3))
               // var status = dbhr.save_date(name, tabo1, tabo2, tabo3)

                Toast.makeText(applicationContext, "celebrity Added !  ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Please Enter All  ", Toast.LENGTH_SHORT).show()

            }

        }

        //-------------------------------------------------------------------------------------
        updateDelete_btn.setOnClickListener {
            intent = Intent(applicationContext, Update_Delete::class.java)
            startActivity(intent)
        }




    }
}