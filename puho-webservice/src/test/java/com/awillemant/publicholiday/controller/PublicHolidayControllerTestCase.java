package puho.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import puho.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PublicHolidayControllerTestCase {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shouldRecognizeSupportedCountry() throws Exception {
        mockMvc.perform(get("/FR")).andExpect(status().isOk());
    }


    @Test
    public void shouldNotRecognizeUnsupportedCountry() throws Exception {
        mockMvc.perform(get("/FO")).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotMatchWithWrongIsoPattern() throws Exception {
        mockMvc.perform(get("/XXX")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotMatchWithLowercase() throws Exception {
        mockMvc.perform(get("/fr")).andExpect(status().isNotFound());
    }
}