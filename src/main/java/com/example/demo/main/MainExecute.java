package com.example.demo.main;
import com.example.demo.model.Artist;
import com.example.demo.model.GenderMusical;
import com.example.demo.model.Music;
import com.example.demo.model.Type;
import com.example.demo.repository.IArtistRepository;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainExecute {
    private final Scanner readInput = new Scanner(System.in);

    private final IArtistRepository repository;
    public MainExecute(IArtistRepository repository) {
        this.repository = repository;
    }

    public void menu() {
        var option = -1;
        System.out.println("***** Welcome Byte music *****");
        while(option != 0) {
            var menu = """
                    1 - Register artist
                    2 - Register music
                    3 - List songs
                    4 - Share music by artist
                    0 - Exit                        
                    """;
            System.out.println(menu);
            option = readInput.nextInt();
            readInput.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerMusic();
                    break;
                case 3:
                    listSongs();
                    break;
                case 4:
                    shareMusicByArtist();
                    break;
                case 0:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Option is invalid");
            }
        }
    }

    private void shareMusicByArtist() {
        System.out.println("List of Artist");
        List<Artist> listArtist = repository.findAll();
        if(!listArtist.isEmpty()){
            listArtist.stream()
                    .map(a -> a.getName())
                    .forEach(System.out::println);
            System.out.println("Chose artist:");
            String name = readInput.nextLine();
            Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);
            if (!artist.isPresent()) {
                throw new RuntimeException("Artist not fond!");
            }
            List<Music> listMusic = repository.findByMusicByArtist(artist.get());
            listMusic.forEach(e -> System.out.println("Music: "+ e.getNameMusic() + " Album: "+ e.getAlbum()));
        }else {
            System.out.println("List of artist is Empty!");
        }
    }

    private void listSongs() {
        System.out.println("List of songs");
        var listMusic = repository.findAllMusic();
        listMusic.forEach(e-> System.out.println("Music: " + e));
    }

    private void registerMusic() {
        System.out.println("List of Artist");
        List<Artist> listArtist = repository.findAll();
        if(!listArtist.isEmpty()) {
            listArtist.stream()
                    .map(a -> a.getName())
                    .forEach(System.out::println);
            System.out.println("Chose artist:");
            String name = readInput.nextLine();
            Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);
            if (!artist.isPresent()) {
                throw new RuntimeException("Artist not fond!");
            }
            System.out.println("type the song name: ");
            String nameMusic = readInput.nextLine();
            System.out.println("type duration of music: ");
            String duration = readInput.nextLine();
            System.out.println("type the album: ");
            String albumName = readInput.nextLine();
            var artistFond = artist.get();
            artistFond.addNewMusic(nameMusic, duration, albumName);
            repository.save(artistFond);
            System.out.println("Music add...");
        }else {
            System.out.println("List of artist is Empty!");
        }
    }

    private void registerArtist() {
        System.out.println("What's name of artist ?");
        String name = readInput.nextLine();
        String textFormatType = """
                The artist is single or group ?
                [S] - Single
                [G] - Group
                """;
        System.out.println(textFormatType);
        String type = readInput.nextLine().toUpperCase();
        String textFormatGender = """
                What's gender musical ?
                [C] - Classic
                [D] - Dance
                [P] - Pop
                [R] - Rock
                [O] - Other
                """;
        System.out.println(textFormatGender);
        String gender = readInput.nextLine().toUpperCase();
        var typeConvert = type.equals("S") ? Type.SINGLE: Type.GROUP;
        GenderMusical genderConvert;
        if(gender.equals("C")){
            genderConvert = GenderMusical.CLASSIC;
        } else if (gender.equals("D")) {
            genderConvert = GenderMusical.DANCE;
        } else if (gender.equals("P")) {
            genderConvert = GenderMusical.POP;
        } else if (gender.equals("R")) {
            genderConvert = GenderMusical.ROCK;
        } else if (gender.equals("O")) {
            genderConvert = GenderMusical.OTHER;
        }else {
            throw new RuntimeException("Gender not Exist");
        }
        Artist artist = new Artist(name,typeConvert,genderConvert);
        repository.save(artist);
    }
}
