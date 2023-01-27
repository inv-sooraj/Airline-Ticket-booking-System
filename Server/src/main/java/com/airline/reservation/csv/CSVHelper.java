package com.airline.reservation.csv;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.airline.reservation.entity.Bookings;
public class CSVHelper {
    public static ByteArrayInputStream bookingsToCSV(List<Bookings> bookings) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
    
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
              List<String> head = Arrays.asList("user id ","flight number","departure","destination","status");
              
              csvPrinter.printRecord(head);
          for (Bookings booking : bookings) {
            List<String> data = Arrays.asList(
               
                  String.valueOf(booking.getUser().getUserId()),
                  booking.getFlight().getFlightNumber(),
                  booking.getFlight().getDeparture(),
                  booking.getFlight().getDestination(),
                  String.valueOf(booking.getStatus())
                );
                csvPrinter.printRecord(data);
          }
    
          csvPrinter.flush();
          return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
          throw new RuntimeException("failed to import data to CSV file: " + e.getMessage());
        }
      }
}
