package hakanyildiz.co.homework1;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by dilkom-hak on 8.03.2016.
 */
public class FragmentContact extends ListFragment implements AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ListView listView;
    ArrayAdapter<CharSequence> adapter;
    String[] contactNames;
    String[] contactIDs;
    ContentResolver contentResolver;


    public FragmentContact() {

    }

    public static FragmentContact newInstance(String param1, String param2) {
        FragmentContact fragment = new FragmentContact();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_contact, container, false);

        /*
        * ICERIK BURAYA EKLENECEK.
        *
        * */
        listView = (ListView) container.findViewById(R.id.listViewContacts);

        setup();

        listView.setOnItemClickListener(this);


        return layout;
    }

    private void setup() {
        contentResolver = getActivity().getContentResolver();
        String[] columnOfInterest = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

        Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                columnOfInterest,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME);

        int i = 0;
        if(cursor.getCount() > 0 )
        {
            //string arrayleri cursor eriştiği lenght kadar büyüklükte yaratılır.
            contactNames = new String[cursor.getCount()];
            contactIDs = new String[cursor.getCount()];

            cursor.moveToFirst(); // go first line
            do
            {
                contactNames[i] = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactIDs[i] = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                i++;
            }while(cursor.moveToNext());
            cursor.close();

            adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_list_item_1,contactNames);
            listView.setAdapter(adapter);

        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] columnsOfInterest = {ContactsContract.CommonDataKinds.StructuredPostal.CITY, ContactsContract.CommonDataKinds.StructuredPostal.REGION, ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE};


        /*
          Bundle datas = getIntent().getExtras();
          tvNumber.setText(datas.getString("number"));

         */

        String whereClause = ContactsContract.Data.CONTACT_ID + " = '" + contactIDs[position] + "' AND " + ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE + "'";
        Log.v("CONTACTS", whereClause);
        final Cursor addressCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, columnsOfInterest, whereClause, null, null);

        if (addressCursor.moveToFirst())
        {
            String name = contactNames[position];
            String phone = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String city = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
            String email = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));
            //String zip = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
            Bundle args = new Bundle();
            args.putString("name" , name);
            args.putString("phone" , phone);
            args.putString("city" , city);
            args.putString("email" , email);

            Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
            intent.putExtras(args);

            startActivity(intent);
        }
        addressCursor.close();
    }
}
