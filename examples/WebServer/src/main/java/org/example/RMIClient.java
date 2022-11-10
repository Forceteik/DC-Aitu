package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8081;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (true) {
                Integer str = service.pollElem();
            if (str == -1) {
                break;
            } else {
                System.out.println("Received: " + str);
                service.output(calculate(str));
            }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static int calculate(int num) {
        int countNumbers = 0;
        for (int i = 1; i < num; i++) {
            if (primeChecker(i)) {
                countNumbers++;
            }
        }
        return countNumbers;
    }
    public static boolean primeChecker(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {return true;}
        else{return false;}
    }
}
