package de.kraft.app.timestamp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static de.kraft.app.timestamp.Constants.*;

public class ListViewAdapter extends BaseAdapter
{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;

    public ListViewAdapter( Activity activity, ArrayList<HashMap<String, String>> list )
    {
        super( );
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount( )
    {
        // TODO Auto-generated method stub
        return list.size( );
    }

    @Override
    public Object getItem( int position )
    {
        // TODO Auto-generated method stub
        return list.get( position );
    }

    @Override
    public long getItemId( int position )
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        // TODO Auto-generated method stub


        LayoutInflater inflater = activity.getLayoutInflater( );

        if ( convertView == null )
        {

            convertView = inflater.inflate( R.layout.column_row, null );

            txtFirst = (TextView) convertView.findViewById( R.id.day );
            txtSecond = (TextView) convertView.findViewById( R.id.start );
            txtThird = (TextView) convertView.findViewById( R.id.end );
            txtFourth = (TextView) convertView.findViewById( R.id.pause );

        }

        HashMap<String, String> map = list.get( position );
        txtFirst.setText( map.get( FIRST_COLUMN ) );
        txtSecond.setText( map.get( SECOND_COLUMN ) );
        txtThird.setText( map.get( THIRD_COLUMN ) );
        txtFourth.setText( map.get( FOURTH_COLUMN ) );

        return convertView;
    }

}