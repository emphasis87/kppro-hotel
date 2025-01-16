package cz.uhk.kppro.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Room room;
    private Customer customer;

    private Date arrival;
    private Date departure;

    private double price;

    public long getId() {
        return id;
    }

    public Room getRoom(){
        return room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Date getArrival(){
        return arrival;
    }

    public  void setArrival(Date arrival){
        this.arrival = arrival;
    }

    public Date getDeparture(){
        return departure;
    }

    public  void setDeparture(Date departure){
        this.departure = departure;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
