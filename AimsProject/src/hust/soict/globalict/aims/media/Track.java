package hust.soict.globalict.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public int getLength() { return length; }

    public void play() {
        if (length <= 0) {
            System.out.println("Cannot play track");
            return;
        }
        System.out.println("Track: " + title);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Track)) return false;
        Track t = (Track) o;
        return title.equals(t.title) && length == t.length;
    }
}