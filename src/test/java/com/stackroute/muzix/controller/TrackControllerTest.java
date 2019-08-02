package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.controller.TrackController;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.GlobalExceptionHandler;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import com.stackroute.muzix.service.TrackServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {


  @Autowired
  private MockMvc mockMvc;
  private Track track;
  @MockBean
  private TrackService trackService;
  @InjectMocks
  private TrackController trackController;

  private List<Track> list = null;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(GlobalExceptionHandler.class).build();
    track = new Track();
    track.setId(1);
    track.setName("Bekhayali");
    track.setComment("Sad Song");
    list = new ArrayList();

    list.add(track);
  }

  //To test save track
  @Test
  public void saveTrack() throws Exception {
    when(trackService.saveTrack(any())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  //To test for failure of save track
  @Test
  public void saveTrackFailure() throws Exception {
    when(trackService.saveTrack(any())).thenThrow(new TrackAlreadyExistsException());
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }

  //To test for get all tracks
  @Test
  public void getAllTracks() throws Exception {
    when(trackService.getAllTracks()).thenReturn(list);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
  //To test getTrack By Id
  @Test
  public void getTrackById() throws Exception {
    when(trackService.getTrackById(anyInt())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  //To test track By Id failure
  @Test
  public void getTrackByIdFailure() throws Exception {
    when(trackService.getTrackById(anyInt())).thenThrow(new TrackNotFoundException());
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

//To test the updation of the track
  @Test
  public void updateTrack() throws Exception {
    when(trackService.updateTrack(any())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  //To test for delete track
  @Test
  public void deleteTrack() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }


  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
