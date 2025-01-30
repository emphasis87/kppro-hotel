package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoomTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final EntityManager entityManager;

    public  RoomTypeServiceImpl(
            RoomTypeRepository roomTypeRepository,
            EntityManager entityManager
    ){
        this.roomTypeRepository = roomTypeRepository;
        this.entityManager = entityManager;
    }

    @Override
    public RoomType addRoomType(int capacity, int size, double price) {
        RoomType rt = new RoomType();
        rt.setCapacity(capacity);
        rt.setSize(size);
        rt.setPrice(price);
        roomTypeRepository.save(rt);
        return rt;
    }

    @Override
    public List<RoomType> findAvailable(
            Optional<LocalDate> arrival,
            Optional<LocalDate> departure
    ){
        if (arrival.isEmpty() || departure.isEmpty()){
            List<RoomType> all = roomTypeRepository.findAll();
            return  all;
        }

        Timestamp checkIn = Timestamp.valueOf(arrival.get().atStartOfDay());
        Timestamp checkOut = Timestamp.valueOf(departure.get().atStartOfDay());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoomType> query = cb.createQuery(RoomType.class);
        Root<RoomType> rt = query.from(RoomType.class);
        Join<RoomType, Room> r = rt.join("rooms");

        // Subquery to get booked room IDs
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Booking> b = subquery.from(Booking.class);
        subquery.select(b.get("room").get("id"))
                .where(cb.and(
                        cb.lessThan(b.get("arrival"), checkIn),   // Booking starts before checkout
                        cb.greaterThan(b.get("departure"), checkOut) // Booking ends after check-in
                ));

        // Main query: Select room types where room ID is NOT in the booked rooms
        query.select(rt).distinct(true)
                .where(cb.not(r.get("id").in(subquery)));

        return entityManager.createQuery(query).getResultList();
    }
}
