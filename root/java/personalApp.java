
//Personal Application Server
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void main(String argv[]) throws Exception {
        // InetAddress addr = InetAddress.getByName("188,248,50,1");
        // String host = addr.getHostName();

        // Date/time
        // Date date = new Date();
        // SimpleDateFormat dateFormat= new SimpleDateFormat("'At date:' dd M yy '
        // Time:' hh:mm:ss");
        // System.out.println(dateFormat.format(date));

        // //AS A CLIENT
        // =======================================================================
        // System.out.println("Call me last");

        // Socket clientSocket = new Socket("localhost", 3333); // create a socket
        // connection to port 6666 from the local host

        // PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        // // create a printwriter to write in socket

        // BufferedReader fromServer = new BufferedReader(new
        // InputStreamReader(clientSocket.getInputStream())); // create a buffer reader
        // to read from socket
        // DataInputStream readFromServerBuffer = new
        // DataInputStream(clientSocket.getInputStream()); // create a buffer reader to
        // read from user

        // AS A SERVER
        // =======================================================================
        // Server socket connection to port 6666.
        ServerSocket serverSocket = new ServerSocket(6666);
        // Client socket connection to port 3333.
        Socket clientSocket = new Socket("localhost", 3333);
        // Client printwriter
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        // Display a message to ensure that the server is running okay
        System.out.println("Waiting for a client ...");
        // Accept connection
        Socket socket = serverSocket.accept();

        InputStreamReader readFromCLientBuffer = new InputStreamReader(socket.getInputStream()); // create a buffer
        BufferedReader fromCLient = new BufferedReader(readFromCLientBuffer);
        // reader
        // to read from socket

        PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);

        String alertMessageIsSent = "An alert message is sent to the Medical Server.";
        while (true) {

            boolean[] alerted = new boolean[4];
            formattedDate = fromCLient.readLine();

            temperature = Double.valueOf(fromCLient.readLine());
            oxygenLevel = Double.valueOf(fromCLient.readLine());
            heartRate = Double.valueOf(fromCLient.readLine());

            // Send messages to medical server ==============================

            // Send values to the medical server

            toServer.println(String.valueOf(temperature));
            toServer.println(String.valueOf(oxygenLevel));
            toServer.println(String.valueOf(heartRate));
            alerted = decision();

            System.out.println(tempStatus2);
            if (alerted[0]) {
                System.out.println(alertMessageIsSent);
            }

            System.out.println(heartRateStatus2);
            if (alerted[1] || alerted[2]) {
                System.out.println(alertMessageIsSent);
            }

            System.out.println(oxygenLevelStatus2);
            if (alerted[3]) {
                System.out.println(alertMessageIsSent);
            }

            System.out.println("\n");

            if ((alerted[0] || alerted[1] || alerted[2] || alerted[3])) {
                
                toServer.println((tempStatus2));
                toServer.println((heartRateStatus2));
                toServer.println((oxygenLevelStatus2));
                toServer.println("true");
            } else {
                
                toServer.println("\b");
                toServer.println("\b");
                toServer.println("\b");
                toServer.println("false");
            }

            if (formattedDate == null)
                break;
        }

        // CLOSE CONNECTION
        serverSocket.close();
        clientSocket.close();
        // clientSocket.close();
        System.exit(0);

        // System.out.println("Call me second");

    }

    // A method to processes the recevied sensor data
    public static boolean[] decision() {
        boolean alerted[] = new boolean[4];
        if (temperature > 38) {
            alerted[0] = true;
            tempStatus = "high " + temperature;
        } else {
            tempStatus = "normal";
        }
        if (heartRate > 100) {
            alerted[1] = true;
            heartRateStatus = "above normal " + heartRate;
        } else if (heartRate < 60) {
            alerted[2] = true;
            heartRateStatus = "below normal " + heartRate;
        } else {
            heartRateStatus = "normal";
        }
        if (oxygenLevel < 75) {
            alerted[3] = true;
            oxygenLevelStatus = "low " + oxygenLevel;
        } else {
            oxygenLevelStatus = "normal";
        }

        tempStatus2 = formattedDate + ", Temperature is " + tempStatus + " ";
        heartRateStatus2 = formattedDate + ", Heart rate is " + heartRateStatus + " ";
        oxygenLevelStatus2 = formattedDate + ", Oxygen saturation is " + oxygenLevelStatus + " ";

        return alerted;
    }

}
