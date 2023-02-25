import com.zeroc.Ice.Current;

public class PrinterI implements Demo.Printer
{
    public void printString(String s, com.zeroc.Ice.Current current)
    {
        System.out.println(s);
    }

    @Override
    public int fibo(int a, int b, Current current) {
        return 0;
    }

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