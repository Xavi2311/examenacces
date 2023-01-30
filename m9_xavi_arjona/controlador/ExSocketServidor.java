package com.m9_xavi_arjona.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ProcessHandle.Info;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.apache.commons.net.ftp.FTPClient;

public class ExSocketServidor {
    public static String getDocFTP() throws IOException {
            FTPClient client = Operacions.conectar();
            Scanner s = new Scanner(client.retrieveFileStream("htdocs/ArchivoEncriptado.txt"));
            String contingut = "";

            while (s.hasNext()){

                contingut = contingut+s.next();

            }

            return contingut;
        }

        public static String rebreInfoSocol() throws IOException {
            String infoSocol="";
            try {
                SSLServerSocketFactory creaSocket = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                SSLServerSocket socketSSL = (SSLServerSocket) creaSocket.createServerSocket(5555);
                SSLSocket socket = (SSLSocket) socketSSL.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                        infoSocol = in.readLine();
                        System.out.println("Missatge del paquet rebut : " + infoSocol);
                        in.close();
                        out.close();
                        socket.close();
                        return infoSocol;

            } catch (Exception ex) {
                System.out.println("Error"+ ex);
            }finally{
                return infoSocol;
            }


        }

    public static void doDigest() throws NoSuchAlgorithmException {
        try {
            String info = rebreInfoSocol();
            ExSocketClient.penjarDocHashFTP(info);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    }

