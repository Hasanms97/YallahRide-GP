package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Service.Interface.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("ride")
public class RideController {

    @Autowired
    RideService rideService;

    @PostMapping("/create")
    public ResponseEntity<Ride>createRide(@RequestBody Ride ride){
        return new ResponseEntity<>(rideService.saveRide(ride), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Ride>updateRide(@RequestBody Ride ride){
        return new ResponseEntity<>(rideService.updateRide(ride), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ride>findRideById(@PathVariable("id") Long id){
        return new ResponseEntity<>(rideService.findRideById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ride>>findAllRides(){
        return new ResponseEntity<>(rideService.findAllRides(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus>deleteRideById(@PathVariable("id") Long id){
        rideService.deleteRideById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/all")
    public ResponseEntity<HttpStatus>deleteAllRides(){
        rideService.deleteAllRides();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reports")
    public ResponseEntity<Set<Report>>findAllRideReports(@RequestBody Ride ride){
        return new ResponseEntity<>(rideService.findRideReports(ride), HttpStatus.OK);
    }


}
