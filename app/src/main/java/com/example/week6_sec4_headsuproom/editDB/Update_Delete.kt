package com.example.week6_sec4_headsuproom.editDB

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_sec4_headsuproom.Game
import com.example.week6_sec4_headsuproom.R
import com.example.week6_sec4_headsuproom.RV_Adapter
import com.example.week6_sec4_headsuproom.models.Celebrity
import com.example.week6_sec4_headsuproom.models.CelebrityDataBase
import kotlinx.android.synthetic.main.activity_update_delete.*

class Update_Delete : AppCompatActivity() {
    lateinit var list : List<Celebrity>
    lateinit var list_RV: RecyclerView
    lateinit var myDBRoom: CelebrityDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        myDBRoom=CelebrityDataBase.getInstance(applicationContext)

        list_RV = findViewById(R.id.recycler_View)// Recycler View
        list= listOf()

        updateList()

        start_btn.setOnClickListener {
            intent = Intent(applicationContext, Game::class.java)
            startActivity(intent)
        }


    }
    //-------------------------------------------------------------------------------------

    fun updateList() {
        list=myDBRoom.CelebrityDao().getAllCelebrities()

        list_RV.adapter = RV_Adapter(this,list)
        list_RV.layoutManager = LinearLayoutManager(this)

        list_RV.scrollToPosition(list.size - 1)

    }

}