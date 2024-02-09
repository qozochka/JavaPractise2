import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<MailingAddress> container = new ArrayList<>();
        while (true){
            int flag = navigate();

            if (flag == 6){
                System.exit(0);
            }

            if (flag == 1){
                container.add(new MailingAddress());
                System.out.println("Стандартный заказ успешно добавлен.");
            }

            if (flag == 4){
                System.out.println("\n\n");
                for (int i = 0; i < container.size(); i++ ){
                    System.out.printf("%s) %s",i, container.get(i));
                    System.out.println("------------------------");
                }
                System.out.println("\n\n");
            }

            if (flag == 2){
                MailingAddress newUser = action2();
                container.add(newUser);
                System.out.println("Заказ успешно добавлен.");
            }
        }
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

/*
Сделал нумерации для вывода списка заказов, нужно сделать выбор какогото заказа и его редактирование!!!
 */


