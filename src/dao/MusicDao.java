package dao;

import entity.Music;

import java.util.List;

public interface MusicDao {
    public boolean addNewMusicTrack(Music music);
    public List<Music> searchMusicTrackByTitle(String title);
    public List<Music> searchMusicTrackByArtist(String artist);
    public boolean updateMusicTrack(int id, Music newMusic);
    public boolean deleteMusicTrack(int id);
    public boolean deleteMusicInLocal(Music music);
    public List<Music> displayListAllMusic();
    public Music getMusicById(int id);
}
