package de.kraft.app.timestamp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static de.kraft.app.timestamp.Constants.*;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    public SectionsPagerAdapter( FragmentManager fm )
    {
        super( fm );
    }

    @Override
    public Fragment getItem( int position )
    {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance( position + 1 );
    }

    @Override
    public int getCount( )
    {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
        Locale l = Locale.getDefault( );
        LocalDate date = new LocalDate( );
        switch ( position )
        {
            case 0:
                return date.minusMonths( 1 ).monthOfYear( ).getAsShortText( l );
            case 1:
                return date.monthOfYear( ).getAsShortText( l );
            case 2:
                return date.plusMonths( 1 ).monthOfYear( ).getAsShortText( l );
        }
        return null;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends ListFragment
    {
        ArrayList<HashMap<String, String>> list;


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance( int sectionNumber )
        {
            PlaceholderFragment fragment = new PlaceholderFragment( );
            Bundle args = new Bundle( );
            args.putInt( ARG_SECTION_NUMBER, sectionNumber );
            fragment.setArguments( args );
            return fragment;
        }

        public PlaceholderFragment( )
        {
        }

        @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState )
        {
            final View rootView = inflater.inflate( R.layout.fragment_time_stamp, container, false );
            ListView listView = (ListView) rootView.findViewById( R.id.timeList );

            list = new ArrayList<HashMap<String, String>>( );

            HashMap<String, String> temp = new HashMap<String, String>( );
            temp.put( FIRST_COLUMN, "Ankit Karia" );
            temp.put( SECOND_COLUMN, "Male" );
            temp.put( THIRD_COLUMN, "22" );
            temp.put( FOURTH_COLUMN, "Unmarried" );
            list.add( temp );

            HashMap<String, String> temp2 = new HashMap<String, String>( );
            temp2.put( FIRST_COLUMN, "Rajat Ghai" );
            temp2.put( SECOND_COLUMN, "Male" );
            temp2.put( THIRD_COLUMN, "25" );
            temp2.put( FOURTH_COLUMN, "Unmarried" );
            list.add( temp2 );

            HashMap<String, String> temp3 = new HashMap<String, String>( );
            temp3.put( FIRST_COLUMN, "Karina Kaif" );
            temp3.put( SECOND_COLUMN, "Female" );
            temp3.put( THIRD_COLUMN, "31" );
            temp3.put( FOURTH_COLUMN, "Unmarried" );
            list.add( temp3 );

            ListViewAdapter adapter = new ListViewAdapter( this, list );
            listView.setAdapter( adapter );

            setListAdapter( adapter );

            listView.setOnItemClickListener( new AdapterView.OnItemClickListener( )
            {
                @Override
                public void onItemClick( AdapterView<?> parent, final View view, int position, long id )
                {
                    int pos = position + 1;
                    Toast.makeText( PlaceholderFragment.this.getActivity( ), Integer.toString( pos ) + " Clicked", Toast.LENGTH_SHORT ).show( );
                }

            } );
            return rootView;
        }
    }
}
