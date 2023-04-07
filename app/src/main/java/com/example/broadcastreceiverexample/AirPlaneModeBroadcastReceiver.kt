package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

private const val TAG = "AirPlaneModeBroadcastReceiver"

class MyBroadcastReceiver(val listener: BroadcastListener) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) { // Run on MainThread
        Log.d(TAG, "onReceive: $intent")
        intent?.let {
            if (it.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isAirPlaneModeOn = intent.getBooleanExtra("state", false)
                listener.onAirPlaneModeChanged(isAirPlaneModeOn)
            }
        }
    }
}