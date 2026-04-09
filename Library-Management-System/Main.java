import java.util.*;

public class Main{

    private static final Database db = new Database();
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args){
        long limit = db.getlimit();
        int increment = db.getincrement();
        int exponential = db.getexponential();
        int booklossfine = db.getbooklossfine();
        int bookreturnfine = db.getbookreturnfine();
        int minsecdep = db.getminsecdep();
        int minborsecdep = db.getminborsecdep();
        int membershipfine = db.getmembershipfine();
        int borrowlimit = db.getborrowlimit();
        Explore explore = new Explore();
        Users users = new Users();
        Admins admins = new Admins();
        while(true){
            System.out.print("\nWelcome to Library\n\n1. Explore Library\n2. Return Book\n3. Login\n4. Library's Regulations\n5. Exit\nEnter choice: ");
            int choice;
            try {
                choice = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid entry, Try again.");
                continue;
            }
            s.nextLine();
            switch(choice){
                case 1:
                    explore.mainpage();
                    break;
                case 2:
                    System.out.println("\n/*User E-Mail ID 1: sanjay@gmail.com\nUser E-Mail ID 2: padmesh@gmail.com\nPassword: 1234*/\n");
                    System.out.print("\nE-Mail ID: ");
                    String eid = s.nextLine();
                    if(db.getuserauth().containsKey(eid)){
                        System.out.print("Password: ");
                        String password = s.nextLine();
                        System.out.println();
                        if(password.equals(db.getuserauth().get(eid))){
                            users.returnbook(eid);
                            break;
                        }
                        else{
                            System.out.println("\nIncorrect Password, Try again.");
                            break;
                        }
                    }
                    else{
                        System.out.println("\nE-Mail ID doesn't exist, Try again.");
                        break;
                    }
                case 3:
                    login(users,admins);
                    break;
                case 4:
                    System.out.println("\nFine Regulations:\n\n1. Borrowed Books should be returned within "+limit+" Days. Otherwise, Fines applied (Return Days Limit)\n2. When return date passed, Increment fine Rs. "+increment+" will be added to fine, per day until the fines are settled.(Increment Fine per Day)\n3. When the increment fines apply, for every "+exponential+" Days, the increment fine doubles exponentially, until the fines are settled.(Exponential Increment of Days)\n4. "+booklossfine+"% of Book Cost should be paid when a Book is Lost.(Book Loss Fine)\n5. Minimum of "+bookreturnfine+"% of Book Cost and Total fine for a book can be paid as fine.(Book Return Fine)\n6. Rs. "+minsecdep+" should be paid as Security Deposit at User Account Creation.(Minimum Security Deposit)\n7. Rs. "+minborsecdep+" should be atleast retained to borrow a book.(Minimum Deposit for Borrowing Book)\n8. "+borrowlimit+" Books can be borrowed at a time. If user have already borrowed and not returned, it applies.\n9. Rs. "+membershipfine+" for Membership Card Loss.\n\nReturning back.\n");
                    break;
                case 5:
                    System.out.println("\nThank you, visit again.");
                    return;
                default:
                    System.out.println("\nInvalid entry, Try again.");
                    break;
            }
        }
    }

    public static void login(Users users, Admins admins){
        System.out.println("\n/*User E-Mail ID 1: sanjay@gmail.com\n User E-Mail ID 2: padmesh@gmail.com\nAdmin E-Mail ID 1: venkat@gmail.com\nAdmin E-Mail ID 2: suda@gmail.com\nPassword: 1234*/\n");
        System.out.print("\nE-Mail ID: ");
        String eid = s.nextLine();
        if(db.getuserauth().containsKey(eid)){
            System.out.print("Password: ");
            String password = s.nextLine();
            if(password.equals(db.getuserauth().get(eid)))
                users.userpage(eid);
            else
                System.out.println("\nIncorrect Password, Try again.");
        }
        else if(db.getadminauth().containsKey(eid)){
            System.out.print("Password: ");
            String password = s.nextLine();
            if(password.equals(db.getadminauth().get(eid)))
                admins.adminpage(eid);
            else
                System.out.println("\nIncorrect Password, Try again.");
        }
        else
            System.out.println("\nE-Mail ID doesn't exist, Try again.");
    }
}
