package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List <Integer> dexOpt = new ArrayList<Integer>();       //Create list of possible indexes in HashMap
        for (int i = 1; i <= 997940; i++) {
            dexOpt.add(i);
        }
        int[] numList = {1000,500,250,100,50,25,10,5};          //List for smaller distribution values
        HashMap hashMap = new HashMap(997940);                  //HashMap for randomly placing numbers
        Random randGen = new Random();

        for (int i = 1; i<=83000; i++) {                        //Add numbers 1-12 to HashMap randomly 83000 times
            for (int n = 1; n <= 12; n++) {
                int index = randGen.nextInt(dexOpt.size());
                Integer number = dexOpt.get(index);
                Integer mapNumber = n;
                Integer lower = (Integer) hashMap.get(number-1);    //Check for same number at index above and below
                Integer upper = (Integer) hashMap.get(number+1);    //to prevent consecutive pairs. Place in HashMap
                if (mapNumber != lower && mapNumber != upper) {     //if no match exists, otherwise try again.
                    hashMap.put(number, mapNumber);
                    dexOpt.remove(index);
                    System.out.println("success");
                }
                else {
                    n--;
                    System.out.println("fail");
                }
            }
        }
        int num = 12;
        for ( int amount : numList) {                               //Add numbers 13-20 using distribution values from
            num++;                                                  //list. Uses same method above.
            for ( int dex = 1; dex <= amount; dex++) {
                int index = randGen.nextInt(dexOpt.size());
                Integer number = dexOpt.get(index);
                Integer mapNumber = num;
                Integer lower = (Integer) hashMap.get(number-1);
                Integer upper = (Integer) hashMap.get(number+1);
                if (mapNumber != lower && mapNumber != upper) {
                    hashMap.put(number, mapNumber);
                    dexOpt.remove(index);
                    System.out.println("success");
                }
                else {
                    dex--;
                    System.out.println("fail");
                }
            }
        }
        FileWriter fileWriter = null;                                       //Create output file and write all values
        try {                                                               //in HashMap. Check for occurrences of 20
            fileWriter = new FileWriter("test.output.txt");                 //and display to command line.
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);

        for (Object entry : hashMap.keySet() ) {
            try {
                buffWriter.write(hashMap.get(entry).toString());
                buffWriter.newLine();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            Integer curEntry = (Integer) hashMap.get(entry);
            if (curEntry == 20) {
                System.out.println("Number 20 found at index: " + entry);
            }
        }
    }
}


