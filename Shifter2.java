import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Port;

import static jssc.SerialPort.*;

public class Shifter2 {
    static SerialPort port = new SerialPort("COM4");
    static Robot robot;
    static byte buffer[][];

    public static void main(String[] args) throws SerialPortException, AWTException, InterruptedException {
        robot = new Robot();
        port.openPort();
        port.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);
        // port.setParams(9600, 8, 1, 0); // alternate technique
        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
        port.setEventsMask(mask);
        port.addEventListener(new MyPortListener() /* defined below */);
    }

    public static class MyPortListener implements SerialPortEventListener {
        byte[] buffer;

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {
                try {
                    buffer = port.readBytes(1 /* read first 10 bytes */);
                } catch (SerialPortException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                for (byte b : buffer) {
                    if (b == (byte) 'U') {
                        robot.keyPress(KeyEvent.VK_I);
                        try {
                            TimeUnit.SECONDS.sleep((long) 0.01);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (b == (byte) 'D') {
                        robot.keyPress(KeyEvent.VK_O);
                        try {
                            TimeUnit.SECONDS.sleep((long) 0.01);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (b == (byte) 'u') {
                        robot.keyRelease(KeyEvent.VK_I);
                        try {
                            TimeUnit.SECONDS.sleep((long) 0.01);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (b == (byte) 'd') {
                        robot.keyRelease(KeyEvent.VK_O);
                        try {
                            TimeUnit.SECONDS.sleep((long) 0.01);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        };
                    }
                    b = 0;
                }
            }

        }
    
    }
}
