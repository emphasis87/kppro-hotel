package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(String name, int capacity, double price) {
        Room room = new Room();
        room.setName(name);
        room.setCapacity(capacity);
        room.setPrice(price);

        roomRepository.save(room);

        return room;
    }
}
