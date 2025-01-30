package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface RoomService {
    Room createRoom(
            String name,
            RoomType roomType);

    List<Room> getAvailableRooms(
            Date arrival,
            Date departure);
}
