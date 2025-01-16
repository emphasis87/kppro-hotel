package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r")
    List<Room> getRooms(
            @Param("arrival") Date arrival,
            @Param("departure") Date departure,
            int page,
            int page_size);
}
