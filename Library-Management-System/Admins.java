import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Admins {

    private final Scanner s = new Scanner(System.in);

    private final Map<String,String[]> isbnbook;
    private final Map<String,String> bookisbn;
    private final Map<Integer,List<String>> bookgenre;
    private final TreeMap<String,Integer> allbooks;
    private final Map<String,List<String[]>> bookborrows;
    private final Map<String,String> userauth;
    private final Map<String,String> usernames;
    private final Map<String,Integer> userborrowcount;
    private final Map<String,List<String[]>> userborrowhistory;
    private final Map<String,Integer> usersecdep;
    private final Map<String,String> adminauth;
    private final Map<String,String> adminnames;
    private final Map<String,List<String>> userfines;
    private long limit;
    private int increment;
    private int exponential;
    private int minsecdep;
    private int minborsecdep;
    private int booklossfine;
    private int bookreturnfine;
    private int borrowlimit;
    private int membershipfine;

    public Admins(){
        Database db = new Database();
        this.isbnbook=db.getisbnbook();
        this.bookisbn=db.getbookisbn();
        this.bookgenre=db.getbookgenre();
        this.allbooks=db.getallbooks();
        this.bookborrows=db.getbookborrows();
        this.userauth=db.getuserauth();
        this.usernames=db.getusernames();
        this.userborrowcount=db.getuserborrowcount();
        this.userborrowhistory=db.getuserborrowhistory();
        this.usersecdep=db.getusersecdep();
        this.adminauth=db.getadminauth();
        this.adminnames=db.getadminnames();
        this.userfines=db.getuserfines();
        this.limit=db.getlimit();
        this.exponential=db.getexponential();
        this.minsecdep=db.getminsecdep();
        this.minborsecdep=db.getminborsecdep();
        this.booklossfine=db.getbooklossfine();
        this.bookreturnfine=db.getbookreturnfine();
        this.borrowlimit=db.getborrowlimit();
        this.increment=db.getincrement();
        this.membershipfine=db.getmembershipfine();
    }

    public void adminpage(String adminid){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while(true){
            System.out.print("\nWelcome Admin, "+adminnames.get(adminid)+". \n\n1. Book Management\n2. Account Management\n3. Users Information\n4. Fine Management\n5. Exit\nEnter choice: ");
            int choice;
            try {
                choice = s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("\nInvalid entry, Try again.\n;");
                continue;
            }
            s.nextLine();
            switch(choice){
                case 1:
                    exteriorloop:
                    while(true){
                    System.out.print("\nBook Management\n\n1. View All Books\n2. Search a Book\n3. Add a new Book\n4. Modify Book Quantity\n5. Delete Book\n6. Most read Books\n7. Least read Books\n8. Left Out Books\n9. Back\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n;");
                        break;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            interiorloop:
                            while(true){
                            System.out.println("\nBooks List\n\n1. Sort by Genre\n2. Sort by Name\n3. Sort by Quantity\n4. Back\nEnter choice: ");
                            try {
                                choice = s.nextInt();
                            } catch (InputMismatchException e) {
                                s.nextLine();
                                System.out.println("\nInvalid entry, Try again.\n;");
                                continue;
                            }
                            s.nextLine();
                            switch(choice){
                                case 1:
                                    System.out.println("\nBooks sorted by Genre\n");
                                    for(Map.Entry<Integer,List<String>> genrelist:bookgenre.entrySet()){
                                        if(genrelist.getKey()==1)
                                            System.out.println("Science/Technology\n");
                                        else if(genrelist.getKey()==2)
                                            System.out.println("Personal Growth/Finance\n");
                                        else if(genrelist.getKey()==3)
                                            System.out.println("Classics\n");
                                        else if(genrelist.getKey()==4)
                                            System.out.println("Exams/Skills Oriented\n");
                                        else if(genrelist.getKey()==5)
                                            System.out.println("Science Fiction\n");
                                        else
                                            System.out.println("Biography/Literature/Historical\n");
                                        List<String> listofbooks = genrelist.getValue();
                                        System.out.printf("%-40s | %-4s%n","Title","Quantity");
                                        System.out.println("-".repeat(47));
                                        for(String books:listofbooks)
                                            System.out.printf("%-40s | %-4s%n",isbnbook.get(books)[0],allbooks.get(isbnbook.get(books)[0]));
                                        System.out.println();
                                    }
                                    break;
                                case 2:
                                    System.out.println("\nBooks sorted by Name\n");
                                    System.out.printf("%-40s | %-4s%n","Title","Quantity");
                                    System.out.println("-".repeat(47));
                                    for(Map.Entry<String,Integer> namesort:allbooks.entrySet())
                                        System.out.printf("%-40s | %-4s%n",namesort.getKey(),namesort.getValue());
                                    break;
                                case 3:
                                    System.out.println("\nBooks sorted by Name\n");
                                    System.out.printf("%-40s | %-4s%n","Title","Quantity");
                                    System.out.println("-".repeat(47));
                                    allbooks.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> System.out.printf("%-40s | %-4s%n",entry.getKey(),entry.getValue()));
                                    break;
                                case 4:
                                    System.out.println("Returning back.");
                                    break interiorloop;
                                default:
                                    System.out.println("\nInvalid entry, Try again.\n");
                                    break;
                            }
                            }
                            break;
                        case 2:
                            System.out.print("\nSearch a Book\n\nEnter the Name/ISBN of Book: ");
                            String name = s.nextLine();
                            System.out.println();
                            if(allbooks.containsKey(name))
                                name=bookisbn.get(name);
                            if(isbnbook.containsKey(name)){
                                System.out.println("Book Name: "+isbnbook.get(name)[0]);
                                System.out.println("Author: "+isbnbook.get(name)[1]);
                                if(isbnbook.get(name)[4].equals("1"))
                                    System.out.println("Genre: Science/Technology");
                                else if(isbnbook.get(name)[4].equals("2"))
                                    System.out.println("Genre: Personal Growth/Finance");
                                else if(isbnbook.get(name)[4].equals("3"))
                                    System.out.println("Genre: Classics");
                                else if(isbnbook.get(name)[4].equals("4"))
                                    System.out.println("Genre: Exams/Skills Oriented");
                                else if(isbnbook.get(name)[4].equals("5"))
                                    System.out.println("Genre: Science Fiction");
                                else
                                    System.out.println("Genre: Biography/Literature/Historical");
                                System.out.println("Quantity: "+isbnbook.get(name)[3]);
                                System.out.println("Total Borrows: "+isbnbook.get(name)[2]);
                                System.out.println("Book Price: Rs. "+isbnbook.get(name)[5]+"\n");
                                if(bookborrows.containsKey(name)){
                                    System.out.println("Current Borrowed Users: ");
                                    List<String[]> listofusers = bookborrows.get(name);
                                    for(String[] array:listofusers){
                                        System.out.println("Name: "+usernames.get(array[0]));
                                        System.out.println("Borrowed Date: "+array[1]);
                                        LocalDate borrowdate = LocalDate.parse(array[1],format);
                                        LocalDate target = borrowdate.plusDays(15L);
                                        System.out.println("Return Date: "+target.format(format));
                                        System.out.println("Tenure Extended: "+array[2]+"\n");
                                    }
                                    System.out.println();
                                }
                                else
                                    System.out.println("No current borrows.");
                            }
                            else
                                System.out.println("Invalid Name/ISBN, Try again.");
                            break;
                        case 3:
                            System.out.print("\nAdd a Book\n\nEnter the Book Name: ");
                            String bookname = s.nextLine();
                            System.out.print("Enter the Author Name: ");
                            String bookauthor = s.nextLine();
                            System.out.print("Enter the ISBN: ");
                            String isbnofbook = s.nextLine();
                            System.out.print("Enter the Genre Number from given below\n\n1. Science/Technology\n2. Personal Growth/Finance\n3. Classics\n4. Exams/Skills Oriented\n5. Science Fiction\n6. Biography/Literature/Historical\nEnter choice: ");
                            int genreofbook = s.nextInt();
                            s.nextLine();
                            System.out.print("\nEnter the Quantity: ");
                            int bookquantity = s.nextInt();
                            s.nextLine();
                            System.out.print("Enter the Book Price: ");
                            String bookprice = s.nextLine();
                            isbnbook.put(isbnofbook,new String[]{bookname,bookauthor,"0",String.valueOf(bookquantity),String.valueOf(genreofbook),bookprice});
                            bookisbn.put(bookname,isbnofbook);
                            bookgenre.get(genreofbook).add(isbnofbook);
                            allbooks.put(bookname,bookquantity);
                            System.out.println("\nBook added successfully.");
                            break;
                        case 4:
                            System.out.print("\nModify a Book Quanity\n\nEnter the Name/ISBN of Book: ");
                            name = s.nextLine();
                            if(isbnbook.containsKey(name))
                                name=isbnbook.get(name)[0];
                            if(allbooks.containsKey(name)){
                                System.out.print("\nBook Title: "+isbnbook.get(name)[0]+"\nBook Quantity: "+isbnbook.get(name)[3]+"\n\nDo you want to modify the book quantity?\n1. Yes\n2. No\nEnter choice: ");
                                try {
                                    choice=s.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("\nInvalid entry, Try again.");
                                    break;
                                }
                                switch(choice){
                                    case 1:
                                        break;
                                    case 2:
                                        System.out.println("\nReturning back.");
                                        continue;
                                }
                                System.out.print("\nEnter the new Quantity: ");
                                int quantity;
                                try {
                                    quantity = s.nextInt();
                                } catch (InputMismatchException e) {
                                    s.nextLine();
                                    System.out.println("\nInvalid entry, Try again.\n");
                                    break;
                                }
                                s.nextLine();
                                allbooks.put(name,quantity);
                                System.out.println("\nQuantity changed successfully.\n");
                            }
                            else
                                System.out.println("\nNo such Book is available.");
                            break;
                        case 5:
                            System.out.print("\nDelete a Book\n\nEnter the Name/ISBN of Book: ");
                            name = s.nextLine();
                            if(bookisbn.containsKey(name))
                                name=bookisbn.get(name);
                            if(isbnbook.containsKey(name)){
                                System.out.print("\nBook Title: "+isbnbook.get(name)[0]+"\nBook Quantity: "+isbnbook.get(name)[3]+"\n\nDo you want to delete the book?\n1. Yes\n2. No\nEnter choice: ");
                                try {
                                    choice=s.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("\nInvalid entry, Try again.");
                                    break;
                                }
                                switch(choice){
                                    case 1:
                                        break;
                                    case 2:
                                        System.out.println("\nReturning back.");
                                        continue;
                                }
                                bookisbn.remove(isbnbook.get(name)[0]);
                                bookgenre.get(Integer.parseInt(isbnbook.get(name)[4])).remove(name);
                                allbooks.remove(isbnbook.get(name)[0]);
                                isbnbook.remove(name);
                                System.out.println("\nBook deleted successfully.");
                            }
                            else
                                System.out.println("\nNo such Book is available.");
                            break;
                        case 7:
                            System.out.println("\nLeast Read Books\n");
                            System.out.printf("%-40s | %-4s%n","Title","Borrow Count");
                            System.out.println("-".repeat(47));
                            isbnbook.values().stream().filter(data -> !data[2].equals("0")).sorted(Comparator.comparingInt(data -> Integer.parseInt(data[2]))).forEach(data -> System.out.printf("%-40s | %-4s%n",data[0],data[2]));
                            break;
                        case 6:
                            System.out.println("\nMost Read Books\n");
                            System.out.printf("%-40s | %-4s%n","Title","Borrow Count");
                            System.out.println("-".repeat(47));
                            isbnbook.values().stream().filter(data -> !data[2].equals("0")).sorted(Comparator.comparingInt((String[] data) -> Integer.parseInt(data[2])).reversed()).forEach(data -> System.out.printf("%-40s | %-4s%n",data[0],data[2]));
                            break;
                        case 8:
                            System.out.println("\nLeft out Books\n");
                            for(Map.Entry<String,String[]> array:isbnbook.entrySet()){
                                if(array.getValue()[2].equals("0"))
                                    System.out.println(array.getValue()[0]);
                            }
                            break;
                        case 9:
                            System.out.println("\nReturning back.\n");
                            break exteriorloop;
                        default:
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                    }
                    }
                    break;
                case 2:
                    interiorloop:
                    while(true){
                    System.out.print("\nAccount Management\n\n1. View User Accounts\n2. View Admin Accounts\n3. Add a User\n4. Add an Admin\n5. Back\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n;");
                        break;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            System.out.println("\nViewing User Accounts\n");
                            for(Map.Entry<String,String>list:usernames.entrySet())
                                System.out.println(list.getValue()+" - "+list.getKey());
                            break;
                        case 2:
                            System.out.println("\nViewing Admin Accounts\n");
                            for(Map.Entry<String,String>list:adminnames.entrySet())
                                System.out.println(list.getValue()+" - "+list.getKey());
                            break;
                        case 3:
                            System.out.print("Enter Full Name: ");
                            String name = s.nextLine();
                            System.out.print("Enter E-Mail ID: ");
                            String emailid = s.nextLine();
                            if(userauth.containsKey(emailid)){
                                System.out.println("\nUser Account already exist. Try again.\n");
                                break;
                            }
                            System.out.print("Enter Password: ");
                            String pass = s.nextLine();
                            System.out.print("\nMinimum Rs. "+minsecdep+" to be deposit at User Account Creation.\nEnter the Amount to deposit: ");
                            int amount = s.nextInt();
                            s.nextLine();
                            if(amount<minsecdep){
                                System.out.println("\nLow Deposit. Try again.");
                                break;
                            }
                            userauth.put(emailid,pass);
                            userborrowcount.put(emailid,0);
                            usernames.put(emailid,name);
                            usersecdep.put(emailid,amount);
                            userborrowhistory.put(emailid,new ArrayList<>());
                            userfines.put(emailid,new ArrayList<>());
                            System.out.println("\nUser Account has been created.");
                            break;
                        case 4:
                            System.out.print("\nEnter Full Name: ");
                            String adname = s.nextLine();
                            System.out.print("Enter E-Mail ID: ");
                            String ademailid = s.nextLine();
                            if(adminauth.containsKey(ademailid)){
                                System.out.println("\nAdmin Account already exist. Try again.\n");
                                break;
                            }
                            System.out.print("\nEnter Password: ");
                            String adpass = s.nextLine();
                            adminauth.put(ademailid,adpass);
                            adminnames.put(ademailid,adname);
                            System.out.println("\nAdmin Account has been created.");
                            break;
                        case 5:
                            System.out.println("\nReturning back.");
                            break interiorloop;
                        default:
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                    }
                    }
                    break;
                case 3:
                    interiorloop:
                    while(true){
                    System.out.print("\nUsers Information\n\n1. View All Users\n2. Most Borrowed Users\n3. Unreturned Users\n4. User Borrow History\n5. Users and their Fines\n6. Back\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n;");
                        break;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            System.out.println("\nViewing User Accounts\n");
                            System.out.printf("%-20s | %-20s%n","Name","E-Mail ID");
                            System.out.println("-".repeat(43));
                            for(Map.Entry<String,String>list:usernames.entrySet())
                                System.out.printf("%-20s | %-20s%n",list.getValue(),list.getKey());
                            break;
                        case 2:
                            System.out.println("\nMost Borrowed Users\n");
                            System.out.printf("%-20s | %-10s%n","Name","Borrow Count");
                            System.out.println("-".repeat(33));
                            userborrowhistory.entrySet().stream().sorted((user1, user2) -> Integer.compare(user2.getValue().size(), user1.getValue().size())).forEach(entry -> {System.out.printf("%-20s | %-10s%n",usernames.get(entry.getKey()),entry.getValue().size());});
                            break;
                        case 3:
                            System.out.println("\nUsers those didn't returned the borrowed books\n");
                            System.out.printf("%-20s | %-40s%n","Users","Unreturned Books");
                            System.out.println("-".repeat(63));
                            for(Map.Entry<String,List<String[]>> listofusers:userborrowhistory.entrySet()){
                                List<String[]> array = listofusers.getValue();
                                for(String[] arr:array){
                                    if(arr[2].equals("Not Returned"))
                                        System.out.printf("%-20s | %-40s%n",usernames.get(listofusers.getKey()),isbnbook.get(arr[0])[0]);
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\nUser Borrow History\n");
                            for(Map.Entry<String,List<String[]>> listofusers:userborrowhistory.entrySet()){
                                System.out.println("Name: "+usernames.get(listofusers.getKey())+"\n");
                                System.out.printf("%-40s | %-10s | %-13s%n","Borrowed Book","Borrowed Date","Return Status");
                                System.out.println("-".repeat(69));
                                List<String[]> array = listofusers.getValue();
                                for(String[] arr:array)
                                    System.out.printf("%-40s | %-10s | %-13s%n",isbnbook.get(arr[0])[0],arr[1],arr[2]);
                                System.out.println();
                            }
                            break;
                        case 5:
                            System.out.println("\nUsers and their fines\n");
                            for(Map.Entry<String,List<String>> listoffines:userfines.entrySet()){
                                System.out.println("Name: "+usernames.get(listoffines.getKey())+"\n");
                                List<String> list = listoffines.getValue();
                                for(String str:list)
                                    System.out.println(str);
                                System.out.println();
                            }
                            break;
                        case 6:
                            System.out.println("\nReturning back.\n");
                            break interiorloop;
                        default:
                            System.out.println("\nInvalid entry, Try again.\n");
                            break;
                    }
                    }
                    break;
                case 4:
                    interiorloop:
                    while(true){
                    System.out.print("\nFine Management\n\n1. View Fine Regulations\n2. Change Fine Regulations\n3. Back\nEnter choice: ");
                    try {
                        choice = s.nextInt();
                    } catch (InputMismatchException e) {
                        s.nextLine();
                        System.out.println("\nInvalid entry, Try again.\n;");
                        break;
                    }
                    s.nextLine();
                    switch(choice){
                        case 1:
                            System.out.println("\nFine Regulations:\n\n1. Borrowed Books should be returned within "+limit+" Days. Otherwise, Fines applied (Return Days Limit)\n2. When return date passed, Increment fine Rs. "+increment+" will be added to fine, per day until the fines are settled.(Increment Fine per Day)\n3. When the increment fines apply, for every "+exponential+" Days, the increment fine doubles exponentially, until the fines are settled.(Exponential Increment of Days)\n4. "+booklossfine+"% of Book Cost should be paid when a Book is Lost.(Book Loss Fine)\n5. Minimum of "+bookreturnfine+"% of Book Cost and Total fine for a book can be paid as fine.(Book Return Fine)\n6. Rs. "+minsecdep+" should be paid as Security Deposit at User Account Creation.(Minimum Security Deposit)\n7. Rs. "+minborsecdep+" should be atleast retained to borrow a book.(Minimum Deposit for Borrowing Book)\n8. "+borrowlimit+" Books can be borrowed at a time. If user have already borrowed and not returned, it applies.\n9. Rs. "+membershipfine+" for Membership Card Loss.\n\nReturning back.\n");
                            break;
                        case 2:
                            interiorloop2:
                            while(true){
                            System.out.print("\nChange Fine Regulations\n\n1. Return Days Limit\n2. Increment fine per day\n3. Exponential increment of days\n4. Book Lost Fine Percentage\n5. Book Return Fine Percentage\n6. Minimum Security Deposit\n7. Minimum Deposit for borrowing\n8. Book Borrow Limit\n9. Membership Card Lost Fine\n10. Back\nEnter choice: ");
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
                                    System.out.print("\nReturn Days Limit: "+limit+"\nEnter new Return Days Limit: ");
                                    limit = s.nextLong();
                                    s.nextLine();
                                    System.out.println("\nChanged successfully.\n");
                                    break;
                                case 2:
                                    System.out.print("\nIncrement Fine per day: Rs. "+increment+"\nEnter new Increment Fine per day: ");
                                    increment = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 3:
                                    System.out.print("\nExponential Increment of days: "+exponential+"\nEnter new Exponential Increment of days: ");
                                    exponential = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 4:
                                    System.out.print("\nBook Lost Fine %: "+booklossfine+"\nEnter new Book Loss Fine %: ");
                                    booklossfine = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 5:
                                    System.out.print("\nBook Return Fine %: "+bookreturnfine+"\nEnter new Book Return Fine %: ");
                                    bookreturnfine = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 6:
                                    System.out.print("\nMinimum Security Deposit Rs: "+minsecdep+"\nEnter new Minimum Security Deposit Rs: ");
                                    minsecdep = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 7:
                                    System.out.print("\nMinimum Deposit for borrowing Rs: "+minborsecdep+"\nEnter new Minimum Deposit for borrowing Rs: ");
                                    minborsecdep = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 8:
                                    System.out.print("\nBook Borrowing Limit is "+borrowlimit+"\nEnter new Book Borrowing Limit : ");
                                    borrowlimit = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                    break;
                                case 9:
                                    System.out.print("\nMembership Card Loss Fine is Rs. "+membershipfine+"\nEnter new Membership Card Loss Fine Rs: ");
                                    membershipfine = s.nextInt();
                                    s.nextLine();
                                    System.out.println("\nChanged Successfully.\n");
                                case 10:
                                    System.out.println("\nReturning back.\n");
                                    break interiorloop2;
                                default:
                                    System.out.println("\nInvalid entry, Try again.\n");
                                    break;
                            }
                            }
                            break;
                        case 3:
                            System.out.println("\nReturning back.");
                            break interiorloop;
                        default:
                            System.out.println("\nInvalid entry, Try again.");
                            break;
                    }
                    }
                    break;
                case 5:
                    System.out.println("\nThank you Admin, "+adminnames.get(adminid)+"\n");
                    return;
                default:
                    System.out.println("\nInvalid entry, Try again.\n");
                    break;
            }
        }
    }
}
