import java.util.*;
public class Main{
    public static void main(String[] args){
        Database database = new Database();
        AdminAccess admin = new AdminAccess(database);
        UserAccess user = new UserAccess(database);
        Scanner s = new Scanner(System.in);
        Map<String,String> userauth = database.getuserauth();
        Map<String,String> adminauth = database.getadminauth();
        while(true){
            System.out.println("\nWelcome to ATM\n");
            System.out.println("\n/* User: sanjay123\n   Admin: venkat123\n   PIN: 1234 */\n");
            System.out.print("Insert Card (Enter User ID): ");
            String inpid = s.next();
            if(userauth.containsKey(inpid)){
                System.out.println("Logged in successfully.\n");
                user.userpage(inpid);
            }
            else if(adminauth.containsKey(inpid)){
                System.out.print("Enter the Password: ");
                String adminpassword = s.next();
                if(!adminpassword.equals(adminauth.get(inpid))){
                    System.out.println("Incorrect Password. Try again.\n");
                    continue;
                }
                System.out.println("\nLogged in successfully.\n");
                admin.adminpage(inpid);
            }
            else
                System.out.println("User ID doesn't exist, Try again.");
            System.out.println();
        }
    }
}