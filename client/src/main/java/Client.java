import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {

        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs)) {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                    communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway.ice_twoway();

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            //
            String hostname;
            try {
                //get hostname
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            //send hostname
            printer.printString(hostname + " says " + "Hello World!");


            //FIBONACCI
            boolean menu=false;

            while(!menu){
                String getIpHost=InetAddress.getLocalHost().getHostName();

                //Read input
                String hostEntraceOption=sc.nextLine();
                sc.nextLine();

                //Verify if string is numeric
                boolean isNumeric = verifyNumeric(hostEntraceOption);
                if(isNumeric){
                    //Verify ifPositive
                    int numberChosen = Integer.parseInt(hostEntraceOption);
                    if(numberChosen > 0){
                        //FIBO
                        String entrance = getIpHost+":"+numberChosen;
                        String message = printer.fibonacciMethod(entrance);
                        System.out.println(message);
                    }
                }else{
                    //Verify String
                    if(hostEntraceOption.equalsIgnoreCase("exit")){
                        menu = true;
                    }
                }
            }//End while
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }

    public static boolean verifyNumeric(String input){
        boolean response = true;
        if(input==null){
            response = false;
        }else{
            try{
                Integer chosenNumber = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                response = false;
            }
        }

        return response;
    }
}