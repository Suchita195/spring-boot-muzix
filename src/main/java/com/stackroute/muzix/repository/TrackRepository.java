package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
  //Query to search using name parameter
//  @Query("FROM Track u WHERE u.name = ?1")
//  Track findTrackByName(String name);
}
