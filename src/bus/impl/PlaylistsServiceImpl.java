package bus.impl;

import bus.PlaylistsService;
import dao.PlaylistsDao;
import dao.impl.PlaylistsImpl;
import entity.Playlists;

import java.util.List;

public class PlaylistsServiceImpl implements PlaylistsService {
    private PlaylistsDao playlistsDao = new PlaylistsImpl();
    @Override
    public boolean createPlaylists(Playlists playlists) {
        return playlistsDao.createPlaylists(playlists);
    }

    @Override
    public boolean addTracksToPlaylists(int idPlaylists, int idMusicTrack) {
        return playlistsDao.addTracksToPlaylists(idPlaylists, idMusicTrack);
    }

    @Override
    public List<Playlists> searchPlaylistsByTitle(String titlePlaylists) {
        return playlistsDao.searchPlaylistsByTitle(titlePlaylists);
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByTitle(String title) {
        return playlistsDao.searchMusicTrackInPlaylistsByTitle(title);
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByArtist(String artist) {
        return playlistsDao.searchMusicTrackInPlaylistsByArtist(artist);
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByAlbum(String album) {
        return playlistsDao.searchMusicTrackInPlaylistsByAlbum(album);
    }

    @Override
    public List<Playlists> searchMusicTrackInPlaylistsByGenre(String genre) {
        return playlistsDao.searchMusicTrackInPlaylistsByGenre(genre);
    }

    @Override
    public List<Playlists> displayListAllPlaylists() {
        return playlistsDao.displayListAllPlaylists();
    }

    @Override
    public boolean deletePlaylistsInLocal(Playlists playlists) {
        return playlistsDao.deletePlaylistsInLocal(playlists);
    }
}
