import java.io.*;
import java.net.*;
import java.io.IOException;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class JBServer {
	public static void main(String [] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = null;
		
		try { serverSocket = new ServerSocket (10007); }
		
		catch (IOException e) {
				System.err.println("Could not listen on port: 10007.");
				System.exit(1);
		}
		
		Socket clientSocket = null;
		System.out.println("Waiting for connection...");
		
		try { clientSocket = serverSocket.accept(); }
		
		catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
		}
		
		System.out.println("Connection Successful.");
		
		//Create GpioController to Control the LED light
		final GpioController gpio = GpioFactory.getry.getInstance();
		
		
		//provision gpio pin#05 as an output pin and turn on
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLED", PinState.HIGH);
		{
			pin.setShutdownOptions(true, PinState.LOW);
			System.out.println("--> GPIO state should be: ON (RED)");
			//wait for 5 seconds
			Thread.sleep(5000) //mS
			//turn off gpio pin #05
			pin.low();
			System.out.println("--> The RED LEd light should be: OFF");
		}
		
		clientSocket.close();
		serverSocket.close();
		
	}


}