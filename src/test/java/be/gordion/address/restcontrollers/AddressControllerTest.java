package be.gordion.address.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertAddress.sql")
public class AddressControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

    public AddressControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    private long idFromTestAddress() {
        return jdbcTemplate.queryForObject("select address_id from addresses where municipality_name = 'test'", Long.class);
    }

    @Test
    void readExistentAddress() throws Exception {
        var id = idFromTestAddress();
        mvc.perform(get("/address/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("addressId").value(id));
    }

    @Test
    void addNewAddress() throws Exception {
        String jsonBody = "{\"municipalityName\":\"Stad\", \"postalCode\":1000, \"streetName\":\"Straat\", \"houseNumber\":1, \"boxNumber\":\"1\"}";
        mvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
    }
}
