import java.util.*;
public class Database {
    public static Map<String,String> userauth = new HashMap<>();
    public static Map<String,String> adminauth = new HashMap<>();
    public static Map<String,String> username = new HashMap<>();
    public static Map<String,String> adminname = new HashMap<>();
    public static Map<String,Long> useramount = new HashMap<>();
    public static Map<String,Boolean> userverify = new HashMap<>();
    public static List<List<String>> transaction = new ArrayList<>();
    public static Long atmamount;

    public Database(){
        userauth.put("sanjay123","1234");
        useramount.put("sanjay123",10000L);
        userverify.put("sanjay123",true);
        adminauth.put("venkat123","1234");
        username.put("sanjay123","Sanjay Narayanan V");
        adminname.put("venkat123","Venkatraman V");
        transaction.add(Arrays.asList("sanjay123","Deposit","10000"));
        atmamount = 1000000L;
    }

    public Map<String,String> getuserauth(){
        return userauth;
    }

    public Map<String,String> getadminauth(){
        return adminauth;
    }

    public Map<String,Long> getuseramount(){
        return useramount;
    }

    public Map<String,String> getadminname(){
        return adminname;
    }
    
    public Map<String,Boolean> getuserverify(){
        return userverify;
    }

    public Map<String,String> getusername(){
        return username;
    }

    public List<List<String>> gettransaction(){
        return transaction;
    }

    public Long getatmamount(){
        return atmamount;
    }
}