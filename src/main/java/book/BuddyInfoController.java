package book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class BuddyInfoController {

    @Autowired
    private  BuddyInfoRepository buddyInfoRepository;
    @Autowired
    private  AddressBookRepository addressBookRepository;

    @GetMapping("/buddyInfoAdd")
    public String buddyInfoForm(Model model)
    {
        model.addAttribute("buddyInfo",new BuddyInfo());
        return "buddyInfoAdd";
    }

    @PostMapping("/buddyInfoAdd")
    public String buddyInfoSubmit(@ModelAttribute  BuddyInfo buddyInfo, Model model)
    {
        model.addAttribute("buddyInfo",buddyInfo);

        buddyInfoRepository.save(buddyInfo);

        AddressBook book = addressBookRepository.findById(Long.parseLong(buddyInfo.getBookId()));
        book.addBuddy(buddyInfo);
        addressBookRepository.save(book);

        return "/buddyInfoDisplay";

    }
    @GetMapping("/buddyInfoRemove")
    public String buddyInfoRemoveForm(Model model)
    {
        model.addAttribute("GUI",new GUIForm());
        return "buddyInfoRemove";
    }

    @PostMapping("/buddyInfoRemove")
    public String buddyInfoRemoveSubmit(@ModelAttribute  GUIForm GUI, Model model)
    {
        BuddyInfo remove = buddyInfoRepository.findByName(GUI.getID()).get(0);

        AddressBook book = addressBookRepository.findById(Long.parseLong(remove.getBookId()));
        book.removeBuddy(remove);
        addressBookRepository.save(book);

        buddyInfoRepository.delete(remove);

        model.addAttribute("buddyInfo",remove);

        return "/buddyInfoDisplay";

    }
}
@RestController
class BuddyInfoRestController{

    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private  BuddyInfoRepository buddyInfoRepository;
    @Autowired
    private  AddressBookRepository addressBookRepository;



    @GetMapping("/buddyInfoRestAdd")
    public BuddyInfo buddyInfoAdd(@RequestParam(value="name") String name,
                               @RequestParam(value="phone") String phone,
                               @RequestParam(value="addr") String addr,
                                  @RequestParam(value="city") String city,
                               @RequestParam(value="bookID") String bookID)
    {
        BuddyInfo newBuddy = new BuddyInfo(counter.incrementAndGet(),name,addr,phone,city);
        newBuddy.setBookId(bookID);
        buddyInfoRepository.save(newBuddy);

        AddressBook book = addressBookRepository.findById(Long.parseLong(bookID));
        book.addBuddy(newBuddy);
        addressBookRepository.save(book);

        return newBuddy;

    }
    @GetMapping("/buddyInfoRestRemove")
    public BuddyInfo buddyInfoRemove(@RequestParam(value="name") String name,
                                  @RequestParam(value="bookID") long bookID)
    {

        BuddyInfo remove = buddyInfoRepository.findByName(name).get(0);

        AddressBook book = addressBookRepository.findById(bookID);
        book.removeBuddy(remove);
        addressBookRepository.save(book);

        buddyInfoRepository.delete(remove);

        return remove;

    }
}
