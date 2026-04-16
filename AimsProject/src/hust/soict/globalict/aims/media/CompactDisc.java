package hust.soict.globalict.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, String artist, float cost) {
        this.title = title;
        this.category = category;
        this.artist = artist;
        this.cost = cost;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track t) {
        if (!tracks.contains(t)) {
            tracks.add(t);
        } else {
            System.out.println("Track already exists in this CD!");
        }
    }

    public void removeTrack(Track t) {
        if (tracks.contains(t)) {
            tracks.remove(t);
            System.out.println("Track removed successfully!");
        } else {
            System.out.println("Track not found in the list!");
        }
    }

    public int getLength() {
        int total = 0;
        for (Track t : tracks) total += t.getLength();
        return total;
    }

    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play this CD!");
            return;
        }
        System.out.println("Playing CD: " + title);
        System.out.println("CD length: " + getLength());
        for (Track t : tracks) t.play();
    }

    @Override
    public String toString() {
        return "CD - " + title + " - " + category + " - Artist: " + artist + ": " + cost + " $";
    }

    @Override
    public String getCategory() {
        return category;
    }
}