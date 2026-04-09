import java.util.*;

public class UserAccess{
    private Database db;
    private Scanner s = new Scanner(System.in);

    public UserAccess(Database db){
        this.db = db;
    }

    public void userpage(String userid){
        Map<String,String> username = db.getusername();
        while(true){
            System.out.println("Welcome User, "+username.get(userid)+"\n\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. Change PIN\n5. Remove Card");
            System.out.print("Enter Choice: ");
            int n=0;
            try{
                n = s.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid entry. Try again.\n");
                continue;
            }
            s.nextLine();
            System.out.println();
            Map<String,Boolean> userverify = db.getuserverify();
            switch(n){
                case 1:
                    if(userverify.get(userid))
                        withdraw(userid);
                    else
                        System.out.println("Your account is blocked for transaction for 24hrs. Try again later.");
                    break;
                case 2:
                    if(userverify.get(userid))
                        deposit(userid);
                    else
                        System.out.println("Your account is blocked for transaction for 24hrs. Try again later.");
                    break;
                case 3:
                    checkbalance(userid);
                    break;
                case 4:
                    changepin(userid);
                    break;
                case 5:
                    System.out.println("Thank you, "+username.get(userid));
                    return;
                default:
                    System.out.println("Invalid entry, Please try again.");
            }
        }
    }

    public boolean verifypin(String userid){
        Map<String,String> userauth = db.getuserauth();
        int count = 3;
        while(count>0){
            System.out.print("Enter the PIN: ");
            String inppin = s.next();
            System.out.println();
            if(userauth.get(userid).equals(inppin))
                return true;
            else{
                count--;
                System.out.println("Incorrect PIN, "+count+" attempts left.\n");
            }
        }
        System.out.println("Your account is blocked for 24 hrs. Try again later.");
        return false;
    }

    public void withdraw(String userid){
        Map<String,Long> useramount = db.getuseramount();
        Map<String,String>username = db.getusername();
        Map<String,Boolean> userverify = db.getuserverify();
        List<List<String>> transaction = db.gettransaction();
        Long atmamount = db.getatmamount();
        System.out.print("Enter the amount: ");
        long withamount;
        try{
            withamount = s.nextLong();
        }
        catch(NumberFormatException e){
            System.out.println("\nInvalid entry, Try again.\n");
            return;
        }
        catch(InputMismatchException e){
            System.out.println("\nInvalid entry, Try again.\n");
            return;
        }
        long balance = useramount.get(userid);
        if(withamount<=0 || withamount%100!=0){
            System.out.println("Invalid entry, Try again");
            return;
        }
        else if(balance<withamount){
            System.out.println("Insufficient Balance, Try again");
            return;
        }
        else{
            if(!verifypin(userid)){
                userverify.put(userid,false);
                return;
            }
            else{
                useramount.put(userid,useramount.get(userid)-withamount);
                atmamount -= withamount;
                transaction.add(Arrays.asList(username.get(userid),"Withdraw",String.valueOf(withamount)));
                System.out.println("Please collect cash. Rs. "+withamount+".\n");
                return;
            }
        }
    }

    public void deposit(String userid){
        Map<String,Long> useramount = db.getuseramount();
        Map<String,String> username = db.getusername();
        Map<String,Boolean> userverify = db.getuserverify();
        List<List<String>> transaction = db.gettransaction();
        Long atmamount = db.getatmamount();
        System.out.print("Enter the amount: ");
        Long depamount;
        try {
            depamount=s.nextLong();
        }
        catch (InputMismatchException e) {
            System.out.println("\nInvalid entry, Try again.\n");
            return;
        }
        catch (NumberFormatException e) {
            System.out.println("\nInvalid entry, Try again.\n");
            return;
        }
        if(depamount<=0 || depamount%100!=0){
            System.out.println("Invalid entry, Try again");
            return;
        }
        else{
            if(!verifypin(userid)){
                userverify.put(userid,false);
                return;
            }
            else{
                useramount.put(userid,useramount.get(userid)+depamount);
                atmamount += depamount;
                transaction.add(Arrays.asList(username.get(userid),"Deposit",String.valueOf(depamount)));
                System.out.println("Rs. "+depamount+" is deposited.\n");
                return;
            }
        }
    }

    public void checkbalance(String userid){
        Map<String,Long> useramount = db.getuseramount();
        Map<String,Boolean> userverify = db.getuserverify();
        if(!verifypin(userid)){
            userverify.put(userid,false);
            return;
        }
        else{
            System.out.println("Your balance is Rs. "+useramount.get(userid)+".\n");
            return;
        }
    }

    public void changepin(String userid){
        Map<String,String> userauth = db.getuserauth();
        System.out.print("Enter the New PIN: ");
        String newpin = s.nextLine();
        if(newpin.equals(userauth.get(userid))){
            System.out.println("The PIN is same. Try again.");
            System.out.println();
            return;
        }
        int count=3;
        while(count>0){
            System.out.print("Re-enter the New PIN: ");
            String newpinconfirm = s.nextLine();
            if(newpin.equals(newpinconfirm)){
                userauth.put(userid,newpin);
                System.out.println("PIN changed successfully.");
                System.out.println();
                return;
            }
            else{
                System.out.println("Incorrect PIN. Try again.");
                System.out.println();
                count--;
            }
            if(count==0){
                System.out.println("Your session ended. Try again later.");
                System.out.println();
                return;
            }
        }
    }
}