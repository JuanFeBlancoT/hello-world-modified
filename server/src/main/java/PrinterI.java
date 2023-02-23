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
}