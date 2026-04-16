package hust.soict.globalict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public void play() {
        if (length <= 0) {
            System.out.println("Cannot play this DVD");
            return;
        }
        System.out.println("Playing DVD: " + title);
        System.out.println("Length: " + length);
    }

    public String toString() {
        return "DVD - " + title + " - " + cost;
    }
}