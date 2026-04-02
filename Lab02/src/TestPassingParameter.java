public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

   
        DigitalVideoDisc[] dvds = {jungleDVD, cinderellaDVD};
        swap(dvds, 0, 1);
        
        System.out.println("jungle dvd title: " + dvds[0].getTitle());
        System.out.println("cinderella dvd title: " + dvds[1].getTitle());

        changeTitle(dvds[0], "New Jungle Title");
        System.out.println("New title: " + dvds[0].getTitle());
    }

  
    public static void swap(DigitalVideoDisc[] dvds, int i, int j) {
        DigitalVideoDisc tmp = dvds[i];
        dvds[i] = dvds[j];
        dvds[j] = tmp;
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd.setTitle(title); 
    }
}