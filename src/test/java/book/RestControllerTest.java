package book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class RestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void addBook() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestCreate"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"buddies\":[],\"id\":1}")));

    }

    @Test
    public void displayBook() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestCreate"));
        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestCreate"));
        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestDisplay").param("bookID", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"buddies\":[],\"id\":2}")));

    }

    @Test
    public void addBuddy() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestCreate"));
        mvc.perform(MockMvcRequestBuilders.get("/buddyInfoRestAdd")
                .param("name", "Omar")
                .param("addr", "Carleton")
                .param("phone", "613")
                .param("bookID", "1")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("{\"id\":1,\"bookId\":\"1\",\"name\":\"Omar\",\"address\":\"Carleton\",\"phone\":\"613\"}")));
    }
    @Test
    public void removeBuddy() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestCreate"));
        mvc.perform(MockMvcRequestBuilders.get("/buddyInfoRestAdd")
                        .param("name", "Omar")
                        .param("addr", "Carleton")
                        .param("phone", "613")
                        .param("bookID", "1"));

        mvc.perform(MockMvcRequestBuilders.get("/buddyInfoRestRemove")
                .param("name", "Omar")
                .param("bookID", "1")
        ).andExpect(status().isOk()).andExpect(content().string(containsString("{\"id\":1,\"bookId\":\"1\",\"name\":\"Omar\",\"address\":\"Carleton\",\"phone\":\"613\"}")));

        mvc.perform(MockMvcRequestBuilders.get("/addressBookRestDisplay").param("bookID", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"buddies\":[],\"id\":1}")));

    }
}