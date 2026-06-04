package hust.soict.globalict.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparable<Media> {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    /**
     * Two medias are equal if they have the same title and cost.
     * Throws NullPointerException if the other object is null.
     * Throws ClassCastException if the other object is not a Media.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare with null!");
        }
        if (!(o instanceof Media)) {
            throw new ClassCastException("Object is not an instance of Media!");
        }
        Media other = (Media) o;
        return this.title.equals(other.title)
            && Float.compare(this.cost, other.cost) == 0;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + Float.floatToIntBits(cost);
        return result;
    }

    /**
     * Default comparison: by title first, then by cost (descending).
     */
    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null!");
        }
        int titleCompare = this.title.compareTo(other.title);
        if (titleCompare != 0) {
            return titleCompare;
        }
        return Float.compare(other.cost, this.cost); // descending by cost
    }

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
        Comparator.comparing(Media::getTitle)
                  .thenComparing(Media::getCost, Comparator.reverseOrder());

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
        Comparator.comparing(Media::getCost, Comparator.reverseOrder())
                  .thenComparing(Media::getTitle);
}