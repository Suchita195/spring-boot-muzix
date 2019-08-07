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
  private TrackService trackService;

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

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

  @GetMapping("track")
  public ResponseEntity getAllTracks()
  {
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
  }

  @DeleteMapping("track/{id}")
  public Track deleteTrack(@PathVariable int id)
  {
    return trackService.deleteTrack(id);
  }

  @GetMapping("id")
  public ResponseEntity getTrackById(@RequestParam int id)
  {
    return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
  }


}
