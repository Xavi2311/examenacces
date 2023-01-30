package com.m9_xavi_arjona;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.m9_xavi_arjona.controlador.ExSocketClient;
import com.m9_xavi_arjona.controlador.ExSocketServidor;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }


    public static void main(String[] args) {
        try {
            ExSocketServidor.rebreInfoSocol();
            ExSocketClient.enviarInfoSocols();

        } catch (NoSuchAlgorithmException | IOException e) {

            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
