package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
class MyButton : Button {
    var isIce = false
    var isHalfIce=false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun isButtonBlue(): Boolean {
        return isIce
    }

    fun setButtonBlue(enabled: Boolean) {
        isIce = enabled
    }
}
class MainActivity : AppCompatActivity() {
    private var secondsPassed = 0
    var durdu=0
    var runnable: Runnable= Runnable { }
    var handler: Handler= Handler(Looper.getMainLooper())
    lateinit var sharedPref:SharedPreferences
    fun start(buttonMatris:Array<IntArray>){
        var control=0
        var time=0
        var time1=0
        val random_letters: ArrayList<Char> = ArrayList<Char>()
        for(i in 0..7){
            random_letters.add(randomLetter())
        }
        for ((index, row) in buttonMatris.withIndex()) {
            Handler().postDelayed({
                var i = 0
                for (button in row) {
                    val mybutton = findViewById<MyButton>(button)
                    mybutton.visibility = View.VISIBLE
                    mybutton.setText(random_letters.get(i).toString())
                    i++
                }
            }, index * 500L)
            if (index == 9) {
                control = 1
                break
            } else {
                Handler().postDelayed({
                    for (button in row) {
                        val mybutton = findViewById<MyButton>(button)
                        mybutton.visibility = View.INVISIBLE
                        mybutton.setText("")
                    }
                }, (index + 1) * 500L)
                time = (index + 1) * 500
            }
        }
        val random_letters1: ArrayList<Char> = ArrayList<Char>()
        for(i in 0..7){
            random_letters1.add(randomLetter())
        }
        Handler().postDelayed({
            for ((index, row) in buttonMatris.withIndex()) {
                Handler().postDelayed({
                    var i=0
                    for (button in row) {
                        val mybutton=findViewById<MyButton>(button)
                        mybutton.visibility = View.VISIBLE
                        mybutton.setText(random_letters1.get(i).toString())
                        i++
                    }
                }, index * 500L)
                if(control==1 && index==8){
                    control=2
                    break
                }
                else{
                    Handler().postDelayed({
                        for (button in row) {
                            val mybutton=findViewById<MyButton>(button)
                            mybutton.visibility = View.INVISIBLE
                            mybutton.setText("")
                        }
                    }, (index + 1) * 500L)
                    time1=(index + 1) * 500
                }
            }


        },time.toLong())
        val random_letters2:ArrayList<Char> = ArrayList<Char>()
        for(i in 0..7){
            random_letters2.add(randomLetter())
        }
        Handler().postDelayed({
            for ((index, row) in buttonMatris.withIndex()) {
                Handler().postDelayed({
                    var i=0
                    for (button in row) {
                        val mybutton=findViewById<MyButton>(button)
                        mybutton.visibility = View.VISIBLE
                        mybutton.setText(random_letters2.get(i).toString())
                        i++
                    }
                }, index * 500L)
                if(control==2 && index==7){
                    break
                }
                else{
                    Handler().postDelayed({
                        for (button in row) {
                            val mybutton=findViewById<MyButton>(button)
                            mybutton.visibility = View.INVISIBLE
                            mybutton.setText("")
                        }
                    }, (index + 1) * 500L)
                }
            }


        },(time*2).toLong())
    }
    fun sendLetter(buttonMatris:Array<IntArray>){
        val randomIndex = Random.nextInt(8)
        val random_buz=Random.nextInt(9)
        val randomletter=randomLetter()
        if(random_buz==1){//
            for(index in 0..9){
                val myButton=findViewById<MyButton>(buttonMatris[index][randomIndex])
                if(index <9){
                    val myButton1=findViewById<MyButton>(buttonMatris[index+1][randomIndex])
                    if(myButton1.visibility==View.VISIBLE){
                        Handler().postDelayed({
                            myButton.setButtonBlue(true)
                            myButton.isHalfIce=false
                            myButton.setBackgroundColor(Color.BLUE)// harf düştüğünde kalan buton beyaz olacak dikkat
                            myButton.setTextColor(Color.WHITE)
                            myButton.setText(randomletter.toString())
                            myButton.visibility=View.VISIBLE
                            if(myButton1.isClickable==true && myButton1.isIce==false){//buzlu harfinin düştüğü harf
                                myButton1.setButtonBlue(true)
                                myButton1.isHalfIce=true
                                myButton1.setBackgroundColor(Color.BLUE)
                                myButton1.setTextColor(Color.WHITE)
                            }
                        },index*200L)
                        break
                    }
                    else{
                        Handler().postDelayed({
                            myButton.setBackgroundColor(Color.BLUE)// harf düştüğünde kalan buton beyaz olacak dikkat
                            myButton.setTextColor(Color.WHITE)
                            myButton.setText(randomletter.toString())
                            myButton.visibility=View.VISIBLE
                            myButton.setButtonBlue(true)
                            myButton.isHalfIce=false

                        },index*200L)
                        Handler().postDelayed({
                            myButton.setBackgroundColor(Color.DKGRAY)
                            myButton.setTextColor(Color.CYAN)
                            myButton.visibility=View.INVISIBLE
                            myButton.setText("")
                            myButton.setButtonBlue(false)
                            myButton.isHalfIce=false
                        },(index+1)*200L)
                    }
                }
                else{
                    Handler().postDelayed({
                        myButton.setBackgroundColor(Color.BLUE)// harf düştüğünde kalan buton beyaz olacak dikkat
                        myButton.setTextColor(Color.WHITE)
                        myButton.setText(randomletter.toString())
                        myButton.visibility=View.VISIBLE
                        myButton.setButtonBlue(true)
                        myButton.isHalfIce=false
                    },index*200L)
                }
            }
        }else{
            for(index in 0..9){
                val myButton=findViewById<MyButton>(buttonMatris[index][randomIndex])
                if(index<9){
                    val myButton1=findViewById<MyButton>(buttonMatris[index+1][randomIndex])
                    if(myButton1.visibility==View.VISIBLE){
                        Handler().postDelayed({
                            myButton.visibility=View.VISIBLE
                            myButton.setText(randomletter.toString())
                            myButton.setBackgroundColor(Color.DKGRAY)
                            myButton.setTextColor(Color.CYAN)
                            if(myButton1.isButtonBlue() && myButton1.isHalfIce==false){
                                myButton.setButtonBlue(true)
                                myButton.isHalfIce=true
                                myButton.setBackgroundColor(Color.BLUE)
                                myButton.setTextColor(Color.WHITE)
                            }
                        },index*200L)
                        break
                    }
                    else
                    {
                        Handler().postDelayed({
                            myButton.visibility=View.VISIBLE
                            myButton.setText(randomletter.toString())

                        },index*200L)
                        Handler().postDelayed({
                            myButton.visibility=View.INVISIBLE
                            myButton.setText("")
                        },(index+1)*200L)
                    }
                }
                else{
                    Handler().postDelayed({
                        myButton.visibility=View.VISIBLE
                        myButton.setText(randomletter.toString())
                    },index*200L)
                }
            }
        }


    }
    fun randomLetter():Char{
        val letters="aabcçdeefgğhııiijklmnooööprsştyuuüüvyz"
        val randomIndex = Random.nextInt(letters.length)
        return(letters[randomIndex])
    }
    fun ceza(buttonMatris: Array<IntArray>){
       for(column in 0..7){
           for(index in 0..9){
               val myButton=findViewById<MyButton>(buttonMatris[index][column])
               if(index<9){
                   val myButton1=findViewById<MyButton>(buttonMatris[index+1][column])
                   if(myButton1.visibility==View.VISIBLE){
                       Handler().postDelayed({
                           myButton.visibility=View.VISIBLE
                           myButton.setBackgroundColor(Color.RED)
                           myButton.isClickable=false
                       },index*200L)
                       break
                   }
                   else
                   {
                       Handler().postDelayed({
                           myButton.visibility=View.VISIBLE
                           myButton.setBackgroundColor(Color.RED)
                           myButton.isClickable=false

                       },index*200L)
                       Handler().postDelayed({
                           myButton.visibility=View.INVISIBLE
                           myButton.setBackgroundColor(Color.DKGRAY)
                           myButton.isClickable=true

                       },(index+1)*200L)
                   }
               }
               else{
                   Handler().postDelayed({
                       myButton.visibility=View.VISIBLE
                       myButton.setBackgroundColor(Color.RED)
                       myButton.isClickable=false

                   },index*200L)
               }

           }
       }
    }
    fun reShape(buttonMatris: Array<IntArray>, column:Int){
        for(index in 0..9){
            val myButton=findViewById<MyButton>(buttonMatris[index][column])
            if(index<9){
                val myButton1=findViewById<MyButton>(buttonMatris[index+1][column])
                if(myButton.visibility==View.VISIBLE && myButton1.visibility==View.INVISIBLE){
                    if(myButton.isClickable==false){    //kırmızı harf aşağı düşer
                        myButton.isClickable=true
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                        myButton.text=""
                        myButton.visibility=View.INVISIBLE
                        myButton1.isClickable=false
                        myButton1.setBackgroundColor(Color.RED)
                        myButton1.visibility=View.VISIBLE
                    }
                    else if(myButton.isButtonBlue() && myButton.isHalfIce==false){//tam buz harfler aşağı düşünce
                        val harf=myButton.text.toString()
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                        myButton.text=""
                        myButton.setButtonBlue(false)
                        myButton.isHalfIce=false
                        myButton.visibility=View.INVISIBLE
                        myButton1.setBackgroundColor(Color.BLUE)
                        myButton1.setTextColor(Color.WHITE)
                        myButton1.setButtonBlue(true)
                        myButton1.isHalfIce=false
                        myButton1.text=harf
                        myButton1.visibility=View.VISIBLE
                        if(index<8){
                            val myButton2=findViewById<MyButton>(buttonMatris[index+2][column])
                            if(myButton2.isIce==false && myButton2.isClickable==true){
                                myButton2.setBackgroundColor(Color.BLUE)
                                myButton2.setTextColor(Color.WHITE)
                                myButton2.setButtonBlue(true)
                                myButton2.isHalfIce=true
                            }
                        }
                    }
                    else if(myButton.isButtonBlue() && myButton.isHalfIce==true){// yarı buz harfler aşşağı düşünce
                        val harf=myButton.text.toString()
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                        myButton.text=""
                        myButton.setButtonBlue(false)
                        myButton.isHalfIce==false
                        myButton.visibility=View.INVISIBLE
                        myButton1.setBackgroundColor(Color.BLUE)
                        myButton1.setTextColor(Color.WHITE)
                        myButton1.text=harf
                        myButton1.setButtonBlue(true)
                        myButton1.isHalfIce=true
                        myButton1.visibility=View.VISIBLE
                    }
                    else if (myButton.isIce==false && myButton.isClickable==true && myButton.isHalfIce==false){
                        val harf=myButton.text.toString()
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                        myButton.text=""
                        myButton.visibility=View.INVISIBLE
                        myButton1.setBackgroundColor(Color.DKGRAY)
                        myButton1.setTextColor(Color.CYAN)
                        myButton1.visibility=View.VISIBLE
                        myButton1.text=harf
                    }
                }

            }
            else{
                val myButton1=findViewById<MyButton>(buttonMatris[index-1][column])
                if(myButton.visibility==View.INVISIBLE && myButton1.visibility==View.VISIBLE){
                    if(myButton1.isClickable==false){
                        myButton.setBackgroundColor(Color.RED)
                        myButton.isClickable=false
                        myButton.visibility=View.VISIBLE
                        myButton.text=""
                        myButton1.setBackgroundColor(Color.DKGRAY)
                        myButton1.setTextColor(Color.CYAN)
                        myButton1.visibility=View.INVISIBLE
                        myButton1.isClickable=true
                    }
                    else if(myButton1.isButtonBlue() && myButton1.isHalfIce==false){// tam buz
                        val harf=myButton1.text.toString()
                        myButton.setBackgroundColor(Color.BLUE)
                        myButton.setTextColor(Color.WHITE)
                        myButton.setButtonBlue(true)
                        myButton.text=harf
                        myButton.visibility=View.VISIBLE
                        myButton1.setButtonBlue(false)
                        myButton1.setBackgroundColor(Color.DKGRAY)
                        myButton1.setTextColor(Color.CYAN)
                        myButton1.text=""
                        myButton1.visibility=View.INVISIBLE
                    }
                    else if(myButton1.isButtonBlue() && myButton1.isHalfIce==true){// yarı buz
                        val harf=myButton1.text.toString()
                        myButton.setBackgroundColor(Color.BLUE)
                        myButton.setTextColor(Color.WHITE)
                        myButton.setButtonBlue(true)
                        myButton.isHalfIce=true
                        myButton.text=harf
                        myButton.visibility=View.VISIBLE
                        myButton1.setButtonBlue(false)
                        myButton1.isHalfIce=false
                        myButton1.setBackgroundColor(Color.DKGRAY)
                        myButton1.setTextColor(Color.CYAN)
                        myButton1.text=""
                        myButton1.visibility=View.INVISIBLE

                    }else{
                        val harf=myButton1.text.toString()
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                        myButton.text=harf
                        myButton.visibility=View.VISIBLE
                        myButton1.setBackgroundColor(Color.DKGRAY)
                        myButton1.setTextColor(Color.CYAN)
                        myButton1.text=""
                        myButton1.visibility=View.INVISIBLE
                    }

                }
            }
        }


    }
    fun isntCorrect(buttonMatris: Array<IntArray>):Boolean{
        for(column in 0..7){
            for(index in 0..9){
                val myButton=findViewById<MyButton>(buttonMatris[index][column])
                if(index<9){
                    val myButton1=findViewById<MyButton>(buttonMatris[index+1][column])
                    if(myButton.visibility==View.VISIBLE && myButton1.visibility==View.INVISIBLE){
                        return true
                    }

                }
            }
        }
        return false
    }
    fun isDone(buttonMatris: Array<IntArray>):Boolean{
        for(column in 0..7){
            var sayac=0
            for(index in 0..9){
                val myButton=findViewById<MyButton>(buttonMatris[index][column])
                if(myButton.visibility==View.VISIBLE){
                    sayac++
                }
            }
            if(sayac==10){
                return true
                break
            }
        }
        return false
    }
    fun pause(buttonMatris: Array<IntArray>){
        //handler.removeCallbacks(runnable)
        while (isntCorrect(buttonMatris)){
            for(i in 0..7){
                reShape(buttonMatris,i)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var score=0
        val points= hashMapOf<String,Int>()
        points.put("a",1);points.put("b",3);points.put("c",4);points.put("ç",4);points.put("d",3);points.put("e",1);points.put("f",7);points.put("g",5);points.put("ğ",8)
        points.put("h",5);points.put("ı",2);points.put("i",1);points.put("j",10);points.put("k",1);points.put("l",1);points.put("m",2);points.put("n",1);points.put("o",2)
        points.put("ö",7);points.put("p",5);points.put("r",1);points.put("s",2);points.put("ş",4);points.put("t",1);points.put("u",2);points.put("ü",3);points.put("v",7)
        points.put("y",3);points.put("z",4)
        sharedPref=this.getSharedPreferences("com.yusuf.example",Context.MODE_PRIVATE)


        val intent = Intent(this,ScoreActivity ::class.java)
        val buttonIds= arrayOf(R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button10,
            R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15,R.id.button16,R.id.button17,R.id.button18,R.id.button19,R.id.button20,
            R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,R.id.button29,R.id.button30,
            R.id.button31,R.id.button32,R.id.bu tton33,R.id.button34,R.id.button35,R.id.button36,R.id.button37,R.id.button38,R.id.button39,R.id.button40,
            R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45,R.id.button46,R.id.button47,R.id.button48,R.id.button49,R.id.button50,
            R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55,R.id.button56,R.id.button57,R.id.button58,R.id.button59,R.id.button60,
            R.id.button61,R.id.button62,R.id.button63,R.id.button64,R.id.button65,R.id.button66,R.id.button67,R.id.button68,R.id.button69,R.id.button70,
            R.id.button71,R.id.button72,R.id.button73,R.id.button74,R.id.button75,R.id.button76,R.id.button77,R.id.button78,R.id.button79,R.id.button80)
        val buttonMatris= arrayOf(
            intArrayOf(R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8),
            intArrayOf(R.id.button9,R.id.button10,R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15,R.id.button16),
            intArrayOf(R.id.button17,R.id.button18,R.id.button19,R.id.button20,R.id.button21,R.id.button22,R.id.button23,R.id.button24),
            intArrayOf(R.id.button25,R.id.button26,R.id.button27,R.id.button28,R.id.button29,R.id.button30,R.id.button31,R.id.button32),
            intArrayOf(R.id.button33,R.id.button34,R.id.button35,R.id.button36,R.id.button37,R.id.button38,R.id.button39,R.id.button40),
            intArrayOf(R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45,R.id.button46,R.id.button47,R.id.button48),
            intArrayOf(R.id.button49,R.id.button50,R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55,R.id.button56),
            intArrayOf(R.id.button57,R.id.button58,R.id.button59,R.id.button60,R.id.button61,R.id.button62,R.id.button63,R.id.button64),
            intArrayOf(R.id.button65,R.id.button66,R.id.button67,R.id.button68,R.id.button69,R.id.button70,R.id.button71,R.id.button72),
            intArrayOf(R.id.button73,R.id.button74,R.id.button75,R.id.button76,R.id.button77,R.id.button78,R.id.button79,R.id.button80)
        )
        var scora_git=findViewById<Button>(R.id.toscore)
        scora_git.visibility=View.INVISIBLE
        val assetManager = applicationContext.assets
        val inputStream = assetManager.open("dosya2.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var kelime=ArrayList<String>()
        while(true){
            val line = reader.readLine() ?: break // Satır null olduğunda döngüyü kır
            kelime.add(line)
        }
        var word=findViewById<TextView>(R.id.word)
        //BUTTONLARIN RENGİNİ AAYARLADIK
        for(buttonId in buttonIds){
            val myButton = findViewById<Button>(buttonId)
            myButton.setBackgroundColor(Color.DKGRAY)
            myButton.setTextColor(Color.CYAN)
        }
        val yesil_button=findViewById<Button>(R.id.yesil)
        yesil_button.setBackgroundColor(Color.GREEN)
        val kirmizi_button=findViewById<Button>(R.id.kirmizi)
        kirmizi_button.setBackgroundColor(Color.RED)
        val durdur_button=findViewById<Button>(R.id.stop)
        durdur_button.setBackgroundColor(Color.BLACK)
        durdur_button.setTextColor(Color.WHITE)
        //BUTONLARI GÖRÜNMEZ YAPTIK
        for(buttonId in buttonIds){
            val myButton = findViewById<Button>(buttonId)
            myButton.visibility = View.INVISIBLE
        }
        // OYUNU BAŞLAT
        start(buttonMatris)
        for (buttonId in buttonIds) {
            val myButton = findViewById<MyButton>(buttonId)
            myButton.setOnClickListener {
                if(myButton.isSelected){
                    //ÇİFT TIKLAMA
                    // ilgili yerdeki harfi silmeli
                    var kelime=ArrayList<String>()
                    var harf=myButton.text.toString()
                    for(i in word.text.toString()){
                        kelime.add(i.toString())
                    }
                    for(i in kelime){
                        if(i.equals(harf)){
                            kelime.remove(i)
                            break
                        }
                    }
                    var yeni_kelime=""
                    for(i in kelime){
                        yeni_kelime=yeni_kelime+i
                    }
                    word.text=yeni_kelime
                    if(myButton.isButtonBlue()){
                        myButton.isSelected=false
                        myButton.setBackgroundColor(Color.BLUE)
                        myButton.setTextColor(Color.WHITE)
                    }
                    else{
                        myButton.isSelected=false
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.setTextColor(Color.CYAN)
                    }

                }
                else{
                    //TEK TIKLAMA
                    // harfin eklendiği yer
                    word.text=word.text.toString()+myButton.text.toString()
                    if (myButton.isButtonBlue()){
                        myButton.isSelected=true
                        myButton.setBackgroundColor(Color.LTGRAY)
                        myButton.setTextColor(Color.BLUE)
                    }
                    else{
                        myButton.isSelected=true
                        myButton.setBackgroundColor(Color.CYAN)
                        myButton.setTextColor(Color.DKGRAY)
                    }

                }

            }
        }
        var seconds = 0
        var seconds1=0
        var seconds2=0
        var seconds3=0
        var seconds4=0
        var wordList= ArrayList<String>()
        runnable = object : Runnable {
            override fun run() {
                if(score in 0..99){
                    seconds++
                    if(seconds%5==0){
                        sendLetter(buttonMatris)

                    }
                }
                else if(score in 100..199){
                    seconds1++
                    if(seconds1%4==0){
                        sendLetter(buttonMatris)
                    }
                }
                else if(score in 200..299){
                    seconds2++
                    if(seconds2%3==0){
                        sendLetter(buttonMatris)
                    }
                }
                else if(score in 300..399){
                    seconds3++
                    if(seconds3%2==0){
                        sendLetter(buttonMatris)
                    }
                }
                else if(score>=400){
                    seconds4++
                    if(seconds4%1==0){
                        sendLetter(buttonMatris)
                    }
                }


                handler.postDelayed(this, 1000)
            }
        }
        Handler().postDelayed({
            handler.postDelayed(runnable, 1000)

        },13000)
        val timer = Timer()
        var saniye = 0
        var sharedPref=this.getSharedPreferences("yazlab2yusuf",Context.MODE_PRIVATE)
        val task = object : TimerTask() {
            override fun run() {
                saniye++
                if(isDone(buttonMatris)){
                    var oku =sharedPref.getStringSet("yusferkam",null)
                    if(oku!=null){
                        for(i in oku){
                            wordList.add(i)
                        }
                    }
                    wordList.add(score.toString())
                    sharedPref.edit().putStringSet("yusferkam",wordList.toSet()).apply()
                    var oku1 =sharedPref.getStringSet("yusferkam",null)
                   println(oku1)


                    handler.removeCallbacks(runnable)
                    timer.cancel()
                    startActivity(intent)

                }


            }
        }
        timer.schedule(task, 0, 1000)

        var sayac=0
        var buz_sayac=0
        yesil_button.setOnClickListener({
            val new_word=word.text.toString()
            if(new_word in kelime && new_word.length>2){
                println("dogru")
                for(buttonId in buttonIds){
                    val myButton=findViewById<MyButton>(buttonId)
                    if(myButton.isSelected==true && myButton.isIce==false){ // normal harf
                        myButton.isSelected=false
                        myButton.setTextColor(Color.CYAN)
                        myButton.setBackgroundColor(Color.DKGRAY)
                        myButton.visibility=View.INVISIBLE
                        myButton.text=""
                    }
                    else if(myButton.isSelected==true && myButton.isIce==true){// buzlu harf
                        myButton.isSelected=false
                        myButton.setTextColor(Color.WHITE)
                        myButton.setBackgroundColor(Color.BLUE)
                        buz_sayac++
                        if(buz_sayac==2){
                            myButton.text=""
                            buz_sayac=0
                            myButton.setButtonBlue(false)
                            myButton.setTextColor(Color.CYAN)
                            myButton.setBackgroundColor(Color.DKGRAY)
                            myButton.visibility=View.INVISIBLE

                        }

                    }
                }
                while (isntCorrect(buttonMatris)){
                    for(i in 0..7){
                        reShape(buttonMatris,i)
                    }
                }
                for(i in new_word){
                    var point=points.get(i.toString())
                    if (point!=null){
                        score=score+point
                    }
                }
                var score_view=findViewById<TextView>(R.id.score_v)
                score_view.text=score.toString()
                word.text=""
            }
            else{
                sayac++
                if(sayac==3){
                    sayac=0
                    ceza(buttonMatris)
                    println("ceza")

                }
                println("yanlis")
                for(buttonId in buttonIds){
                    val myButton=findViewById<MyButton>(buttonId)
                    if(myButton.isSelected==true){
                        myButton.isSelected=false
                        if(myButton.isButtonBlue()){
                            myButton.setTextColor(Color.WHITE)
                            myButton.setBackgroundColor(Color.BLUE)
                        }else{
                            myButton.setTextColor(Color.CYAN)
                            myButton.setBackgroundColor(Color.DKGRAY)
                        }



                    }
                }
                word.text=""
            }

        })
        kirmizi_button.setOnClickListener({
            for(buttonId in buttonIds){
                val myButton=findViewById<MyButton>(buttonId)
                if(myButton.isSelected==true){
                    myButton.isSelected=false
                    if(myButton.isButtonBlue()){
                        myButton.setTextColor(Color.WHITE)
                        myButton.setBackgroundColor(Color.BLUE)
                    }
                    else{
                        myButton.setTextColor(Color.CYAN)
                        myButton.setBackgroundColor(Color.DKGRAY)
                    }
                }
            }
            word.text=""
            //sil
        })
        durdur_button.setOnClickListener({
            handler.removeCallbacks(runnable)
            pause(buttonMatris)
            durdur_button.isClickable=false
            scora_git.visibility=View.VISIBLE
        })

        scora_git.setOnClickListener({
            var oku =sharedPref.getStringSet("yusferkam",null)
            if(oku!=null){
                for(i in oku){
                    wordList.add(i)
                }
            }
            wordList.add(score.toString())
            sharedPref.edit().putStringSet("yusferkam",wordList.toSet()).apply()
            var oku1 =sharedPref.getStringSet("yusferkam",null)
            println(oku1)
            handler.removeCallbacks(runnable)
            startActivity(intent)

        })
        val duzenle_button=findViewById<Button>(R.id.duzen)
        duzenle_button.setOnClickListener({
            pause(buttonMatris)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }
}