package dao.impl;

import dao.MusicDao;
import entity.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicImpl implements MusicDao {
    private static List<Music> musicList = new ArrayList<Music>();
    @Override
    public boolean addNewMusicTrack(Music music) {
        for (Music ms : musicList) {
            if (ms.equals(music)) {
                return false;
            }
        }
        musicList.add(music);
        return true;
    }

    @Override
    public List<Music> searchMusicTrackByTitle(String title) {
        List<Music> musicLists = new ArrayList<Music>();
        for (Music ms : musicList) {
            if (ms.getTitle().equalsIgnoreCase(title)) {
                musicLists.add(ms);
            }
        }
        return musicLists;
    }

    @Override
    public List<Music> searchMusicTrackByArtist(String artist) {
        List<Music> artistMusicList = new ArrayList<Music>();
        for (Music ms : musicList) {
            if (ms.getArtist().equalsIgnoreCase(artist)) {
                artistMusicList.add(ms);
            }
        }
        return artistMusicList;
    }

    @Override
    public boolean updateMusicTrack(int id, Music newMusic) {
        for (Music ms : musicList) {
            if (ms.getId() == id) {
                ms.setTitle(newMusic.getTitle());
                ms.setArtist(newMusic.getArtist());
                ms.setAlbum(newMusic.getAlbum());
                ms.setGenre(newMusic.getGenre());
                ms.setReleaseYear(newMusic.getReleaseYear());
                ms.setDuration(newMusic.getDuration());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMusicTrack(int id) {
        for (Music ms : musicList) {
            if (ms.getId() == id) {
                musicList.remove(ms);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMusicInLocal(Music music) {
            musicList.remove(music);
            return true;
    }

    @Override
    public List<Music> displayListAllMusic() {
        return musicList;
    }

    @Override
    public Music getMusicById(int id) {
        for (Music ms : musicList) {
            if(ms.getId() == id) {
                return ms;
            }
        }
        return null;
    }
}
