package com.nopalsoft.dragracer.handlers;

public interface GameServicesHandler {

    // This method abstracts away GPGS or AGC.
    void submitScore(long score);

    // This method abstracts away GPGS or AGC.
    void getLeaderboard();

    boolean isSignedIn();

    void signIn();

}
