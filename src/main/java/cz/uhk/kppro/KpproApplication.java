package cz.uhk.kppro;

import cz.uhk.kppro.model.Booking;
import cz.uhk.kppro.model.Customer;
import cz.uhk.kppro.model.Room;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class KpproApplication {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public KpproApplication(UserService userService, PasswordEncoder passwordEncoder){
        this.userService =userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            addUser("admin", "heslo", "ADMIN");
            addUser("user", "heslo", "USER");
        };
    }

    private void addUser(String username, String password, String role) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.save(user);
        }
    }

    private void addRoom(String name, int capacity){

    }

    private void addCustomer(String firstName, String surname){

    }

    private void addBooking(Customer customer, Room room, Date arrival, Date departure){

    }

    public static void main(String[] args) {
        SpringApplication.run(KpproApplication.class, args);
    }

}
