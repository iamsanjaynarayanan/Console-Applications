import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Explore {

    private final Scanner s = new Scanner(System.in);

    private final Map<String,String[]> isbnbook;
    private final Map<String,String> bookisbn;
    private final Map<Integer,List<String>> bookgenre;
    private final TreeMap<String,Integer> allbooks;
    private final Map<String,List<String[]>> bookborrows;
    private final Map<String,String> userauth;
    private final Map<String,Integer> userborrowcount;
    private final Map<String,List<String[]>> userborrowhistory;
    private final Map<String,Integer> usersecdep;
    private final long limit;
    private final int minborsecdep;
    private final int minsecdep;
    private final int borrowlimit;

    public Explore(){
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
        this.limit=db.getlimit();
        this.minborsecdep=db.getminborsecdep();
        this.minsecdep=db.getminsecdep();
        this.borrowlimit=db.getborrowlimit();
    }

    public void mainpage(){
        int count = borrowlimit;
        List<String> templist1 = new ArrayList<>(bookgenre.get(1));
        List<String> templist2 = new ArrayList<>(bookgenre.get(2));
        List<String> templist3 = new ArrayList<>(bookgenre.get(3));
        List<String> templist4 = new ArrayList<>(bookgenre.get(4));
        List<String> templist5 = new ArrayList<>(bookgenre.get(5));
        List<String> templist6 = new ArrayList<>(bookgenre.get(6));
        List<String> cart = new ArrayList<>();
        int choice;
        boolean check;
        while(true){
            if(!cart.isEmpty()){
                System.out.print(cart.size()+" Books in Cart\n\n1. Proceed to Checkout\n2. Modify Cart\n3. Explore Library\nEnter choice: ");
                try {
                    choice = s.nextInt();
                } catch (InputMismatchException e) {
                    s.nextLine();
                    System.out.println("\nInvalid entry, Try again.");
                    continue;
                }
                s.nextLine();
                switch(choice){
                    case 1:
                        check = checkoutpage(cart);
                        if(check)
                            continue;
                        cart.clear();
                        count=borrowlimit;
                        break;
                    case 2:
                        check = modifycart(templist1,templist2,templist3,templist4,templist5,templist6,cart,choice);
                        if(check)
                            count++;
                        continue;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid entry, Try again.");
                        continue;
                }
            }
            if(count==0){
                System.out.println("Borrow Limit Reached.\nCheckout and Modify Cart options are available.");
                continue;
            }
            System.out.print("\nSelect Genre:\n\n1. Science/Technology\n2. Personal Growth/Finance\n3. Classics\n4. Exams/Skills Oriented\n5. Science Fiction\n6. Biography/Literature/Historical\n7. Back\nEnter choice: ");
            try {
                choice = s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("\nInvalid entry, Try again.");
                continue;
            }
            s.nextLine();
            if(choice==7){
                System.out.println("\nReturning to Home Page.\n");
                return;
            }
            System.out.printf("%n%-40s | %-25s | %-15s%n","Title","Author","ISBN");
            System.out.println("-".repeat(84));
            switch(choice){
                case 1:
                    for(String name:templist1)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Title/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist1.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist1.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist1.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                case 2:
                    for(String name:templist2)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Name/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist2.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist2.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist2.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                case 3:
                    for(String name:templist3)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Name/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist3.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist3.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist3.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                case 4:
                    for(String name:templist4)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Name/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist4.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist4.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist4.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                case 5:
                    for(String name:templist5)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Name/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist5.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist5.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist5.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                case 6:
                    for(String name:templist6)
                        System.out.printf("%-40s | %-25s | %-15s%n",isbnbook.get(name)[0],isbnbook.get(name)[1],name);
                    System.out.println();
                    System.out.print(count+" borrows left.\n\n1. Borrow Book\n2. Back\nEnter choice: ");
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.");
                            continue;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("\nEnter the Name/ISBN of Book: ");
                                String name = s.nextLine();
                                if(templist6.contains(bookisbn.get(name)))
                                    name=bookisbn.get(name);
                                if(templist6.contains(name)){
                                    count--;
                                    cart.add(name);
                                    templist6.remove(name);
                                    System.out.println(isbnbook.get(name)[0]+" added to cart.\n");
                                }
                                else
                                    System.out.println("Invalid entry, Try again.\n");
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                break;
                        }
                    break;
                default:
                    System.out.println("\nInvalid entry, Try again.\n");
                    break;
            }
        }
    }

    public boolean modifycart(List<String> templist1,List<String> templist2,List<String> templist3,List<String> templist4,List<String> templist5,List<String> templist6,List<String> cart,int choice){
        boolean check = false;
        System.out.printf("%n%-40s | %-15s%n","Title","ISBN");
        System.out.println("-".repeat(58));
        for(String name:cart)
            System.out.printf("%-40s | %-15s%n",isbnbook.get(name)[0],name);
        System.out.print("\nEnter the Title/ISBN to the Book to remove: ");
        String name = s.nextLine();
        if(bookisbn.containsKey(name))
            name=bookisbn.get(name);
        if(cart.contains(name)){
            check = true;
            cart.remove(name);
            int genre = Integer.parseInt(isbnbook.get(name)[4]);
            switch(genre){
                case 1:
                    templist1.add(name);
                    break;
                case 2:
                    templist2.add(name);
                    break;
                case 3:
                    templist3.add(name);
                    break;
                case 4:
                    templist4.add(name);
                    break;
                case 5:
                    templist5.add(name);
                    break;
                case 6:
                    templist6.add(name);
                    break;
            }
            System.out.println(isbnbook.get(name)[0]+" Book removed from Cart.");
        }
        else
            System.out.println("Invalid entry, Try again.\n");
        return check;
    }

    public boolean checkoutpage(List<String> cart){
        LocalDate today = LocalDate.now();
        LocalDate target = today.plusDays(limit);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int count=3;
        String email="";
        while(count>0){
            System.out.println("\n/*User E-Mail ID: sanjay@gmail.com\nUser E-Mail ID 2: padmesh@gmail.com\nPassword: 1234*/\n");
            System.out.print("\nEnter E-Mail ID: ");
            String emailid = s.nextLine();
            if(!userauth.containsKey(emailid)){
                System.out.println("\nE-Mail ID doesn't exist.");
                count--;
                if(count!=0)
                    System.out.println(count+" attempts left. Try again.\n");
                else{
                    System.out.println("Session timout, Try again later.\n");
                    return true;
                }
            }
            else{
                email=emailid;
                break;
            }
        }
        count=3;
        while(count>0){
            System.out.print("Enter Password: ");
            String password = s.nextLine();
            if(!password.equals(userauth.get(email))){
                System.out.println("\nIncorrect Password.");
                count--;
                if(count!=0)
                    System.out.println(count+" attempts left. Try again.\n");
                else{
                    System.out.println("Session timout, Try again later.\n");
                    return true;
                }
            }
            else{
                System.out.println("\nLogged in Successfully.");
                break;
            }
        }
        if(cart.size()+userborrowcount.get(email)>borrowlimit){
            System.out.println("\nYou have already borrowed "+userborrowcount.get(email)+" books.\n");
            for(Map.Entry<String,List<String[]>> book:bookborrows.entrySet()){
                List<String[]> listofarray = book.getValue();
                for(String[] array:listofarray){
                    if(array[0].equals(email))
                        System.out.println(isbnbook.get(book.getKey())[0]);
                }
            }
            System.out.println("\nReturn "+(cart.size()+userborrowcount.get(email)-borrowlimit)+" books to borrow a new one.\n");
            return false;
        }
        for(String book:cart){
            if(bookborrows.containsKey(book)){
                List<String[]> listofarray = bookborrows.get(book);
                for(String[] array:listofarray){
                    if(array[0].equals(email)){
                        System.out.println("\nYou have already borrowed the book - "+isbnbook.get(book)[0]+". Hence, This book is removed from your cart.");
                        cart.remove(book);
                        System.out.print("\n1. Proceed to Checkout\n2. Go to Explore Page\nEnter choice: ");
                        int choice;
                        try {
                            choice = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.\n");
                            return true;
                        }
                        s.nextLine();
                        switch(choice){
                            case 1:
                                break;
                            case 2:
                                System.out.println("\nCart cleared. Returning to Explore Library.\n");
                                return false;
                            default:
                                System.out.println("\nInvalid entry, Try again.\n");
                                return true;
                        }
                    }
                }
            }
        }
        if(usersecdep.get(email)<minsecdep){
            System.out.println("\nInsufficient Security Deposit. Minimum Rs. "+minborsecdep+" is to be in Security Deposit for Borrowing.");
            count=3;
            int choice;
            whileloop:
            while(count>0){
                System.out.print("\nYour deposit balance is Rs. "+usersecdep.get(email)+"\nMinimum Rs. "+(minborsecdep-usersecdep.get(email))+" should be deposited to borrow.\n\n1. Deposit\n2. Go to Explore Page\nEnter choice: ");
                try {
                    choice = s.nextInt();
                } catch (InputMismatchException e) {
                    s.nextLine();
                    System.out.println("\nInvalid entry, Try again.\n");
                    continue;
                }
                s.nextLine();
                switch(choice){
                    case 1:
                        System.out.print("\nEnter the amount to be deposited: ");
                        int deposit;
                        try {
                            deposit = s.nextInt();
                        } catch (InputMismatchException e) {
                            s.nextLine();
                            System.out.println("\nInvalid entry, Try again.\n");
                            return true;
                        }
                        s.nextLine();
                        if(deposit<(minborsecdep-usersecdep.get(email))){
                            System.out.println("\nLow deposit amount. Try again.");
                            count--;
                            if(count!=0){
                                System.out.println(count+" attempts left. Try again.\n");
                                continue;
                            }
                            else{
                                System.out.println("Session timout, Try again later.\n");
                                return true;
                            }
                        }
                        else{
                            usersecdep.put(email,usersecdep.get(email)+deposit);
                            System.out.println("\nAmount Deposited.\n");
                            break whileloop;
                        }
                    case 2:
                        System.out.println("\nReturning to Explore Page.\n");
                        cart.clear();
                        mainpage();
                    default:
                        System.out.println("\nInvalid entry, Try again.\n");
                        break;
                }
            }
        }
        for(String books : cart){
            if(!bookborrows.containsKey(books))
                bookborrows.put(books,new ArrayList<>());
            bookborrows.get(books).add(new String[]{email,today.format(format),"0"});
            userborrowcount.put(email,userborrowcount.get(email)+1);
            if(!userborrowhistory.containsKey(email))
                userborrowhistory.put(email,new ArrayList<>());
            userborrowhistory.get(email).add(new String[]{books,today.format(format),"Not Returned"});
            allbooks.put(isbnbook.get(books)[0],allbooks.get(isbnbook.get(books)[0])-1);
            if(allbooks.get(isbnbook.get(books)[0])==0)
                bookgenre.values().forEach(list -> list.removeIf(isbn -> isbn.equals(books)));
            String[] details = isbnbook.get(books);
            details[2] = String.valueOf(Integer.parseInt(details[2])+1);
            details[3] = String.valueOf(Integer.parseInt(details[3])-1);
        }
        System.out.println("Books are registered in your Borrow History.\n\nReturn date is "+target.format(format)+".\nFines would be applied if not returned within respective dates.\n\nHappy Reading");
        return false;
    }
}