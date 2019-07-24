package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;

import java.util.List;

public interface TrackService {
  //method to sav track
  public Track saveTrack(Track track);
  //method to get all tracks
  public List<Track> getAllTracks();
  //method to delete track
  public void deleteTrack(int id);
  //method to get track by id
  public Track getTrackById(int id);
  //method to get track by name
  public Track getTrackByName(String name);
}
