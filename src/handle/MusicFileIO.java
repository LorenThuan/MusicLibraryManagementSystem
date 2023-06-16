package handle;

import bus.MusicService;
import bus.PlaylistsService;
import bus.impl.MusicServiceImpl;
import bus.impl.PlaylistsServiceImpl;
import entity.Music;
import entity.Playlists;

import java.io.*;
import java.util.List;

public class MusicFileIO {
    private static MusicService musicService = new MusicServiceImpl();
    private static PlaylistsService playlistsService = new PlaylistsServiceImpl();
    public void writeDataMusicToFile() throws IOException {
        File file = new File("./file/music.txt");
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (Music music : musicService.displayListAllMusic()) {
            bufferedWriter.write(music.getId() + "," + music.getTitle() + "," + music.getArtist()
                    + "," + music.getAlbum() + "," + music.getGenre() + "," + music.getReleaseYear()
                    + "," + music.getDuration() + "," + music.getFilename()
            );
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        writer.close();
    }

    public void readDataMusicFromFile() throws IOException {
        File file = new File("./file/music.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] musicData = line.split(",");
            int id = Integer.parseInt(musicData[0]);
            String title = musicData[1];
            String artist = musicData[2];
            String album = musicData[3];
            String genre = musicData[4];
            int releaseYear = Integer.parseInt(musicData[5]);
            String duration = musicData[6];
            String filename = musicData[7];
            Music music = new Music(id, title, artist, album, genre, releaseYear, duration, filename);
            musicService.displayListAllMusic().add(music);
        }
        bufferedReader.close();
        reader.close();
    }

    public void writeDataPlaylistsToFile() throws IOException {
        File file = new File("./file/playlists.txt");
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (Playlists playlists : playlistsService.displayListAllPlaylists()) {
            bufferedWriter.write(playlists.getId() + "," + playlists.getTitlePlaylists());
            bufferedWriter.newLine();
            for (Music music : playlists.getMusicList()) {
                bufferedWriter.write(music.getTitle() + "," + music.getArtist() + "," + music.getAlbum());
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
        writer.close();
    }

    public void readDataPlaylistsFromFile() throws IOException {
        File file = new File("./file/playlists.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while (line != null) {
            int idPlaylists = Integer.parseInt(line.split(",")[0]);
            String titlePlaylists = line.split(",")[1];
            Playlists playlists = new Playlists(idPlaylists, titlePlaylists);
            line = bufferedReader.readLine();
            while (line != null && !line.equals("")) {
                int idMusic = Integer.parseInt(line.split(",")[0]);
                String title = line.split(",")[1];
                String artist = line.split(",")[2];
                String album = line.split(",")[3];
                String genre = line.split(",")[4];
                int releaseYear = Integer.parseInt(line.split(",")[5]);
                String duration = line.split(",")[6];
                String filename = line.split(",")[7];
                Music music = new Music(idMusic, title, artist, album, genre, releaseYear, duration, filename);
                playlists.getMusicList().add(music);
                line = bufferedReader.readLine();
            }
            playlistsService.displayListAllPlaylists().add(playlists);
        }
        bufferedReader.close();
        reader.close();
    }
}