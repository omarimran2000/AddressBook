package book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    private List<BuddyInfo> buddies;
    private Long id;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public AddressBook(long id) {
        this.id = id;
        buddies = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies){
        this.buddies = buddies;
    }

    public String toString()
    {
        String s = "ID: "+id+"\n";

        for (BuddyInfo b: buddies)
        {
            s += b.toString();
            s +="\n";
        }
        return s;
    }
}
