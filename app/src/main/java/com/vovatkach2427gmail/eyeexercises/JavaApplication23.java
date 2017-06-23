
package com.vovatkach2427gmail.eyeexercises;


public class JavaApplication23 {

   
    public static void main(String[] args) {
        
         int a,b;
         a = 23;
        
         System.out.println(a);
         String x;
         x = Time(a);
         System.out.println(x);
         a = 1;
         x = Time(a);
         System.out.println(x);
         a = 3;
         x = Time(a);
         System.out.println(x);
         a = 5;
         x = Time(a);
         System.out.println(x);
         a = 10;
         x = Time(a);
         System.out.println(x);
         a = 2;
         x = Time(a);
         System.out.println(x);
         
         
         a = 230;
        
         System.out.println(a);
       
         x = Time(a);
         System.out.println(x);
         a = 389;
         x = Time(a);
         System.out.println(x);
         a = 168;
         x = Time(a);
         System.out.println(x);
         a = 568;
         x = Time(a);
         System.out.println(x);
         a = 600;
         x = Time(a);
         System.out.println(x);
         a = 150;
         x = Time(a);
         System.out.println(x);
            a = 830;
        
         System.out.println(a);
       
         x = Time(a);
         System.out.println(x);
         a = 989;
         x = Time(a);
         System.out.println(x);
         a = 1668;
         x = Time(a);
         System.out.println(x);
         a = 2568;
         x = Time(a);
         System.out.println(x);
         a = 3600;
         x = Time(a);
         System.out.println(x);
         a = 1400;
         x = Time(a);
         System.out.println(x);
              a = 8830;
        
         System.out.println(a);
       
         x = Time(a);
         System.out.println(x);
         a = 10989;
         x = Time(a);
         System.out.println(x);
         a = 21668;
         x = Time(a);
         System.out.println(x);
         a = 32568;
         x = Time(a);
         System.out.println(x);
         a = 53600;
         x = Time(a);
         System.out.println(x);
         a = 11400;
         x = Time(a);
         System.out.println(x);
    }
    static String Time(int hours){
        
    String T = null;
//      if(hours<=24){
//         T = "Користувач не тренувався "+Hours(hours);
//      }
//      if (hours<720&&hours>24){
//         T = "Користувач не тренувався "+Weeks(hours);
//      }
    T = "Користувач не тренувався "+Years(hours);
    return T;
    }
    static String Hours(int hours){
        String T = "";
         if(hours == 1||hours == 21){
          T = hours+" годину";
          }
          if(hours>=2&&hours<=4||hours>=22&&hours<=24){
             T = hours+" години";  
          }
          if(hours>=5&&hours<=20){
             T = hours+" годин";  
          } 
    return T;
    }
    static String Days(int hours){
      
        int days,hour = 0;
        days = hours/24;
        if(hours>24){
            hour = hours%24;
        }else if(hours<24){
            hour = hours;
        }
        if(days>30){
            System.out.println("30 dniv maksumym!");
        }
        String T = "";
        if(days==0){
            T =  ""+Hours(hour);
        }
        if(days==1||days==21){
            T = days+" день "+Hours(hour);
        }
        if(days>=5&&days<=20||days>=25&&days<=30){
            T = days+" днів "+Hours(hour);
        }
        if(days>1&&days<5||days>=22&&days<=24){
            T = days+" дні "+Hours(hour);
        }
       return T; 
    }
    static String Weeks(int hours){
        int weeks = hours / 168;
        int daysH=0;
        if(hours>168){
            daysH = hours % 168;
        }else if(hours<168){
            daysH = hours;
        }
          if(weeks>4){
            System.out.println("4 weeks maksumym!");
        }
        String T = "";
        if(weeks==0){
            T =  ""+Days(daysH);
        }
        if(weeks==1){
            T = weeks+" тиждень "+Days(daysH);
        }
        if(weeks>=2&&weeks<=4){
            T = weeks+" тижні "+Days(daysH);
        }
       
       return T;
    }
    static String Months(int hours){
        int months = hours / 720;
        int weeksH=0;
        if(hours>720){
            weeksH = hours % 720;
        }else if(hours<720){
            weeksH = hours;
        }
          if(months>12){
            System.out.println("12 month maksumym!");
        }
        String T = "";
        if(months==0){
            T =  ""+Weeks(weeksH);
        }
        if(months==1){
            T = months+" місяць "+Weeks(weeksH);
        }
        if(months>=2&&months<=4){
            T = months+" місяці "+Weeks(weeksH);
        }
        if(months>=5&&months<=12){
            T = months+" місяців "+Weeks(weeksH);
        }
       
       return T;
    }
    static String Years(int hours){
        int years = hours / 8640;
        int monthsH=0;
        if(hours>8640){
            monthsH = hours % 8640;
        }else if(hours<8640){
            monthsH = hours;
        }
          
        String T = "";
        if(years==0){
            T =  ""+Months(monthsH);
        }
        if(years>60){
            T = "Years Error, maximum 60 years!";
        }
        if(years==1||years==21||years==31||years==41||years==41||years==51){
            T = years+" рік "+Months(monthsH);
        }
        if(years>=2&&years<=4||years>=22&&years<=24||years>=32&&years<=34
                ||years>=42&&years<=44||years>=52&&years<=54){
            T = years+" роки "+Months(monthsH);
        }
        if(years>=5&&years<=20||years>=25&&years<=30||years>=35&&years<=40
                ||years>=45&&years<=50||years>=55&&years<=60){
            T = years+" років "+Months(monthsH);
        }
       
       return T;
    }
}