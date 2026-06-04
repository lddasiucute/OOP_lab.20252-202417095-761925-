package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.exception.PlayerException;
public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public void play() throws PlayerException {
    if (length <= 0) {
        System.err.println("ERROR: DVD length is non-positive!");
        throw new PlayerException("ERROR: DVD length is non-positive!");
    }
    System.out.println("Playing DVD: " + title);
    System.out.println("Length: " + length);
}

    @Override
    public String toString() {
        return "DVD - " + title + " - " + category + " - " + director + " - " + length + ": " + cost + " $";
    }
}