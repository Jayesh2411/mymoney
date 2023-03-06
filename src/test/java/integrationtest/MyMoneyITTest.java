package integrationtest;

import org.example.service.RequestProcessor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MyMoneyITTest {

    @Test
    public void TestMyMoneyITForTestData1() {

        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String testData = "/Users/jayeshsinha/Documents/mymoney/src/test/java/integrationtest/testdata1.txt";

        String expected = "10593 7897 2272\n" +
                "23619 11809 3936\n";


        RequestProcessor.processRequest(testData);
        assertEquals(expected, out.toString());
        System.setOut(save_out);

    }

    @Test
    public void TestMyMoneyITForTestData2() {

        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String testData = "/Users/jayeshsinha/Documents/mymoney/src/test/java/integrationtest/testdata2.txt";

        String expected = "15937 14552 6187\n" +
                "23292 16055 7690\n" +
                "CANNOT_REBALANCE\n";


        RequestProcessor.processRequest(testData);
        assertEquals(expected, out.toString());
        System.setOut(save_out);

    }

    @Test
    public void it_prints_out() {

        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        System.out.println("Hello World!");
        assertEquals("Hello World!\n", out.toString());

        System.setOut(save_out);
    }
}
