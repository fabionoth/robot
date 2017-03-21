package com.fabionoth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author Fabio Noth
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RobotApplication.class)
@WebAppConfiguration
public class RobotApplicationTests {

    private final MediaType contentType = new MediaType(MediaType.TEXT_HTML, Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void isMarsOk() throws Exception {
        mockMvc.perform(post("/rest/mars/")
                .content("(0, 0, N)")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void movimentoRotacoesDireita() throws Exception {
        mockMvc.perform(post("/rest/mars/MMRMMRMM")
                .content("(2, 0, S)")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void movimentoRotacoesEsquerda() throws Exception {
        mockMvc.perform(post("/rest/mars/MML")
                .content("(2, 0, S)")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void comandoInvalido() throws Exception {
        mockMvc.perform(post("/rest/mars/AAA")
                .content("Erro")
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void posicaoInvalida() throws Exception {
        mockMvc.perform(post("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM")
                .content("Erro")
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

}
