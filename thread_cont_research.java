package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class thread_cont_research implements Runnable{
    private Thread t;
    List<ContResearch> cur_list;
    List<String> prefs;

    thread_cont_research(List<ContResearch> cur_list1, List<String> pref1) {
        cur_list = cur_list1;
        prefs = pref1;
    }

    public void run() {
        try {

            PreferenceHandling.ContDataSplit(cur_list, prefs);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thread  exiting.");
    }
    public void start () {
        System.out.println("Starting ");
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
