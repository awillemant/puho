package puho.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import puho.Application;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PublicHolidayControllerTests {

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

    @Test
    public void shouldReturnJsonStructureIfCorrectYear() throws Exception{
        mockMvc.perform(get("/FR/2016"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(11)))
                .andExpect(jsonPath("$[*].year", contains(2016,2016,2016,2016,2016,2016,2016,2016,2016,2016,2016)))
                .andExpect(jsonPath("$[*].monthValue", contains(1,3,5,5,5,5,7,8,11,11,12)))
                .andExpect(jsonPath("$[*].dayOfMonth", contains(1,28,1,5,8,16,14,15,1,11,25)));
    }

    @Test
    public void shouldNotMatchWithWrongYearFormat() throws Exception {
        mockMvc.perform(get("/FR/16")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnJsonStructureIfCorrectPeriod() throws Exception{
        mockMvc.perform(get("/FR/20150612/20160513"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*].year", contains(2015,2015,2015,2015,2015,2016,2016,2016,2016,2016)))
                .andExpect(jsonPath("$[*].monthValue", contains(7,8,11,11,12,1,3,5,5,5)))
                .andExpect(jsonPath("$[*].dayOfMonth", contains(14,15,1,11,25,1,28,1,5,8)));
    }

    @Test
    public void shouldReturnErrorIfWrongPeriod() throws Exception{
        mockMvc.perform(get("/FR/20160513/20150612/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("2016-05-13 is after 2015-06-12"));
    }
}