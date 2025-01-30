package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(String name, RoomType roomType) {
        Room room = new Room();
        room.setName(name);
        //room.setRoomType(type);

        roomRepository.save(room);

        return room;
    }

    @Override
    @Query("select r from Room r where r not in (select b.Room from Booking b where b.Arrival)")
    public List<Room> getAvailableRooms(
            @Param("arrival") Date arrival,
            @Param("departure") Date departure) {
        return new ArrayList<Room>();
    }

    @Override
    public Room addRoom(RoomType roomType, String name, int floor) throws Exception {
        if (roomRepository.findByName(name) != null)
            throw new Exception("Room already exists");

        Room room = new Room();
        room.setRoomType(roomType);
        room.setName(name);
        room.setFloor(floor);
        roomRepository.save(room);
        return  room;
    }
}
