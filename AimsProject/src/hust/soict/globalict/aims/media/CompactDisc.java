package hust.soict.globalict.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String title, String category, String artist, float cost) {
        this.title = title;
        this.category = category;
        this.artist = artist;
        this.cost = cost;
    }

    public void addTrack(Track t) {
        if (!tracks.contains(t)) tracks.add(t);
    }

    public int getLength() {
        int total = 0;
        for (Track t : tracks) total += t.getLength();
        return total;
    }

    public void play() {
        System.out.println("Playing CD: " + title);
        for (Track t : tracks) t.play();
    }
}