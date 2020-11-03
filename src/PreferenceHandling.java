package com.company;

import com.sun.java.accessibility.util.AccessibilityListenerList;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


import static com.company.DataRead.*;



public class PreferenceHandling {


    public static List<String> FileReading(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<String> Preferences = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] splited = line.split(" ");

            Preferences.add(splited[1]);

        }


        return Preferences;


    }

    public synchronized static void writeToFile(StringBuilder data) {
        String result = data.toString();


        try {

            Files.write(Paths.get("output.txt"), result.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void DataSplit(List<Research> temp, List<String> Preferences) throws IOException {


        StringBuilder result = new StringBuilder();

        if (Preferences.get(1).equals("r")) {


            Files.write(Paths.get("output.txt"), "rs    Q    I^2    T^2    Weighted Mean    Cases    Controls    N    StdError    Lower Limit    Upper Limit    Zeta    P-Value    Pos.    Chrom.\r\n".getBytes(), StandardOpenOption.WRITE);
            if (Preferences.get(2).equals("c")) {

                //writer1.printf(temp.get(0).getName()+ "    ");
                result.append(temp.get(0).getName()).append("    ");
                List<Double> CodomOddsRatio = MetaStats.CodomOddsRatio(temp);

                //writer1.println("Codominant Odds Ratio: " + CodomOddsRatio);

                List<Double> InvVariance = MetaStats.CodomFixedInvVariance(temp);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                //writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, CodomOddsRatio);
                // writer1.printf(weightedMean+ "    ");

                double Q = MetaStats.CochransQ(CodomOddsRatio, weightedMean, InvVariance);
                //writer1.printf(Q+ "    ");
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                //writer1.printf(isquared+ "    ");
                result.append(String.valueOf(isquared));
                result.append("    ");


                double tau = MetaStats.TauSquared(InvVariance, Q);
                // writer1.printf(tau+ "    ");
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);
                //writer1.println("Random Effect Inverse Variance: " + RandomInvVariance);

                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, CodomOddsRatio);
                //writer1.printf(randWeightedMean+ "    ");
                result.append(String.valueOf(randWeightedMean));
                result.append("    ");

                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");


                List<Double> StdError = MetaStats.StdError(InvVariance);
                //writer1.println(StdError+ "    ");

                List<Double> randRSLimits = MetaStats.RSLimits(randWeightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                // writer1.printf(randStdError+ "    ");
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                //writer1.printf(RandLimits+"    ");
                result.append(String.valueOf(RandLimits.get(0)));
                result.append("    ");
                result.append(String.valueOf(RandLimits.get(1)));
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                //writer1.printf(randZeta + "    ");
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);
                // result=+randPhi + "    ");


                double pvalue = MetaStats.Pvalue(randPhi);
                //writer1.printf(pvalue + "    ");
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = temp.get(0).getpos();
               // System.out.println("POOOOOOOOOOOOOSSSSSIIIIITION:" +pos);
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = temp.get(0).getchrom();
                //System.out.println("CHROOOOOOOOOOOOOMMM:" +chrom);
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);


                //System.out.println("Res " + result);
                // writer1.close();
            } else if (Preferences.get(2).equals("d")) {
                result.append(temp.get(0).getName()).append("    ");
                List<Double> DomOddsRatio = MetaStats.DomOddsRatio(temp);


                // MetaStats.SumWeight(DataRead.aa1All, DataRead.ab1All, DataRead.bb1All, DataRead.aa0All, DataRead.ab0All, DataRead.bb0All);

                List<Double> InvVariance = MetaStats.DomFixedInvVariance(temp);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                //writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, DomOddsRatio);


                double Q = MetaStats.CochransQ(DomOddsRatio, weightedMean, InvVariance);
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                //writer1.printf(isquared+ "    ");
                result.append(String.valueOf(isquared));
                result.append("    ");

                double tau = MetaStats.TauSquared(InvVariance, Q);
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);
                //writer1.println("Random Effect Inverse Variance: " + RandomInvVariance);

                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, DomOddsRatio);
                result.append(String.valueOf(randWeightedMean));
                result.append("    ");

                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);
                //writer1.printf(Casecontrol.get(0)+ "    ");
                //writer1.printf(Casecontrol.get(1)+ "    ");
                //writer1.printf(Casecontrol.get(2)+ "    ");
                //result=+Casecontrol.get(0)+ "    "+Casecontrol.get(1)+ "    "+Casecontrol.get(2)+ "    ";
                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                List<Double> StdError = MetaStats.StdError(InvVariance);
                //result.append(StdError);
                // result.append("    ");

                List<Double> randRSLimits = MetaStats.RSLimits(randWeightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                result.append(RandLimits);
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);
                // writer1.println("Phi Value: " + randPhi);

                double pvalue = MetaStats.Pvalue(randPhi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = rs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = rs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);


            } else if (Preferences.get(2).equals("r")) {
                result.append(temp.get(0).getName()).append("    ");
                List<Double> RecOddsRatio = MetaStats.RecOddsRatio(temp);
                //writer1.println("Dominant Odds Ratio: " + RecOddsRatio);

                //MetaStats.SumWeight(DataRead.aa1All, DataRead.ab1All, DataRead.bb1All, DataRead.aa0All, DataRead.ab0All, DataRead.bb0All);

                List<Double> InvVariance = MetaStats.RecFixedInvVariance(temp);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                // writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, RecOddsRatio);


                double Q = MetaStats.CochransQ(RecOddsRatio, weightedMean, InvVariance);
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                //writer1.printf(isquared+ "    ");
                result.append(String.valueOf(isquared));
                result.append("    ");

                double tau = MetaStats.TauSquared(InvVariance, Q);
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);
                // writer1.println("Random Effect Inverse Variance: " + RandomInvVariance);

                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, RecOddsRatio);
                result.append(String.valueOf(randWeightedMean));
                result.append("    ");


                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);
                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                List<Double> StdError = MetaStats.StdError(InvVariance);
                //result.append(StdError);
                // result.append("    ");

                List<Double> randRSLimits = MetaStats.RSLimits(randWeightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                result.append(RandLimits);
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);
                // writer1.println("Phi Value: " + randPhi);

                double pvalue = MetaStats.Pvalue(randPhi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = rs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = rs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

            }
        }


        if (Preferences.get(1).equals("f")) {
            FileWriter flusher2 = new FileWriter("output.txt", false);
            flusher2.flush();
            Files.write(Paths.get("output.txt"), "rs    Weighted Mean    Cases    Controls    N    StdError    Limits    Zeta    P-Value    Pos.    Chrom.\r\n".getBytes(), StandardOpenOption.WRITE);

            if (Preferences.get(2).equals("c")) {
                result.append(temp.get(0).getName()).append("    ");

                List<Double> CodomOddsRatio = MetaStats.CodomOddsRatio(temp);


                List<Double> InvVariance = MetaStats.CodomFixedInvVariance(temp);
                // writer1.println("Fixed Effect Inverse Variance: " + InvVariance);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                //writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, CodomOddsRatio);
                result.append(String.valueOf(weightedMean));
                result.append("    ");

                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                List<Double> StdError = MetaStats.StdError(InvVariance);


                List<Double> randRSLimits = MetaStats.RSLimits(weightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = rs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = rs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

            } else if (Preferences.get(2).equals("d")) {
                result.append(temp.get(0).getName()).append("    ");

                List<Double> DomOddsRatio = MetaStats.DomOddsRatio(temp);


                List<Double> InvVariance = MetaStats.DomFixedInvVariance(temp);
                // writer1.println("Fixed Effect Inverse Variance: " + InvVariance);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                //writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, DomOddsRatio);
                result.append(String.valueOf(weightedMean));
                result.append("    ");

                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                List<Double> StdError = MetaStats.StdError(InvVariance);


                List<Double> randRSLimits = MetaStats.RSLimits(weightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = rs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = rs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

            } else if (Preferences.get(2).equals("r")) {
                result.append(temp.get(0).getName()).append("    ");

                List<Double> RecOddsRatio = MetaStats.RecOddsRatio(temp);


                List<Double> InvVariance = MetaStats.RecFixedInvVariance(temp);
                // writer1.println("Fixed Effect Inverse Variance: " + InvVariance);

                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);
                //writer1.println("Study Weights: " + StudyWeights);

                double weightedMean = MetaStats.WeightedMean(InvVariance, RecOddsRatio);
                result.append(String.valueOf(weightedMean));
                result.append("    ");

                List<Double> Casecontrol = MetaStats.AlleleFreq(temp);

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                List<Double> StdError = MetaStats.StdError(InvVariance);


                List<Double> randRSLimits = MetaStats.RSLimits(weightedMean, StdError, temp);
                //writer1.println("Random Effect Confidence Interval for each study: " + randRSLimits);

                double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = rs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = rs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

            }
        }
        temp.clear();
    }


    public static void ContDataSplit(List<ContResearch> ctemp, List<String> Preferences) throws IOException {
        StringBuilder result = new StringBuilder();

        if (Preferences.get(1).equals("r")) {
            FileWriter flusher = new FileWriter("output.txt", false);
            flusher.flush();
            Files.write(Paths.get("output.txt"), "rs    Q    I^2    T^2    Weighted Mean    Cases    Controls    N    StdError    Limits    Zeta    P-Value    Pos.    Chrom.\r\n".getBytes(), StandardOpenOption.WRITE);


            if (Preferences.get(2).equals("c")) {

                result.append(ctemp.get(0).getName()).append("    ");
                List<Double> CodomEffectSize = ContMetaStats.CodomEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.CodomInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                double weightedMean = MetaStats.WeightedMean(InvVariance, CodomEffectSize);


                double Q = MetaStats.CochransQ(CodomEffectSize, weightedMean, InvVariance);
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                result.append(String.valueOf(isquared));
                result.append("    ");


                double tau = MetaStats.TauSquared(InvVariance, Q);
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);


                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, CodomEffectSize);
                result.append(String.valueOf(weightedMean));
                result.append("    ");


                List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp, Preferences.get(2));

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");


                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                result.append(String.valueOf(RandLimits));
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);


                double pvalue = MetaStats.Pvalue(randPhi);
                result.append(String.valueOf(pvalue));
                result.append("    ");


                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);


            }
            else if (Preferences.get(2).equals("d")) {

                result.append(ctemp.get(0).getName()).append("    ");
                List<Double> DomEffectSize = ContMetaStats.DomEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.DomInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                double weightedMean = MetaStats.WeightedMean(InvVariance, DomEffectSize);


                double Q = MetaStats.CochransQ(DomEffectSize, weightedMean, InvVariance);
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                result.append(String.valueOf(isquared));
                result.append("    ");


                double tau = MetaStats.TauSquared(InvVariance, Q);
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);


                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, DomEffectSize);
                result.append(String.valueOf(weightedMean));
                result.append("    ");


                List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp, Preferences.get(2));

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");


                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                result.append(String.valueOf(RandLimits));
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);


                double pvalue = MetaStats.Pvalue(randPhi);
                result.append(String.valueOf(pvalue));
                result.append("    ");


                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);


            }
            else if (Preferences.get(2).equals("r")) {

                result.append(ctemp.get(0).getName()).append("    ");
                List<Double> RecEffectSize = ContMetaStats.RecEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.RecInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                double weightedMean = MetaStats.WeightedMean(InvVariance, RecEffectSize);


                double Q = MetaStats.CochransQ(RecEffectSize, weightedMean, InvVariance);
                result.append(String.valueOf(Q));
                result.append("    ");

                double isquared = MetaStats.ISquared(InvVariance, Q);
                result.append(String.valueOf(isquared));
                result.append("    ");


                double tau = MetaStats.TauSquared(InvVariance, Q);
                result.append(String.valueOf(tau));
                result.append("    ");

                List<Double> RandomInvVariance = MetaStats.RandomInvVariance(InvVariance, tau);


                double randWeightedMean = MetaStats.WeightedMean(RandomInvVariance, RecEffectSize);
                result.append(String.valueOf(weightedMean));
                result.append("    ");


                List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp, Preferences.get(2));

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");


                double randStdError = MetaStats.StdErrorCombined(RandomInvVariance);
                result.append(String.valueOf(randStdError));
                result.append("    ");

                List<Double> RandLimits = MetaStats.Limits(randWeightedMean, randStdError);
                result.append(String.valueOf(RandLimits));
                result.append("    ");

                double randZeta = MetaStats.Zeta(randWeightedMean, randStdError);
                result.append(String.valueOf(randZeta));
                result.append("    ");

                double randPhi = Gaussian.Phi(randZeta);


                double pvalue = MetaStats.Pvalue(randPhi);
                result.append(String.valueOf(pvalue));
                result.append("    ");


                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);
            }
        }
        if (Preferences.get(1).equals("f")) {
            FileWriter flusher2 = new FileWriter("output.txt", false);
            flusher2.flush();
            Files.write(Paths.get("output.txt"), "rs    Weighted Mean    Cases    Controls    N    StdError    Limits    Zeta    P-Value    Pos.    Chrom.\r\n".getBytes(), StandardOpenOption.WRITE);

            if (Preferences.get(2).equals("c")) {
                result.append(ctemp.get(0).getName()).append("    ");

                List<Double> CodomEffectSize = ContMetaStats.CodomEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.CodomInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                 double weightedMean = MetaStats.WeightedMean(InvVariance, CodomEffectSize);
                 result.append(String.valueOf(weightedMean));
                 result.append("    ");

                 List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp,Preferences.get(2));

                 result.append(String.valueOf(Casecontrol.get(0)));
                 result.append("    ");
                 result.append(String.valueOf(Casecontrol.get(1)));
                 result.append("    ");
                 result.append(String.valueOf(Casecontrol.get(2)));
                 result.append("    ");

                  double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

                }
            else if (Preferences.get(2).equals("d")) {
                result.append(ctemp.get(0).getName()).append("    ");

                List<Double> DomEffectSize = ContMetaStats.DomEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.DomInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                double weightedMean = MetaStats.WeightedMean(InvVariance, DomEffectSize);
                result.append(String.valueOf(weightedMean));
                result.append("    ");

                List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp,Preferences.get(2));

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

                }
            else if (Preferences.get(2).equals("r")) {
                result.append(ctemp.get(0).getName()).append("    ");

                List<Double> RecEffectSize = ContMetaStats.RecEffSize(ctemp);


                List<Double> InvVariance = ContMetaStats.RecInvVariance(ctemp);


                List<Double> StudyWeights = MetaStats.StudyWeights(InvVariance);


                double weightedMean = MetaStats.WeightedMean(InvVariance, RecEffectSize);
                result.append(String.valueOf(weightedMean));
                result.append("    ");

                List<Double> Casecontrol = ContMetaStats.AlleleFreq(ctemp,Preferences.get(2));

                result.append(String.valueOf(Casecontrol.get(0)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(1)));
                result.append("    ");
                result.append(String.valueOf(Casecontrol.get(2)));
                result.append("    ");

                double stdError = MetaStats.StdErrorCombined(InvVariance);
                result.append(String.valueOf(stdError));
                result.append("    ");

                List<Double> Limits = MetaStats.Limits(weightedMean, stdError);
                result.append(Limits);
                result.append("    ");

                double zeta = MetaStats.Zeta(weightedMean, stdError);
                result.append(String.valueOf(zeta));
                result.append("    ");

                double phi = Gaussian.Phi(zeta);
                //writer1.println("Fixed Effect Phi Value: " + phi);


                double pvalue = MetaStats.Pvalue(phi);
                result.append(String.valueOf(pvalue));
                result.append("    ");

                int pos = crs.get(8).getpos();
                // writer1.printf(pos + "    ");
                result.append(String.valueOf(pos));
                result.append("    ");


                int chrom = crs.get(9).getchrom();
                result.append(String.valueOf(chrom));
                result.append("    ");
                result.append("\r\n");


                writeToFile(result);

            }
            }
        }
    }







