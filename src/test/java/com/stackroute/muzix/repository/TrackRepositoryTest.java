package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
  @Autowired
  TrackRepository trackRepository;
  Track track;

  @Before
  public void setUp()
  {
    track = new Track();
    track.setId(1);
    track.setName("Bekhayali");
    track.setComment("Sad Song");
  }

  @After
  public void tearDown(){

    trackRepository.deleteAll();
  }

//To test for save track
  @Test
  public void testSaveTrack(){
    trackRepository.save(track);
    Track fetchTrack = trackRepository.findById(track.getId()).get();
    Assert.assertEquals(1,fetchTrack.getId());

  }

  //To test for save track failure
  @Test
  public void testSaveUserFailure(){
    Track testTrack = new Track(2,"tum hi ho","romantic");
    trackRepository.save(track);
    Track fetchTrack = trackRepository.findById(track.getId()).get();
    Assert.assertNotSame(testTrack,track);
  }

  //To test for get all tracks
  @Test
  public void testGetAllTrack() {
    Track track1 = new Track(3,"waka waka","rock");
    Track track2 = new Track(4,"shape of you","soft song");
    trackRepository.save(track1);
    trackRepository.save(track2);

    List<Track> list = trackRepository.findAll();
    Assert.assertEquals("shape of you", list.get(1).getName());
  }

  //To test get track by Id
  @Test
  public void testGetTrackById() {
    Track getTrack=new Track(5,"summer of 69","rock");
    trackRepository.save(getTrack);
    Track fetchTrack=trackRepository.findById(getTrack.getId()).get();
    Assert.assertEquals("rock",fetchTrack.getComment());
    trackRepository.deleteAll();
  }

  //To test get track by Name
  @Test
  public void testGetTrackByName() {
    trackRepository.save(track);
    Track fetchTrack=trackRepository.findTrackByName(track.getName());
    //System.out.println(fetchTrack);
    Assert.assertEquals("Bekhayali",track.getName());
  }

  //To test for delete track
  @Test
  public void testDeleteTrack(){
    Track newTrack=new Track(5,"summer of 69","rock");
    trackRepository.save(newTrack);
    trackRepository.deleteById(newTrack.getId());
    Assert.assertEquals(null,trackRepository.findById(newTrack.getId()).orElse(null));
  }

  //To test for the updated track
  @Test
  public void testUpdateTrack() {
    trackRepository.save(track);
    Track track1 = new Track(1,"waka waka","rock");
    trackRepository.save(track1);
    Track fetchTrack=trackRepository.findById(track1.getId()).get();
    Assert.assertNotEquals(track.getName(),fetchTrack.getName());
  }
}
