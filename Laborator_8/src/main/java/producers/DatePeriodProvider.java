package producers;

import annotations.Period;
import dataLayer.DatePeriod;

import javax.enterprise.inject.Produces;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePeriodProvider {

    @Produces
    @Period
    public static DatePeriod getPeriod()
    {
        DatePeriod period=new DatePeriod();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Resources\\period.txt"));
            String start = reader.readLine();
            String end=reader.readLine();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            period.setStartDate(dateFormat.parse(start));
            period.setEndDate(dateFormat.parse(end));
            reader.close();
            return period;
        } catch (IOException | ParseException e) {
            period.setEndDate(new Date());
            period.setStartDate(new Date());
            return period;
        }
    }
}
