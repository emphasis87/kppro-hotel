package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private final EntityManager entityManager;

    @Autowired
    public RoomServiceImpl(
            RoomRepository roomRepository,
            EntityManager entityManager
    ) {
        this.roomRepository = roomRepository;
        this.entityManager = entityManager;
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

    @Override
    public Room findAvailable(
            long roomTypeId,
            LocalDate arrival,
            LocalDate departure) {

        Timestamp checkIn = Timestamp.valueOf(arrival.atStartOfDay());
        Timestamp checkOut = Timestamp.valueOf(departure.atStartOfDay());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> r = query.from(Room.class);
        Join<Room, RoomType> rt = r.join("roomType");

        // Subquery to get booked room IDs
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Booking> b = subquery.from(Booking.class);
        subquery.select(b.get("room").get("id"))
                .where(cb.and(
                        cb.lessThan(b.get("arrival"), checkIn),   // Booking starts before checkout
                        cb.greaterThan(b.get("departure"), checkOut) // Booking ends after check-in
                ));

        // Main query: Select room types where room ID is NOT in the booked rooms
        query.select(r)
                .distinct(true)
                .where(cb.not(r.get("id").in(subquery)))
                .where(cb.equal(rt.get("id"), roomTypeId));

        Room result = entityManager.createQuery(query).setMaxResults(1).getSingleResult();
        return result;
    }
}
