package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.service.BookingService;
import cz.uhk.kppro.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Presents the booking interface to the customers
 */

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private RoomService roomService;

    @Autowired
    public BookingController(
            BookingService bookingService,
            RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

}
