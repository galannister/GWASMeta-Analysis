package com.company;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.company.DataRead.crs;
import static com.company.DataRead.rs;
import static java.lang.Thread.sleep;

public class Main {


    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();


        String prefpath = args[0];
        String datapath = args[1];


        List<String> pref = PreferenceHandling.FileReading(prefpath);
        System.out.println("pref" + pref);
        List<Research> temp = new ArrayList<Research>();
        List<ContResearch> ctemp;


        if (pref.get(0).equals("d")) {

            DataRead.DiscreteDataRead(datapath);
            boolean key = true;

            int size = rs.size();

            for (int i = 0; i < rs.size() - 1; i++) {
                temp.add(rs.get(i));
                if (i == (size - 2)) {
                    temp.add(rs.get(i + 1));
                    key = false;
                }
                if (key) {
                    if (rs.get(i).getName().equals(rs.get(i + 1).getName())) {

                        continue;

                    }
                }
            }
            temp = rs;

            /////////////thread d
            String temp_name = temp.get(0).getName();
            List<Research> temp_list = new ArrayList<>();


            for (int i = 0; i < temp.size(); i++) {

                if (temp_name.equals(temp.get(i).getName())) {                  ///if we have the same names---->add to list

                    temp_list.add(temp.get(i));

                } else {                          ////if not--->start thread, clear temp list and change the name

                    thread_research t = new thread_research(temp_list, pref);

                    t.start();

                    temp_list = new ArrayList<>();
                    temp_list.add(temp.get(i));
                    temp_name = temp.get(i).getName();


                }
            }
            thread_research t = new thread_research(temp_list, pref);
            t.start();


        } else if (pref.get(0) == "c") {
            ctemp = new ArrayList<ContResearch>();

            DataRead.ContinuousDataRead(datapath);
            boolean key = true;

            int size = crs.size();

            for (int i = 0; i < crs.size() - 1; i++) {
                ctemp.add(crs.get(i));
                if (i == (size - 2)) {
                    ctemp.add(crs.get(i + 1));
                    key = false;
                }
                if (key) {
                    if (crs.get(i).getName().equals(crs.get(i + 1).getName())) {

                        continue;

                    }
                }


            }
            String conttemp_name = ctemp.get(0).getName();
            List<ContResearch> conttemp_list = new ArrayList<>();


            for (int i = 0; i < ctemp.size(); i++) {

                if (conttemp_name.equals(ctemp.get(i).getName())) {                  ///if we have the same names---->add to list

                    conttemp_list.add(ctemp.get(i));

                } else {                          ////if not--->start thread, clear temp list and change the name

                    thread_cont_research t = new thread_cont_research(conttemp_list, pref);

                    t.start();

                    conttemp_list = new ArrayList<>();
                    conttemp_list.add(ctemp.get(i));
                    conttemp_name = ctemp.get(i).getName();


                }
            }
            thread_cont_research t = new thread_cont_research(conttemp_list, pref);
            t.start();

        }
        ctemp = crs;
        //////////////////thread cont

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;


        System.out.println("Total SNP's: 1.000");
        System.out.println("Total time = " + totalTime);
    }
}


//---> format Για να ελεγχει τη θεση του πολυμορφισμου
