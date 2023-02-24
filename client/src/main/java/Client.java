import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
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

                System.out.print("_____________________________________INGRESAR OPCION________________________________________________________\n");
                System.out.print("\n¿Cual opcion desea escoger?");
                System.out.print("\n");
                System.out.print("\nEscriba 1, si quiere ingresar un numero para calcular el ultimo numero de la serie de fibonacci");
                System.out.print("\nEscriba 2, si quiere finalizar el menu");
                
                int hostEntraceOption=lector.nextInt();
                lector.nextLine();

                if(hostEntraceOption==1){
                    System.out.println("Ingrese el numero: ");
                    String number = lector.nextLine();

                    String entrance=getIpHost+":"+number;
                    String message=printer.fibonacciMethod(entrance);

                }

                else{
                    menu=true;
                    System.out.print("\n");
                    System.out.print("_____________________________________APLICACION CERRADA____________________________________________________\n");
                    System.out.print("\n");
                    break;
                }
            }
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
}