package cz.uhk.kppro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
