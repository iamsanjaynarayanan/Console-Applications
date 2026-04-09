import java.util.*;

public class AdminAccess {
    private Database db;
    private Scanner s = new Scanner(System.in);

    public AdminAccess(Database db){
        this.db = db;
    }

    public void adminpage(String adminid){
        Map<String,String> adminname = db.getadminname();
        while(true){
            System.out.println("Welcome Admin, "+adminname.get(adminid)+"\n\n1. User Account Management\n2. ATM Transaction History\n3. Cash Management\n4. Exit");
            System.out.print("Enter Choice: ");
            int n=0;
            try{
                n = s.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid entry. Try again.\n");
                System.out.println();
                continue;
            }
            s.nextLine();
            System.out.println();
            switch(n){
                case 1:
                    useraccountmanagement();
                    break;
                case 2:
                    atmtransactionhistory();
                    break;
                case 3:
                    cashmanagement();
                    break;
                case 4:
                    System.out.println("Thank you, "+adminname.get(adminid));
                    return;
                default:
                    System.out.println("Invalid entry, Please try again.");
            }
        }
    }

    public void useraccountmanagement(){
        Map<String,String> username = db.getusername();
        Map<String,String> userauth = db.getuserauth();
        Map<String,Boolean> userverify = db.getuserverify();
        Map<String,Long> useramount = db.getuseramount();
        List<List<String>> transaction = db.gettransaction();
        Long atmamount = db.getatmamount();
        while(true){
            System.out.print("User Account Management\n\n1. View User Accounts\n2. Add User Account\n3. Delete User Account\n4. User ATM Access\n5. Back\nEnter choice: ");
            int n=0;
            try{
                n = s.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid entry. Try again.\n");
                System.out.println();
                continue;
            }
            s.nextLine();
            System.out.println();
            switch(n){
                case 1:
                    int count=1;
                    System.out.println("Name - User ID\n");
                    for(Map.Entry<String,String> list:username.entrySet()){
                        System.out.println(list.getValue()+" - "+list.getKey());
                        count++;
                    }
                    System.out.println("\nTotal Accounts - "+(count-1)+"\n");
                    break;
                case 2:
                    System.out.print("Enter the Name: ");
                    String name = s.nextLine();
                    System.out.print("Enter the User ID: ");
                    String id = s.next();
                    if(userauth.containsKey(id)){
                        System.out.println("\nUser ID already exist. Try again.");
                        break;
                    }
                    System.out.print("Enter the PIN: ");
                    String pin = s.next();
                    System.out.print("Deposit Amount (Minimum Deposit Rs. 1000): ");
                    Long amount = s.nextLong();
                    if(amount<1000){
                        System.out.println("Low Deposit. Try again.");
                        continue;
                    }
                    username.put(id,name);
                    userauth.put(id,pin);
                    useramount.put(id,amount);
                    userverify.put(id,true);
                    atmamount += amount;
                    transaction.add(Arrays.asList(username.get(id),"Deposit",String.valueOf(amount)));
                    System.out.println("\nUser ID created successfully\n");
                    break;
                case 3:
                    System.out.print("Enter the User ID: ");
                    String uid = s.next();
                    if(!userauth.containsKey(uid)){
                        System.out.println("\nUser account doesn't exist. Try again.");
                        break;
                    }
                    userauth.remove(uid);
                    username.remove(uid);
                    useramount.remove(uid);
                    userverify.remove(uid);
                    System.out.println("User account deleted successfully\n");
                    break;
                case 4:
                    System.out.println("Accounts - Access\n");
                    for(Map.Entry<String,Boolean> list:userverify.entrySet()){
                        System.out.print(list.getKey()+" - ");
                        if(list.getValue())
                            System.out.println("Allowed");
                        else
                            System.out.println("Blocked");
                    }
                    System.out.print("\n1. Edit Access\n2. Back\nEnter choice: ");
                    int choice=s.nextInt();
                    System.out.println();
                    if(choice==2)
                        continue;
                    System.out.println("\nEnter the User ID to toggle the access: ");
                    String usid = s.next();
                    if(userverify.get(usid))
                        userverify.put(usid,false);
                    else
                        userverify.put(usid,true);
                    System.out.println("\nAccess modified successfully.\n");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid entry, Please try again.\n");
            }
        }
    }

    public void atmtransactionhistory(){
        List<List<String>> transaction = db.gettransaction();
        System.out.println("\nName - Transaction Type - Amount\n");
        for(List<String> lst:transaction)
            System.out.println(lst.get(0)+" - "+lst.get(1)+" - "+lst.get(2));
        System.out.println();
        System.out.println();
        return;
    }

    public void cashmanagement(){
        Long atmamount = db.getatmamount();
        System.out.print("Cash Management\n\n1. Load Cash\n2. Unload Cash\n3. ATM Balance\nEnter the choice: ");
        int n=0;
        try{
            n = s.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Invalid entry. Try again.\n");
            System.out.println();
            return;
        }
        switch(n){
            case 1:
                System.out.print("Enter the amount to be loaded: ");
                long load = s.nextLong();
                if(load<=0){
                    System.out.println("Invalid entry. Try again later.\n");
                    return;
                }
                atmamount+=load;
                System.out.println("\nCash loaded successfully\n");
                break;
            case 2:
                System.out.print("Enter the amount to be unloaded: ");
                long unload = s.nextLong();
                if(unload<=0){
                    System.out.println("Invalid entry. Try again later.\n");
                    return;
                }
                else if(unload>atmamount){
                    System.out.println("Insufficient Balance. Try again later.\n");
                    return;
                }
                else{
                    atmamount-=unload;
                    System.out.println("\nCash unloaded successfully\n");
                }
                break;
            case 3:
                System.out.println("ATM Balance: Rs. "+atmamount);
                break;
            default:
                System.out.println("Invalid entry. Try again.");
                break;
        }
    }
}