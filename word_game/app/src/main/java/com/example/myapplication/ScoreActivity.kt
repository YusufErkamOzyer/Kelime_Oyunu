package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        var sharedPref=this.getSharedPreferences("yazlab2yusuf", MODE_PRIVATE)
        val my_scores=sharedPref.getStringSet("yusferkam",null)
        val scoresView=findViewById<TextView>(R.id.score_view)
        var skor=""
        var intScore=ArrayList<Int>()
        if(my_scores!=null){
            for(i in my_scores){
                intScore.add(i.toInt())

            }
            Collections.sort(intScore,Collections.reverseOrder())
            for(i in intScore){
                skor=skor+i.toString()+"\n"
            }
        }
        scoresView.text=skor
    }
}