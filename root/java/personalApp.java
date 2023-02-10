//Personal Application Server
import java.io.*;
import java.net.*;

public class personalApp {
    public static Double temperature;
    public static Double heartRate;
    public static Double oxygenLevel;
    public static String formattedDate;

    public static String tempStatus;
    public static String heartRateStatus;
    public static String oxygenLevelStatus;

    public static String tempStatus2;
    public static String heartRateStatus2;
    public static String oxygenLevelStatus2;

    public static boolean[] alerted = new boolean[4];

    public static void main(String argv[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        String alertMessageIsSent = "An alert message is sent to the Medical Server.";
        personalDataGUI personalGUI = new personalDataGUI();

        while(true){ // Outer while loop for controlling connections
            
        // Recieve a new Connection from the Sensor Application ==========================================================
        System.out.println(">> Waiting for the Sensor Application to connect ...");     // Display a message to ensure that the server is running okay
        Socket socket = serverSocket.accept();                                          // Accept connection
        System.out.println("~ The client is connected with the Personal server ~\n");   // Print an acceptance message
        InputStreamReader readFromCLientBuffer =                                        // Read Inputs from client
                new InputStreamReader(socket.getInputStream());
        BufferedReader fromCLient = new BufferedReader(readFromCLientBuffer);           // create a buffer
        PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);         // PrintWriter obj To send messages to the client
        
        // Create a Connection with the Medical Server ===================================================================
        Socket clientSocket = new Socket("localhost", 3333);                            // Client socket connection to port 3333.
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);   // PrintWriter obj To send messages to the Medical Server
            

            while (true) { //While loop to keep recieving messages from the Sensor app 
                String checkConnection = fromCLient.readLine();                         // Check if there are more messages to read
                if(checkConnection==null || checkConnection.equalsIgnoreCase("disconnect")) break;
                
                // Reading information from Sensor App =========================
                formattedDate = checkConnection;
                temperature = Double.valueOf(fromCLient.readLine());
                oxygenLevel = Double.valueOf(fromCLient.readLine());
                heartRate = Double.valueOf(fromCLient.readLine());

                // Send information to Medical server ==========================
                toServer.println(String.valueOf(temperature));
                toServer.println(String.valueOf(oxygenLevel));
                toServer.println(String.valueOf(heartRate));
                alerted = decision();

                System.out.println(tempStatus2);
                personalGUI.dataField.append(tempStatus2 + "\n");
                if (alerted[0]) { System.out.println(alertMessageIsSent);
                personalGUI.dataField.append(alertMessageIsSent + "\n");}

                System.out.println(heartRateStatus2);
                personalGUI.dataField.append(heartRateStatus2 + "\n");
                if (alerted[1] || alerted[2]) { System.out.println(alertMessageIsSent);
                personalGUI.dataField.append(alertMessageIsSent + "\n");}

                System.out.println(oxygenLevelStatus2);
                personalGUI.dataField.append(oxygenLevelStatus2 + "\n");
                if (alerted[3]) { System.out.println(alertMessageIsSent);
                personalGUI.dataField.append(alertMessageIsSent + "\n");}

                System.out.println();
                personalGUI.dataField.append("\n");

                if ((alerted[0] || alerted[1] || alerted[2] || alerted[3])) {
                    toServer.println("true");
                    toServer.println((tempStatus2));
                    toServer.println((heartRateStatus2));
                    toServer.println((oxygenLevelStatus2));
                } else {
                    toServer.println("false");
                }
                
                personalGUI.setVisible(true);
            }
            toServer.print("disconnect");
            // Close the connection with the Medical Server
            clientSocket.close();
        }
    }

    // A method to processes the recevied sensor data
    public static boolean[] decision() {
        alerted = new boolean[4];
        if (temperature > 38) {
            alerted[0] = true;
            tempStatus = "high " + temperature;
        } else {
            tempStatus = "normal";
        }
        if (heartRate > 100) {
            alerted[1] = true;
            heartRateStatus = "above normal " + heartRate.intValue() + ".";
        } else if (heartRate < 60) {
            alerted[2] = true;
            heartRateStatus = "below normal " + heartRate.intValue() + ".";
        } else {
            heartRateStatus = "normal";
        }
        if (oxygenLevel < 75) {
            alerted[3] = true;
            oxygenLevelStatus = "low " + oxygenLevel.intValue() + ".";
        } else {
            oxygenLevelStatus = "normal";
        }

        tempStatus2 = formattedDate + ", Temperature is " + tempStatus + " ";
        heartRateStatus2 = formattedDate + ", Heart rate is " + heartRateStatus + " ";
        oxygenLevelStatus2 = formattedDate + ", Oxygen saturation is " + oxygenLevelStatus + " ";

        return alerted;
    }
    
    public static void terminate(){
        System.exit(0);
    }

}