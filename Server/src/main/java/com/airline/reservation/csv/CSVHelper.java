package com.airline.reservation.csv;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import com.airline.reservation.entity.Bookings;
import com.fasterxml.jackson.annotation.JsonAlias;
public class CSVHelper {
    public static ByteArrayInputStream tutorialsToCSV(List<Bookings> tutorials) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            ) {
              csvPrinter.printRecord("Booking ID"	,"Flight Number"	,"Departing From	","Destination",	"Status"	);
          for (Bookings tutorial : tutorials) 
     
            {
            List<String> data = Arrays.asList(   
                  String.valueOf(tutorial.getUser().getUserId()),
                  tutorial.getFlight().getFlightNumber(),
                  tutorial.getFlight().getDeparture(),
                  tutorial.getFlight().getDestination(),
                  String.valueOf(tutorial.getStatus()));
        csvPrinter.printRecord(data);
          }
          csvPrinter.flush();
          return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
          throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
      }

      public static ByteArrayInputStream cancelledBookingCSV(List<Bookings> tutorials) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
              csvPrinter.printRecord("Full Name", "Flight Number ", "Departing From ", "Destination  ", "Departing Date","Reason");
              for (Bookings tutorial : tutorials) {
            String pattern,date;
            pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            List<String> data = Arrays.asList(   
                  String.valueOf(tutorial.getUser().getFullName()),
                  tutorial.getFlight().getFlightNumber(),
                  tutorial.getFlight().getDeparture(),
                  tutorial.getFlight().getDestination(),
                  // String.valueOf(tutorial.getFlight().getDepDateTime()));
              String.valueOf(tutorial.getFlight().getDepDateTime()));
            csvPrinter.printRecord(data);
          }
          csvPrinter.flush();
          return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
          throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
      }
}
