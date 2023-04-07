package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_finish).setOnClickListener { finish() }
        val tv = findViewById<TextView>(R.id.textView)

        broadcastReceiver = MyBroadcastReceiver(listener = object : BroadcastListener {
            override fun onAirPlaneModeChanged(isOn: Boolean) {
                tv.text = if (isOn) "AirPlaneModeChanged: On" else "AirPlaneModeChanged: Off"
            }
        })
        registerBroadcastReceiver()
    }

    private fun registerBroadcastReceiver() {
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val listenToBroadcastsFromOtherApps = false
        val receiverFlags = if (listenToBroadcastsFromOtherApps) { // receive from other application
            ContextCompat.RECEIVER_EXPORTED
        } else {
            ContextCompat.RECEIVER_NOT_EXPORTED // only receive from the system and the application itself
        }
        ContextCompat.registerReceiver(this, broadcastReceiver, filter, receiverFlags)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}