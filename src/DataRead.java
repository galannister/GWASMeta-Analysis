package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;


public class DataRead  {



    public static List<Research> rs=new ArrayList<Research>();
    public static List<ContResearch> crs=new ArrayList<>();


    public static void DiscreteDataRead(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));


        String line;
        int counter = 0;

        while ((line = br.readLine()) != null ) {
            String[] splited = line.split(" ");

            if (counter==0){
                counter++;
                continue;
            }




           // DataRead.snpAll.add(splited[0]);        //gene names
            int id= Integer.valueOf(splited[1]);
            double aa1data= Double.valueOf(splited[2]);
            double ab1data= Double.valueOf(splited[3]);
            double bb1data= Double.valueOf(splited[4]);
            double aa0data= Double.valueOf(splited[5]);
            double ab0data= Double.valueOf(splited[6]);
            double bb0data= Double.valueOf(splited[7]);
            int chrom=Integer.valueOf(splited[8]);
            int pos=Integer.valueOf(splited[9]);


            Research research= new Research(splited[0],id,aa1data,ab1data,bb1data,aa0data,ab0data,bb0data,chrom,pos);
            rs.add(research);


        }
        rs.sort(Comparator.comparing(Research::getName)

                 );


        br.close();

    }
////////////////////////////////////////Continuous////////////////////////////////////////////////


    public static void ContinuousDataRead(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        int counter = 0;

        while ((line = br.readLine()) != null) {
            String[] splited = line.split(" ");

            if (counter == 0) {
                counter++;
                continue;
            }



            int id= Integer.valueOf(splited[1]);
            double popaa= Double.valueOf(splited[1]);
            double popab = Double.valueOf(splited[2]);
            double popbb = Double.valueOf(splited[3]);
            double meanaa = Double.valueOf(splited[4]);
            double meanab = Double.valueOf(splited[5]);
            double meanbb = Double.valueOf(splited[6]);
            double stdDevaa = Double.valueOf(splited[7]);
            double stdDevab = Double.valueOf(splited[8]);
            double stdDevbb = Double.valueOf(splited[9]);
            int chrom=Integer.valueOf(splited[10]);
            int pos=Integer.valueOf(splited[11]);

            ContResearch contResearch= new ContResearch(splited[0],id,popaa,popab,popbb,meanaa,meanab,meanbb,stdDevaa,stdDevab,stdDevbb,chrom,pos);
            crs.add(contResearch);


        }
        crs.sort(Comparator.comparing(ContResearch::getName)
                .thenComparing(ContResearch::getId)
        );


        br.close();
    }




}





//////Continuous Data path: "C:\\Users\\GEORGE\\Desktop\\Σχολη\\Πτυχιακή\\meta-analysis\\meta-analysis\\ContinuousTestDataSet.txt" /////////////
