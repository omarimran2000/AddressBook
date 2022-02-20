package book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BuddyInfo {
    private Long id;
    private String bookId;
    private String name;
    private String address;
    private String city;
    private String phone;

    public BuddyInfo(Long id, String name, String address, String phone, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.city = city;
    }
    public BuddyInfo(){

    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return "Name: " + getName() + " Address: " + getAddress() + " Phone: " + getPhone() + "City: "+ getCity();
    }
}
