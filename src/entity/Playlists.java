package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlists implements Serializable {
    private int id;
    private String titlePlaylists;
    private List<Music> musicList = new ArrayList<Music>();

    public Playlists() {
    }

    public Playlists(int id, String titlePlaylists) {
        this.id = id;
        this.titlePlaylists = titlePlaylists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlePlaylists() {
        return titlePlaylists;
    }

    public void setTitlePlaylists(String titlePlaylists) {
        this.titlePlaylists = titlePlaylists;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlists playlists = (Playlists) o;
        return id == playlists.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Playlists{" +
                "id=" + id +
                ", titlePlaylists='" + titlePlaylists + '\'' +
                ", musicList=" + musicList +
                '}';
    }
}
