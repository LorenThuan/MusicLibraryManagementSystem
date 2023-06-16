package dao.impl;

import bus.MusicService;
import bus.impl.MusicServiceImpl;
import dao.PlaylistsDao;
import entity.Music;
import entity.Playlists;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsImpl implements PlaylistsDao {
    private static List<Music> musicList = new ArrayList<Music>();
    private static List<Playlists> playlistsList= new ArrayList<Playlists>();

    private static MusicService musicService = new MusicServiceImpl();

    @Override
    public boolean createPlaylists(Playlists playlists) {
        for (Playlists pl : playlistsList) {
            if (pl.equals(playlists)) {
                return false;
            }
        }
        playlistsList.add(playlists);
        return true;
    }

    @Override
    public boolean addTracksToPlaylists(int idPlaylists, int idMusicTrack) {
        for(Playlists pl : playlistsList) {
            if(pl.getId() == idPlaylists) {
                if(!pl.getMusicList().isEmpty()) {
                    for(Music ms : pl.getMusicList()) {
                        if(ms.getId() == idMusicTrack) {
                            return false;
                        }
                    }
                }
                Music music = musicService.getMusicById(idMusicTrack);
                musicList.add(music);
                pl.setMusicList(musicList);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Playlists> searchPlaylistsByTitle(String titlePlaylists) {
        List<Playlists> playlistsNew = new ArrayList<Playlists>();
        for (Playlists pl : playlistsList) {
            if (pl.getTitlePlaylists().equalsIgnoreCase(titlePlaylists)) {
                playlistsNew.add(pl);
            }
        }
        return playlistsNew;
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByTitle(String title) {
        List<Playlists> playlistsMusicTrackByTitle = new ArrayList<Playlists>();
        for (Playlists pl : playlistsList) {
           for (Music ms : pl.getMusicList()) {
               if(ms.getTitle().equalsIgnoreCase(title)){
                   playlistsMusicTrackByTitle.add(pl);
               }
           }
        }
        return playlistsMusicTrackByTitle;
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByArtist(String artist) {
        List<Playlists> playlistsMusicTrackByArtist = new ArrayList<Playlists>();
        for (Playlists pl : playlistsList) {
            for (Music ms : pl.getMusicList()) {
                if(ms.getTitle().equalsIgnoreCase(artist)){
                    playlistsMusicTrackByArtist.add(pl);
                }
            }
        }
        return playlistsMusicTrackByArtist;
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByAlbum(String album) {
        List<Playlists> playlistsMusicTrackByAlbum = new ArrayList<Playlists>();
        for (Playlists pl : playlistsList) {
            for (Music ms : pl.getMusicList()) {
                if(ms.getTitle().equalsIgnoreCase(album)){
                    playlistsMusicTrackByAlbum.add(pl);
                }
            }
        }
        return playlistsMusicTrackByAlbum;
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByGenre(String genre) {
        List<Playlists> playlistsMusicTrackByGenre = new ArrayList<Playlists>();
        for (Playlists pl : playlistsList) {
            for (Music ms : pl.getMusicList()) {
                if(ms.getTitle().equalsIgnoreCase(genre)){
                    playlistsMusicTrackByGenre.add(pl);
                }
            }
        }
        return playlistsMusicTrackByGenre;
    }

    @Override
    public List<Playlists> displayListAllPlaylists() {
        return playlistsList;
    }

    @Override
    public boolean deletePlaylistsInLocal(Playlists playlists) {
        playlistsList.remove(playlists);
        return true;
    }
}
