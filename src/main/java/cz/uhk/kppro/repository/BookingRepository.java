package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
