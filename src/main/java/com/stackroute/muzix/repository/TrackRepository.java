package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
  //Query to search using name parameter
  @Query("FROM Track u WHERE u.name = ?1")
  Track findTrackByName(String name);
}
