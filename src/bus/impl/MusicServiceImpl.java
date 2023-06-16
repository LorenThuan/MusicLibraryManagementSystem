package bus.impl;

import bus.MusicService;
import dao.MusicDao;
import dao.impl.MusicImpl;
import entity.Music;

import java.util.List;

public class MusicServiceImpl implements MusicService {
    private MusicDao musicDao = new MusicImpl();
    @Override
    public boolean addNewMusicTrack(Music music) {
        return musicDao.addNewMusicTrack(music);
    }

    @Override
    public List<Music> searchMusicTrackByTitle(String title) {
        return musicDao.searchMusicTrackByTitle(title);
    }

    @Override
    public List<Music> searchMusicTrackByArtist(String artist) {
        return musicDao.searchMusicTrackByArtist(artist);
    }

    @Override
    public boolean updateMusicTrack(int id, Music newMusic) {
        return musicDao.updateMusicTrack(id, newMusic);
    }

    @Override
    public boolean deleteMusicTrack(int id) {
        return musicDao.deleteMusicTrack(id);
    }

    @Override
    public boolean deleteMusicInLocal(Music music) {
        return musicDao.deleteMusicInLocal(music);
    }

    @Override
    public List<Music> displayListAllMusic() {
        return musicDao.displayListAllMusic();
    }

    @Override
    public Music getMusicById(int id) {
        return musicDao.getMusicById(id);
    }
}
