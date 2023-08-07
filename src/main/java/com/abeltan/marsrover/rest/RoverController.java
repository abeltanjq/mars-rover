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

    /**
     * @param create Creates a new Rover. eg. 0,0,N -> format: x pos, y pos, direction.
     * @return Newly created Rover's info.
     *
     * Returns 400 if there is already a rover at that position.
     */
    @PostMapping("/rovers")
    public ResponseEntity<Rover> createRover(@RequestParam String create) {
        Rover r = RoverFactory.create(create);
        roverService.add(r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    /**
     * @param id A Rover's id. Eg. R1
     * @return Rover's info
     */
    @GetMapping("/rovers/{id}")
    public ResponseEntity<Rover> currentPosition(@PathVariable String id) {
        return ResponseEntity.of(Optional.ofNullable(roverService.find(id)));
    }

    /**
     * @return A list of Rovers and its positions
     */
    @GetMapping("/rovers")
    public List<Rover> getRovers() {
        return roverService.findAll();
    }

    /**
     * @param id Rover's id. Eg. R1
     * @param command Command to move a Rover separated by a comma. "f": forward, "b": back, "r": turn right, "l": turn left
     * @return Final position of the rover
     *
     * Should the movement intersect with another rover, it will stop at its tracks and stop future commands.
     */
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
