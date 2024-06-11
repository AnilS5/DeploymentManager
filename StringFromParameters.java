//package com.deplomanager;

public class StringFromParameters {

    // To get data based on number of arguments.
    public static StringBuilder getParameters(String[] args) {
        
        // To get data based on number of arguments.
        StringBuilder myString = new StringBuilder();
        for(int i=0; i< args.length; i++) {
            myString.append(args[i] + "&");
        }
        return myString;
    }
}
