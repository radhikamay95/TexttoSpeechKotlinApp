package com.example.testkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private  var tts: TextToSpeech? =null
    private  var button: Button? =null
    private  var edText: EditText? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  

        button= findViewById(R.id.speak)
        edText=findViewById(R.id.text)
        button!!.isEnabled=false;
        tts= TextToSpeech(this, this)
        button!!.setOnClickListener{convert()}

    }

    override fun onInit(status: Int) {
        if(status ==TextToSpeech.SUCCESS)
        {
            var result=tts!!.setLanguage(Locale.US)
            if (result ==TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS","Language not support")
            }
            else{
                button!!.isEnabled=true
            }
        }
        else
        {
            Log.e("TTS","Initialization failed")
        }
    }
   private fun convert()
   {
       var text = edText!!.text.toString()
       tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
   }


}
