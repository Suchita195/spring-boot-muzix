package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.GlobalExceptionHandler;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController extends GlobalExceptionHandler {
  private TrackService trackService;

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  //For save track
  @PostMapping("track")
  public ResponseEntity<?> saveTrack (@RequestBody Track track) throws TrackAlreadyExistsException{
    ResponseEntity responseEntity;
   
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);

      
    return responseEntity;
  }

  //For update track
  @PutMapping("track")
  public ResponseEntity<?> updateTrack(@RequestBody Track track) {
    ResponseEntity responseEntity;
        trackService.updateTrack(track);
        responseEntity = new ResponseEntity<String>("successfully updated", HttpStatus.OK);
    return responseEntity;
  }


  //To retrieve all tracks
  @GetMapping("track")
  public ResponseEntity getAllTracks()
  {
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
  }

  //To delete the track on basis of id
  @DeleteMapping("track/{id}")
  public HttpStatus deleteTrack(@PathVariable int id)
  {
    trackService.deleteTrack(id);
    return HttpStatus.FORBIDDEN;
  }

  //To retrieve the record on the basis of id
  @GetMapping("track/{id}")
  public ResponseEntity getTrackById (@PathVariable int id) throws TrackNotFoundException
  {
    ResponseEntity responseEntity;
      responseEntity = new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
  
    return responseEntity;
  }

  //To retrieve the record on the basis of name
  @GetMapping("trackByName/{name}")
  public ResponseEntity getTrackByName(@PathVariable String name)
  {
    return new ResponseEntity<Track>(trackService.getTrackByName(name),HttpStatus.OK);
  }
}
