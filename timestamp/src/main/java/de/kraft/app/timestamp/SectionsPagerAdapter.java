package de.kraft.app.timestamp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.joda.time.LocalDate;

import java.util.Locale;


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
    public static class PlaceholderFragment extends Fragment
    {
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
            View rootView = inflater.inflate( R.layout.fragment_time_stamp, container, false );
            return rootView;
        }
    }
}
