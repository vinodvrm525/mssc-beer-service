package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootConfiguration
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "guru.springframework.msscbeerservice.web.mapper")
public class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception{
        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/"+ UUID.randomUUID().toString()).
                accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
        BeerDto beerDto = getValidBeerDto();
        String beerDtoStr = mapper.writeValueAsString(beerDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/beer/")
                                       .contentType(MediaType.APPLICATION_JSON)
                                        .content(beerDtoStr)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        given(beerService.updateBeerById(any(),any())).willReturn(getValidBeerDto());
       BeerDto beerDto = getValidBeerDto();
       String beerDtoStr = mapper.writeValueAsString(beerDto);

       mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"+UUID.randomUUID().toString())
               .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoStr)).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .BeerName("My Beer")
                .beerStyle(BeerStyle.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}
