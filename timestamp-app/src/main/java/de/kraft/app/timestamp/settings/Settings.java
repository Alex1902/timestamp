package de.kraft.app.timestamp.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import de.kraft.app.timestamp.TimeStamp;
import de.kraft.app.timestamp.common.CommonConst;
import de.kraft.app.timestamp.model.WorkDayDateTime;

public class Settings
{
    private static Settings instance;
    private SharedPreferences appSettings;

    private Settings( )
    {
        appSettings = PreferenceManager.getDefaultSharedPreferences( TimeStamp.getTimeStampApplicationContext( ) );
    }

    public static Settings getInstance( )
    {
        if ( instance == null )
        {
            instance = new Settings( );
        }
        return instance;
    }

    public boolean readLunchFlag( )
    {
        return appSettings.getBoolean( CommonConst.LUNCHFLAG, false );
    }

    public void saveLunchFlag( boolean flag )
    {
        appSettings.edit( ).putBoolean( CommonConst.LUNCHFLAG, flag ).commit( );
    }

    public void saveStartOfLunchTime( long value )
    {
        appSettings.edit( ).putLong( CommonConst.STARTLUNCHTIME, value ).commit( );
    }

    public WorkDayDateTime readStartOfLunchTime( )
    {
        return createWorkDayDateTime( CommonConst.STARTLUNCHTIME );
    }

    public void saveEndOfLunchTime( long value )
    {
        appSettings.edit( ).putLong( CommonConst.ENDLUNCHTIME, value ).commit( );
    }

    public WorkDayDateTime readEndOfLunchTime( )
    {
        return createWorkDayDateTime( CommonConst.ENDLUNCHTIME );
    }

    public void saveStartOfWorkTime( long value )
    {
        appSettings.edit( ).putLong( CommonConst.STARTWORKTIME, value ).commit( );
    }

    public WorkDayDateTime readStartOfWorkTime( )
    {
        return createWorkDayDateTime( CommonConst.STARTWORKTIME );
    }

    public void saveEndOfWorkTime( long value )
    {
        appSettings.edit( ).putLong( CommonConst.ENDWORKTIME, value ).commit( );
    }

    public WorkDayDateTime readEndOfWorkTime( )
    {
        return createWorkDayDateTime( CommonConst.ENDWORKTIME );
    }

    private WorkDayDateTime createWorkDayDateTime( String key )
    {
        long millis = appSettings.getLong( key, -1 );
        return WorkDayDateTime.fromMilliseconds( millis );
    }
}
