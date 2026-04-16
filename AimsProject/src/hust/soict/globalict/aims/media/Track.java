package hust.soict.globalict.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public int getLength() { return length; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "Track - " + title + " - " + length + " seconds";
    }

    public void play() {
        if (length <= 0) {
            System.out.println("Cannot play track");
            return;
        }
        System.out.println("Track: " + title + " (" + length + "s)");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Track)) return false;
        Track t = (Track) o;
        return title.equals(t.title) && length == t.length;
    }

    @Override
    public int hashCode() {
        return (title != null ? title.hashCode() : 0) * 31 + length;
    }
}