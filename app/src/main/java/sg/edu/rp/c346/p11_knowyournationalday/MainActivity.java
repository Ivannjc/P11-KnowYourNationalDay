package sg.edu.rp.c346.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String>fact;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lv);

        fact = new ArrayList<>();
        fact.add("Singapore National Day is on 9 Aug");
        fact.add("Singapore is 52 Years Old");
        fact.add("Theme is #OneNationTogether");

        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fact);
        lv.setAdapter(aa);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase = (LinearLayout) inflater.inflate(R.layout.passphrase, null);
        final EditText etPassphrase = (EditText) passPhrase.findViewById(R.id.editTextPassPhrase);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please login").setView(passPhrase).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(etPassphrase.getText().toString().equals("738964")) {

                            lv = (ListView) findViewById(R.id.lv);

                            fact = new ArrayList<>();
                            fact.add("Singapore National Day is on 9 Aug");
                            fact.add("Singapore is 52 Years Old");
                            fact.add("Theme is #OneNationTogether");

                            aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, fact);
                            lv.setAdapter(aa);
                        } else{
                            finish();
                        }

                    }
                });
        builder.setNegativeButton("NO ACCESS CODE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.sendToFriend) {
            String[] list = new String[]{"Email", "SMS"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            for(int i = 1; i<fact.size(); i++){
                                message += i + fact.get(i) + "\n";
                            }
                            if (which == 0) {
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_EMAIL,
                                        new String[]{"15017608@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "Test Email from C347");
                                email.putExtra(Intent.EXTRA_TEXT,
                                        message);
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));
                                try {
                                    startActivity(Intent.createChooser(email, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                };

                            } else {
                                Toast.makeText(MainActivity.this, "SMS", Toast.LENGTH_LONG).show();
                                Intent smsIntent = new Intent(Intent.ACTION_SEND);
                                smsIntent.setType("text/plain");
                                smsIntent.putExtra(Intent.EXTRA_TEXT, fact.get(0) + "\n" + fact.get(1) + "\n" + fact.get(2));
                                try {
                                    startActivity(Intent.createChooser(smsIntent, "Send sms..."));
                                    finish();
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There is no SMS client installed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.quiz) {



        } else if (item.getItemId() == R.id.quit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Not really", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;

    }
}
