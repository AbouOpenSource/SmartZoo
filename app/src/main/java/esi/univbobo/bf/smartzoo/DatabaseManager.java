package esi.univbobo.bf.smartzoo;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abou on 29/09/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME="records.db";
    private static final int DATABASE_VERSION=4;

    public DatabaseManager(Context context)

    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String strSql="create table animal("
                +"     idanimal integer primary key autoincrement,"
                +"     pseudo text not null,"
                +"     espece text not null,"
                +"     image blob not null"
                +")";
        db.execSQL(strSql);
        Log.i("DATABASE","On Create Invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion , int newVersion)
    {
        //String strSql="alter table T_Scores add column ...";
        String strSql="drop table animal";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.i("DATABASE","onUpgrade invoked");

    }

    public long insertAnimal(AnimalRecord animalRecord)
    {

        String sql="INSERT INTO ANIMAL (idanimal,pseudo,espece,image) values(null,?,?,?)";

        SQLiteStatement statement=this.getWritableDatabase().compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,animalRecord.getPseudo());
        statement.bindString(2,animalRecord.getEspece());
        statement.bindBlob(3,animalRecord.getImage());
        return statement.executeInsert();


    }

    public List<AnimalRecord> getAnimal()
    {
        List<AnimalRecord> animalRecords= new ArrayList<>();


        //1ere technique : Ordre SQL

          /*  String strSql="select * from ANIMAL ";
            Cursor cursor =this.getReadableDatabase()
                    .rawQuery(strSql,null);
*/


        //2eme technique Plus objet
        Cursor cursor =this.getReadableDatabase().query("animal",new String[]{"idanimal","pseudo","espece","image"},
               null,null,null,null,null,null);


        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            AnimalRecord animalRecord = new AnimalRecord(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3));
            animalRecords.add(animalRecord);
            cursor.moveToNext();
        }

        cursor.close();
        return animalRecords;
    }
}
