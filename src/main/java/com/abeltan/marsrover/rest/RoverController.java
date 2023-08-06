package com.abeltan.marsrover.rest;

import com.abeltan.marsrover.exception.InvalidRoverConfiguration;
import com.abeltan.marsrover.exception.RoverNotFoundException;
import com.abeltan.marsrover.response.RoverConfigurationErrorResponse;
import com.abeltan.marsrover.service.RoverService;
import com.abeltan.marsrover.utility.RoverFactory;
import com.abeltan.marsrover.entity.Rover;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoverController {

    private RoverService roverService;

    public RoverController(RoverService roverService) {
        this.roverService = roverService;
    }

    @PostMapping("/rovers")
    public ResponseEntity<Rover> createRover(@RequestParam String create) {
        Rover r = RoverFactory.create(create);
        roverService.add(r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @GetMapping("/rovers/{id}")
    public ResponseEntity<Rover> currentPosition(@PathVariable String id) {
        return ResponseEntity.of(Optional.ofNullable(roverService.find(id)));
    }

    @GetMapping("/rovers")
    public List<Rover> getRovers() {
        return roverService.findAll();
    }

    @PutMapping("/rovers/{id}")
    public Rover moveRover(@PathVariable String id, @RequestParam String command) {
        return roverService.move(id, command);
    }

    @ExceptionHandler
    public ResponseEntity<RoverConfigurationErrorResponse> handleRoverConfigurationException(InvalidRoverConfiguration exc) {
        RoverConfigurationErrorResponse errorResponse = new RoverConfigurationErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exc.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Rover> handleRoverNotFoundException(RoverNotFoundException exc) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
