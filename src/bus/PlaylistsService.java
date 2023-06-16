package bus;

import entity.Music;
import entity.Playlists;

import java.util.List;

public interface PlaylistsService {
    public boolean createPlaylists(Playlists playlists);
    public boolean addTracksToPlaylists(int idPlaylists, int idMusicTrack);
    public List<Playlists> searchPlaylistsByTitle(String titlePlaylists);
    public List<Playlists> searchMusicTrackInPlaylistsByTitle(String title);
    public List<Playlists> searchMusicTrackInPlaylistsByArtist(String artist);
    public List<Playlists> searchMusicTrackInPlaylistsByAlbum(String album);
    public List<Playlists> searchMusicTrackInPlaylistsByGenre(String genre);
    public List<Playlists> displayListAllPlaylists();
    public boolean deletePlaylistsInLocal(Playlists playlists);
}
