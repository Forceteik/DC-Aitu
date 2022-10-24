package org.example;

import java.io.*;
import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.lang.Thread;
import java.nio.charset.StandardCharsets;

/**
 * Processor of HTTP request.
 */
public class Processor {
     static Integer item = 0;
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException, InterruptedException {
        if (request.getRequestLine().contains("/create/itemid")) {
            create();
        }
        else if (request.getRequestLine().contains("/delete/itemid")) {
            delete();
        }
        else if (request.getRequestLine().contains("/exec/params")) {
            exec();
        }
        else {
            // Print request that we received.
            System.out.println("Got request:");
            System.out.println(request.toString());
            System.out.flush();

            // To send response back to the client.
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();
        }
        socket.close();
    }


    public void create() throws IOException{
        item = item + 1;

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Create</title></head>");
        output.println("<body><p>New Item was created</p></body>");
        output.println(item);
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void delete() throws IOException{
        item = item - 1;

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Delete</title></head>");
        output.println("<body><p>New Item was deleted</p></body>");
        output.println(item);
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void exec() throws IOException, InterruptedException {
        Thread.sleep(10000);

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Exec</title></head>");
        output.println("<body><p>Load test</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
}
