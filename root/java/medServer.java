//Medical Server

import java.net.*;
import java.io.*;

public class medServer {
    
    public static double temperature;
    public static double heartRate;
    public static double oxygenLevel;
    public static String patientStatus;
    static medicalServerGUI medicalGUI = new medicalServerGUI();
    
    public static void main(String argv[]) throws Exception {

        // Declaration.
        Socket socket;
        ServerSocket serverSocket;
        InputStreamReader readFromClient;
        BufferedReader fromClient;

        // Create a Server Socket Host: localhost, Port: 3333.
        serverSocket = new ServerSocket(3333);
        
        while (true) { // Outer while loop for controlling connections
            
            // Recieve a new Connection from the Personal Server ==========================================================
            System.out.println(">> Waiting for a personal server to connect ...");          // Display a message to ensure that the server is running okay
            socket = serverSocket.accept();                                                 // Accept connection
            System.out.println("~ The client is connected with the Medical server ~\n");    // Print an acceptance message
            readFromClient = new InputStreamReader(socket.getInputStream());                // Read Inputs from client
            fromClient = new BufferedReader(readFromClient);                                // create a buffer

            while (true) { // Inner while loop to keep recieving messages

                String checkConnection = fromClient.readLine();                             // Check if there are more messages to read
                if (checkConnection == null || checkConnection.equalsIgnoreCase("disconnect")) {
                    break;
                }

                // Get patient's information ===================================
                temperature = Double.parseDouble(checkConnection);
                oxygenLevel = Double.parseDouble(fromClient.readLine());
                heartRate = Double.parseDouble(fromClient.readLine());
                
                boolean abnormal = Boolean.parseBoolean(fromClient.readLine());

                // Print patient's status ======================================
                // Choose and print appropriate action based on patient's information
                if (abnormal) {
                    String readFromClient1 = fromClient.readLine();
                    String readFromClient2 = fromClient.readLine();
                    String readFromClient3 = fromClient.readLine();
                    System.out.println(readFromClient1);
                    medicalGUI.dataField.append(readFromClient1 + "\n");
                    System.out.println(readFromClient2);
                    medicalGUI.dataField.append(readFromClient2 + "\n");
                    System.out.println(readFromClient3);
                    medicalGUI.dataField.append(readFromClient3 + "\n");
                    appropraiteAction();
                }
                
                medicalGUI.setVisible(true);
            }
        }
    }

    // Choose and print appropriate action based on patient's information
    public static void appropraiteAction() {
        if ((temperature > 39) && (heartRate > 100) && (oxygenLevel < 95)) {
            System.out.println("ACTION: Send an ambulance to the patient!\n");
            medicalGUI.dataField.append("ACTION: Send an ambulance to the patient!\n");
            medicalGUI.dataField.append("\n");
        } else if ((temperature > 38 && temperature < 38.9) && (heartRate > 95 && heartRate < 98)
                && (oxygenLevel < 80)) { // 38.9 >>= temperature =<<38
            System.out.println("ACTION: Call the patient's family!\n");
            medicalGUI.dataField.append("ACTION: Call the patient's family!\n");
            medicalGUI.dataField.append("\n");
        } else {
            System.out.println("ACTION: Warning, advise patient to make a checkup appointment!\n");
            medicalGUI.dataField.append("ACTION: Warning, advise patient to make a checkup appointment!\n");
            medicalGUI.dataField.append("\n");
        }
    }
    public static void terminate(){
        System.exit(0);
    }
}
