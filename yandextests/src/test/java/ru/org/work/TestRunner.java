package ru.org.work;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(YandexTest.class);

        String compliance = "passed";

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            compliance = "failed";
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        File dir = new File("C://TestReports");
        dir.mkdir();
        try {
            FileWriter writer = new FileWriter("C://TestReports//result.xml", false);
            writer.write("<test>\n");
            writer.write("\t<name>yandex</yandex>\n");
            writer.write("\t<date>" + dateFormat.format(calendar.getTime()) + "</date>\n");
            writer.write("\t<result>" + compliance + "</result>\n");
            writer.write("</test>\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
