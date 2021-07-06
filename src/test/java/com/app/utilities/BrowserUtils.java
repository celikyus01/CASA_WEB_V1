package com.app.utilities;

public class BrowserUtils {

    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
