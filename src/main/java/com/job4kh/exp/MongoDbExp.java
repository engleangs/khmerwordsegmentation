package com.job4kh.exp;

import java.util.Scanner;

public class MongoDbExp {
    private static MongoDbConnection connection;
    public static void find()
    {
        System.out.println("****************************************");
        System.out.print("Input your text : ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        long count = connection.find( text.trim());
        System.out.println(" find result : "+count);
        System.out.println("****************************************");


    }

    public static void  add()
    {
        System.out.println("****************************************");
        System.out.print("Input your word : ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        if( "no".equals(text))
        {
            return;
        }
        connection.addWord( text.trim());
        System.out.println("****************************************");
    }

    public static void main(String[] args)
    {
         connection = MongoDbConnection.getInstance();
        System.out.println("****************************************");
        System.out.println("total word :"+connection.getTotalWord());
        Scanner scanner = new Scanner( System.in);
        while (true)
        {
            System.out.println("Input your choice : ");
            System.out.println("1 => Add new word");
            System.out.println("2 => Find text");
            System.out.println("0 => Exit");
            System.out.println("****************************************");
            int select = scanner.nextInt();
            switch (select){
                case 0:
                    System.out.println("bye bye ....");
                    return;
                case 1:
                    add();
                    break;

                case 2:
                    find();
                    break;
            }

        }





    }
}
