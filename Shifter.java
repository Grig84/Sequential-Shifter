import jssc.SerialPort;
import jssc.SerialPortException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static jssc.SerialPort.*;

public class Shifter {
    public static void main(String[] args) throws SerialPortException, AWTException, InterruptedException {

        boolean i = true;
        boolean UP = false;
        boolean DOWN = false;

        Robot robot = new Robot();

        SerialPort port = new SerialPort("COM4");
        port.openPort();
        port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
        // port.setParams(9600, 8, 1, 0); // alternate technique
        while(i == true){
            TimeUnit.SECONDS.sleep((long) 0.01);
            byte[] buffer = port.readBytes(1 /* read first 10 bytes */);
            for (byte b : buffer) {
                if(b==(byte)'U'){
                    System.out.println("UPPPPPP");
                        robot.keyPress(KeyEvent.VK_I);
                }else{
                    robot.keyRelease(KeyEvent.VK_I);
                }
                if(b==(byte)'D'){
                    System.out.println("DOWNNNNNN");
                        robot.keyPress(KeyEvent.VK_O);
                }else{
                    robot.keyRelease(KeyEvent.VK_O);
                }
            }
        }
        port.closePort();
    }
}