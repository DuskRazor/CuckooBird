package com.kali;

public class Main {
    public static void main(String[] args) {
        Cuckoo frame = new Cuckoo();
        while (true){
            try {
                Thread.sleep(100);
                if(!frame.crash){
                    frame.repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
