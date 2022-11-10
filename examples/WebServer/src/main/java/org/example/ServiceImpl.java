package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    ArrayList<Integer> primeNum = new ArrayList<Integer>();
    private final BlockingQueue<Integer> queue;
    static long startTime = 0, endTime = 0;
    boolean firstProcessStarted = false;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    public void output(int num) throws RemoteException {
        System.out.println("List: " + queue);
        primeNum.add(num);
        if (queue.isEmpty()) {
            try {
                Thread.sleep((long) 11.11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void finish(ArrayList<Integer> numberList) {
        int sum = 0;
        for (int numbers : numberList) {
            sum += numbers;
        }
        System.out.println("Sum: "+sum);
        endTime = System.currentTimeMillis();
        System.out.println("Time taken:" + (endTime - startTime) );
    }

    public int pollElem() throws RemoteException {
        if (!firstProcessStarted) {
            startTime = System.currentTimeMillis();
        }
        firstProcessStarted = true;
        if (this.queue.isEmpty()) finish(primeNum);
        return this.queue.poll();
    }

    public void addElem(int num) throws RemoteException {
        this.queue.add(num);
        System.out.println("Added: " + num);
    }

}