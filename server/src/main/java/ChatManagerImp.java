import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.zeroc.Ice.Current;

import Demo.CallbackPrx;

public class ChatManagerImp implements Demo.ChatManager {


    //Store messages sent to the server
    private List<String> messages;
    // Semaphore mSemaphore = new Semaphore(1);
    private CallbackPrx callbackPrx;

    //Sets the new lsit of messages of the server
    ChatManagerImp() {
        messages = new ArrayList<>();
    }

    @Override
    public void subscribe(CallbackPrx callback, String hostname, Current current) {
        System.out.println("subscribe");
        this.callbackPrx = callback;
    }

    @Override
    public String registerClient(HelloWorldCallbackReceiverPrx proxy, String hostname, Current current) {
        return null;
    }

    @Override
    public String[] getState(Current current) {
        System.out.println("GetState");
        String[] state = new String[messages.size()];

        for (int i = 0; i < state.length; i++) {
            state[i] = messages.get(i);
        }
        return state;
    }

  @Override
    public String printFibonacci(String hostname, String input, Current current) {
        String message = hostname + ":" + input;
        System.out.println(message);

        int output = 0;
        if(validateInputIsNumber(input)){
            int numberSent = Integer.parseInt(input);
            result = fibonacciMethod(inputNumber);
            //showFibonacciSequence(hostname, inputNumber);
            return output + "";
        }else{
            return input;
        }
        
    }
    
    private boolean validateInputIsNumber(String input){
        return input.matches("\\d+");
    }

    @Override
    public void sendMessage(String msg, Current current) {

        new Thread(() -> {
            System.out.println("new Message: " + msg);
            messages.add(msg);
            try {

                Thread.sleep(2000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            callbackPrx.notifyCallback();
        }).start();
    }

    @Override
    public void receiveMessage(String msg, Current current) {
        System.out.println(msg);
    }


public int fibo(int number) {
    public String fibonacciMethod(String information, com.zeroc.Ice.Current current)
    {
        //VARIABLES
        int num1 = 1, num2 = 1, sum=0;
        
        String[] clientInformation = information.split(":");

        String clientInfo = clientInformation[0]; // client info
        //parce
        int fibonacciNumber = Integer.valueOf(clientInformation[1]); // fibonnaci number

        int[] fibonacciNumbers = new int[fibonacciNumber];

        //FIBONACCI
        if (fibonacciNumber==1 || fibonacciNumber==2){
            return "Client: " +clientInfo+ " the last number of the Fibonnaci Succession is: " +fibonacciNumber;
        }else{
            for (int i=2;i<fibonacciNumber;i++) {
                sum = num1 + num2;
                fibonacciNumbers[i] = sum;
                num1 = num2;
                num2 = sum;
                System.out.println(sum);
            }
            return "Client: " +clientInfo+ " the last number of the Fibonnaci Succession is: " +fibonacciNumbers[fibonacciNumber-1];
        }
    }

  

}
