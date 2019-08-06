package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
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
  public Track saveTrack(Track track) throws TrackAlreadyExistsException {
    if(trackRepository.existsById(track.getId()))
    {
      throw new TrackAlreadyExistsException("Track already exists");
    }
    else {
      Track savedTrack = trackRepository.save(track);
//    if(savedTrack==null)
//    {
//      throw new TrackAlreadyExistsException("Track already exists");
//    }
      return savedTrack;
    }
  }

  @Override
  public Track updateTrack(Track track) {
    Track savedTrack=trackRepository.save(track);
    return savedTrack;
  }

  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();
  }

  @Override
  public void deleteTrack(int id) {
    //trackRepository.deleteById(id);
    Optional<Track> track = trackRepository.findById(id);

    if(track.isPresent()) {
      trackRepository.deleteById(id);
    } else {
      //throw new RecordNotFoundException("No employee record exist for given id");
    }
  }

  @Override
  public Track getTrackById(int id) throws TrackNotFoundException {
    Optional<Track> track = trackRepository.findById(id);

    if(track.isPresent()) {
      return track.get();

    }
    else
    {
      throw new TrackNotFoundException("track does not exist");
    }
    //return trackRepository.findById(id).orElse(null);
  }

  @Override
  public Track getTrackByName(String name) {
    return trackRepository.findTrackByName(name);
  }


}

