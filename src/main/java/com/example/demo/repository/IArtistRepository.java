package com.example.demo.repository;

import com.example.demo.model.Artist;
import com.example.demo.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IArtistRepository extends JpaRepository<Artist,Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String name);

    @Query("SELECT m FROM Artist a JOIN a.music m WHERE a = :artist")
    List<Music> findByMusicByArtist(Artist artist);

    @Query("SELECT m.nameMusic FROM Music m ")
    List<String> findAllMusic();
}
