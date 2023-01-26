
//Medical Server
/*
Med Server To do: 
have an infinite loop to accept connection requests from the Personal Server.
The output: 
date time Temprature
date time heart rate
date time Oxygen Saturations
Decide whether the elder is in danger or not
*/
import java.net.*;
import java.io.*;

public class medServer {

    public static double temperature;
    public static double heartRate;
    public static double oxygenLevel;
    public static String formattedDate;
    public static String patientStatus;

    public static void main(String argv[]) throws IOException {
        // Declare.
        Socket socket;
        ServerSocket serverSocket;
        InputStreamReader readFromClient;
        BufferedReader fromClient;

        // Host: localhost, Port: 3333.
        serverSocket = new ServerSocket(3333);
        while (true) {
            try {
                // Establish connection.
                socket = serverSocket.accept();
                System.out.println("~ The client is connected with the server ~");

                // Get input from client.
                readFromClient = new InputStreamReader(socket.getInputStream());
                // outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                fromClient = new BufferedReader(readFromClient);
                // bufferedWriter = new BufferedWriter(outputStreamWriter);

                // Get patient's information
                temperature = Double.valueOf(fromClient.readLine());
                oxygenLevel = Double.valueOf(fromClient.readLine());
                heartRate = Double.valueOf(fromClient.readLine());
                patientStatus = fromClient.readLine();

                // Print patient's status
                System.out.println(patientStatus);
                
                // Choose and print appropriate action based on patient's information
                appropraiteAction();

                if(1<1){
                    break;
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        // Closing connection
        socket.close();
        serverSocket.close();
        readFromClient.close();
        // outputStreamWriter.close();
        fromClient.close();
        // bufferedWriter.close();
    }

    // Choose and print appropriate action based on patient's information
    public static void appropraiteAction() {
        if ((temperature > 39) && (heartRate > 100) && (oxygenLevel < 95)) {
            System.out.println("ACTION: Send an ambulance to the patient!");
        } else if ((temperature > 38 && temperature < 38.9) && (heartRate > 95 && heartRate < 98) && (oxygenLevel < 80)) { // 38.9 >>= temperature =<<38
            System.out.println("ACTION: Call the patient's family!");
        } else {
            System.out.println("ACTION: Warning, advise patient to make a checkup appointment!");
        }
    }
}
