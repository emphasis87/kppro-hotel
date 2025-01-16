package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.service.BookingService;
import cz.uhk.kppro.service.CustomerService;
import cz.uhk.kppro.service.RoomService;
import cz.uhk.kppro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Provides interface for managing users, customers, rooms and bookings
 * to the hotel administration and staff.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private CustomerService customerService;
    private final RoomService roomService;
    private final BookingService bookingService;

    @Autowired
    public AdminController(
            UserService userService,
            CustomerService customerService,
            RoomService roomService,
            BookingService bookingService) {
        this.userService = userService;
        this.customerService = customerService;
        this.roomService = roomService;
        this.bookingService = bookingService;
    }


}
