
//Sensor Application Client
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class sensorApplication {
    public static double temperature;
    public static double heartRate;
    public static double oxygenLevel;

    public static LocalDateTime dateTime;
    public static DateTimeFormatter myFormatObj;
    public static String formattedDate;

    // Scanner to read user inputs
    public static Scanner sc = new Scanner(System.in);

    public static void main(String argv[]) throws Exception {
        
        // Client Socket of SensorApp to connect with Personal Server
        Socket clientSocket = new Socket("localhost", 6666);
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);

        // Read sensor execution time from Personal Server
        // if the user input is less than 60 sec, then set the timer to 60sec.
        BufferedReader fromServer= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        long time = Long.valueOf(fromServer.readLine());
        System.out.println("Time is: " + time);
        if (time < 60) time = 60;
        long timeLimit = Timer(time);
        
        // Keep connection open as long as Timer hasn't finished
        while (System.currentTimeMillis() < timeLimit) {
            dateTime = LocalDateTime.now();
            myFormatObj = DateTimeFormatter.ofPattern("'At date:' dd M yy 'Time:' hh:mm:ss");
            formattedDate = dateTime.format(myFormatObj);

            // Reading sensors
            temperature = RandomTemperature();
            heartRate = RandomHeartRate();
            oxygenLevel = RandomOxygenLevel();

            // display data in sensor command
            System.out.println(formattedDate + ", sensed temperature is "+ temperature);
            System.out.printf("%s%s%.0f%n",formattedDate , ", sensed heart rate is " , heartRate);
            System.out.printf("%s%s%.0f%n",formattedDate , ", sensed oxygen saturation is " , oxygenLevel);
            System.out.println();

            // send values to personalApp server
            toServer.println((formattedDate).toString());
            toServer.println(temperature);
            toServer.println(oxygenLevel);
            toServer.println(heartRate);

            // @todo skip sleep in last call
            Thread.sleep(5000);
        }
        toServer.print("disconnect");
        clientSocket.close();
        System.exit(0);

    }

    // Generating random temperature
    public static double RandomTemperature() {
        return ((int)(ThreadLocalRandom.current().nextDouble(36, 41)*10))/10.0; 
    }

    // Generating random heart rate
    public static int RandomHeartRate() {
        return (int) ((Math.random() * (120 - 50)) + 50);
    }

    // Generating random oxygen level
    public static int RandomOxygenLevel() {
        return (int) ((Math.random() * (100 - 60)) + 60);
    }

    // Generating end execution time
    public static long Timer(long time) {
        return System.currentTimeMillis() + (time * 1000);
    }

}
