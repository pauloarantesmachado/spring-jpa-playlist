package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameMusic;

    private String duration;

    private String album;

    @ManyToOne
    private Artist artist;

    public Music(String nameMusic, String duration, String album){
        this.nameMusic = nameMusic;
        this.duration = duration;
        this.album = album;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nameMusic='" + nameMusic + '\'' +
                ", duration='" + duration + '\'' +
                ", album='" + album + '\'' +
                ", artist=" + artist;
    }
}
