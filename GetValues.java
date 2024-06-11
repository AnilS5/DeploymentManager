//package com.deplomanager;

// Java in-built libraries for fine tuning fetched data.

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class GetValues {
    public static void getValues(List<String> list) throws ArrayIndexOutOfBoundsException {
        
        // Count number of deployments from DM.
        String sts = "\"deployment\"";
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0; i < list.size(); i++) {
            
            if(list.get(i).contentEquals(sts)) {
                stk.push(i);
            }
        }
        stk.push(list.size() + 1);

        // Maintain status of fine tuning.
        int ob = 0;
        List<Integer> str_count = new ArrayList<Integer>();

        String data = "";
        String next_data="";

        // Fine tuning of fetched deployments data in required format.
        for(int j=0; j < stk.size()-1; j++) {

            
            List<String> new_list = list.subList(stk.get(j)-1, stk.get(j+1)-1);
               
            for(int k=1; k < new_list.size(); k++) {

                if(new_list.get(k).contentEquals("{") || new_list.get(k).contentEquals("[")) {

                    ++ob;

                    if(!new_list.get(k-1).contentEquals("[") && !new_list.get(k-1).contentEquals("}") && !new_list.get(k-1).contentEquals("{") && !new_list.get(k-1).contentEquals("]") && !new_list.get(k-1).contentEquals("}") && !new_list.get(k-1).contentEquals("}")) {
                        
                        next_data = new_list.get(k-1).replace("\"", "") + ".";
                        str_count.add(str_count.size(), next_data.length());

                        data = data.concat(next_data);

                    }else {

                        next_data = "";
                        str_count.add(str_count.size(), next_data.length());
                        data = data.concat(next_data);                        
                    }

                }else if(ob >= 1 && (new_list.get(k).contentEquals("}") || new_list.get(k).contentEquals("]"))){

                    --ob;
                    
                    data = data.substring(0, data.length()-str_count.get(str_count.size()-1));
                    next_data = "";
                    str_count.remove(ob);
                    
                    
                }else if(!new_list.get(k).contentEquals("[") && !new_list.get(k).contentEquals("]")){

                    next_data = new_list.get(k).replace("\"", "");

                    if(next_data.contains(":")) {
                        System.out.println("\"" + data.concat(next_data).replace(":", "\"=\"").replace("https\"=\"", "https:") + "\"");
                    }
                }
            }

            // Clear off the pre-loaded data.
            ob = 0;
            System.out.println("\n\n");
        }
    }
}
