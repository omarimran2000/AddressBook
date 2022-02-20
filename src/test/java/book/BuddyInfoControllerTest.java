package book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BuddyInfoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void addBuddy() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookCreate"));


        mvc.perform(MockMvcRequestBuilders.post("/buddyInfoAdd")
                .param("name", "Omar")
                .param("address", "Carleton")
                .param("phone", "613")
                .param("city", "Ottawa")
                .param("bookId", "1")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("Buddy Info : Omar has been modified.")));

    }
    @Test
    public void removeBuddy() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookCreate"));


        mvc.perform(MockMvcRequestBuilders.post("/buddyInfoAdd")
                .param("name", "Omar")
                .param("address", "Carleton")
                .param("phone", "613")
                .param("city", "Ottawa")
                .param("bookId", "1")
        );
        mvc.perform(MockMvcRequestBuilders.post("/buddyInfoRemove")
                .param("ID", "Omar")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("Buddy Info : Omar has been modified.")));


    }


}
