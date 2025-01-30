package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(
            Room room,
            Customer customer,
            double price,
            Date arrival,
            Date departure) {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setArrival(arrival);
        booking.setDeparture(departure);
        booking.setPrice(price);

        bookingRepository.save(booking);

        return booking;
    }

    @Override
    public void cancelBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByCustomer(Customer customer) {
        return bookingRepository.findAll();
    }
}
