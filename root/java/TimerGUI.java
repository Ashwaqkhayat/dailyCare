
// Timer GUI

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TimerGUI extends javax.swing.JFrame {
    public static double temperature;
    public static double heartRate;
    public static double oxygenLevel;

    public static LocalDateTime dateTime;
    public static DateTimeFormatter myFormatObj;
    public static String formattedDate;
    public static Scanner sc = new Scanner(System.in);
    
    static long time;
    //to move borderless window frame
    int xMouse;
    int yMouse;

    public TimerGUI() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ExitBtn = new javax.swing.JLabel();
        FrameDrag = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TimerField = new javax.swing.JTextField();
        StartBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        ExitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExitBtn.setForeground(new java.awt.Color(136, 136, 136));
        ExitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitBtn.setText("X");
        ExitBtn.setToolTipText("Click To Close Window");
        ExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitBtnMouseExited(evt);
            }
        });
        getContentPane().add(ExitBtn);
        ExitBtn.setBounds(420, 20, 30, 30);

        FrameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                FrameDragMouseDragged(evt);
            }
        });
        FrameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FrameDragMousePressed(evt);
            }
        });
        getContentPane().add(FrameDrag);
        FrameDrag.setBounds(0, 0, 480, 140);

        jLabel5.setMaximumSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(239, 127, 0, 200);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/colored_dimmedt.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(170, 70, 140, 140);

        jLabel3.setForeground(new java.awt.Color(139, 139, 111));
        jLabel3.setText("How long do you want the Sensor\n Applicaton to run?\n");
        jLabel3.setToolTipText("Write in seconds (Min is 60 sec)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 240, 300, 30);

        TimerField.setBackground(new java.awt.Color(221, 221, 221));
        TimerField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TimerField.setToolTipText("Write in seconds (Min is 60 sec)");
        TimerField.setBorder(null);
        TimerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimerFieldActionPerformed(evt);
            }
        });
        TimerField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TimerFieldKeyReleased(evt);
            }
        });
        getContentPane().add(TimerField);
        TimerField.setBounds(150, 270, 180, 40);

        StartBtn.setBackground(new java.awt.Color(179, 51, 51));
        StartBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StartBtn.setText("Start!");
        StartBtn.setBorder(null);
        StartBtn.setEnabled(false);
        StartBtn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                StartBtnStateChanged(evt);
            }
        });
        StartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartBtnActionPerformed(evt);
            }
        });
        getContentPane().add(StartBtn);
        StartBtn.setBounds(190, 320, 100, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(158, 158, 115));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DailyCare");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 200, 135, 40);

        Bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new-bg+wh.png"))); // NOI18N
        Bg.setAlignmentY(0.0F);
        getContentPane().add(Bg);
        Bg.setBounds(0, 0, 480, 420);

        setSize(new java.awt.Dimension(478, 419));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TimerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimerFieldActionPerformed
        
    }//GEN-LAST:event_TimerFieldActionPerformed

    private void TimerFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimerFieldKeyReleased
        // Enable Start button if there's an entered number in the textField
        if(TimerField.getText().length()>0 && TimerField.getText().matches("[0-9]+")){
            StartBtn.setEnabled(true);
        }else{
            StartBtn.setEnabled(false);
        }
    }//GEN-LAST:event_TimerFieldKeyReleased

    private void StartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartBtnActionPerformed
        try {
            //Read Time & Store it
            time = Long.parseLong(TimerField.getText());
            //Set the TimerGUI visible
            this.setVisible(false);
            sensorDataGUI sensorData = new sensorDataGUI();
            
            // The below lines are used to connect to server from another device
            // InetAddress addresses = InetAddress.getByName("x.x.x.x");
            // String hostName = addr.getHostName();
            //Socket s = new Socket(hostName, port);
            
            // Client Socket of SensorApp to connect with Personal Server
            Socket clientSocket = new Socket("localhost", 6666);
            PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // Read sensor execution time from Personal Server
            // if the user input is less than 60 sec, then set the timer to 60sec.
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
                
                //Print on sensor GUI
                sensorData.dataField.append(formattedDate + ", sensed temperature is "+ temperature+"\n");
                sensorData.dataField.append(String.format("%s%s%.0f%n",formattedDate , ", sensed heart rate is " , heartRate));
                sensorData.dataField.append(String.format("%s%s%.0f%n",formattedDate , ", sensed oxygen saturation is " , oxygenLevel));
                sensorData.dataField.append("\n");
                
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
            
            sensorData.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(TimerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_StartBtnActionPerformed
    //Close window when ExitBtn is activated
    private void ExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_ExitBtnMouseClicked
    //set ExitBtn's color to white when the mouse hovers on it
    private void ExitBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitBtnMouseEntered
        ExitBtn.setOpaque(true);
        ExitBtn.setBackground(new Color(179,51,51));
        ExitBtn.setForeground(Color.white);
    }//GEN-LAST:event_ExitBtnMouseEntered
    //set ExitBtn's color to grey
    private void ExitBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitBtnMouseExited
        ExitBtn.setOpaque(false);
        ExitBtn.setForeground(new Color(136,136,136));
    }//GEN-LAST:event_ExitBtnMouseExited
    //set StartBtn's color to white when it's activated
    private void StartBtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_StartBtnStateChanged
        StartBtn.setForeground(Color.white);
        StartBtn.setBorderPainted(false);
    }//GEN-LAST:event_StartBtnStateChanged
    
    //Control the window location on screen
    private void FrameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x-xMouse, y-yMouse);
    }//GEN-LAST:event_FrameDragMouseDragged
    //Get the location of the window at current time
    private void FrameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
        
    }//GEN-LAST:event_FrameDragMousePressed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerGUI().setVisible(true);
            }
        });
    }
    
//    public static void stop(){
//        System.exit(0);
//    }
    
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
    
    public static void terminate(){
        System.exit(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bg;
    private javax.swing.JLabel ExitBtn;
    private javax.swing.JLabel FrameDrag;
    private javax.swing.JButton StartBtn;
    public javax.swing.JTextField TimerField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
