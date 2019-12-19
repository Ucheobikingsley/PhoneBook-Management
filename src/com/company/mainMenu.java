package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainMenu {
    static Scanner x;
    static mainMenu menu = new mainMenu();
    static Scanner input = new Scanner(System.in);

    //    Content menu user must choose from in the Order 1-6;
    private int Content(){
        System.out.println("1.Add Contact\n"
                + "2.Show Contact\n"
                + "3.Delete contact\n"
                + "4.Modify Contact\n"
                + "5.Search Contact\n"
                + "6.count.\n"
                + "0.end");
        int choice = readInt(0,5);
        return choice;

    }

    //    Case Condition based on the Number User must have selected.Each leading to a Method That handles the request;
    public void  start() throws IOException{
        while(true){
            int choice = Content();
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    add();
                    break;
                case 2:
                    ShowAll();
                    break;
                case 3:
                    Delete();
                    break;
                case 4:
                    edit();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    count();
                    break;


            }
        }
    }

    //Validate if Request entered by user is within 1-6
    private  int readInt(int min,int max){
        int choice;
        while(true){
            try{
                System.out.print("Enter Selection:");
                choice = input.nextInt();
                if(choice >= min && choice <= max){
                    break;
                }
            }catch(Exception e){
                System.out.println("Selection Not Found");
            }
        }
        return  choice;
    }

    //    First Content menu Option:Adds Records.
    public void add(){
        String Phonenumber;
        String LastName;
        String FirstName;
        String Address;
        String city;

        System.out.print("Enter Lastname:");
        LastName = input.next();
//

        System.out.print("Enter Firstname: ");
        FirstName = input.next();
//
        System.out.print("Enter Address: ");
        Address = input.next();
//
        System.out.print("Enter city");
        city = input.next();
        System.out.print("Enter PhoneNumber");
        Phonenumber = input.next();

        try{
            File file = new File("Mainmenu.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));

            pw.println();

            pw.print(LastName+       "\t\t"          +FirstName+          "\t\t"         +Address+          "\t\t"          +city+           "\t\t"   +Phonenumber);

            pw.close();
            System.out.println("Successfully");

        }catch(IOException e){
            System.out.println("An error has occurred");
            e.printStackTrace();
        }




    }
    //       Displays all Record
    public void ShowAll() {


        try{
            File Obj = new File("Mainmenu.txt");

            Scanner reader = new Scanner(Obj);
            while(reader.hasNextLine()){
                String data = reader.nextLine();

                System.out.println(data);

            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");

        }

    }
    //        Prompt user to Enter the Lastname of the Record that should be deleted;
    public static void Delete(){

        System.out.println("Enter LastName:");
        String LastName = input.next();

        menu.delete(LastName);
    }
    //    Delete the Record
    public static void delete(String removeTerm){
        String filepath = "Mainmenu.txt";

        int position = 1 - 1;
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String data[];

        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null){
                data = currentLine.split("\t\t");
                if(!(data[position].equalsIgnoreCase(removeTerm))){
                    System.out.println("Deleted");
                    pw.println(currentLine);
                }
            }

            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);



        }catch(IOException e){
            System.out.println("Cannot delete");
        }
    }
    // prompt user to Enter the Lastname of the record that should be searched for.
    private void search() {
        System.out.println("Enter LastName or city:");
        String LastName = input.next();
        menu.read(LastName);
    }

    //   Reads the search and Display record that marches.
    public static void read(String removeTerm){
        String filepath = "Mainmenu.txt";

        int position = 1 - 1;
        int position2 = 0;


        File oldFile = new File(filepath);


        String currentLine;
        String data[];

        try{



            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null){
                data = currentLine.split("\t\t");
                if((data[position].equalsIgnoreCase(removeTerm))){
                    System.out.println("Contact Found:"+ currentLine);
                }
            }


            fr.close();
            br.close();





        }catch(IOException e){
            System.out.println("Cannot delete");
        }
    }
    //     prompt user to enter the Record lastname and New information to be assigned to each record;
    public static void edit(){
        System.out.println("Enter the Lastname of the record you want to Modify:");
        String oldLast = input.next();
        System.out.println("Enter new Lastname:");
        String newLastName = input.next();
        System.out.println("Enter new Firstname:");
        String newFirstName = input.next();
        System.out.println("Enter Enter new address:");
        String newAddress = input.next();
        System.out.println("Enter new city:");
        String newCity = input.next();
        System.out.println("Enter new Phonenumber:");
        String newPhone=input.next();
        menu.Modify(oldLast,newLastName,newFirstName,newAddress,newCity,newPhone);

    }
    //     Execute the Edit
    public static void Modify(String ModifyTerm,String newLastName,String newFirstName,String newAddress,String newCity,String newPhone){

        String filepath = "Mainmenu.txt";

        int position = 1 - 1;
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String data[];

        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null){
                data = currentLine.split("\t\t");
                if((data[position].equalsIgnoreCase(ModifyTerm))){
                    System.out.println("Record modified");
                    pw.println(newLastName+"\t\t"+newFirstName+"\t\t"+newAddress+"\t\t"+newCity+"\t\t"+newPhone);
                }else{
                    System.out.println("Record doesnt exist2");
                    pw.println(currentLine);
                }
            }

            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);



        }catch(IOException e){
            System.out.println("Error");
        }
    }

    public static void count() throws IOException {

        File file = new File("Mainmenu.txt");
        if(file.exists()){
            try {
                FileReader read = new FileReader(file);
                LineNumberReader br = new LineNumberReader(read);
                int Line = 0;
                while(br.readLine()!= null ){
                    Line++;

                }
                System.out.println("Total number of record:"+ Line);
            } catch (FileNotFoundException ex) {
                System.out.println("File Not foud");
            }catch (IOException e){
                System.out.println("NO record");
            }

        }
    }
}
