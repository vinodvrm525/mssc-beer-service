package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("api/v1/beer")
@RestController
public class BeerController {

    public final BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity getBeerById(@PathVariable("beerId") UUID beerId){
        // TODO: 04-07-2020
        return new ResponseEntity(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody @Validated BeerDto beerDto){
        // TODO: 04-07-2020
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> updateBeer(@RequestBody @Validated BeerDto beerDto, @PathVariable("beerId") UUID beerId){
        // TODO: 04-07-2020
        return new ResponseEntity<>(beerService.updateBeerById(beerId , beerDto), HttpStatus.NO_CONTENT);
    }

    
}
