package com.company;


import java.util.ArrayList;
import java.util.List;

public class ContMetaStats {

    public static List<Double> CodomEffSize(List<ContResearch> temp) {
        List<Double> CodomEffSize = new ArrayList<>();


        for (int i = 0; i < temp.size(); i++) {
            double aa = temp.get(i).getPopaa();
            double ab = temp.get(i).getPopab();
            double bb = temp.get(i).getPopbb();
            double maa = temp.get(i).getMeanaa();
            double mab = temp.get(i).getMeanab();
            double mbb = temp.get(i).getMeanbb();

            double effsize = ((2 * bb * mbb + ab * mab) / ((2 * bb) + ab)) - ((2 * aa * maa + ab * mab) / ((2 * aa) + ab));
            CodomEffSize.add(effsize);

        }
        System.out.println(CodomEffSize);

        return CodomEffSize;
    }

    public static List<Double> DomEffSize(List<ContResearch> temp) {
        List<Double> DomEffSize = new ArrayList<>();


        for (int i = 0; i < temp.size(); i++) {
            double aa = temp.get(i).getPopaa();
            double ab = temp.get(i).getPopab();
            double bb = temp.get(i).getPopbb();
            double maa = temp.get(i).getMeanaa();
            double mab = temp.get(i).getMeanab();
            double mbb = temp.get(i).getMeanbb();

            double effsize = (((bb * mbb) + (ab * mab)) / bb * ab) - maa;
            DomEffSize.add(effsize);

        }
        System.out.println(DomEffSize);

        return DomEffSize;
    }


    public static List<Double> RecEffSize(List<ContResearch> temp) {
        List<Double> RecEffSize = new ArrayList<>();


        for (int i = 0; i < temp.size(); i++) {
            double aa = temp.get(i).getPopaa();
            double ab = temp.get(i).getPopab();
            double bb = temp.get(i).getPopbb();
            double maa = temp.get(i).getMeanaa();
            double mab = temp.get(i).getMeanab();
            double mbb = temp.get(i).getMeanbb();

            double effsize = mbb - (((aa * maa) + (ab * mab)) / aa * ab);
            RecEffSize.add(effsize);

        }
        System.out.println(RecEffSize);

        return RecEffSize;
    }

    public static List<Double> CodomInvVariance(List<ContResearch> temp) {

        List<Double> CodomInvVariance = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            double AA = temp.get(i).getPopaa();
            double AB = temp.get(i).getPopab();
            double BB = temp.get(i).getPopbb();
            double stdDevAA = temp.get(i).getStdDevaa();
            double stdDevAB = temp.get(i).getStdDevab();
            double stdDevBB = temp.get(i).getStdDevbb();

            double varianceA = (((2 * AA - 1) * stdDevAA * stdDevAA) + ((AB - 1) * stdDevAB * stdDevAB)) / ((2 * AA + AB - 2) * (2 * AA + AB));
            double varianceB = (((2 * BB - 1) * stdDevBB * stdDevBB) + ((AB - 1) * stdDevAB * stdDevAB)) / ((2 * BB + AB - 2) * (2 * BB + AB));
            double variance = varianceA + varianceB;
            double invVariance = 1 / variance;
            CodomInvVariance.add(invVariance);

        }
        System.out.println("Codominant Variance: " + CodomInvVariance);
        return CodomInvVariance;
    }


    public static List<Double> DomInvVariance(List<ContResearch> temp) {

        List<Double> DomInvVariance = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            double AA = temp.get(i).getPopaa();
            double AB = temp.get(i).getPopab();
            double BB = temp.get(i).getPopbb();
            double stdDevAA = temp.get(i).getStdDevaa();
            double stdDevAB = temp.get(i).getStdDevab();
            double stdDevBB = temp.get(i).getStdDevbb();

            double variance = (((BB - 1) * stdDevBB * stdDevBB) + ((AB - 1) * stdDevAB * stdDevAB)) / ((BB + AB - 2) * (BB + AB)) + ((stdDevAA * stdDevAA) / AA);
            DomInvVariance.add(variance);

        }
        System.out.println("Dominant Variance: " + DomInvVariance);
        return DomInvVariance;
    }


    public static List<Double> RecInvVariance(List<ContResearch> temp) {

        List<Double> RecInvVariance = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            double AA = temp.get(i).getPopaa();
            double AB = temp.get(i).getPopab();
            double BB = temp.get(i).getPopbb();
            double stdDevAA = temp.get(i).getStdDevaa();
            double stdDevAB = temp.get(i).getStdDevab();
            double stdDevBB = temp.get(i).getStdDevbb();

            double variance = (((AA - 1) * stdDevAA * stdDevAA) + ((AB - 1) * stdDevAB * stdDevAB)) / ((AA + AB - 2) * (AA + AB)) + ((stdDevBB * stdDevBB) / BB);
            RecInvVariance.add(variance);

        }
        System.out.println("Recessive Variance: " + RecInvVariance);
        return RecInvVariance;
    }

    public static List<Double> AlleleFreq(List<ContResearch> ctemp, String Preferences) {
        List<Double> AlleleFreq = new ArrayList();
        List<Double> a0 = new ArrayList<>();
        List<Double> b0 = new ArrayList<>();
        List<Double> a1 = new ArrayList<>();
        List<Double> b1 = new ArrayList<>();
        double AA = 0;
        double AB = 0;
        double BB = 0;
        double cases = 0;
        double control = 0;
        double n = 0;

        for (int i = 0; i < ctemp.size(); i++) {
            AA = ctemp.get(i).getPopaa();
            AB = ctemp.get(i).getPopab();

            BB = ctemp.get(i).getPopbb();

            if (Preferences.equals("c")) {
                cases = BB;
                control = AA;
                n = cases + control;
            } else if (Preferences.equals("d")) {
                cases = BB;
                control = AA + AB;
                n = cases + control + AB;
            } else if (Preferences.equals("r")) {
                cases = BB + AB;
                control = AA;
                n = cases + control + AB;
            }
        }

        AlleleFreq.add(cases);
        AlleleFreq.add(control);
        AlleleFreq.add(n);

        return AlleleFreq;

    }
}
