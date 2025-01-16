package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Room;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RoomService {
    Room createRoom(
            String name,
            int capacity,
            double price);

    List<Room> getAvailableRooms();
}
