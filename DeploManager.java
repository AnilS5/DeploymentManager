//package com.deplomanager;

// Java inbuit libraries to to store data and exceptions.
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeploManager {

    /**
     * @param organization
     * @param deploymentUnitName
     * @param target
     */


    public StringBuilder myMethod(String[] args) {

        return StringFromParameters.getParameters(args);
    }

    public StringBuffer  getHttpData(String result) throws IOException, KeyManagementException, NoSuchAlgorithmException {
    
        return GetDeployments.getDeployments(result);
    }

    public static void getValues(List<String> list) {
        GetValues.getValues(list);
    }

    /**
     * @param args
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchFieldException, SecurityException {
        
        if (args.length == 0) {
            System.out.println("\n\nNo parameters passed..!\n\nUsage is: java_executor class_file parameters" + "\n" + "  Parameters:\n    pageSize\n    organization\n    deploymentUnitName\n    target\n    duration\n\n");
        }else {
            DeploManager myArgs = new DeploManager();
            StringBuilder result = myArgs.myMethod(args); 
            StringBuffer myDeplo = myArgs.getHttpData(result.toString());
            
            ArrayList<String> myarray = new ArrayList<String>(Arrays.asList(myDeplo.toString().replaceAll("\\{\"content\":\\[", "").replaceAll("\\],\"totalElements\":(.*)\\}",",").replaceAll("\\{", "\\{\\\\").replaceAll("\\}", "\\\\}").replaceAll("\\},", "\\}\\\\").replaceAll(":\\{", "\\\\{").replaceAll(":\\[\\{", "\\\\[\\\\{").replaceAll("\\}\\]", "\\}\\\\]").split("(\\\\|,)")));
            //System.out.println(myarray);
            DeploManager.getValues(myarray);

        }
    }
}
