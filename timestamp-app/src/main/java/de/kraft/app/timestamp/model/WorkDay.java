package de.kraft.app.timestamp.model;

import de.kraft.app.timestamp.settings.Settings;

import java.util.GregorianCalendar;
import java.util.Random;


public class WorkDay
{
    private static Settings appSettings = Settings.getInstance( );
    private long id;
    private WorkDayDateTime startOfWorkDay;
    private WorkDayDateTime endOfWorkDay;
    private WorkDayDateTime startOfLunchBreak;
    private WorkDayDateTime endOfLunchBreak;

    public WorkDay( WorkDayDateTime startOfWorkDay, WorkDayDateTime endOfWorkDay, WorkDayDateTime startOfLunchBreak, WorkDayDateTime endOfLunchBreak )
    {
        this.startOfWorkDay = new WorkDayDateTime( );
        this.endOfWorkDay = new WorkDayDateTime( );
        this.startOfLunchBreak = new WorkDayDateTime( );
        this.endOfLunchBreak = new WorkDayDateTime( );
    }

    public WorkDay( )
    {
        this( appSettings.readStartOfWorkTime( ),
                appSettings.readEndOfWorkTime( ),
                appSettings.readStartOfLunchTime( ),
                appSettings.readEndOfLunchTime( ) );
    }

    public long getId( )
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public WorkDayDateTime getStartOfWorkDay( )
    {
        return startOfWorkDay;
    }

    public WorkDayDateTime getEndOfWorkDay( )
    {
        return endOfWorkDay;
    }

    public WorkDayDateTime getStartOfLunchBreak( )
    {
        return startOfLunchBreak;
    }

    public WorkDayDateTime getEndOfLunchBreak( )
    {
        return endOfLunchBreak;
    }

    public void setStartOfWork( long startOfWorkDay )
    {
        this.startOfWorkDay = WorkDayDateTime.fromMilliseconds( startOfWorkDay );
    }

    public void setEndOfWork( long endOfWorkDay )
    {
        this.endOfWorkDay = WorkDayDateTime.fromMilliseconds( endOfWorkDay );
    }

    public void setStartOfLunchBreak( long startOfLunchBreak )
    {
        this.startOfLunchBreak = WorkDayDateTime.fromMilliseconds( startOfLunchBreak );
    }

    public void setEndOfLunchBreak( long endOfLunchBreak )
    {
        this.endOfLunchBreak = WorkDayDateTime.fromMilliseconds( endOfLunchBreak );
    }

    public void stampStartOfWork( )
    {
        startOfWorkDay = new WorkDayDateTime( );
    }

    public void stampEndOfWork( )
    {
        endOfWorkDay = new WorkDayDateTime( );
    }

    public void stampStartOfLunchBreak( )
    {
        startOfLunchBreak = new WorkDayDateTime( );
    }

    public void stampEndOfLunchBreak( )
    {
        endOfLunchBreak = new WorkDayDateTime( );
    }

    public WorkDayDuration getLunchBreakDuration( )
    {
        if ( endOfLunchBreak == null || startOfLunchBreak == null )
        {
            return null;
        }
        return new WorkDayDuration( startOfLunchBreak, endOfLunchBreak );
    }

    public WorkDayDuration getGrossWorkingHours( )
    {
        if ( startOfWorkDay == null || endOfWorkDay == null )
        {
            return null;
        }
        return new WorkDayDuration( startOfWorkDay, endOfWorkDay );
    }

    public WorkDayDuration getWorkingHours( )
    {
        return getGrossWorkingHours( ).minus( getLunchBreakDuration( ) );
    }

    public static WorkDay getMockWorkDay( )
    {
        int day = new Random( ).nextInt( 31 );
        int month = new Random( ).nextInt( 12 );

        WorkDayDateTime mockStartDay = new WorkDayDateTime( new GregorianCalendar( 2014, month, day, new Random( ).nextInt( 3 ) + 7, new Random( ).nextInt( 59 ), new Random( ).nextInt( 59 ) ) );
        WorkDayDateTime mockStartLuch = new WorkDayDateTime( new GregorianCalendar( 2014, month, day, 12, new Random( ).nextInt( 59 ), new Random( ).nextInt( 59 ) ) );
        WorkDayDateTime mockEndLuch = new WorkDayDateTime( new GregorianCalendar( 2014, month, day, 13, new Random( ).nextInt( 10 ), new Random( ).nextInt( 59 ) ) );
        WorkDayDateTime mockEndDay = new WorkDayDateTime( new GregorianCalendar( 2014, month, day, new Random( ).nextInt( 3 ) + 15, new Random( ).nextInt( 59 ), new Random( ).nextInt( 59 ) ) );

        return new WorkDay( mockStartDay, mockEndDay, mockStartLuch, mockEndLuch );
    }

    @Override
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( );
        sb.append( "Overview\n" );
        sb.append( String.format( "Start: %s\n", startOfWorkDay.toString( ) ) );
        sb.append( String.format( "Break: %s\n", getLunchBreakDuration( ).toString( ) ) );
        sb.append( String.format( "Leave: %s\n", endOfWorkDay.toString( ) ) );
        return sb.toString( );
    }
}
