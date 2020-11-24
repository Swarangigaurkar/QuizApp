package com.example.swarangigaurkar.quizzup;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_CATEGORY ="quiz_categories";
    public static  final String COLUMN_CATEGORY_NAME = "name";
    public static final String  COLUMN_CATEGORY_ID = "Category_id";
    public static final String COLUMN_DIFFICULTY ="difficultyLevel";

    public static final String TABLE_QUESTIONS ="quiz_questions";
    public static final String  COLUMN_QUESTION_ID = "Question_id";
    public static final String COLUMN_QUESTION ="question";
    public static final String COLUMN_OPTION1 = "option1";
    public static final String COLUMN_OPTION2 = "option2";
    public static final String COLUMN_OPTION3 = "option3";
    public static final String COLUMN_OPTION4 = "option4";
    public static final String COLUMN_ANS = "ansNo";

    private static final  String DATABASE_NAME ="quizup.db";
    private static final int DATABASE_VERSION = 12;
    private SQLiteDatabase db;
    private static QuizDBHelper instance;

    public QuizDBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static  synchronized QuizDBHelper getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new QuizDBHelper(context.getApplicationContext());
        }
        return  instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +  TABLE_CATEGORY + " ( "+
                COLUMN_CATEGORY_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY_NAME +	" TEXT, " +
                COLUMN_DIFFICULTY +" TEXT "	+
                " )";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                TABLE_QUESTIONS + " ( "+
                COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_OPTION1 + " TEXT, "  +
                COLUMN_OPTION2 + " TEXT, "  +
                COLUMN_OPTION3 + " TEXT, "  +
                COLUMN_OPTION4 + " TEXT,"  +
                COLUMN_ANS + " INTEGER, "  +
                COLUMN_CATEGORY_ID+ " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_CATEGORY_ID  + ") REFERENCES " +
                TABLE_CATEGORY + "(" + COLUMN_CATEGORY_ID + ")" + "ON DELETE CASCADE" +
                " )";


        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    public void addCategory(int id,String category,String difficulty){
        db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORY_ID,id);
        contentValues.put(COLUMN_CATEGORY_NAME,category);
        contentValues.put(COLUMN_DIFFICULTY,difficulty);

        db.insert(TABLE_CATEGORY,null,contentValues);
    }


    public Cursor getAllCategories()
    {
        db =getReadableDatabase();

        Cursor c = db.rawQuery("SELECT DISTINCT " + COLUMN_CATEGORY_NAME + " FROM "+ TABLE_CATEGORY,null);

        return c;
    }

    public int getCategoryID(String CategoryName,String Difficulty)
    {
        db =getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_CATEGORY + " WHERE " + COLUMN_CATEGORY_NAME +
                " = '" + CategoryName + "' AND " + COLUMN_DIFFICULTY + " = '" + Difficulty + "'",null);
        c.moveToFirst();

        if (c.getCount()==0)
            return -1;

        return c.getInt(c.getColumnIndex(COLUMN_CATEGORY_ID));
    }

    public Cursor getAllQuestions(int CategoryId){
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + COLUMN_CATEGORY_ID + " = " + CategoryId,null);
        return c;
    }

    private void addQuestion(Questions question)
    {

        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.COLUMN_QUESTION, question.getQuestion());
        values.put(QuizDBHelper.COLUMN_OPTION1, question.getOption1());
        values.put(QuizDBHelper.COLUMN_OPTION2, question.getOption2());
        values.put(QuizDBHelper.COLUMN_OPTION3, question.getOption3());
        values.put(QuizDBHelper.COLUMN_OPTION4, question.getOption4());
        values.put(QuizDBHelper.COLUMN_ANS, question.getAnsNr());
        values.put(QuizDBHelper.COLUMN_QUESTION_ID, question.getQuestionId());
        values.put(QuizDBHelper.COLUMN_CATEGORY_ID, question.getCategoryId());

        db.insert(QuizDBHelper.TABLE_QUESTIONS,null,values);

    }

    public void fillQuestionsTable()
    {
        Questions q1= new Questions("Which is not an access specifier" , "public","private","protected","main",4,
                1,  4);
        addQuestion(q1);
        Questions q2= new Questions("Inline functions are useful when" , "Function is large with many nested loops","Function has many static variables","Function is small and we want to avoid function call overhead","None of the above",3,
                2,4);
        addQuestion(q2);
        Questions q3= new Questions("Which of the following is true?" , "All objects of a class share all data members of class","Objects of a class do not share non-static members. Every object has its own copy.","Objects of a class do not share codes of non-static methods, they have their own copy","None Of the above",2,
                3,5);
        addQuestion(q3);
        Questions q4= new Questions("#include<iostream>\n" +
                "using namespace std;\n" +
                " \n" +
                "class Test\n" +
                "{\n" +
                "    static int x;\n" +
                "    int *ptr;\n" +
                "    int y;\n" +
                "};\n" +
                " \n" +
                "int main()\n" +
                "{\n" +
                "    Test t;\n" +
                "    cout << sizeof(t) << \" \";\n" +
                "    cout << sizeof(Test *);\n" +
                "}\n" , "12 4","12 12","8 4","8 8",3,4,6);
        addQuestion(q4);
        Questions q5= new Questions("#include<iostream>\n" +
                "using namespace std;\n" +
                " \n" +
                "class Base\n" +
                "{\n" +
                "public:\n" +
                "    virtual void show() = 0;\n" +
                "};\n" +
                " \n" +
                "int main(void)\n" +
                "{\n" +
                "    Base b;\n" +
                "    Base *bp;\n" +
                "    return 0;\n" +
                "}" , "There are compiler errors in lines \"Base b;\" and \"Base bp;\"","There is compiler error in line \"Base b;","There is compiler error in line \"Base bp;\"","No compiler Error",2,5,6);
        addQuestion(q5);

        Questions q6  = new Questions("Can dot operator be applied to pointers","yes","no","yes with some limitations",
                "no,with some limitations",2,6,4
        );
        addQuestion(q6);

        Questions q7  = new Questions(" Which of the following is not used to seek a file pointer?","ios::cur","ios::set ",
                " ios::end ",
                " ios::beg",2,7,5
        );
        addQuestion(q7);
        Questions q8  = new Questions(" During dynamic memory allocation in CPP, new operator returns _________ value if memory allocation is unsuccessful",
                "False","NULL","Zero ","None of these",2,8,5
        );
        addQuestion(q8);
        Questions q9  = new Questions("Can dot operator be applied to pointers","yes","no","yes with some limitations",
                "no,with some limitations",2,9,6);
        addQuestion(q9);
        Questions q10  = new Questions(" C99 standard guarantees uniqueness of ____ characters for internal names."," 31" ,"63","12",
                "14",2,10, 1
        );
        addQuestion(q10);
        Questions q11  = new Questions(" Which of the following is not a valid variable name declaration?"," int _a3" ,"int 3_a","int a_3",
                "int _3a",2,11, 1
        );
        addQuestion(q11);
        Questions q12  = new Questions("Why do variable names beginning with the underscore is not encouraged?",
                " It is not standardized" ,
                " To avoid conflicts since assemblers and loaders use such names",
                " To avoid conflicts since library routines use such names" ,
                " To avoid conflicts with environment variables of an operating system",2,12,1);
        addQuestion(q12);
        Questions q13  = new Questions("Arguments that take input by user before running a program are called?",
                " main function arguments\n" ,
                " main arguments\n" ,
                " Command-Line arguments\n" ,
                " Parameterized arguments",3,13, 1
        );
        addQuestion(q13);
        Questions q14  = new Questions("The maximum number of arguments that can be passed in a single function are_____________\n" ,
                " 127\n" ,
                " 253\n" ,
                " 361\n" ,
                " No limits in number of arguments",2,14, 2
        );
        addQuestion(q14);
        Questions q15  = new Questions("1. Which of the following are C preprocessors?\n" ,
                " #ifdef\n" ,
                " #define\n" ,
                " #endif\n" ,
                " all of the mentioned",4,15, 3
        );
        addQuestion(q15);
        Questions q16  = new Questions("The C-preprocessors are specified with _________symbol.\n" ,
                " #\n" ,
                " $\n" ,
                " ” ”\n" ,
                " None of the mentioned\n",1,16, 2
        );
        addQuestion(q16);
        Questions q17  = new Questions(" Automatic variables are variables that are\n" ,
                " Declared within the scope of a block, usually a function\n" ,
                " Declared outside all functions\n" ,
                " Declared with auto keyword\n" ,
                " Declared within the keyword extern",1,17, 3
        );
        addQuestion(q17);
        Questions q18  = new Questions("Automatic variables\n" ,
                " Exist only within that scope in which it is declared\n" ,
                " Cease to exist after the block is exited\n" ,
                " Exist only within that scope in which it is declared & exist after the block is exited\n" ,
                " Only 1",3,18, 3
        );
        addQuestion(q18);
        Questions q19  = new Questions("Which object of HttpSession can be used to view and manipulate information about a session?\n",
                " session identifier\n" ,
                " creation time\n" ,
                " last accessed time\n" ,
                " All mentioned above ",4,19,9
        );
        addQuestion(q19);
        Questions q20  = new Questions("Which case of a session bean obtains the UserTransaction object via the EJBContext using the getUserTransaction() method in EJB transaction management?\n" ,
                " Bean-managed transactions \n" ,
                " Container-managed transactions\n" ,
                " Both A & B\n" ,
                " None of the above",1,20, 8
        );
        addQuestion(q20);
        Questions q21  = new Questions("In RMI Architecture which layer Intercepts method calls made by the client/redirects these calls to a remote RMI service?\n",
                " Stub & Skeleton Layer\n" ,
                "Application Layer\n" ,
                " Remote Reference Layer\n" ,
                " Transport Layer",1,21, 9);
        addQuestion(q21);
        Questions q22  = new Questions("Which attribute specifies a JSP page that should process any exceptions thrown but not caught in the current page?\n",
                " The ErrorPage Attribute \n" ,
                " The IsErrorPage Attribute\n",
                " Both A & B\n" ,
                " None of the above",1,22, 7
        );
        addQuestion(q22);
        Questions q23  = new Questions(" Which method is used for retrieving streams of both ASCII and Unicode characters is new in the JDBC 2.0 core API?\n",
                " getCharacterStream \n" ,
                " getBinaryStream\n" ,
                " getAsciiStream\n" ,
                " getUnicodeStream",1,23, 8
        );
        addQuestion(q23);
        Questions q24  = new Questions(" In Servlet Terminology what provides runtime environment for JavaEE (j2ee) applications. It performs many operations that are given below:\n" +
                "\n" +
                "1. Life Cycle Management\n" +
                "2. Multithreaded support\n" +
                "3. Object Pooling\n" +
                "4. Security etc",
                " Server\n" ,
                " Webserver\n" ,
                " Container\n" ,
                " Application Server",3,24, 9
        );
        addQuestion(q24);
        Questions q25  = new Questions("Abbreviate the term UDA?\n",
                " Unified Data Access\n" ,
                " Universal Data Access \n" ,
                " Universal Digital Access\n" ,
                " Uniform Data Access",2,25, 7
        );
        addQuestion(q25);
        Questions q26  = new Questions("Which methods are provided by the PrintStream class?\n",
                " Read data to another stream\n" ,
                " Write data to another stream \n" ,
                " Read data to same stream\n" ,
                " Write data to same stream",2,26, 8
        );
        addQuestion(q26);
        Questions q27  = new Questions("Which is irrecoverable?\n",
                " Error \n" ,
                " Checked Exception\\n" ,
                " Unchecked Exception\n" ,
                " Both B & C",1,27, 9
        );
        addQuestion(q27);
        Questions q28  = new Questions("How many types of constructor are defined in the StringTokenizer class?\n",
                " 2\n" ,
                " 3\n" ,
                " 4\n" ,
                " 5",3,28, 7
        );
        addQuestion(q28);
        Questions q29 = new Questions("Mutual exclusive and inter-thread communication are which type of Synchorization?\n",
                " Thread Synchronization \n" ,
                " Process Synchronization\n" ,
                " Object Synchronization\n" ,
                "d None of the above",1,29, 8
        );
        addQuestion(q29);
        Questions q30  = new Questions("Dalvik Virtual Machine (DVM) actually uses core features of\n" ,
                "Windows\n" ,
                "Mac\n" ,
                "Linux\n" ,
                "Contiki",3,30, 10
        );
        addQuestion(q30);
        Questions q31  = new Questions(" A type of service provided by android that allows sharing and publishing of data to other applications is\n" ,
                "View System\n" ,
                "Content Providers\n" ,
                "Activity Manager\n" ,
                "Notifications Manager",2,31, 12
        );
        addQuestion(q31);
        Questions q32  = new Questions("Android library that provides access to UI pre-built elements such as buttons, lists, views etc. is\n",
                "android.text\n" ,
                "android.os\n" ,
                "android.view\n" ,
                "android.webkit",4,32, 11
        );
        addQuestion(q32);
        Questions q33  = new Questions("A type of service provided by android that shows messages and alerts to user is\n",
                "Content Providers\n" ,
                "View System\n" ,
                "Notifications Manager\n" ,
                "Activity Manager",3,33, 10
        );
        addQuestion(q33);
        Questions q34  = new Questions("Which i s not a component of APK file?","Resources","All components of APK","Native libraries","Dalvik executables",2,34, 12
        );
        addQuestion(q34);
        Questions q35  = new Questions(" Activity diagram, use case diagram, collaboration diagram and sequence diagram are considered as types of\n" ,
                "non-behavioral diagrams\n" ,
                "non structural diagrams\n" ,
                "structural diagrams\n" ,
                "behavioral diagrams",4,35, 11
        );
        addQuestion(q35);
        Questions q36  = new Questions("Kind of diagrams which are used to show interactions between series of messages are classified as\n",
                "activity diagrams\n" ,
                "state chart diagrams\n" ,
                "collaboration diagrams\n" ,
                "object lifeline diagrams",3,36, 10
        );
        addQuestion(q36);
        Questions q37  = new Questions("Diagrams which are used to distribute files, libraries and tables across topology of hardware are called\n" ,
                "\n" +
                        "deployment diagrams\n" ,
                "use case diagrams\n" ,
                "sequence diagrams\n" ,
                "collaboration diagrams",1,37, 12
        );
        addQuestion(q37);
        Questions q38  = new Questions("Dynamic aspects related to a system are shown with help of\n" +
                "\n" ,
                "sequence diagrams\n" ,
                "interaction diagrams\n",
                "deployment diagrams\n" ,
                "use case diagrams",2,38, 11
        );
        addQuestion(q38);
        Questions q39  = new Questions("When did Google purchase Android","2007","2005","2010","2008",2,39, 10
        );
        addQuestion(q39);
        Questions q40 = new Questions("What is the output of this C code?\n" +
                "\n" +
                "    #include <stdio.h>\n" +
                "    int main()\n" +
                "    {\n" +
                "        char buf[12];\n" +
                "        stderr = stdin;\n" +
                "        fscanf(stderr, \"%s\", buf);\n" +
                "        printf(\"%s\\n\", buf);\n" +
                "    }\n" ,
                " Compilation error\n" ,
                " Undefined behaviour\n" ,
                " Whatever user types\n" ,
                " None of the mentioned",3,40,2
        );
        addQuestion(q40);

    }


}
