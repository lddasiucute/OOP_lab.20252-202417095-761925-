public class Aims {

    public static void main(String[] args) {

        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        anOrder.displayCart();

        System.out.println("\n>> Removing 'Star Wars'...");
        anOrder.removeDigitalVideoDisc(dvd2);
        anOrder.displayCart();

        System.out.println("\n>> Testing overloaded addDigitalVideoDisc (array)...");
        Cart cart2 = new Cart();
        DigitalVideoDisc[] batch = {
            new DigitalVideoDisc("Mulan", "Animation", 15.99f),
            new DigitalVideoDisc("Titanic", "Drama", "James Cameron", 195, 22.50f)
        };
        cart2.addDigitalVideoDisc(batch);
        cart2.displayCart();

        System.out.println("\n>> Testing overloaded addDigitalVideoDisc (two params)...");
        Cart cart3 = new Cart();
        cart3.addDigitalVideoDisc(
            new DigitalVideoDisc("Inception"),
            new DigitalVideoDisc("Interstellar")
        );
        cart3.displayCart();

        System.out.println("\n>> Total DVDs created (class variable): "
                + DigitalVideoDisc.getNbDigitalVideoDiscs());
    }
}
