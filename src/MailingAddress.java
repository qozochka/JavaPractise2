public class MailingAddress {
  // static ArrayList<MailingAddress> container = new ArrayList<>();
  private String name;
  private String city;
  private String street;
  private int number;
  private double distance;
  private int index;

  MailingAddress() {
    name = "User";
    city = "City";
    street = "Street";
    number = 0;
    distance = 30.5;
  }

  MailingAddress(String name, String city, String street, int number, double distance, int index) {
    this.name = name;
    this.city = city;
    this.street = street;
    this.number = number;
    this.distance = distance;
    this.index = index;
  }

  @Override
  public String toString() {
    return "Получатель: "
        + name
        + "\nГород: "
        + city
        + "\nУлица: "
        + street
        + "\nДом: "
        + this.getNumber()
        + "\nРасстояние от филиала: "
        + distance
        + "км"
        + "\nПримерное время ожидания: "
        + this.getTime()
        + "ч"
        + "\n"
        + "Индекс: "
        + index + "\n";

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public int getTime() {
    return (int) (2 + (this.distance / 10));
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
