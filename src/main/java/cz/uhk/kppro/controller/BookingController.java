package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.service.BookingService;
import cz.uhk.kppro.service.RoomService;
import cz.uhk.kppro.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Presents the booking interface to the customers
 */

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private RoomService roomService;
    private final RoomTypeService roomTypeService;

    @Autowired
    public BookingController(
            BookingService bookingService,
            RoomService roomService,
            RoomTypeService roomTypeService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
    }

    @GetMapping({"", "/", "/list"})
    public String list(
            Model model,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Optional<LocalDate> arrival,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Optional<LocalDate> departure){
        List<RoomType> rooms = roomTypeService.findAvailable(arrival,departure);
        model.addAttribute("roomTypes", rooms);
        model.addAttribute("arrival", arrival.orElse(LocalDate.now()));
        model.addAttribute("departure", departure.orElse(LocalDate.now().plusDays(7)));
        return "booking_list";
    }

    @RequestMapping("/reservation")
    public String reserve(
            Model model,
            @RequestParam(name = "id", required = true) long roomTypeId,
            @RequestParam(required = true) LocalDate arrival,
            @RequestParam(required = true) LocalDate departure
    ){
        Room room = roomService.findAvailable(roomTypeId, arrival, departure);
        model.addAttribute("room", room);

        return "reservation";
    }
}
