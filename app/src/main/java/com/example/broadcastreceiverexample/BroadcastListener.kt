package com.example.broadcastreceiverexample

interface BroadcastListener {
    fun onAirPlaneModeChanged(isOn: Boolean)
}