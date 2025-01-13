package com.example.chatflow.websocket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketTestClient {
	public static void main(String[] args) {
		String serverUri = "ws://localhost:8080/chat"; 
		
		WebSocketClient client = new WebSocketClient(URI.create(serverUri)) {
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println("Connected to the server");
				// Enviar un mensaje al servidor después de la conexión
				send("Hello Server!");
			}

			@Override
			public void onMessage(String message) {
				System.out.println("Received message: " + message);
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				System.out.println("Disconnected from the server. Reason: " + reason);
			}

			@Override
			public void onError(Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		};

		client.connect();

		// Esperar a que se conecte y se pueda enviar/recibir mensajes
		try {
			Thread.sleep(5000); // Espera 5 segundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		client.close();
	}
}