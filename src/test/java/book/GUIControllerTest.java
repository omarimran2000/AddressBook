package book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GUIControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void getForm() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookForm"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(" <p><input type=\"submit\" value=\"Submit\" /> <input type=\"reset\" value=\"Reset\" /></p>")));
    }
    @Test
    public void submitForm() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/addressBookCreate"));
        mvc.perform(MockMvcRequestBuilders.post("/addressBookForm").param("ID","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id: 1")));
    }
}
