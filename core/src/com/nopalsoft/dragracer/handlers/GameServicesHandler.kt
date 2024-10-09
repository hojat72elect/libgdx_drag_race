package com.nopalsoft.dragracer.handlers

interface GameServicesHandler {

    // This method abstracts away GPGS or AGC.
    fun submitScore(score:Long)

    // This method abstracts away GPGS or AGC.
    fun getLeaderboard()

    fun isSignedIn():Boolean

    fun signIn()
}