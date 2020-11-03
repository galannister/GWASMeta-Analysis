package com.company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class thread_research implements Runnable{
    private Thread t;
    List<Research> cur_list;
    List<String> prefs;



    thread_research(List<Research> cur_list1, List<String> pref1) throws FileNotFoundException {
        cur_list = cur_list1;
        prefs = pref1;

    }


    public void run() {
            synchronized (this) {
                try {

                    PreferenceHandling.DataSplit(cur_list, prefs);
                    cur_list = new ArrayList<>();

                } catch (IOException e) {
                    e.printStackTrace();

                System.out.println("Thread exiting.");
            }

    }
    }

    public void start () {
        System.out.println("Starting ");
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
