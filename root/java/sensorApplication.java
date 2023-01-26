
//Sensor application client
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class sensorApplication {

    public static double temperature;
    public static double heartRate;
    public static double oxygenLevel;

    public static LocalDateTime dateTime;
    public static DateTimeFormatter myFormatObj;
    public static String formattedDate;

    public static int counter = 0;
    // Scanner object creation
    public static Scanner sc = new Scanner(System.in);

    public static void main(String argv[]) throws Exception {

        // InetAddress addr = InetAddress.getByName("188,248,50,1");
        // String host = addr.getHostName();

        System.out.println("Call me last");
        Socket clientSocket = new Socket("localhost", 6666);
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        // BufferedReader fromServer = new BufferedReader(new
        // InputStreamReader(clientSocket.getInputStream()));
        // BufferedReader readFromServerBuffer = new BufferedReader(new
        // InputStreamReader(System.in));

        // Read sensor execution time
        System.out.println("How long do you want the Sensor Application to run (in seconds)?" +
                "\nNote: The minimum execution time is 60sec.\n");
        long time = sc.nextLong();
        // if the user input is less than 60 sec, then set the timer to 60sec.
        if (time < 60)
            time = 60;

        System.out.println("Time=" + time);
        long timeLimit = Timer(time);
        // Timer
        while (System.currentTimeMillis() < timeLimit) {
            ////// System.out.println((System.currentTimeMillis() / 1000));
            ////// System.out.println(++counter);

            dateTime = LocalDateTime.now();
            myFormatObj = DateTimeFormatter.ofPattern("'At date:' dd M yy 'Time:' hh:mm:ss");
            formattedDate = dateTime.format(myFormatObj);

            // Reading sensors
            temperature = RandomTemperature();
            heartRate = RandomHeartRate();
            oxygenLevel = RandomOxygenLevel();

            // display data in sensor command
            System.out.println("At date: " + formattedDate + ", sensed temperature is " + temperature);
            System.out.println("At date: " + formattedDate + ", sensed heart rate is " + heartRate);
            System.out.println("At date: " + formattedDate + ", sensed oxygen saturation is " + oxygenLevel);
            System.out.println("\n");

            // send values to personalApp server
            toServer.println((formattedDate).toString());
            toServer.println(temperature);
            toServer.println(oxygenLevel);
            toServer.println(heartRate);

            // @todo skip sleep in last call
            Thread.sleep(5000);
        }
        clientSocket.close();
        System.exit(0);

    }

    // Generating random temperature
    public static int RandomTemperature() {
        return (((int) ((Math.random() * (41 - 36)) + 36) * 100) / 100);
    }

    // Generating random heart rate
    public static int RandomHeartRate() {
        return (int) ((Math.random() * (100 - 60)) + 60);
    }

    // Generating random oxygen level
    public static int RandomOxygenLevel() {
        return (int) ((Math.random() * (100 - 60)) + 60);
    }

    // Generating end execution time
    public static long Timer(long time) {
        long start = System.currentTimeMillis();
        long end = start + (time * 1000);
        return end;
    }

}
