package com.king.service.utils;

import java.io.IOException;
import java.util.Scanner;

public class GetProcessID {

    public String getid() throws IOException{
        Process process = Runtime.getRuntime().exec(
                new String[] { "wmic", "cpu", "get", "ProcessorId" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        return serial;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        GetProcessID getProcessID = new GetProcessID();
        System.out.println("ProcessID: " + getProcessID.getid());

    }

}


