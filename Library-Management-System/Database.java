import java.util.*;

public class Database {

    public static final Map<String,String[]> isbnbook = new HashMap<>();
    public static final Map<String,String> bookisbn = new HashMap<>();
    public static final Map<Integer,List<String>> bookgenre = new HashMap<>();
    public static final TreeMap<String,Integer> allbooks = new TreeMap<>();
    public static final Map<String,List<String[]>> bookborrows = new HashMap<>();
    public static final Map<String,String> userauth = new HashMap<>();
    public static final Map<String,Integer> userborrowcount = new HashMap<>();
    public static final Map<String,List<String[]>> userborrowhistory = new HashMap<>();
    public static final Map<String,Integer> usersecdep = new HashMap<>();
    public static final Map<String,String> adminauth = new HashMap<>();
    public static final Map<String,String> usernames = new HashMap<>();
    public static final Map<String,String> adminnames = new HashMap<>();
    public static final Map<String,List<String>> userfines = new HashMap<>();
    public static long limit;
    public static int increment;
    public static int exponential;
    public static int minsecdep;
    public static int minborsecdep;
    public static int booklossfine;
    public static int bookreturnfine;
    public static int membershipfine;
    public static int borrowlimit;

    static{
        isbnbook.put("9785842106366", new String[]{"Introduction to Algorithms", "Thomas H. Cormen", "2","15","1","500"});
        isbnbook.put("0574163907", new String[]{"Clean Code", "Robert C. Martin", "0","45","1","450"});
        isbnbook.put("9781146740685", new String[]{"Database System Concepts", "Abraham Silberschatz", "0","12","1","600"});
        isbnbook.put("4552438622", new String[]{"Rich Dad Poor Dad", "Robert Kiyosaki", "1","83","2","750"});
        isbnbook.put("9789314417105", new String[]{"The Psychology of Money", "Morgan Housel", "1","77","2","1000"});
        isbnbook.put("0794101831", new String[]{"The Intelligent Investor", "Benjamin Graham", "1","20","2","950"});
        isbnbook.put("9782806283595", new String[]{"1984", "George Orwell", "1","38","3","1250"});
        isbnbook.put("3185347913", new String[]{"To Kill a Mockingbird", "Harper Lee", "0","32","3","1000"});
        isbnbook.put("9787130541243", new String[]{"The Great Gatsby", "F. Scott Fitzgerald", "0","49","3","850"});
        isbnbook.put("6387063162", new String[]{"Quantitative Aptitude", "R.S. Aggarwal", "0","69","4","600"});
        isbnbook.put("9783852026118", new String[]{"Word Power Made Easy", "Norman Lewis", "0","56","4","850"});
        isbnbook.put("6508930412", new String[]{"Manorama Yearbook 2024", "Mammen Mathew", "0","80","4","450"});
        isbnbook.put("9781845185412", new String[]{"Dune", "Frank Herbert", "1","73","5","1500"});
        isbnbook.put("041076935X", new String[]{"Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1","90","5","1650"});
        isbnbook.put("9782502693710", new String[]{"The Fellowship of the Ring", "J.R.R. Tolkien", "0","54","5","1450"});
        isbnbook.put("2481065792", new String[]{"Wings of Fire", "A.P.J. Abdul Kalam", "1","89","6","550"});
        isbnbook.put("9781156820542", new String[]{"The Diary of a Young Girl", "Anne Frank", "0","56","6","675"});
        isbnbook.put("4056285413", new String[]{"Sapiens", "Yuval Noah Harari", "0","96","6","500"});

        bookisbn.put("Introduction to Algorithms", "9785842106366");
        bookisbn.put("Clean Code", "0574163907");
        bookisbn.put("Database System Concepts", "9781146740685");
        bookisbn.put("Rich Dad Poor Dad", "4552438622");
        bookisbn.put("The Psychology of Money", "9789314417105");
        bookisbn.put("The Intelligent Investor", "0794101831");
        bookisbn.put("1984", "9782806283595");
        bookisbn.put("To Kill a Mockingbird", "3185347913");
        bookisbn.put("The Great Gatsby", "9787130541243");
        bookisbn.put("Quantitative Aptitude", "6387063162");
        bookisbn.put("Word Power Made Easy", "9783852026118");
        bookisbn.put("Manorama Yearbook 2024", "6508930412");
        bookisbn.put("Dune", "9781845185412");
        bookisbn.put("Harry Potter and the Philosopher's Stone", "041076935X");
        bookisbn.put("The Fellowship of the Ring", "9782502693710");
        bookisbn.put("Wings of Fire", "2481065792");
        bookisbn.put("The Diary of a Young Girl", "9781156820542");
        bookisbn.put("Sapiens", "4056285413");

        bookgenre.put(1, new ArrayList<>(Arrays.asList("9785842106366", "0574163907", "9781146740685")));
        bookgenre.put(2, new ArrayList<>(Arrays.asList("4552438622", "9789314417105", "0794101831")));
        bookgenre.put(3, new ArrayList<>(Arrays.asList("9782806283595", "3185347913", "9787130541243")));
        bookgenre.put(4, new ArrayList<>(Arrays.asList("6387063162", "9783852026118", "6508930412")));
        bookgenre.put(5, new ArrayList<>(Arrays.asList("9781845185412", "041076935X", "9782502693710")));
        bookgenre.put(6, new ArrayList<>(Arrays.asList("2481065792", "9781156820542", "4056285413")));

        allbooks.put("Introduction to Algorithms", 15);
        allbooks.put("Clean Code", 45);
        allbooks.put("Database System Concepts", 12);
        allbooks.put("Rich Dad Poor Dad", 83);
        allbooks.put("The Psychology of Money", 77);
        allbooks.put("The Intelligent Investor", 20);
        allbooks.put("1984", 38);
        allbooks.put("To Kill a Mockingbird", 32);
        allbooks.put("The Great Gatsby", 49);
        allbooks.put("Quantitative Aptitude", 69);
        allbooks.put("Word Power Made Easy", 56);
        allbooks.put("Manorama Yearbook 2024", 80);
        allbooks.put("Dune", 73);
        allbooks.put("Harry Potter and the Philosopher's Stone", 90);
        allbooks.put("The Fellowship of the Ring", 54);
        allbooks.put("Wings of Fire", 89);
        allbooks.put("The Diary of a Young Girl", 56);
        allbooks.put("Sapiens", 96);

        List<String[]> borrowlist;
        borrowlist = new ArrayList<>();
        borrowlist.add(new String[]{"sanjay@gmail.com","17-01-2026","0"});
        borrowlist.add(new String[]{"padmesh@gmail.com","10-01-2026","1"});
        bookborrows.put("9785842106366",borrowlist);
        borrowlist = new ArrayList<>();
        borrowlist.add(new String[]{"sanjay@gmail.com","17-03-2026","0"});
        bookborrows.put("4552438622",borrowlist);
        borrowlist = new ArrayList<>();
        borrowlist.add(new String[]{"sanjay@gmail.com","26-03-2026","1"});
        bookborrows.put("9782806283595",borrowlist);
        borrowlist = new ArrayList<>();
        borrowlist.add(new String[]{"padmesh@gmail.com","27-03-2026","0"});
        bookborrows.put("9789314417105",borrowlist);
        
        userauth.put("sanjay@gmail.com","1234");
        userauth.put("padmesh@gmail.com","1234");

        userborrowcount.put("sanjay@gmail.com",3);
        userborrowcount.put("padmesh@gmail.com",2);

        List<String[]> borrowhistory;
        borrowhistory = new ArrayList<>();
        borrowhistory.add(new String[]{"9781845185412","17-01-2026","Returned"});
        borrowhistory.add(new String[]{"041076935X","01-03-2026","Returned"});
        borrowhistory.add(new String[]{"9785842106366","05-02-2026","Not Returned"});
        borrowhistory.add(new String[]{"4552438622","17-03-2026","Not Returned"});
        borrowhistory.add(new String[]{"9782806283595","28-02-2026","Not Returned"});
        userborrowhistory.put("sanjay@gmail.com",borrowhistory);
        borrowhistory = new ArrayList<>();
        borrowhistory.add(new String[]{"0794101831","02-01-2026","Lost"});
        borrowhistory.add(new String[]{"2481065792","05-02-2026","Returned"});
        borrowhistory.add(new String[]{"9785842106366","10-01-2026","Not Returned"});
        borrowhistory.add(new String[]{"9789314417105","02-03-2026","Not Returned"});
        userborrowhistory.put("padmesh@gmail.com",borrowhistory);

        List<String> finelist = new ArrayList<>();
        finelist.add("Rs. 12 for returning the book, Dune, 5 days after the returning date.");
        finelist.add("Rs. 10 for Membership Card loss.");
        userfines.put("sanjay@gmail.com",finelist);
        finelist = new ArrayList<>();
        finelist.add("Rs. 60 for returning the book, Wings of Fire, 20 days after the returning date.");
        finelist.add("Rs. 475 for loss of book, The Intelligent Investor.");
        userfines.put("padmesh@gmail.com",finelist);

        usersecdep.put("sanjay@gmail.com",1500);
        usersecdep.put("padmesh@gmail.com",450);

        usernames.put("sanjay@gmail.com","Sanjay Narayanan V");
        usernames.put("padmesh@gmail.com","Padmesh V");
        
        adminauth.put("venkat@gmail.com","1234");
        adminauth.put("suda@gmail.com","1234");

        adminnames.put("venkat@gmail.com","Venkatraman V");
        adminnames.put("suda@gmail.com","Subalakshmi V");

        limit = 15L;
        increment = 2;
        exponential = 10;
        minsecdep = 1500;
        minborsecdep = 500;
        booklossfine = 50;
        bookreturnfine = 80;
        membershipfine = 10;
        borrowlimit = 3;
    }

    public Database(){
    }

    public Map<String,String[]> getisbnbook(){
        return isbnbook;
    }
    public Map<String,String> getbookisbn(){
        return bookisbn;
    }
    public Map<Integer,List<String>> getbookgenre(){
        return bookgenre;
    }
    public TreeMap<String,Integer> getallbooks(){
        return allbooks;
    }
    public Map<String,List<String[]>> getbookborrows(){
        return bookborrows;
    }
    public Map<String,String> getuserauth(){
        return userauth;
    }
    public Map<String,Integer> getuserborrowcount(){
        return userborrowcount;
    }
    public Map<String,List<String[]>> getuserborrowhistory(){
        return userborrowhistory;
    }
    public Map<String,Integer> getusersecdep(){
        return usersecdep;
    }
    public Map<String,String> getusernames(){
        return usernames;
    }
    public Map<String,List<String>> getuserfines(){
        return userfines;
    }
    public Map<String,String> getadminnames(){
        return adminnames;
    }
    public Map<String,String> getadminauth(){
        return adminauth;
    }
    public long getlimit(){
        return limit;
    }
    public int getincrement(){
        return increment;
    }
    public int getexponential(){
        return exponential;
    }
    public int getminsecdep(){
        return minsecdep;
    }
    public int getminborsecdep(){
        return minborsecdep;
    }
    public int getbooklossfine(){
        return booklossfine;
    }
    public int getbookreturnfine(){
        return bookreturnfine;
    }
    public int getmembershipfine(){
        return membershipfine;
    }
    public int getborrowlimit(){
        return borrowlimit;
    }
}
