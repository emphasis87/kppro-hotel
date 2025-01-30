package cz.uhk.kppro.service;

import cz.uhk.kppro.model.RoomType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomTypeService {
    RoomType addRoomType(int capacity, int size, double price);

    List<RoomType> findAvailable(
            Optional<LocalDate> arrival,
            Optional<LocalDate> departure);
}
