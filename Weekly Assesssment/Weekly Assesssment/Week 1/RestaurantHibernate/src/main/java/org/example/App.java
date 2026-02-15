package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Available (true/false): ");
                    boolean available = sc.nextBoolean();

                    MenuItem item = new MenuItem(name, price, category, available);
                    session.persist(item);
                    tx.commit();
                    session.close();
                    break;

                case 2:
                    List<MenuItem> list = session.createQuery("from MenuItem", MenuItem.class).list();
                    list.forEach(System.out::println);
                    tx.commit();
                    session.close();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();

                    MenuItem updateItem = session.get(MenuItem.class, id);
                    if (updateItem != null) {
                        updateItem.setPrice(newPrice);
                        session.merge(updateItem);
                    }
                    tx.commit();
                    session.close();
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int deleteId = sc.nextInt();

                    MenuItem deleteItem = session.get(MenuItem.class, deleteId);
                    if (deleteItem != null) {
                        session.remove(deleteItem);
                    }
                    tx.commit();
                    session.close();
                    break;

                case 5:
                    HibernateUtil.getSessionFactory().close();
                    System.exit(0);
            }
        }
    }
}
