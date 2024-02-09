import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<MailingAddress> container = new ArrayList<>();
        while (true){
            int flag = navigate();
            switch (flag) {

                case 6:
                    System.exit(0);

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
                    int choice22 = navigateForListOfOrders(container.size());
                    if (choice22 > 0) {
                        MailingAddress obj = container.get(choice22 - 1);
                        System.out.printf("%s", obj);

                        MailingAddress newObj = actionRedact(obj, container);
                        if (newObj.getName().equals("---error---")){
                            container.remove(newObj);
                            System.out.println("Заказ удален");
                            break;
                        }
                        System.out.printf("%s00000", choice22); /////////////////////&&&&&&&&&&&
                        container.set(choice22 - 1, newObj);
                        if (container.get(choice22 - 1) != newObj) {
                            System.out.println("Детали заказа изменены");
                        }
                    }
                    break;

                case 2:
                    MailingAddress newUser = action2();
                    container.add(newUser);
                    System.out.println("Заказ успешно добавлен.");
                    break;
            }
        }
    }


    public static MailingAddress actionRedact(MailingAddress obj, ArrayList<MailingAddress> container) {
        System.out.print(
                """
                \n
                0) Удалить заказ.
                1) Имя.
                2) Город.
                3) Улицу.
                4) Номер дома.
                5) Примерное расстояние.""" + "\nЧто хотите отредактировать: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        try {
            int choice2 = Integer.parseInt(choice);
            if (choice2 <= 6 && choice2 >= 1){
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
                            obj.setNumber(Integer.parseInt(newValue));
                            break;
                        case 5:
                            obj.setDistance(Integer.parseInt(newValue));
                            break;
                    }
            }
            else if (choice2 == 0 ){
                container.remove(obj);
                System.out.println("Заказ успешно удален");
                MailingAddress check = new MailingAddress();
                check.setName("---error---");
                return check;
            }
        }
        catch (InputMismatchException | NumberFormatException | java.lang.IndexOutOfBoundsException e){
            System.out.println("Неверный ввод, заказ не изменен.");
        }
        return obj;
    }

    public static int navigate(){
        System.out.println("""
                \n
                1) Добавить заказ по умолчанию.
                2) Добавить заказ, указав данные.
                3) Перейти к конктретному заказу.
                4) Посмотреть все заказы.
                5) Отсортировать заказы.
                6) Выйти.""");

        System.out.print("Ввод: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int navigateForListOfOrders(int containerLen){
        System.out.print("0 - выйти\nВвод: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try{
            if (Integer.parseInt(choice) >= 0 && Integer.parseInt(choice) <= containerLen)
                return Integer.parseInt(choice);
            else {
                System.out.println("Такого заказа нет.");
                return -1;
            }
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Неверный ввод");
            return -1;
        }
    }

    public static MailingAddress action2(){
        Scanner scanner = new Scanner(System.in);

        String name;
        String city;
        String street;
        int number;
        double distance;

        System.out.println("Введите имя пользователя: ");
        name = scanner.nextLine();

        System.out.println("Введите город: ");
        city = scanner.nextLine();

        System.out.println("Введите улицу: ");
        street = scanner.nextLine();

        try{
            System.out.println("Введите номер дома: ");
            number = scanner.nextInt();

            System.out.println("Введите примерное расстояние до ближайшего филиала в км. (ex. 4.2) : ");
            distance = scanner.nextDouble();
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Введенные данные неккоректны, будет создан стандартный заказ");
            return new MailingAddress();
        }
        return new MailingAddress(name, city, street, number, distance);
    }
}



// Теперь нужно будет сделать? ту функцию и внедрить ее, потом сделать сортировку
