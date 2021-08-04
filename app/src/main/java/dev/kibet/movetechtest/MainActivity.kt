package dev.kibet.movetechtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.kibet.movetechtest.databinding.ActivityMainBinding
import dev.kibet.movetechtest.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        
        binding.buttonSend.setOnClickListener { 
            binding.apply { 
                val username = username.text.trim().toString()
                val apikey = apikey.text.trim().toString()
                val sender = sender.text.trim().toString()
                val sendTo = sendTo.text.trim().toString()
                val message = yourMessage.text.trim().toString()
                val msgtype = msgtype.text.trim().toString()
                val delivery = deliverry.text.trim().toString()


                viewmodel.sendSms(
                    username = username,
                    api_key = apikey,
                    sender = sender,
                    to = sendTo,
                    message = message,
                    msgtype = msgtype,
                    dlr = delivery
                )
            }
            Toast.makeText(this,"Message sent",Toast.LENGTH_LONG).show()
        }

        //subscribeToObservers()
        
    }

//    private fun subscribeToObservers(){
//        viewmodel.sendSmsStatus.observe(life)
//    }
}