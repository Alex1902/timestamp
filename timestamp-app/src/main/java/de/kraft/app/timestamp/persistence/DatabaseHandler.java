package de.kraft.app.timestamp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import de.kraft.app.timestamp.model.WorkDay;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String TAG = DatabaseHandler.class.getSimpleName( );

    private static final String DATABASE_NAME = "timestamp.db";
    private static final int DATABASE_VERSION = 1;

    public static final String _ID = "_id";
    public static final String TABLE_NAME = "january";
    public static final String DayColumnName = "Day";
    public static final String StartColumnName = "Start";
    public static final String EndColumnName = "End";
    public static final String PauseColumnName = "Pause";


    DatabaseHandler( Context context )
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( "CREATE TABLE "
                + TABLE_NAME + " (" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DayColumnName + " INTEGER, "
                + StartColumnName + " INTEGER, " + EndColumnName + " INTEGER, "
                + PauseColumnName + " INTEGER);" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public void insertWorkDay( int day, WorkDay workDay )
    {
        try
        {
            SQLiteDatabase db = getWritableDatabase( );
            ContentValues values = new ContentValues( );
            values.put( DayColumnName, day );
            values.put( StartColumnName, workDay.getStartOfWorkDay( ).getValue( ).getTime( ).getHours( ) );
            values.put( EndColumnName, workDay.getEndOfWorkDay( ).getValue( ).getTime( ).getHours( ) );
            values.put( PauseColumnName, (int) workDay.getLunchBreakDuration( ).getDuration( ) );
            db.insert( TABLE_NAME, null, values );
        } catch ( SQLiteException e )
        {
        } finally
        {
        }
    }
}
