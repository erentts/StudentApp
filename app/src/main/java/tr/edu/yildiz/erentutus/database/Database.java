package tr.edu.yildiz.erentutus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String name = "quiz";
    private static final int ver = 1;
    public Database(Context context){
        super(context, name,null,ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (Name Text, Surname Text, Username Text, Email Text, Phone Text, Birthdate Text,Photo Blob,PasswordHash Text,PasswordSalt Text);");
        db.execSQL("CREATE TABLE Question (question Text, choiceOne Text, choiceTwo Text, choiceThree Text, choiceFour Text, answer Text,choiceCount Text,userFK Text," +
                "FOREIGN KEY(userFK) REFERENCES User(Username));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Question");
        onCreate(db);
    }
}
