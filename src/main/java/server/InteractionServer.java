package server;

import utility.interaction.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class InteractionServer {
    private static InteractionServer interactionServer = null;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;
    private static final int BUFFER_SIZE = 4096;

    private InteractionServer() {
        try {
            this.socket = new DatagramSocket(new InetSocketAddress(3000));

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static InteractionServer getInstance() {
        if (interactionServer == null) {
            interactionServer = new InteractionServer();
        }
        return interactionServer;
    }

    public Command getCommand() {
        Command command = null;
        try {
            DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
            socket.receive(packet);
            this.address = packet.getAddress();
            this.port = packet.getPort();
            ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
            command = (Command) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return command;
    }

    public void sendAnswer(Answer answer) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(out);
            output.writeObject(answer);
            byte[] serializedAnswer = out.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(serializedAnswer);
            buffer.get(serializedAnswer);
            DatagramPacket packet = new DatagramPacket(serializedAnswer, serializedAnswer.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}