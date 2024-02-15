import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ozerov_kirill
 * @version 1.1
 */
public class Main {
  public static void main(String[] args) {
    ArrayList<MailingAddress> container = new ArrayList<>();
    while (true) {
      int flag = navigate();
      if (flag > 0 && flag < 7) {
        switch (flag) {
          case 5:
            if (container.size() > 1) {

              int choice22 = navigateForListOfOrdersToSort();
              switch (choice22) {
                case 1:
                  Comparator<MailingAddress> comparatorString1 =
                      (p1, p2) -> p2.getName().compareTo(p1.getName());
                  container.sort(comparatorString1);
                  System.out.println("Заказы отсортированы.");
                  break;
                case 2:
                  Comparator<MailingAddress> comparatorString2 =
                      (p1, p2) -> p2.getCity().compareTo(p1.getCity());
                  container.sort(comparatorString2);
                  System.out.println("Заказы отсортированы.");
                  break;
                case 3:
                  Comparator<MailingAddress> comparatorString3 =
                      (p1, p2) -> p2.getStreet().compareTo(p1.getStreet());
                  container.sort(comparatorString3);
                  System.out.println("Заказы отсортированы.");
                  break;
                case 4:
                  Comparator<MailingAddress> comparator4 =
                      Comparator.comparingInt(MailingAddress::getNumber);
                  container.sort(comparator4);
                  System.out.println("Заказы отсортированы.");
                  break;
                case 5:
                  Comparator<MailingAddress> comparator5 =
                      Comparator.comparingDouble(MailingAddress::getDistance);
                  container.sort(comparator5);
                  System.out.println("Заказы отсортированы.");
                  break;
              }
            } else {
              System.out.println("Список заказов соишком мал, чтобы его отсортировать!");
            }
            break;

          case 6:
            System.exit(0);
            break;

          case 1:
            container.add(new MailingAddress());
            System.out.println("Стандартный заказ успешно добавлен.");
            break;

          case 4:
            System.out.println("\n\n");
            for (int i = 1; i <= container.size(); i++) {
              System.out.printf("%s)------------------------\n%s", i, container.get(i - 1));
            }
            System.out.println("\n\n");
            break;

          case 3:
            System.out.println("\n");
            int choice23 = navigateForListOfOrders(container.size());
            if (choice23 > 0) {
              MailingAddress obj = container.get(choice23 - 1);
              System.out.printf("%s", obj);

              MailingAddress newObj = actionRedact(obj, container);
              if (newObj.getName().equals("---error---")) {
                container.remove(newObj);
                break;
              }
              container.set(choice23 - 1, newObj);
              if (container.get(choice23 - 1) != newObj) {
                System.out.println("Детали заказа изменены");
              }
            }
            break;

          case 2:
            MailingAddress newUser = action2();
            if (newUser.getNumber() >= 0 && newUser.getDistance() > 0 && checkerForIndex(newUser.getIndex())) {
              container.add(newUser);
              System.out.println("Заказ успешно добавлен.");
            } else {
              System.out.println("Неверный ввод, заказ отменен.");
            }
            break;
        }
      }
    }
  }

  /**
   * @param obj экземпляр - заказ
   * @param container Массив, хранящий все заказы
   * @return Измененный заказ
   */
  public static MailingAddress actionRedact(
      MailingAddress obj, ArrayList<MailingAddress> container) {
    System.out.print(
        """
                \n
                0) Удалить заказ.
                1) Имя.
                2) Город.
                3) Улицу.
                4) Номер дома.
                5) Примерное расстояние.
                6) Индекс"""
            + "\nЧто хотите отредактировать: ");
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);
    String choice = scanner.nextLine();

    try {
      int choice2 = Integer.parseInt(choice);
      if (choice2 <= 6 && choice2 >= 1) {
        System.out.println("Введите новое значение: ");
        String newValue = scanner.nextLine();
        switch (choice2) {
          case 1:
            obj.setName(newValue);
            break;
          case 2:
            obj.setCity(newValue);
            break;
          case 3:
            obj.setStreet(newValue);
            break;
          case 4:
            if ((Integer.parseInt(newValue) > 0)) {
              obj.setNumber(Integer.parseInt(newValue));
            } else {
              System.out.println("Неверное значение, детали не изменены.");
            }
            break;
          case 5:
            if (Double.parseDouble(newValue) > 0) {
              obj.setDistance(Double.parseDouble(newValue));
            } else {
              System.out.println("Неверное значение, детали не изменены.");
            }
            break;
          case 6:
            if (checkerForIndex(Integer.parseInt(newValue))){
              obj.setIndex(Integer.parseInt(newValue));
            }
            else{
              System.out.print("Неккоректный индекс");
            }
            break;
        }
      } else if (choice2 == 0) {
        container.remove(obj);
        System.out.println("Заказ успешно удален");
        MailingAddress check = new MailingAddress();
        check.setName("---error---");
        return check;
      }
    } catch (InputMismatchException | NumberFormatException | IndexOutOfBoundsException e) {
      System.out.println("Неверный ввод, заказ не изменен.");
    }
    return obj;
  }

  /**
   * @return Значение, указывающее на действие в меню.
   */
  public static int navigate() {
    System.out.println(
        """
                \n
                1) Добавить заказ по умолчанию.
                2) Добавить заказ, указав данные.
                3) Перейти к конктретному заказу.
                4) Посмотреть все заказы.
                5) Отсортировать заказы.
                6) Выйти.""");

    System.out.print("Ввод: ");
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);
    int value = -1;
    if (scanner.hasNextInt()) {
      value = scanner.nextInt();
      System.out.println("Вы ввели: " + value);
      return value;
    }
    return value;
  }

  /**
   * @param containerLen Колличество заказов
   * @return Выбор в промежуточном меню, либо "ошибка"
   */
  public static int navigateForListOfOrders(int containerLen) {
    System.out.print("0 - выйти\nВвод: ");
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);
    String choice = scanner.nextLine();
    try {
      if (Integer.parseInt(choice) >= 0 && Integer.parseInt(choice) <= containerLen)
        return Integer.parseInt(choice);
      else {
        System.out.println("Такого заказа нет.");
        return -1;
      }
    } catch (java.util.InputMismatchException e) {
      System.out.println("Неверный ввод");
      return -1;
    }
  }

  /**
   * @return Выбор в промежуточном меню 2го уровня, либо "ошибка"
   */
  public static int navigateForListOfOrdersToSort() {
    System.out.print(
        """
                0 - выйти
                1) Сортировать по Имени.
                2) Сортировать по Городу.
                3) Сортировать по Улице.
                4) Сортировать по Номеру дома.
                5) Сортировать по Расстоянию.
                Ввод:""");
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);
    String choice = scanner.nextLine();
    try {
      if (Integer.parseInt(choice) >= 0 && Integer.parseInt(choice) <= 5)
        return Integer.parseInt(choice);
      else {
        System.out.println("Такого выбора нет.");
        return -1;
      }
    } catch (java.util.InputMismatchException e) {
      System.out.println("Неверный ввод");
      return -1;
    }
  }

  public static boolean checkerForIndex(int index){
    String stringOfIndex = String.valueOf(index);
    String regex = "[1-9][1-9][1-9][1-9][1-9][1-9]";
    Pattern pattern = Pattern.compile(regex);

    Matcher matcher = pattern.matcher(stringOfIndex);
    return matcher.matches();
  }

  /**
   * @return Новый создаваемый заказ
   */
  public static MailingAddress action2() {
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);

    String name;
    String city;
    String street;
    int number;
    double distance;
    int index;

    System.out.println("Введите имя пользователя: ");
    name = scanner.nextLine();

    System.out.println("Введите город: ");
    city = scanner.nextLine();

    System.out.println("Введите улицу: ");
    street = scanner.nextLine();

    try {
      System.out.println("Введите номер дома: ");
      number = scanner.nextInt();

      System.out.println("Введите примерное расстояние до ближайшего филиала в км. (ex. 4.2) : ");
      distance = scanner.nextDouble();

      System.out.println("Введите ваш почтовый индекс (6 цифр): ");
      index = scanner.nextInt();

    } catch (java.util.InputMismatchException e) {
      System.out.println("Введенные данные неккоректны, будет создан стандартный заказ");
      return new MailingAddress();
    }
    return new MailingAddress(name, city, street, number, distance, index);
  }
}
