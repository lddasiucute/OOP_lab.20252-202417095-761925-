package hust.soict.globalict.aims.media;

import java.util.Comparator;

public abstract class Media {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public String getTitle() { return title; }
    public float getCost() { return cost; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Media)) return false;
        Media m = (Media) o;
        return this.title.equals(m.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
        Comparator.comparing(Media::getTitle)
                  .thenComparing(Media::getCost, Comparator.reverseOrder());

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
        Comparator.comparing(Media::getCost, Comparator.reverseOrder())
                  .thenComparing(Media::getTitle);
}