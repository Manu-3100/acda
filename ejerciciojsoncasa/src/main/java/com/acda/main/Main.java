package com.acda.main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        

        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", 
                                                          "C:\\Users\\ferna\\Pictures\\Screenshots\\Captura de pantalla 2024-10-27 013547.png");
        
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}