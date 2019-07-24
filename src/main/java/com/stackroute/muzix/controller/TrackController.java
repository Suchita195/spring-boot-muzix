package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
  TrackService trackService;

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  //For save and update track
  @PostMapping("track")
  public ResponseEntity<?> saveTrack(@RequestBody Track track) {
    ResponseEntity responseEntity;
    try {

      if (trackService.getTrackById(track.getId())!=null) {
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully updated", HttpStatus.OK);
      } else {
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
      }
    }
      catch (Exception e) {
      responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
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
  @GetMapping("id")
  public ResponseEntity getTrackById(@RequestParam int id)
  {
    return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
  }

  //To retrieve the record on the basis of name
  @GetMapping("track/{name}")
  public ResponseEntity getTrackByName(@PathVariable String name)
  {
    return new ResponseEntity<Track>(trackService.getTrackByName(name),HttpStatus.OK);
  }


}
