package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Booking createBooking(
            Room room,
            Customer customer,
            double price,
            Date arrival,
            Date departure);
    void cancelBooking(Booking booking);
    List<Booking> getBookingsByCustomer(Customer customer);
}
