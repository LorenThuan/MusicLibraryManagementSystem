package main;

import bus.MusicService;
import bus.PlaylistsService;
import bus.impl.MusicServiceImpl;
import bus.impl.PlaylistsServiceImpl;
import entity.Music;
import entity.Playlists;
import handle.MusicFileIO;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Calendar;
import java.util.regex.Pattern;

public class ComandLineOption {
    public static MusicService musicService = new MusicServiceImpl();
    public static PlaylistsService playlistsService = new PlaylistsServiceImpl();

    public static MusicFileIO musicFileIO = new MusicFileIO();

    public void HandleOption() {
        Scanner scanner = new Scanner(System.in);
        String response = "";
        while (!response.equals("Q")) {
            System.out.println("MT = music collection tracks, MP = music collection playlists, Q = quit");
            System.out.print("Enter your choice: ");
            response = scanner.nextLine().trim().toUpperCase();
            while (!response.equals("MT") && !response.equals("MP") && !response.equals("Q")) {
                System.out.println("Invalid choice, please select MT, MP or Q");
                System.out.print("Enter your choice: ");
                response = scanner.nextLine().trim().toUpperCase();
            }
            switch (response) {
                case ("MT"):
                    while (!response.equals("Q")) {
                        musicTrackinformationGuide();
                        response = scanner.nextLine().trim().toUpperCase();
                        while (!response.equals("A") && !response.equals("ST") && !response.equals("SA")
                                && !response.equals("U") && !response.equals("D") && !response.equals("V")
                                && !response.equals("R") && !response.equals("Q"))
                        {
                            System.out.println("Invalid choice, please select A, ST, SA, U, D, V, R or Q");
                            System.out.print("Enter your choice: ");
                            response = scanner.nextLine().trim().toUpperCase();
                        }
                        switch (response) {
                            case ("A"):
                                inputInformationMusicTrack();
                                break;
                            case ("ST"):
                                System.out.print("Searching music collection tracks by title: ");
                                String keyword = scanner.nextLine();
                                System.out.println(musicService.searchMusicTrackByTitle(keyword));
                                break;
                            case ("SA"):
                                System.out.print("Searching music collection tracks by artist: ");
                                keyword = scanner.nextLine();
                                System.out.println(musicService.searchMusicTrackByArtist(keyword));
                                break;
                            case ("U"):
                                checkInformationUpdate();
                                break;
                            case ("D"):
                                handleDeleteMusic();
                                break;
                            case ("V"):
                                try {
                                    while (!musicService.displayListAllMusic().isEmpty()) {
                                        for(Music ms : musicService.displayListAllMusic()) {
                                            musicService.deleteMusicInLocal(ms);
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Done!");
                                }
                                System.out.println("All list music collection tracks: ");
                                readDataMusic();
                                System.out.println(musicService.displayListAllMusic());
                                break;
                            case ("R"):
                                HandleOption();
                                break;
                            case ("Q"):
                                System.exit(0);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + response);
                        }
                    }
                    break;
                case ("MP"):
                    while (!response.equals("Q")) {
                        playlistsInformationGuide();
                        response = scanner.nextLine().trim().toUpperCase();
                        while (!response.equals("C") && !response.equals("AP") && !response.equals("STP")
                                && !response.equals("ST") && !response.equals("SA") && !response.equals("SAB")
                                && !response.equals("SG") && !response.equals("R") && !response.equals("Q"))
                        {
                            System.out.println("Invalid choice, please select C, AP, STP, ST, SA, SAB, SG, R or Q");
                            System.out.print("Enter your choice: ");
                            response = scanner.nextLine().trim().toUpperCase();
                        }
                        switch (response) {
                            case ("C"):
                                handleCreatePlaylists();
                                break;
                            case ("AP"):
                                addMusicToPlaylists();
                                break;
                            case ("STP"):
                                System.out.print("Searching playlists by title playlists: ");
                                String keyword = scanner.nextLine();
                                System.out.println(playlistsService.searchPlaylistsByTitle(keyword));
                                break;
                            case ("ST"):
                                System.out.print("Searching music collection playlists by title music tracks: ");
                                keyword = scanner.nextLine();
                                System.out.println(playlistsService.searchMusicTrackInPlaylistsByTitle(keyword));
                                break;
                            case ("SA"):
                                System.out.print("Searching music collection playlists by artist music tracks: ");
                                keyword = scanner.nextLine();
                                System.out.println(playlistsService.searchMusicTrackInPlaylistsByArtist(keyword));
                                break;
                            case ("SAB"):
                                System.out.print("Searching music collection playlists by album music tracks: ");
                                keyword = scanner.nextLine();
                                System.out.println(playlistsService.searchMusicTrackInPlaylistsByAlbum(keyword));
                                break;
                            case ("SG"):
                                System.out.print("Searching music collection playlists by genre music tracks: ");
                                keyword = scanner.nextLine();
                                System.out.println(playlistsService.searchMusicTrackInPlaylistsByGenre(keyword));
                                break;
                            case ("R"):
                                HandleOption();
                                break;
                            case ("Q"):
                                System.exit(0);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + response);
                        }
                    }
                    break;
                case ("Q"):
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please press the key according to the instructions to use the command-line app");
                    break;
            }
        }
    }

    public void musicTrackinformationGuide() {
        System.out.println("A = Add new music track");
        System.out.println("ST = Search music collection tracks by title");
        System.out.println("SA = Search music collection tracks by artist");
        System.out.println("U = Update the details of an existing music track");
        System.out.println("D = Delete a music track from the library");
        System.out.println("V = View all list music collection tracks");
        System.out.println("R = Return menu choose music collection (tracks and playlists)");
        System.out.println("Q = Quit");
        System.out.print("Enter your choice: ");
    }

    public void playlistsInformationGuide() {
        System.out.println("C = Create new playlists");
        System.out.println("AP = Add tracks to playlists");
        System.out.println("STP = Search music collection playlists by title playlists");
        System.out.println("ST = Search music collection playlists by title music tracks");
        System.out.println("SA = Search music collection playlists by artist music tracks");
        System.out.println("SAB = Search music collection playlists by album music tracks");
        System.out.println("SG = Search music collection playlists by genre music tracks");
        System.out.println("R = Return menu choose music collection (tracks and playlists)");
        System.out.println("Q = Quit");
        try {
            while (!playlistsService.displayListAllPlaylists().isEmpty()) {
                for(Playlists pl : playlistsService.displayListAllPlaylists()) {
                    playlistsService.deletePlaylistsInLocal(pl);
                }
            }
            while (!musicService.displayListAllMusic().isEmpty()) {
                for(Music ms : musicService.displayListAllMusic()) {
                    musicService.deleteMusicInLocal(ms);
                }
            }
        } catch (Exception e) {
            System.out.println("Done!");
        }
        readDataPlaylists();
        readDataMusic();
        System.out.println("All playlists: ");
        System.out.println(playlistsService.displayListAllPlaylists());
        System.out.println("All list music collection tracks: ");
        System.out.println(musicService.displayListAllMusic());
        System.out.print("Enter your choice: ");
    }

    public int checkReleaseYear() {
        Scanner scanner = new Scanner(System.in);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int releaseYear = 0;
        boolean isValidYear = false;
        while (!isValidYear) {
            System.out.print("Enter release year: ");
            if (scanner.hasNextLine()) {
                String inputYear = scanner.nextLine();
                if (inputYear.matches("^\\d{4}$")) {
                    int releaseYearCheck = Integer.parseInt(inputYear);
                    if (releaseYearCheck <= currentYear) {
                        releaseYear = releaseYearCheck;
                        isValidYear = true;
                    } else {
                        System.out.println("Year must be " + currentYear + " or earlier");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid year");
                }
            }
        }
        System.out.println("Valid release year: " + releaseYear);
        return releaseYear;
    }

    public void inputInformationMusicTrack() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        System.out.print("Enter album: ");
        String album = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        int releaseYear = checkReleaseYear();
        String duration = "";
        String regex = "(^[0-9]+:0[0-9]:[0-5][0-9]$)|(^[0-9]+:[0-5]?[0-9]:[0-5][0-9]$)|(^[0-5]?[0-9]:[0-5][0-9]$)|(^[0-5]?[0-9]$)";
        while (true) {
            System.out.print("Enter the duration (in format 'hour:minutes:seconds', 'minutes:seconds', or 'seconds'): ");
            duration = scanner.nextLine();
            if (Pattern.matches(regex, duration)) {
                String[] time = duration.split(":");

                // get the last element as seconds
                int seconds = Integer.parseInt(time[time.length - 1]);

                // get the second last element as minutes, if it exists
                int minutes = time.length > 1 ? Integer.parseInt(time[time.length - 2]) : 0;

                // get the third last element as hours, if it exists
                int hours = time.length > 2 ? Integer.parseInt(time[time.length - 3]) : 0;

                if (seconds >= 0 && seconds <= 59 && minutes >= 0 && minutes <= 59 && hours >= 0) {
                    System.out.println("Valid duration: " + duration);
                    break;
                } else {
                    System.out.println("Invalid duration, please enter a valid duration");
                }
            } else {
                System.out.println("Invalid duration, please enter a valid duration");
            }
        }
        System.out.print("Enter file name (in format 'number.wav') where number is the number starting from 1: ");
        String filename = scanner.nextLine();
        int randomNum = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
        Music music = new Music(randomNum, title, artist, album, genre, releaseYear, duration, filename);
        musicService.addNewMusicTrack(music);
        writeDataMusic();
        System.out.println("Add data successfully");
    }

    public void checkInformationUpdate() {
        Scanner scanner = new Scanner(System.in);
        String regexId = "^[0-9]+$";
        String idCheck = "";
        int id = 0;
        while (true) {
            System.out.print("Enter id you want update: ");
            idCheck = scanner.nextLine();
            if (idCheck.matches(regexId) && Integer.parseInt(idCheck) <= Integer.MAX_VALUE) {
                System.out.println("Valid input: " + idCheck);
                id = Integer.parseInt(idCheck);
                break;
            } else {
                System.out.println("Invalid input, please enter a valid input");
            }
        }
        if (!musicService.displayListAllMusic().isEmpty()) {
            for (Music ms : musicService.displayListAllMusic()) {
                while (ms.getId() != id) {
                    System.out.println("Id does not exist please re-enter");
                    System.out.print("Enter id you want update: ");
                    idCheck = scanner.nextLine();
                    if (idCheck.matches(regexId) && Integer.parseInt(idCheck) <= Integer.MAX_VALUE) {
                        id = Integer.parseInt(idCheck);
                    } else {
                        System.out.println("Invalid input, please enter a valid input");
                    }
                }
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.print("Enter album: ");
                String album = scanner.nextLine();
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();
                int releaseYear = checkReleaseYear();
                String regexYear = "(^[0-9]+:0[0-9]:[0-5][0-9]$)|(^[0-9]+:[0-5]?[0-9]:[0-5][0-9]$)|(^[0-5]?[0-9]:[0-5][0-9]$)|(^[0-5]?[0-9]$)";
                String duration = "";
                while (true) {
                    System.out.print("Enter the duration (in format 'hour:minutes:seconds', 'minutes:seconds', or 'seconds'): ");
                    duration = scanner.nextLine();
                    if (Pattern.matches(regexYear, duration)) {
                        String[] time = duration.split(":");
                        int seconds = Integer.parseInt(time[time.length - 1]); // get the last element as seconds
                        int minutes = time.length > 1 ? Integer.parseInt(time[time.length - 2]) : 0; // get the second last element as minutes, if it exists
                        int hours = time.length > 2 ? Integer.parseInt(time[time.length - 3]) : 0; // get the third last element as hours, if it exists
                        if (seconds >= 0 && seconds <= 59 && minutes >= 0 && minutes <= 59 && hours >= 0) {
                            System.out.println("Valid duration: " + duration);
                            break;
                        } else {
                            System.out.println("Invalid duration, please enter a valid duration");
                        }
                    } else {
                        System.out.println("Invalid duration, please enter a valid duration");
                    }
                }
                System.out.print("Enter file name (in format 'number.wav') where number is the number starting from 1: ");
                String filename = scanner.nextLine();
                Music musicUpdate = new Music(id, title, artist, album, genre, releaseYear, duration, filename);
                boolean rs = musicService.updateMusicTrack(id, musicUpdate);
                System.out.println("Update successful");
                writeDataMusic();
            }
        } else {
            System.out.println("No music exists");
        }
    }

    public void handleDeleteMusic() {
        Scanner scanner = new Scanner(System.in);
        String regexId = "^[0-9]+$";
        String idCheck = "";
        int id = 0;
        while (true) {
            System.out.print("Enter id you want delete: ");
            idCheck = scanner.nextLine();
            if (idCheck.matches(regexId) && Integer.parseInt(idCheck) <= Integer.MAX_VALUE) {
                System.out.println("Valid input: " + idCheck);
                id = Integer.parseInt(idCheck);
                break;
            } else {
                System.out.println("Invalid input, please enter a valid input");
            }
        }
        if (!musicService.displayListAllMusic().isEmpty()) {
            for (Music ms : musicService.displayListAllMusic()) {
                while (ms.getId() != id) {
                    System.out.println("Id does not exist please re-enter");
                    System.out.print("Enter id you want delete: ");
                    idCheck = scanner.nextLine();
                    if (idCheck.matches(regexId) && Integer.parseInt(idCheck) <= Integer.MAX_VALUE) {
                        id = Integer.parseInt(idCheck);
                    } else {
                        System.out.println("Invalid input, please enter a valid input");
                    }
                }
                boolean rs = musicService.deleteMusicTrack(id);
                System.out.println("Successful delete");
                writeDataMusic();
                break;
            }
        } else {
            System.out.println("No music exists");
        }
    }

    public void handleCreatePlaylists() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title playlists: ");
        String titlePlaylists = scanner.nextLine();
        int randomNum = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
        Playlists playlists = new Playlists(randomNum, titlePlaylists);
        playlistsService.createPlaylists(playlists);
        System.out.println("Add data successfully");
        writeDataPlaylists();
    }

    public void addMusicToPlaylists() {
        Scanner scanner = new Scanner(System.in);
        String regexId = "^[0-9]+$";
        String idCheckPlaylists = "";
        String idCheckMusic = "";
        int idPlaylists = 0;
        int idMusic = 0;
        while (true) {
            System.out.print("Enter id playlists you want add: ");
            idCheckPlaylists = scanner.nextLine();
            if (idCheckPlaylists.matches(regexId) && Integer.parseInt(idCheckPlaylists) <= Integer.MAX_VALUE) {
                System.out.println("Valid input: " + idCheckPlaylists);
                idPlaylists = Integer.parseInt(idCheckPlaylists);
                break;
            } else {
                System.out.println("Invalid input, please enter a valid input");
            }
        }
        while (true) {
            System.out.print("Enter id music you want add: ");
            idCheckMusic = scanner.nextLine();
            if (idCheckMusic.matches(regexId) && Integer.parseInt(idCheckMusic) <= Integer.MAX_VALUE) {
                System.out.println("Valid input: " + idCheckMusic);
                idMusic = Integer.parseInt(idCheckMusic);
                break;
            } else {
                System.out.println("Invalid input, please enter a valid input");
            }
        }
        if (!playlistsService.displayListAllPlaylists().isEmpty() && musicService.getMusicById(idMusic) != null) {
            boolean rs = playlistsService.addTracksToPlaylists(idPlaylists, idMusic);
            System.out.println("Add data successfully");
            writeDataPlaylists();
        } else {
            System.out.println("No playlists exists");
        }
    }

    public void writeDataMusic() {
        try {
            musicFileIO.writeDataMusicToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDataMusic() {
        try {
            musicFileIO.readDataMusicFromFile();
        } catch (IOException e) {
            System.out.println("There is no data in the file");
        }
    }

    public void writeDataPlaylists() {
        try {
            musicFileIO.writeDataPlaylistsToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDataPlaylists() {
        try {
            musicFileIO.readDataPlaylistsFromFile();
        } catch (IOException e) {
            System.out.println("There is no data in the file");
        }
    }
}
