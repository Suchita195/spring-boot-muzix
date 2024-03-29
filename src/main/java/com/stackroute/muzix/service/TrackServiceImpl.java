package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
  private TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository)
  {
    this.trackRepository=trackRepository;
  }

  @Override
  public Track saveTrack(Track track) {
    Track savedTrack=null;
    try
    {
      savedTrack=trackRepository.save(track);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return savedTrack;
  }

  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();
  }

  @Override
  public Track deleteTrack(int id) {
    //trackRepository.deleteById(id);
    Optional<Track> track = trackRepository.findById(id);

    if(track.isPresent()) {
      trackRepository.deleteById(id);
      return track.get();
    } else {
      return null;
    }
  }

  @Override
  public Track getTrackById(int id) {
    return trackRepository.findById(id).orElse(null);
  }

  }

