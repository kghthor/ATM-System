import java.util.*;
import java.io.*;

class Menus { 
    public void displayWelcomeScreen() {
        ATMS.clearConsol();
        System.out.print( 
                      "\n\t\t" + " ****** WELCOME TO ATM! ****** " + "\n" +
                        "\t\t" + " _____________________________ " + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 1) Login _______________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 2) Registration ________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 3) Change PIN __________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|____ 4) Exit ________________|" + "\n" +
                        "\t\t" + "|                             |" + "\n" +
                        "\t\t" + "|_____________________________|" + "\n");
        System.out.print("\n\t\tYour Choice: ");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                try {
                    ATMS.login.login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    ATMS.registration.registration();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    ATMS.changePin.changePin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.print("\n\t\t*** Thank You! For Using ATM ***\n");
                try {
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                break;
            default:
                System.out.print("\n\t\tInvalid Choice!\n");
                try {
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ATMS.menu.displayWelcomeScreen();
        }
        scan.close();
    }

    public void displayMainMenu() {
        ATMS.clearConsol();
        System.out.print(
                      "\n\t\t" + "*********** MAIN MENU ***********" + "\n" +
                        "\t\t" + " _______________________________ " + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 1) View Balance __________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 2) Withdraw Cash _________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 3) Deposit Money _________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|____ 4) Logout ________________|" + "\n" +
                        "\t\t" + "|                               |" + "\n" +
                        "\t\t" + "|_______________________________|" + "\n");
        System.out.print("\n\t\tYour Choice: ");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                try {
                    ATMS.transaction.viewBalance();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    ATMS.transaction.withdrawal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    ATMS.transaction.deposit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.print("\n\t\tLogged Out!!\n");
                try {
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ATMS.menu.displayWelcomeScreen();
                break;
            default:
                System.out.print("\n\t\tInvalid Choice!\n");
                try {
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ATMS.menu.displayMainMenu();
        }
        scan.close();
    }
}

class Registration {

    Scanner scan = new Scanner(System.in);

    String Acc_no;
    String nam;
    String dob;
    String phn;
    String pin;
    String bal;

    public void registration() throws IOException {
        ATMS.clearConsol();
        System.out.print("\n\t\t*** Enter New Customer Details ***\n");
        System.out.print("\n\t\tEnter Account Number: ");
        String Acc_no = scan.nextLine();

        try {
            ATMS.database.getRecords(Acc_no);
        } catch (Exception e) {
        }

        if (Acc_no.equals(ATMS.accountNumber)) {
            System.out.print("\n\t\tThis Account Number Already Exist!\n");
            System.out.print("\n\nPress Enter key to continue...");
            System.in.read();
            ATMS.menu.displayWelcomeScreen();
        } else {
            createAccount(Acc_no);
        }
    }

    public void createAccount(String Acc_no) throws IOException {
        String acc_no = Acc_no;
        System.out.print("\n\t\tEnter Name: ");
        String nam = scan.nextLine();
        System.out.print("\n\t\tEnter Date of Birth(dd-mm-yyyy): ");
        String dob = scan.nextLine();
        System.out.print("\n\t\tEnter Phone Number: ");
        String phn = scan.nextLine();
        System.out.print("\n\t\tEnter 4-Digit PIN: ");
        String pin = scan.nextLine();
        System.out.print("\n\t\tEnter Amount to Deposit: ");
        String bal = scan.nextLine();
        System.out.print("\n\t\tEnter a Password: ");
        String pas = scan.nextLine();

        // write to file
        File log = new File("ATM.txt");
        try {
            if (!log.exists()) {
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter
                    .write(acc_no + " : " + nam + " , " + dob + " , " + phn + " , " + pin + " , " + bal + " , " + pas);
            bufferedWriter.write("\n");
            bufferedWriter.close();
            System.out.print("\n\t\tAccount Created Successfully!!\n");
            System.out.print("\n\nPress Enter key to continue...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("\n\t\tAn error occurred.\n");
            e.printStackTrace();
        }
        ATMS.menu.displayWelcomeScreen();
    }
}

class Login {

    public void login() throws IOException {
        ATMS.clearConsol();
        Scanner scan = new Scanner(System.in);
        if (new File("ATM.txt").isFile()) {
            System.out.print("\n\t\tEnter Account Number: ");
            String acc_no = scan.nextLine();

            ATMS.database.getRecords(acc_no);

            if (acc_no.equals(ATMS.accountNumber)) {
                System.out.print("\n\t\tEnter Your PIN: ");
                String pin = scan.nextLine();
                if (pin.equals(ATMS.pin)) {
                    ATMS.menu.displayMainMenu();
                } else {
                    System.out.print("\n\t\tInvalid PIN! Try Again!\n");
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                    scan.nextLine();
                    login();
                }
            } else {
                System.out.print("\n\t\tThis Account Number is not Registered!!\n");
                System.out.print("\n\nPress Enter key to continue...");
                System.in.read();
                ATMS.menu.displayWelcomeScreen();
            }
        } else {
            System.out.print("\n\t\tEmpty Database!!\n");
            System.out.print("\n\nPress Enter key to continue...");
            System.in.read();
            ATMS.menu.displayWelcomeScreen();
        }
        scan.close();
    }
}

class Transaction {
    public void viewBalance() throws IOException {
        ATMS.clearConsol();
        String name = ATMS.name;
        int bal = Integer.parseInt(ATMS.balance);
        System.out.print(
                        "\n\t\t  =============================" +
                        "\n\t\t    Name     :  " + name +
                        "\n\t\t    Balance  :  " + "Rs. " + bal +
                        "\n\t\t  =============================\n");
        System.out.print("\n\nPress Enter key to continue...");
        System.in.read();
        ATMS.menu.displayMainMenu();
    }

    public void withdrawal() throws IOException {
        ATMS.clearConsol();
        Scanner scan = new Scanner(System.in);

        int balance = Integer.parseInt(ATMS.balance);
        int userChoice = 0;

        while (userChoice == 0) {
            ATMS.clearConsol();
            System.out.print(
                          "\n\t\t" + "    *** WITHDRAWAL MENU ***" + "\n" +
                            "\t\t" + "    ________________________ " + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 1) Rs. 500 ________|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 2) Rs. 1000 _______|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 3) Rs. 2000 _______|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 4) Rs. 2500 _______|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 5) Rs. 5000 _______|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |____ 6) Customize ______|" + "\n" +
                            "\t\t" + "   |                        |" + "\n" +
                            "\t\t" + "   |________________________|" + "\n");
            System.out.print("\n\t\t   PRESS 0 TO CANCEL\n");
            System.out.print("\n\t\t   Choose a Option: ");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    if (500 <= balance) {
                        balance = balance - 500;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else {
                        System.out.println("\n\t\t   Insufficient Cash!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 2:
                    if (1000 <= balance) {
                        balance = balance - 1000;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else {
                        System.out.print("\n\t\t   Insufficient Cash!! Please Choose a Smaller Amount.\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 3:
                    if (2000 <= balance) {
                        balance = balance - 2000;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else {
                        System.out.print("\n\t\t   Insufficient Cash!! Please Choose a Smaller Amount.\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 4:
                    if (2500 <= balance) {
                        balance = balance - 2500;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else {
                        System.out.print("\n\t\t   Insufficient Cash!! Please Choose a Smaller Amount.\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 5:
                    if (5000 <= balance) {
                        balance = balance - 5000;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else {
                        System.out.print("\n\t\t   Insufficient Cash!! Please Choose a Smaller Amount.\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 6:
                    System.out.print("\n\t\t   Enter a Amount to Withdraw: ");
                    int withdraw_amount = scan.nextInt();
                    if(withdraw_amount > 0 && withdraw_amount <= balance) {
                        balance = balance - withdraw_amount;
                        ATMS.balance = String.valueOf(balance);
                        System.out.print("\n\t\t   Transaction Completed Successfully!!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                        ATMS.database.updateRecord();
                    } else if(withdraw_amount < 0) {
                        System.out.print("\n\t\t   Please Enter a Valid Amount!\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    } else {
                        System.out.print("\n\t\t   Insufficient Cash!! Please Enter a Smaller Amount.\n");
                        System.out.print("\n\nPress Enter key to continue...");
                        System.in.read();
                    }
                    break;

                case 0:
                    System.out.print("\n\t\t   TRANSACTION CANCELED!!\n");
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                    ATMS.menu.displayMainMenu();
                    break;

                default:
                    System.out.println("\n\t\t   Invalid Choice! Please Try Again\n");
                    System.out.print("\n\nPress Enter key to continue...");
                    System.in.read();
                    break;
            }
        }
        scan.close();
    }

    public void deposit() throws IOException {
        ATMS.clearConsol();
        int balance = Integer.parseInt(ATMS.balance);

        Scanner scan = new Scanner(System.in);
        System.out.print("\n\t\tEnter Amount to Deposit(Limit - 50,000): ");
        int amount = scan.nextInt();
        if (amount > 0 || amount < 50000) {
            balance = balance + amount;
            ATMS.balance = String.valueOf(balance);
            System.out.print("\n\t\tTransaction Completed Successfully!!\n");
            System.out.print("\n\nPress Enter key to continue...");
            System.in.read();
            ATMS.database.updateRecord();
            ATMS.menu.displayMainMenu();
        }
        scan.close();
    }
}

class ChangePin {
    public void changePin() throws IOException {
        ATMS.clearConsol();
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\t\tEnter Your Account Number: ");
        String acc_no = scan.nextLine();
        ATMS.database.getRecords(acc_no);
        if (acc_no.equals(ATMS.accountNumber)) {
            System.out.print("\n\t\tEnter Your Account Password: ");
            String pas = scan.nextLine();
            if (pas.equals(ATMS.password)) {
                System.out.print("\n\t\tEnter New PIN: ");
                String PIN = scan.nextLine();
                ATMS.pin = PIN;
                System.out.print("\n\t\tYour ATM-PIN Changed Successfully!!\n");
                System.out.print("\n\nPress Enter key to continue...");
                System.in.read();
                ATMS.database.updateRecord();
            } else {
                System.out.print("\n\t\tYou Entered a Wrong Password!!\n");
                System.out.print("\n\nPress Enter key to continue...");
                System.in.read();
            }
        } else {
            System.out.print("\n\t\tAccount Does Not Exist!!\n");
            System.out.print("\n\nPress Enter key to continue...");
            System.in.read();
        }
        scan.close();
    }
}

class Database {

    public void getRecords(String acc_no) throws IOException {

        File file = new File("ATM.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        if (file.exists()) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String Accounts = parts[0].trim();
                if (acc_no.equals(Accounts)) {
                    String values = parts[1].trim();
                    String[] arrSplit = values.split(",");
                    ATMS.accountNumber = acc_no;
                    ATMS.name = arrSplit[0].trim();
                    ATMS.dob = arrSplit[1].trim();
                    ATMS.phoneNumber = arrSplit[2].trim();
                    ATMS.pin = arrSplit[3].trim();
                    ATMS.balance = arrSplit[4].trim();
                    ATMS.password = arrSplit[5].trim();
                    break;
                }
            }
        }
        br.close();
    }

    public void updateRecord() throws IOException {
        // construct the new file that will later be renamed to the original filename.
        File tempFile = new File("tempfile");
        FileWriter fw = new FileWriter(tempFile);

        File originalFile = new File("ATM.txt");
        FileReader fr = new FileReader(originalFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            if (originalFile.exists()) {
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    String Account = parts[0].trim();
                    if (Account.equals(ATMS.accountNumber)) {
                        continue;
                    }
                    fw.write(line);
                    fw.write("\n");
                    fw.flush();
                }
                fw.close();
                br.close();

                // delete the original file
                originalFile.delete();

                // rename the new file to the original filename
                tempFile.renameTo(originalFile);
            }
        } catch (IOException e) {
            System.out.println("\n\t\t   An error occurred.\n");
            e.printStackTrace();
        }

        // write to file
        File log = new File("ATM.txt");
        try {
            if (!log.exists()) {
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ATMS.accountNumber + " : " + ATMS.name + " , " + ATMS.dob + " , " + ATMS.phoneNumber
                    + " , " + ATMS.pin + " , " + ATMS.balance + " , " + ATMS.password);
            bufferedWriter.write("\n");
            bufferedWriter.close();
            ATMS.menu.displayMainMenu();
        } catch (IOException e) {
            System.out.println("\n\t\t   An error occurred.\n");
            e.printStackTrace();
        }
    }
}

public class ATMS {

    // static variables
    static String accountNumber = null;
    static String name = null;
    static String dob = null;
    static String phoneNumber = null;
    static String pin = null;
    static String balance = null;
    static String password = null;

    // static objects
    static Menus menu = new Menus();
    static Registration registration = new Registration();
    static Database database = new Database();
    static Login login = new Login();
    static Transaction transaction = new Transaction();
    static ChangePin changePin = new ChangePin();

    public final static void clearConsol()
    {
        System.out.print("\033[H\033[2J"); // it clears the consol
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {

        Menus menu = new Menus();
        menu.displayWelcomeScreen();
    }
}
