package com.bam.timerapp.model;

import com.bam.timerapp.responce.Response;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketService extends Thread {



    public interface ResponseListener{
        void message(Response response);
    }

    private ResponseListener __listener;

    private Socket __socket;
    private BufferedReader __bufferedReader;
    private PrintWriter __printWriter;

    private boolean __disconnect;

    String host;
    int port;

    public SocketService(String string, int port) throws IOException {

        host =string;
        this.port = port;
    }


    private void  create(String host, int port) throws IOException {
        __socket = new Socket(host, port);
        __bufferedReader = new BufferedReader(new InputStreamReader(__socket.getInputStream()));
        __printWriter = new PrintWriter(__socket.getOutputStream(), true);
    }
    @Override
    public void run() {

        try {
            create(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (!__disconnect){
                String msg = __bufferedReader.readLine();
                if (msg != null){
                    Response _response = new Gson().fromJson(msg, Response.class);
                    __listener.message(_response);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeMessage(String result) {
        __printWriter.println(result);
    }

    public void setListener(ResponseListener listener) {
        __listener = listener;
    }

    public boolean isDisconnect() {
        return __disconnect;
    }

    public void setDisconnect(boolean disconnect) {
        __disconnect = disconnect;
    }
}
