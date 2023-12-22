package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private GenderMusical genre;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Music> music;

    public Artist(String name, Type type, GenderMusical genre) {
        this.name = name;
        this.type = type;
        this.genre = genre;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", genre=" + genre +
                ", music=" + music;
    }

    public void addNewMusic(String name, String duration, String album){
        Music music = new Music(name, duration, album);
        music.setArtist(this);
        this.music.add(music);
    }
}
