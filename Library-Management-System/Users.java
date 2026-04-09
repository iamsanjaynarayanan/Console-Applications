import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Users {

    private Scanner s = new Scanner(System.in);

    private final Map<String,String[]> isbnbook;
    private final Map<String,String> bookisbn;
    private final Map<Integer,List<String>> bookgenre;
    private final TreeMap<String,Integer> allbooks;
    private final Map<String,List<String[]>> bookborrows;
    private final Map<String,String> userauth;
    private final Map<String,Integer> userborrowcount;
    private final Map<String,List<String[]>> userborrowhistory;
    private final Map<String,Integer> usersecdep;
    private final Map<String,String> usernames;
    private final Map<String,List<String>> userfines;
    private final long limit;
    private final int increment;
    private final int exponential;
    private final int booklossfine;
    private final int bookreturnfine;
    private final int minsecdep;
    private final int minborsecdep;
    private final int membershipfine;

    public Users(){
        Database db = new Database();
        this.isbnbook=db.getisbnbook();
        this.bookisbn=db.getbookisbn();
        this.bookgenre=db.getbookgenre();
        this.allbooks=db.getallbooks();
        this.bookborrows=db.getbookborrows();
        this.userauth=db.getuserauth();
        this.userborrowcount=db.getuserborrowcount();
        this.userborrowhistory=db.getuserborrowhistory();
        this.usersecdep=db.getusersecdep();
        this.usernames=db.getusernames();
        this.userfines=db.getuserfines();
        this.limit=db.getlimit();
        this.increment=db.getincrement();
        this.exponential=db.getexponential();
        this.booklossfine=db.getbooklossfine();
        this.bookreturnfine=db.getbookreturnfine();
        this.minsecdep=db.getminsecdep();
        this.minborsecdep=db.getminborsecdep();
        this.membershipfine=db.getmembershipfine();
    }

    public void userpage(String userid){
        List<String[]> borrowhistory = userborrowhistory.get(userid);
        LocalDate today = LocalDate.now();
        LocalDate target = today.plusDays(15L);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> listoffines = userfines.get(userid);
        String comment;
        while(true){
            System.out.print("\nWelcome User, "+usernames.get(userid)+".\n\n1. Fines Paid\n2. Books Borrowed\n3. Security Deposit\n4. Change Password\n5. Report Lost Book\n6. Extend Tenure for Borrowed Books\n7. Account Closure\n8. Repost Lost Membership Card\n9. Exit\nEnter choice: ");
            int choice;
            try {
                choice = s.nextInt();
            } 
            catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("\nInvalid entry, Try again.\n");
                continue;
            }
            s.nextLine();
            System.out.println();
            switch(choice){
                case 1:
                    System.out.println("Previous paid Fines\n");
                    for(String list:listoffines)
                        System.out.println(list);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Borrowed Books\n");
                    System.out.printf("%-40s | %-14s | %-14s%n","Title","Borrowed Date","Return Status");
                    System.out.println("-".repeat(74));
                    for(String[] array:borrowhistory)
                        System.out.printf("%-40s | %-14s | %-14s%n",isbnbook.get(array[0])[0],array[1],array[2]);
                    System.out.println("\nReturning books within the returning date is suggested. Otherwise, Fines would be applied.\n");
                    break;
                case 3:
                    int secdep = usersecdep.get(userid);
                    System.out.println("Security Deposit: Rs. "+secdep);
                    if(secdep<minsecdep){
                        System.out.print("\nYour Security Deposit amount is lower. Repaying is recommended.\n1. Repay\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } 
                        catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nMinimum amount to be paid - Rs. "+(minborsecdep-secdep)+"\nEnter the amount to deposit: ");
                                int deposit;
                                try {
                                    deposit = s.nextInt();
                                } 
                                catch (InputMismatchException e) {
                                    s.nextLine();
                                    System.out.println("\nInvalid entry, Try again.\n");
                                    break;
                                }
                                s.nextLine();
                                if(deposit<(minborsecdep-secdep))
                                    System.out.println("Amount is low, Try again.\n");
                                else if(deposit==0)
                                    System.out.println("Invalid amount, Try again.\n");
                                else{
                                    usersecdep.put(userid,usersecdep.get(userid)+secdep);
                                    System.out.println("Amount deposited successfully. Returning to Home Page.\n");
                                }
                                break;
                            case 2:
                                System.out.println("\n");
                                break;
                            default:
                                System.out.println("Invalid entry, Try again.\n");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter New Password: ");
                    String newpass = s.nextLine();
                    if(newpass.equals(userauth.get(userid))){
                        System.out.println("Password already exist. Create a new one.\n");
                        break;
                    }
                    System.out.print("Re-Enter New Password: ");
                    String renewpass = s.nextLine();
                    if(!renewpass.equals(newpass)){
                        System.out.println("\nIncorrect Password, Try again.\n");
                        break;
                    }
                    userauth.put(userid,newpass);
                    System.out.println("Password changed successfully. Returning to Home Page.\n");
                    break;
                case 5:
                    Map<String,String> returnbooks = new HashMap<>();
                    System.out.println("Borrowed Books\n");
                    System.out.printf("%-40s | %-13s | %-13s%n","Title","ISBN","Borrowed Date");
                    System.out.println("-".repeat(72));
                    for(String[] array:borrowhistory){
                        if(array[2].equals("Not Returned")){
                            returnbooks.put(array[0],array[1]);
                            System.out.printf("%-40s | %-13s | %-13s%n",isbnbook.get(array[0])[0],array[0],array[1]);
                        }
                    }
                    if(returnbooks.isEmpty()){
                        System.out.println("You have no books to return.\n");
                        break;
                    }
                    System.out.print("\nEnter the Lost Book Name/ISBN: ");
                    String name = s.nextLine();
                    if(bookisbn.containsKey(name))
                        name=bookisbn.get(name);
                    if(!returnbooks.containsKey(name)){
                        System.out.println("\nIncorrect ISBN, Try again.\n");
                        break;
                    }
                    System.out.println("\n"+booklossfine+"% of the Book Cost should be paid as Fine for Book Loss.");
                    System.out.println("The book - "+isbnbook.get(name)[0]+" - costs Rs. "+isbnbook.get(name)[5]+"\n"+booklossfine+"% of book cost is Rs. "+((Integer.parseInt(isbnbook.get(name)[5]))*booklossfine)/100);
                    if(usersecdep.get(userid)>=(((Integer.parseInt(isbnbook.get(name)[5]))*booklossfine)/100)){
                        System.out.print("\n1. Pay via Cash\n2. Deduct from Security Deposit\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry, Try again.\n");
                            break;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.println("\nPress 'Enter' to pay cash.");
                                s.nextLine();
                                System.out.println("Fine paid via cash successfully.");
                                break;
                            case 2:
                                usersecdep.put(userid,usersecdep.get(userid)-((Integer.parseInt(isbnbook.get(name)[5]))*booklossfine)/100);
                                System.out.println("\nFine paid via security deposit successfully.");
                                break;
                            default:
                                System.out.println("Invalid entry, Try again.\n");
                                continue;
                        }
                    }
                    else{
                        System.out.println("\nPress 'Enter' to pay cash.");
                        s.nextLine();
                        System.out.println("Fine paid via cash successfully.\n");
                    }
                    List<String[]> list = bookborrows.get(name);
                    int listindex = 0;
                    for(String[] array:list){
                        if(array[0].equals(userid))
                            break;
                        listindex++;
                    }
                    list.remove(listindex);
                    userborrowcount.put(userid,userborrowcount.get(userid)-1);
                    List<String[]> listofarray = userborrowhistory.get(userid);
                    for(String[] array:listofarray){
                        if(array[0].equals(name)){
                            array[2] = "Lost";
                            break;
                        }
                    }
                    List<String> fineslist = userfines.get(userid);
                    comment = "Rs. ";
                    comment += String.valueOf(((Integer.parseInt(isbnbook.get(name)[5]))*booklossfine)/100);
                    comment += " for loss of book, ";
                    comment += isbnbook.get(name)[0];
                    comment += ".";
                    fineslist.add(comment);
                    break;
                case 6:
                    Map<String,String[]> returnbooks1 = new HashMap<>();
                    System.out.println("Borrowed Books\n");
                    System.out.printf("%-40s | %-13s | %-14s | %-15s%n","Title","ISBN","Borrowed Date","Extended Tenure");
                    System.out.println("-".repeat(92));
                    for(Map.Entry<String,List<String[]>> listofarrays:bookborrows.entrySet()){
                        List<String[]> arrays = listofarrays.getValue();
                        for(String[] arr:arrays){
                            if(arr[0].equals(userid)){
                                System.out.printf("%-40s | %-13s | %-14s | %-14s%n",isbnbook.get(listofarrays.getKey())[0],listofarrays.getKey(),arr[1],arr[2]);
                                returnbooks1.put(listofarrays.getKey(),new String[]{arr[1],arr[2]});
                            }
                        }
                    }
                    if(returnbooks1.isEmpty()){
                        System.out.println("\nYou have no books to return.\n");
                        break;
                    }
                    System.out.println();
                    for(Map.Entry<String,String[]> book:returnbooks1.entrySet()){
                        if(book.getValue()[1].equals("2")){
                            System.out.println("Tenure Extension for the book - "+isbnbook.get(book.getKey())[0]+" has reached its limit.");
                            continue;
                        }
                        System.out.print("Extend tenure for the book - "+isbnbook.get(book.getKey())[0]+"\n\n1. Extend\n2. Skip\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                        }
                        s.nextLine();
                        System.out.println();
                        switch(choice){
                            case 1:
                                LocalDate borrowdate = LocalDate.parse(book.getValue()[0],format);
                                LocalDate borrowtarget = borrowdate.plusDays(15L);
                                if(borrowtarget.isBefore(today)){
                                    List<String[]> listofarrays = bookborrows.get(book.getKey());
                                    for(String[] array:listofarrays){
                                        if(array[0].equals(userid)){
                                            array[1]=today.format(format);
                                            array[2]=String.valueOf(Integer.parseInt(array[2])+1);
                                            break;
                                        }
                                    }
                                    for(String[] arrays:borrowhistory){
                                        if(arrays[0].equals(book.getKey())){
                                            arrays[1]=today.format(format);
                                            break;
                                        }
                                    }
                                    System.out.println("Tenure extended successfully. Return the book within "+target.format(format)+"\n");
                                    break;
                                }
                                else{
                                    System.out.println("The Return date has passed. Fine should be paid and Tenure Extension cannot be done.\n");
                                }
                            case 2:
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 7:
                    System.out.print("Do you want to close the account\n1. Yes\n2. No\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n");
                        break;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            if(userborrowcount.get(userid)>0){
                                System.out.println("\nYou have not returned all books. Close your account after returning books.");
                                break;
                            }
                            userauth.remove(userid);
                            System.out.println("\nYour Account in Library is successfully cleared. Please collect your Security Deposit Rs. "+usersecdep.get(userid));
                            usersecdep.remove(userid);
                            userborrowcount.remove(userid);
                            usernames.remove(userid);
                            System.out.println("\nPress 'Enter' to collect cash.");
                            s.nextLine();
                            System.out.println("Thank you, Have a good time.");
                            return;
                        case 2:
                            System.out.println("\nReturning to Home Page.\n");
                            break;
                        default:
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                    }
                    break;
                case 8:
                    System.out.print("Rs. "+membershipfine+" fine would be applied for Loss of Library Membership Card\n\n1. Accept\n2. Decline\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n");
                        break;
                    }
                    s.nextLine();
                    System.out.println();
                    switch(choice){
                        case 1:
                            if(usersecdep.get(userid)>=10){
                                System.out.print("1. Pay via Cash\n2. Deduct from Security Deposit\nEnter choice: ");
                                try {
                                    choice = s.nextInt();
                                } catch (InputMismatchException e) {
                                    s.nextLine();
                                    System.out.println("Invalid entry, Try again.\n");
                                    break;
                                }
                                s.nextLine();
                                System.out.println();
                                switch(choice){
                                    case 1:
                                        System.out.println("Press 'Enter' to pay cash.");
                                        s.nextLine();
                                        System.out.println("Fine paid via cash successfully.");
                                        break;
                                    case 2:
                                        usersecdep.put(userid,usersecdep.get(userid)-membershipfine);
                                        System.out.println("Fine paid via security deposit successfully.");
                                        break;
                                    default:
                                        System.out.println("Invalid entry, Try again.");
                                        break;
                                }
                            }
                            else{
                                System.out.println("Press 'Enter' to pay cash.");
                                s.nextLine();
                                System.out.println("Fine paid via cash successfully.");
                            }
                            comment = "Rs. ";
                            comment += String.valueOf(membershipfine);
                            comment += " for Membership Card Loss.";
                            listoffines.add(comment);
                            break;
                        case 2:
                            System.out.println("\nReturning back.");
                            break;
                        default:
                            System.out.println("Invalid entry, Try again.\n");
                            break;
                    }
                    break;
                case 9:
                    System.out.println("Returning to Main Page.\n");
                    return;
                default:
                    System.out.println("Invalid entry, Try again.\n");
                    break;
            }
        }
    }

    public void returnbook(String userid){
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String[]> borrowhistory = userborrowhistory.get(userid);
        Map<String,String> returnbooks = new HashMap<>();
        System.out.println("Borrowed Books\n");
        System.out.printf("%-40s | %-15s | %-13s%n","Title","ISBN","Borrowed Date");
        System.out.println("-".repeat(74));
        int bookcount = 0;
        int choice;
        for(String[] array:borrowhistory){
            if(array[2].equals("Not Returned")){
                bookcount++;
                returnbooks.put(array[0],array[1]);
                System.out.printf("%-40s | %-15s | %-13s%n",isbnbook.get(array[0])[0],array[0],array[1]);
            }
        }
        if(returnbooks.isEmpty()){
            System.out.println("\nYou have no books to return. Returning to Main page.\n");
            return;
        }
        if(returnbooks.size()==1){
            System.out.print("\nDo you want to return this book?\n1. Yes\n2. No\nEnter choice");
            try {
                choice = s.nextInt();
            } 
            catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("\nInvalid entry, Try again.\n");
                return;
            }
            s.nextLine();
            switch(choice){
                case 1:
                    choice=2;
                    break;
                case 2:
                    System.out.println("\nReturning back.");
                    return;
            }
        }
        else{
            System.out.print("\n1. Return all "+bookcount+" Books\n2. Return a single book\nEnter choice: ");
            try {
                choice = s.nextInt();
            } 
            catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("\nInvalid entry, Try again.\n");
                return;
            }
            s.nextLine();
        }
        System.out.println("\nNo fines for returning within 15 days of borrowing.\n");
        switch(choice){
            case 1:
                int totalfine = 0;
                for(Map.Entry<String,String> book : returnbooks.entrySet()){
                    LocalDate borroweddate = LocalDate.parse(book.getValue(),format);
                    int days = (int)Math.abs(ChronoUnit.DAYS.between(today,borroweddate));
                    int individualfine = 0;
                    int inc = increment;
                    if(days>limit){
                        days-=limit;
                        for(int i=1;i<=days;i++){
                            individualfine += inc;
                            if(i%exponential==0)
                                inc*=2;
                        }
                        System.out.println("Fine for the Book - "+isbnbook.get(book.getKey())[0]+" - Rs. "+individualfine+" - Returned in "+(days+limit)+" Days.");
                        if(((Integer.parseInt(isbnbook.get(book.getKey())[5])*bookreturnfine)/100)<individualfine){
                            System.out.println("The "+bookreturnfine+"% of Book cost is lower than the above fine amount.\nAccording to Library's Regulations, this amount is preferred for the above book's fine.\nThe Book cost is Rs. "+isbnbook.get(book.getKey())[5]+", "+bookreturnfine+"% of book cost is Rs. "+(Integer.parseInt(isbnbook.get(book.getKey())[5])*bookreturnfine)/100);
                            individualfine = (Integer.parseInt(isbnbook.get(book.getKey())[5])*bookreturnfine)/100;
                        }
                        totalfine += individualfine;
                        List<String> fineslist = userfines.get(userid);
                        String comment = "Rs. ";
                        comment += String.valueOf(individualfine);
                        comment += " for returning the book, ";
                        comment += isbnbook.get(book.getKey())[0];
                        comment += ", ";
                        comment += String.valueOf(days);
                        comment += " days after the returning date.";
                        fineslist.add(comment);
                    }
                    else
                        System.out.println("No fine for the Book - "+isbnbook.get(book.getKey())[0]+" - Returned in "+(days+limit)+" Days");
                    System.out.println();
                }
                if(totalfine==0){
                    System.out.println("\nYou have no fines.");
                }
                else if(usersecdep.get(userid)>totalfine){
                    System.out.print("\nTotal fine to be paid - Rs. "+totalfine+"\n\n1. Pay by cash\n2. Pay from Security Deposit\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    }        
                    catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n");
                        return;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            System.out.println("\nPress 'Enter' to pay cash.");
                            s.nextLine();
                            System.out.println("Cash paid.");
                            break;
                        case 2:
                            System.out.println("\nAmount deducted from Security Deposit.");
                            usersecdep.put(userid,usersecdep.get(userid)-totalfine);
                            break;
                        default:
                            System.out.println("\nInvalid entry, Try again.\n");
                            return;
                    }
                }
                else{
                    System.out.println("\nTotal fine to be paid - Rs. "+totalfine+"\nPay by cash. If paid, Press 'Enter' to pay.");
                    s.nextLine();
                }
                for(Map.Entry<String,String> book:returnbooks.entrySet()){
                    int genre = Integer.parseInt(isbnbook.get(book.getKey())[4]);
                    allbooks.put(isbnbook.get(book.getKey())[0],allbooks.get(isbnbook.get(book.getKey())[0])+1);
                    if(!bookgenre.get(genre).contains(book.getKey()))
                        bookgenre.get(genre).add(book.getKey());
                    List<String[]> list = bookborrows.get(book.getKey());
                    int listindex = 0;
                    for(String[] array:list){
                        if(array[0].equals(userid))
                            break;
                        listindex++;
                    }
                    list.remove(listindex);
                    userborrowcount.put(userid,userborrowcount.get(userid)-1);
                    List<String[]> listofarray = userborrowhistory.get(userid);
                    for(String[] array:listofarray){
                        if(array[0].equals(book.getKey())){
                            array[2] = "Returned";
                            break;
                        }
                    }
                }
                System.out.println("\nBooks returned successfully");
                break;
            case 2:
                while(bookcount>0){
                    System.out.print("Enter the Name/ISBN of Book: ");
                    String name = s.nextLine();
                    if(bookisbn.containsKey(name))
                        name=bookisbn.get(name);
                    if(!returnbooks.containsKey(name)){
                        System.out.println("\nIncorrect ISBN, Try again.");
                        return;
                    }
                    LocalDate borroweddate = LocalDate.parse(returnbooks.get(name),format);
                    int days = (int)Math.abs(ChronoUnit.DAYS.between(today,borroweddate));
                    int fine = 0;
                    int inc = increment;
                    if(days>limit){
                        days-=limit;
                        for(int i=1;i<=days;i++){
                            fine += inc;
                            if(i%exponential==0)
                                inc*=2;
                        }
                        System.out.println("\nFine for the Book - "+isbnbook.get(name)[0]+" - Rs. "+fine+" - Returned in "+(days+limit)+" Days.");
                        if(((Integer.parseInt(isbnbook.get(name)[5])*bookreturnfine)/100)<fine){
                            System.out.println("The "+bookreturnfine+"% of Book cost is lower than the above fine amount.\nAccording to Library's Regulations, this amount is preferred for the above book's fine.\nThe Book cost is Rs. "+isbnbook.get(name)[5]+", "+bookreturnfine+"% of book cost is Rs. "+(Integer.parseInt(isbnbook.get(name)[5])*bookreturnfine)/100);
                            fine = (Integer.parseInt(isbnbook.get(name)[5])*bookreturnfine)/100;
                        }
                        List<String> fineslist = userfines.get(userid);
                        String comment = "Rs. ";
                        comment += String.valueOf(fine);
                        comment += " for returning the book, ";
                        comment += isbnbook.get(name)[0];
                        comment += ", ";
                        comment += String.valueOf(days);
                        comment += " days after the returning date.";
                        fineslist.add(comment);
                        if(usersecdep.get(userid)>fine){
                            System.out.print("\nTotal fine to be paid - Rs. "+fine+"\n\n1. Pay by cash\n2. Pay from Security Deposit\nEnter choice: ");
                            try {
                                choice = s.nextInt();
                            }        
                            catch (InputMismatchException e) {
                                s.nextLine();
                                System.out.println("\nInvalid entry, Try again.\n");
                                return;
                            }
                            s.nextLine();
                            switch(choice){
                                case 1:
                                    System.out.println("\nPress 'Enter' to pay cash.");
                                    s.nextLine();
                                    System.out.println("Cash Paid.\n");
                                    break;
                                case 2:
                                    System.out.println("\nAmount deducted from Security Deposit.");
                                    usersecdep.put(userid,usersecdep.get(userid)-fine);
                                    break;
                                default:
                                    System.out.println("Invalid entry, Try again.\n");
                                    return;
                            }
                        }
                        else{
                            System.out.println("\nTotal fine to be paid - Rs. "+fine+"\nPay by cash. If paid, Press 'Enter'.");
                            s.nextLine();
                            System.out.println("Cash paid.");
                        }
                    }
                    else{
                        System.out.println("No fine for the Book - "+isbnbook.get(name)[0]+" - Returned in "+(days+limit)+" Days.\n");
                        bookcount--;
                    }
                    int genre = Integer.parseInt(isbnbook.get(name)[4]);
                    allbooks.put(isbnbook.get(name)[0],allbooks.get(isbnbook.get(name)[0])+1);
                    if(!bookgenre.get(genre).contains(name))
                        bookgenre.get(genre).add(name);
                    List<String[]> list = bookborrows.get(name);
                    int listindex = 0;
                    for(String[] array:list){
                        if(array[0].equals(userid))
                            break;
                        listindex++;
                    }
                    list.remove(listindex);
                    userborrowcount.put(userid,userborrowcount.get(userid)-1);
                    List<String[]> listofarray = userborrowhistory.get(userid);
                    for(String[] array:listofarray){
                        if(array[0].equals(name)){
                            array[2] = "Returned";
                            break;
                        }
                    }
                    if(bookcount>0){
                        System.out.print("1. Return another book\n2. Exit\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        }        
                        catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.\n");
                            return;
                        }
                        System.out.println();
                        s.nextLine();
                        switch(choice){
                            case 1:
                                break;
                            case 2:
                                return;
                            default:
                                System.out.println("Invalid entry, Try again.\n");
                                return;
                        }
                    }
                }
                break;
        }
        return;
    }
}
