package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import com.stackroute.muzix.service.TrackService;
import com.stackroute.muzix.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {

    Track track;

  //Create a mock for TrackRepository
  @Mock
  TrackRepository trackRepository;

  //Inject the mocks as dependencies into TrackserviceImpl
  @InjectMocks
  TrackServiceImpl trackService;
  List<Track> list= null;


  @Before
  public void setUp(){
    //Initialising the mock object
    MockitoAnnotations.initMocks(this);
    track = new Track();
    track.setId(1);
    track.setName("Bekhayali");
    track.setComment("Sad Song");
    list = new ArrayList<>();
    list.add(track);


  }

  //To test save Track
  @Test
  public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

    when(trackRepository.save((Track)any())).thenReturn(track);
    Track savedTrack = trackService.saveTrack(track);
    Assert.assertEquals(track,savedTrack);

    //verify here verifies that userRepository save method is only called once
    verify(trackRepository,times(1)).save(track);

  }

  //To test save track failure
  @Test(expected = TrackAlreadyExistsException.class)
  public void saveTrackTestFailure() throws TrackAlreadyExistsException {
    when(trackRepository.save((Track)any())).thenReturn(null);
    Track savedTrack = trackService.saveTrack(track);
    System.out.println("savedTrack" + savedTrack);
    //Assert.assertEquals(user,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
  }
  //To test getall tracks
  @Test
  public void getAllTracks(){
    //trackRepository.save(track);
    //stubbing the mock to return specific data
    when(trackRepository.findAll()).thenReturn(list);
    List<Track> userlist = trackService.getAllTracks();
    Assert.assertEquals(list,userlist);
    verify(trackRepository,times(1)).findAll();
  }

  //To test get track by Id
  @Test
  public void getTrackByIdSuccess() throws TrackNotFoundException {

    when(trackRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));
    Track savedTrack = trackService.getTrackById(track.getId());
    Assert.assertEquals(track,savedTrack);

    //verify here verifies that userRepository save method is only called once
    verify(trackRepository,times(1)).findById(anyInt());

  }

  //To test the updation of track
  @Test
  public void updateTrackTestSuccess()  {

    when(trackRepository.save((Track)any())).thenReturn(track);
    Track updatedTrack = trackService.updateTrack(track);
    Assert.assertEquals(track,updatedTrack);

    //verify here verifies that userRepository save method is only called once
    verify(trackRepository,times(1)).save(track);

  }

  //To test the deletion of track
  @Test
  public void deleteTrackTestSuccess() throws Exception{
    when(trackRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));
     trackService.deleteTrack(track.getId());
     //Assert.assertNotEquals(null,track);

    //verify here verifies that userRepository save method is only called once
    verify(trackRepository,times(1)).deleteById(track.getId());
  }

}
