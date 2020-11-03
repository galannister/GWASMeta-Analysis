package com.company;

import java.lang.Math;
import java.util.*;

import static java.lang.Math.abs;


public  class MetaStats  {

//finds codominant Odds Ratio

    public static List<Double> AlleleFreq(List<Research> temp) {
        List<Double> AlleleFreq = new ArrayList();
        List<Double> a0= new ArrayList<>();
        List<Double> b0= new ArrayList<>();
        List<Double> a1= new ArrayList<>();
        List<Double> b1 =new ArrayList<>();
        double AA1=0;
        double AB1=0;
        double BB1=0;
        double AA0=0;
        double AB0=0;
        double BB0=0;
        for (int i = 0; i < temp.size(); i++) {
                AA1 = temp.get(i).getAa1();
                AB1 = temp.get(i).getAb1();

                BB1 = temp.get(i).getBb1();
                AA0 = temp.get(i).getAa0();

                AB0 = temp.get(i).getAb0();

                BB0 = temp.get(i).getBb0();
        }
        double cases=AA0+BB0+AB0;
        double control=AA1+BB1+AB1;
        double n=cases+control;
        AlleleFreq.add(cases);
        AlleleFreq.add(control);
        AlleleFreq.add(n);

        return AlleleFreq;
    }
    public static List<Double> CodomOddsRatio(List<Research> temp) {
        List<Double> OddsRatio = new ArrayList();
        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();

            double AB1 = temp.get(i).getAb1();

            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();

            double AB0 = temp.get(i).getAb0();

            double BB0 = temp.get(i).getBb0();

            double B1 = 2 * BB1 + AB1;
            //System.out.println("AA0" +B1);
            double A1 = 2 * AA1 + AB1;
            //System.out.println("AA0" +A1);
            double B0 = 2 * BB0 + AB0;
            //System.out.println("AA0" +B0);
            double A0 = 2 * AA0 + AB0;
            //System.out.println("AA0" +A0);
            try {
                double OR = (B1 / B0) / (A1 / A0);


                double logOr= Math.log(OR);
                OddsRatio.add(logOr);
            } catch (ArithmeticException e) {
                System.out.println("You Shouldn't divide a number by zero");
            }
        }

        //System.out.println("OR: " +Arrays.toString(OddsRatio.toArray()));


        return OddsRatio;
    }

//finds dominant Odds Ratio
    public static List<Double> DomOddsRatio(List<Research> temp) {

        List<Double> DomOddsRatio= new ArrayList<>();
        double domcase;
        double domcontrol;
        double reccase;
        double reccontrol;
        double or;
        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();
            double AB1 = temp.get(i).getAb1();
            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();
            double AB0 = temp.get(i).getAb0();
            double BB0 = temp.get(i).getBb0();

            domcase= AA1+ AB1;
            domcontrol=AA0+AB0;
            reccase=BB1;
            reccontrol=BB0;



            or= (domcase/domcontrol) / (reccase/reccontrol);
            double logOr= Math.log(or);


            DomOddsRatio.add(logOr);


        }
        return DomOddsRatio;
    }

//finds recessive Odds Ratio
    public static List<Double> RecOddsRatio(List<Research> temp) {

        List<Double> RecOddsRatio = new ArrayList<>();
        double domcase;
        double domcontrol;
        double reccase;
        double reccontrol;
        double or;
        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();
            double AB1 = temp.get(i).getAb1();
            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();
            double AB0 = temp.get(i).getAb0();
            double BB0 = temp.get(i).getBb0();

            domcase = BB1 + AB1;
            domcontrol = BB0 + AB0;
            reccase = AA1;
            reccontrol = AA0;


            or = (domcase / domcontrol) / (reccase / reccontrol);
            double logOr= Math.log(or);

            RecOddsRatio.add(logOr);


        }
        return RecOddsRatio;
    }


//weight of studies based on population
    public static int SumWeight(List<Double> aa1, List<Double> ab1, List<Double> bb1, List<Double> aa0, List<Double> ab0, List<Double> bb0) {
        List<Double> Weight = new ArrayList();
        List<Double> Total =new ArrayList<>();
        double sum ;
        double weight;
        double total=0;
        for (int i = 0; i < aa1.size(); i++) {
            double AA1 = aa1.get(i);
            double AB1 = ab1.get(i);
            double BB1 = bb1.get(i);
            double AA0 = aa0.get(i);
            double AB0 = ab0.get(i);
            double BB0 = bb0.get(i);

            sum = AA1 + AB1 + BB1 + AA0 + AB0 + BB0;
            Total.add(sum);
        }
        for(int i =0; i<Total.size(); i++){
            total=total+Total.get(i);
        }
        for(int i =0; i<Total.size(); i++){
            weight= Total.get(i)/total;
            Weight.add(weight);
        }

        //System.out.println("Weight: " +Arrays.toString(Weight.toArray()));

        return 0;

    }


   /* public static List<Double> Mean(List<Double> aa1, List<Double> ab1, List<Double> bb1, List<Double> aa0, List<Double> ab0, List<Double> bb0){

        List<Double> Mean= new ArrayList<>();

        for (int i = 0; i < aa1.size(); i++) {              //aa1.size() equilavent to the number of studies in the input file
            double AA1 = aa1.get(i);
            double AB1 = ab1.get(i);
            double BB1 = bb1.get(i);
            double AA0 = aa0.get(i);
            double AB0 = ab0.get(i);
            double BB0 = bb0.get(i);

           double sum = AA1 + AB1 + BB1 + AA0 + AB0 + BB0 ;
          double  mean=sum/aa1.size();
            Mean.add(mean);
            System.out.println("mean="+ mean);
        }
        return Mean;
    }


//finds Mean for patients and controls
    public static List<Double> PartialMean(List aa, List ab, List bb) {    //used to find means of patients and controls

        List<Double> PartialMean = new ArrayList<>();

        for (int i = 0; i < aa.size(); i++) {              //aa1.size() equilavent to the number of studies in the input file
            int AA1 = (int) aa.get(i);
            int AB1 = (int) ab.get(i);
            int BB1 = (int) bb.get(i);


            int sum = AA1 + AB1 + BB1;
            double mean = sum / aa.size();
            PartialMean.add(mean);
            System.out.println("mean=" + mean);
        }
        return PartialMean;
    }
*/

//finds Codominant Inverse Variance (Fixed Effects Model)
    public static List<Double> CodomFixedInvVariance(List<Research> temp) {


        List<Double> InvVariance = new ArrayList();


        double inverse;
        float mean;


        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();
            double AB1 = temp.get(i).getAb1();
            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();
            double AB0 = temp.get(i).getAb0();
            double BB0 = temp.get(i).getBb0();


            double B1 = 2 * BB1 + AB1;

            double A1 = 2 * AA1 + AB1;

            double B0 = 2 * BB0 + AB0;

            double A0 = 2 * AA0 + AB0;

           double variance = (1/B1) + (1/A1) + (1/B0)+ (1/A0);
           inverse =1/variance;


            // Η ριζα αυτου που εχω τωρα ειναι το se



            InvVariance.add(inverse);

        }
       // System.out.println("Inverse variance: " +Arrays.toString(InvVariance.toArray()));

        return InvVariance;

    }


    public static List<Double> DomFixedInvVariance(List<Research> temp) {


        List<Double> DomInvVariance = new ArrayList();


        double inverse;
        float mean;
        double domcase;
        double domcontrol;
        double reccase;
        double reccontrol;


        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();
            double AB1 = temp.get(i).getAb1();
            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();
            double AB0 = temp.get(i).getAb0();
            double BB0 = temp.get(i).getBb0();


            domcase= AA1+ AB1;
            domcontrol=AA0+AB0;
            reccase=BB1;
            reccontrol=BB0;

            double variance = 1 / domcase + 1 /domcontrol + 1 / reccase + 1 / reccontrol;
            inverse =1/variance;

            DomInvVariance.add(inverse);

        }
        //System.out.println("Inverse variance: " + Arrays.toString(DomInvVariance.toArray()));

        return DomInvVariance;
    }


    public static List<Double> RecFixedInvVariance(List<Research> temp) {


        List<Double> RecInvVariance = new ArrayList();


        double inverse;
        float mean;
        double domcase;
        double domcontrol;
        double reccase;
        double reccontrol;


        for (int i = 0; i < temp.size(); i++) {
            double AA1 = temp.get(i).getAa1();
            double AB1 = temp.get(i).getAb1();
            double BB1 = temp.get(i).getBb1();
            double AA0 = temp.get(i).getAa0();
            double AB0 = temp.get(i).getAb0();
            double BB0 = temp.get(i).getBb0();

            domcase= BB1+ AB1;
            domcontrol=BB0+AB0;
            reccase=AA1;
            reccontrol=AA0;

            double variance = 1 / domcase + 1 /domcontrol + 1 / reccase + 1 / reccontrol;
            inverse =1/variance;

            RecInvVariance.add(inverse);

        }
       // System.out.println("Inverse variance: " + Arrays.toString(RecInvVariance.toArray()));

        return RecInvVariance;
    }

    public  static List<Double> StdError(List<Double> CodomFixedInvVariance){
        List<Double> StdError= new ArrayList<>();
        for(int i=0; i<CodomFixedInvVariance.size();i++){
            double temp= 1/CodomFixedInvVariance.get(i);
            //System.out.println("TEMP"+temp);
            double stderror= Math.sqrt(temp);
            StdError.add(stderror);
        }
        return StdError;
    }
  /*  //finds Inverse Variance for patients and controls(Fixed Effects Model)
     public static List<Double> PartialFixedInvVariance(List aa, List ab, List bb, List<Double> PartialMean) {


        List<Double> PartialInvVariance = new ArrayList();


        double inverse;
        float mean;


        for (int i = 0; i < aa.size(); i++) {
            int AA = (int) aa.get(i);
            int AB = (int) ab.get(i);
            int BB = (int) bb.get(i);


            double var = ((AA-PartialMean.get(i))*(AA-PartialMean.get(i)) + (AB-PartialMean.get(i))*(AB-PartialMean.get(i)) + (BB-PartialMean.get(i))*(BB-PartialMean.get(i)))/aa.size()-1;



            inverse= 1/var;
            System.out.println("Partial Variance="+ var);
            PartialInvVariance.add(inverse);

        }
        System.out.println("Partial Inverse Variance=" +Arrays.toString(PartialInvVariance.toArray()));

        return PartialInvVariance;

    }

//Cohen's d effect size
   public static List<Double> EffectSize(List<Double>ControlInvVariance,List<Double>PatientInvVariance, List<Double> ControlMean, List<Double> PatientMean){
        List<Double> PatientVariance= new ArrayList<>();
        List<Double> ControlVariance= new ArrayList<>();
        List<Double> EffSize= new ArrayList<>();
        double effSize;
        for(int i=0; i<ControlInvVariance.size(); i++){
            double controlVar=1/ControlInvVariance.get(i);
            double patientVar=1/PatientInvVariance.get(i);
            ControlVariance.add(controlVar);
            PatientVariance.add(patientVar);
        }


        for (int i=0; i<ControlMean.size(); i++){
            effSize=(PatientMean.get(i)-ControlMean.get(i))/((Math.sqrt(PatientVariance.get(i)+ControlVariance.get(i))/2));
            EffSize.add(effSize);

        }

        System.out.println("Effect size: " +EffSize);

        return EffSize;
    }
*/

    public  static List<Double> StudyWeights(List<Double> InvVariance){
        double total=0;
        double weight=0;
        List<Double> studyWeights=new ArrayList<>();
        for(int i=0; i<InvVariance.size();i++){
            total+=InvVariance.get(i);
        }
        for(int j=0; j<InvVariance.size();j++){
            weight=InvVariance.get(j)/total;
            studyWeights.add(weight);
        }
        return studyWeights;
    }

//Weighted Effect Mean of all studies
    public  static double WeightedMean(List<Double> FixedInvVariance, List<Double> EffectSize){

        double numerator=0;
        double denominator=0;
        for(int i=0; i< FixedInvVariance.size(); i++) {
            numerator += (EffectSize.get(i)) * (FixedInvVariance.get(i));
            denominator += FixedInvVariance.get(i);

        }
        double WeightedMean=numerator/denominator;
       // System.out.println("WeightedMean= " +WeightedMean);
        return WeightedMean;
    }

//Combined Std Error
    public static double StdErrorCombined(List<Double> InvVariance){
        double combVariance=0;
        for(int i=0; i<InvVariance.size(); i++){
            combVariance+=InvVariance.get(i);
        }
        combVariance=1/combVariance;
        double stdErr= Math.sqrt(combVariance);
        //System.out.println("Std. Error= " +stdErr);
        return stdErr;
    }

    public static List<Double> RSLimits(double weightedMean, List<Double> StdError, List<Research> temp ) {
        double lowerLimit = 0;
        double upperLimit = 0;
        List<Double> RSLimits = new ArrayList<>();
        List<Double>OR=CodomOddsRatio(temp);
        for(int i=0; i<StdError.size(); i++){
            lowerLimit = OR.get(i) - 1.96 * StdError.get(i);
            upperLimit = OR.get(i) + 1.96 * StdError.get(i);
            //System.out.println("ORrrr: =" + OR.get(i));
            //System.out.println("StdERR: =" + StdError.get(i));
            RSLimits.add(lowerLimit);
            RSLimits.add(upperLimit);
           // System.out.println("Limits: =" + RSLimits);
        }

        return RSLimits;
    }
//Limits with 95% confidence interval for the combined effect
    public static List<Double> Limits(double weightedMean, double stdErrorCombined ){
        double lowerLimit=0;
        double upperLimit=0;
        lowerLimit= weightedMean - 1.96*stdErrorCombined;
        upperLimit= weightedMean + 1.96*stdErrorCombined;
        List<Double> Limits= new ArrayList<>();
        Limits.add(lowerLimit);
        Limits.add(upperLimit);
        //System.out.println("Limits: =" +Limits);
        return Limits;
    }

//Finds the zeta value which is used to find th φ(z) value
    public static double Zeta(double weightedMean, double stdErrorCombined){
        double Zeta=abs(weightedMean/stdErrorCombined);

        //System.out.println("Z= "+Zeta);
        return Zeta;

    }

    public  static double Pvalue(double phi){
        double pvalue=2*(1-phi);
       // System.out.println("p=" +pvalue);

        return pvalue;
    }

    public static double CochransQ(List<Double> EffectSize, double weightedMean, List<Double> FixedInvVariance){
        double q=0;
        for (int i=0; i<EffectSize.size();i++){
           q+= FixedInvVariance.get(i)*((EffectSize.get(i)-weightedMean)*(EffectSize.get(i)-weightedMean));
        }
        //System.out.println("Cochran's Q= " +q);
        return q;
    }

    public  static double ISquared(List<Double> InvVariance, double q){
        double df= InvVariance.size()-1;
        double numerator=0;
        double denominator=0;
        double i=((q-df)/q)*100;
        return i;
    }

   //Calculates Tau Squared
   public  static double TauSquared(List<Double> InvVariance, double q){
       double df= InvVariance.size()-1;

       double c=0;
       double numerator=0;
       double denominator=0;
       double tau;
       if (q>df){
           for(int i=0; i<InvVariance.size(); i++){
               denominator += InvVariance.get(i);
               numerator += InvVariance.get(i)* InvVariance.get(i);

           }
           c=denominator-(numerator/denominator);
           tau = (q-df)/c;
       }else{
           tau=0;
       }
      // System.out.println("TauSquared= " +tau);
       return tau;

   }






    public  static List<Double> RandomInvVariance(List<Double> FixedInvVariance, double tau) {


        List<Double> RandomInvVariance = new ArrayList();
        List<Double> Inverse= new ArrayList<>();

        double inverse;
        float mean;
        double sum=0;
        for(int i=0;i<FixedInvVariance.size(); i++){
            inverse=1/(1/FixedInvVariance.get(i)+tau);    //<------------------------ pros8etw to tau ston paronomasth
            Inverse.add(inverse);
            sum=inverse+sum;

        }
        for(int i=0;i<FixedInvVariance.size(); i++){
            RandomInvVariance.add(Inverse.get(i));
        }



       // System.out.println("Random Inverse Variance: " +Arrays.toString(RandomInvVariance.toArray()));

        return RandomInvVariance;

    }


    //Nothing else needs to be changed in order to use the random effects model. Every other method remains the same. We just change their input in order to get the appropriate calculations.
}
