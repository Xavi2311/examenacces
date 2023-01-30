package com.m9_xavi_arjona.controlador;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ExSocketClient {






    public static void penjarDocHashFTP(String contingut) throws NoSuchAlgorithmException, IOException {
        //Demanar l'algorisme
        System.out.println("Introdueix el nom de l'algorisme a aplicar:");
        String algorisme ="";
        Scanner sc = new Scanner(System.in);
        algorisme=sc.nextLine();

        //Escriure el contingut
        // System.out.println("Introdueix el contimgut del fitxer:");
        // String contingut ="";
        // contingut=sc.nextLine();

                //Pasar el contingut a array i xifrar-lo
                byte [] dades = contingut.getBytes();
                MessageDigest md = MessageDigest.getInstance(algorisme);
                byte [] hash = md.digest(dades);

                //Fer un fitxer i escriure el contingut
                File arxiu = new File("Arxiu.txt");
                FileOutputStream fos = new FileOutputStream(arxiu);
                String hex = javax.xml.bind.DatatypeConverter.printHexBinary(hash);
                System.out.println(hex);
                byte [] hexarray = hex.getBytes();
                fos.write(hexarray);

                //Crear el file remot
                String remotefile="htdocs/ArchivoEncriptado.txt";
                FTPClient client = Operacions.conectar();
                InputStream inputStream = new FileInputStream(arxiu);
                client.storeFile(remotefile, inputStream);
    }

    public static void enviarInfoSocols() throws IOException, NoSuchAlgorithmException, KeyManagementException {

        final int puerto = 5555;

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);

        // Crear un socket SSL
        SSLSocketFactory ssf = context.getSocketFactory();
        SSLSocket clientSocket = (SSLSocket) ssf.createSocket("localhost", puerto);

        //ServerSocket servidor = null;
        clientSocket.startHandshake();

        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        // Enviar y recibir datos
        out.println("Xavi Arjona Martin - 30/01/2023");
        out.flush();


        clientSocket.close();
    }


}
