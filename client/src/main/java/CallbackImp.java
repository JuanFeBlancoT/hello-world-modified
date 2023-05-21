import java.util.Arrays;

import com.zeroc.Ice.Current;

import Demo.ChatManagerPrx;

public class CallbackImp implements Demo.Callback {

    private ChatManagerPrx server;

    private int request_size = 10000;
    private int timeout_time_bot = 0;
    private int timeout_time_top = 100;

    CallbackImp(ChatManagerPrx m) {
        server = m;
    }


  @Override
    public void receiveMessage(String msg, Current current) {
        System.out.println(msg);
    }

    @Override
    public void notifyCallback(Current current) {
        System.out.println("notifyCall");
        String state[] = server.getState();

        System.out.println("Callback exec: " + Arrays.toString(state));
    }

    public void testFiboTimeout() {
        int index = 0;
        String hostname = "";
        ArrayList<CompletableFuture<String>> calculatedNumbers = new ArrayList<>();

        try {
            int[] array = createFiboTestArray();
            while(index < array.length - 1){
                int number = array[index];
                senderPrx.printFibonacci(hostname, String.valueOf(number));
                index++;
            }

        } catch (TimeoutException timeoutException) {
            System.out.println("TIMEOUT");
            System.out.println(index+1);
        }
    }

    private int[] createFiboTestArray(){
        return new Random().ints(request_size, timeout_time_bot, timeout_time_top + 1).toArray();
    }

}
