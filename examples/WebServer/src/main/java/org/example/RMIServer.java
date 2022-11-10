package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIServer {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8081;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        ArrayList<String> macs = new ArrayList<String>();
        int lines = 0;
        try {

            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service
            BufferedReader reader = new BufferedReader(new FileReader("D:\\IntelliJ IDEA Setup\\dc-course-master 2\\examples\\WebServer\\src\\main\\java\\org\\example\\randomNums.txt"));
            String temp = reader.readLine();


            String[] list = temp.split("\\s+");
            for (String token : list) {
                int token1 = Integer.parseInt(token);
                service.addElem(token1);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}