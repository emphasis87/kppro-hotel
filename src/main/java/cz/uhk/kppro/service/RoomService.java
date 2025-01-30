package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface RoomService {
    Room createRoom(
            String name,
            RoomType roomType);

    List<Room> getAvailableRooms(
            Date arrival,
            Date departure);

    Room addRoom(RoomType roomType, String name, int floor) throws Exception;
}
