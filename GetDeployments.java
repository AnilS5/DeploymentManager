//package com.deplomanager;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.net.ssl.SSLContext;


public class GetDeployments {
     
    /**
     * @param result
     * @param args4
     * @param args3
     * @param args2
     * @param args
     * @return
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */

    public static StringBuffer getDeployments(String result) throws IOException, KeyManagementException, NoSuchAlgorithmException {
    
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return null;
            }
      
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
      
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
      
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
       
        URL url = new URL("https://deployment-manager-api.tnz.xxx.net/api/v1/deployment/container?" + result);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("Authorization", System.getenv("MYTOKEN"));
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        
        in.close();
        return content;
        
    }
}
