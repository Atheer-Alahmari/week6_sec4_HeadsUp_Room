package com.example.week6_sec4_headsuproom

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.week6_sec4_headsuproom.models.Celebrity
import com.example.week6_sec4_headsuproom.models.CelebrityDataBase
import kotlinx.android.synthetic.main.rotate.*
import kotlin.random.Random

class Game : AppCompatActivity() {
   lateinit  var list : List<Celebrity>
    private var gameActive: Boolean = false
    lateinit var myDBRoom:CelebrityDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpageingame)
        list= listOf()
        newTimer()

    }
//-------------------------------------------------------------------------

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.rotate)
            fetchData()
            gameActive = true
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.firstpageingame)
        }
    }
//-------------------------------------------------------------------------

    fun fetchData() {

        myDBRoom= CelebrityDataBase.getInstance(applicationContext)

        list = myDBRoom.CelebrityDao().getAllCelebrities()
        var randomIndex = Random.nextInt(list.size)

        tvShow.setText("${list[randomIndex].name}")
        var text =
            "${list[randomIndex].taboo1}\n${list[randomIndex].taboo2}\n${list[randomIndex].taboo3}\n"
        tvTaboo.setText(text)


    }

//-------------------------------------------------------------------------

    private fun newTimer() {
        if (!gameActive) {
            gameActive = true
            object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTime.text = "Time: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    gameActive = false
                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }.start()
        }
    }


}