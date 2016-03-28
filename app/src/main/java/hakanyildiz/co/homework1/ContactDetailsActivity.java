package hakanyildiz.co.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ContactDetailsActivity extends AppCompatActivity {
    String name,phone,city,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getExtras() != null){
            Bundle args = getIntent().getExtras();

            name = args.getString("name");
            phone = args.getString("phone");
            city = args.getString("city");
            email = args.getString("email");
        }


    }



}


/*


final String particularContactName = contactNames[position];
            // custom dialog
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.operation);
            dialog.setTitle("Select an Option");

            Button btnDelete = (Button)dialog.findViewById(R.id.btnDelete);
            Button btnDeleteAll = (Button)dialog.findViewById(R.id.btnDeleteAll);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selection = ContactsContract.Data.DISPLAY_NAME + " = ? ";
                    String[] selectionArgs = new String[] { particularContactName };

                    ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
                    ContentProviderOperation.Builder op = ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI);
                    op.withSelection(selection, selectionArgs);
                    ops.add(op.build());
                    try {
                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                        RefreshContactList();
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, particularContactName + " contact is deleted!", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                    } catch (OperationApplicationException e) {
                        Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                    }
                }
            });
 */