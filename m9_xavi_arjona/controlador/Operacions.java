package com.m9_xavi_arjona.controlador;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class Operacions {

    static String sFTP = "ftpupload.net";
    static String sUser = "epiz_33481923";
    static String sPassword = "PAVYXpajSGH";

    public static void main() {
        System.out.println("Menu xavi arjona martin");
        System.out.println("Opcions");
        System.out.println("1-Client");
        System.out.println("2-Servidor");
    }
    public static void mostrarMenuClient() {
        System.out.println("Menu xavi arjona martin");
        System.out.println("Socol Client");
        System.out.println("1-Penjar document hash al servidor FTP");
        System.out.println("2-Enviar info");
        System.out.println("Introdueix opcio:");
        Scanner scaner = new Scanner(System.in);
        int opcio = scaner.nextInt();
        switch (opcio) {
            case 1:
            String contingut="a";
                try {
                    ExSocketClient.penjarDocHashFTP(contingut);
                } catch (NoSuchAlgorithmException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    ExSocketClient.enviarInfoSocols();
                } catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void mostrarMenuServer() {
        System.out.println("Menu xavi arjona martin");
        System.out.println("Socol Client");
        System.out.println("1-Penjar document hash al servidor FTP");
        System.out.println("2-Enviar info");
        System.out.println("Introdueix opcio:");
        Scanner scaner = new Scanner(System.in);
        int opcio = scaner.nextInt();
        switch (opcio) {
            case 1:
            String contingut="";
                try {
                    ExSocketServidor.rebreInfoSocol();
                } catch (NoSuchAlgorithmException | IOException e) {
                    // TODO Auto-generated catc.h block
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    ExSocketServidor.getDocFTP();
                } catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
    }
    public static FTPClient conectar() {
        FTPClient cliente = new FTPClient();

        try {
            cliente.connect(sFTP);
            int reply = cliente.getReplyCode();

         if (!FTPReply.isPositiveCompletion(reply)) {
             cliente.disconnect();
             throw new IOException("Exception in connecting to FTP Server");
         }

         cliente.login(sUser, sPassword);
         } catch (Exception e) {
             System.out.println(e);
         }

         return cliente;
    }
}
