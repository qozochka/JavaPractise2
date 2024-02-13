public class MailingAddress {
    //static ArrayList<MailingAddress> container = new ArrayList<>();
    private String name;
    private String city;
    private String street;
    private int number;
    private double distance;

    MailingAddress() {
        name = "User";
        city = "City";
        street = "Street";
        number = 0;
        distance = 30.5;
    }

    MailingAddress(String name, String city, String street, int number, double distance) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.distance = distance;

    }
@Override
    public String toString(){
        return  "Получатель: " + name
                + "\nГород: " + city
                + "\nУлица: " + street
                + "\nДом: " + this.getNumber()
                + "\nРасстояние от филиала: " + distance + "км"
                + "\nПримерное время ожидания: " + this.getTime() + "ч" + "\n";
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }

    public String getStreet(){
        return street;
    }

    public int getNumber(){
        return number;
    }

    public double getDistance(){
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime(){
        return (int) (2 + (this.distance / 10));
    }

//    static ArrayList<MailingAddress> getContainer(){
//        return container;
//    }

//    public void addContainer(MailingAddress obj){
//        container.add(obj);
//    }
}




