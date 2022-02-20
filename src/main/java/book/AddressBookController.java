package book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;


    @GetMapping("/addressBookCreate")
    public String addressBookCreate(Model model)
    {
        AddressBook newBook = new AddressBook();
        model.addAttribute("addressBook",newBook);
        addressBookRepository.save(newBook);
        return "/addressBookCreate";

    }
    @GetMapping("/addressBookForm")
    public String addressBookForm(Model model)
    {
        model.addAttribute("GUI",new GUIForm());
        return "addressBookForm";
    }

    @PostMapping("/addressBookForm")
    public String addressBookSubmit(@ModelAttribute  GUIForm GUI, Model model)
    {
        AddressBook addressBook = addressBookRepository.findById(Long.parseLong(GUI.getID()));
        model.addAttribute("addressBook",addressBook);
        return "/addressBookDisplay";

    }
    @GetMapping("/oldIndex")
    public String oldIndex()
    {
        return "/oldIndex";
    }

}
@RestController
class AddressBookRestController {

    @Autowired
    private AddressBookRepository addressBookRepository;


    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/addressBookRestCreate")
    public AddressBook addressBookCreate()
    {
        long id = counter.incrementAndGet();

        AddressBook newBook = new AddressBook(id);
        addressBookRepository.save(newBook);
        return newBook;

    }
    @GetMapping("/addressBookRestDisplay")
    public AddressBook addressBookDisplay(@RequestParam(value="bookID") String bookID)
    {
        AddressBook addressBook = addressBookRepository.findById(Long.parseLong(bookID));
        return addressBook;

    }


}
