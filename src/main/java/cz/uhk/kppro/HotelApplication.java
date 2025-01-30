package cz.uhk.kppro;

import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoomTypeRepository;
import cz.uhk.kppro.repository.UserRepository;
import cz.uhk.kppro.service.RoomService;
import cz.uhk.kppro.service.RoomTypeService;
import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HotelApplication {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoomTypeService roomTypeService;
    private final RoomService roomService;

    @Autowired
    public HotelApplication(
            UserService userService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoomTypeService roomTypeService,
            RoomService roomService){
        this.userService =userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roomTypeService = roomTypeService;
        this.roomService = roomService;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            User admin = userRepository.findByUsername("admin");
            if (admin == null){
                admin = userService.addUser("admin", "heslo", "ADMIN");
                User user = userService.addUser("user", "heslo", "USER");
                RoomType rt1 = roomTypeService.addRoomType(1,15, 1200);
                RoomType rt2 =roomTypeService.addRoomType(2, 23, 2000);
                RoomType rt3 =roomTypeService.addRoomType(3, 30, 2600);
                roomService.addRoom(rt1, "101", 1);
                roomService.addRoom(rt1, "102", 1);
                roomService.addRoom(rt2, "103", 1);
                roomService.addRoom(rt3, "201", 2);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

}
