
#acivity life cycyle :

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}


#chooser :
        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chooseImageButton = findViewById(R.id.chooseImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Handle the selected image here
            // For example, you can get the URI of the selected image using data.getData()
        }
    }
}


# list view code
public class MainActivity extends AppCompatActivity {
    ListView lv;
    EditText et;
    Button bt;
    ArrayList<String> arrlist ;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv);
        et=findViewById(R.id.et);
        bt=findViewById(R.id.bt);
        arrlist=new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        lv.setAdapter(arrayAdapter);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et.getText().toString();
                if(!name.isEmpty()){
                    arrlist.add(name);
                    arrayAdapter.notifyDataSetChanged();
                    et.setText("");
                    Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int len= arrlist.size();
                Toast.makeText(MainActivity.this, "clicked!"+i, Toast.LENGTH_SHORT).show();

            }
        });




    }
}



# service
        manifest -  <service android:name=".MusicService"/>
        musicserviceclass : public class MusicService extends Service{
    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp=MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }
}
--javamainfile
public class MainActivity extends AppCompatActivity {
    Button btnstart , btnend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart=findViewById(R.id.btnstart);
        btnend=findViewById(R.id.btnend);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this,MusicService.class));

            }
        });
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,MusicService.class));
            }
        });

    }
}


#optiomenu :

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id==R.id.option1){
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item_id==R.id.option2) {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item_id==R.id.option3) {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


#contextmenu :
        javamainfile--
public class MainActivity extends AppCompatActivity {
    ListView lv;
 #String[] list ={ "chinya" , "ramya" , "pakya" , "surya" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= findViewById(R.id.lv);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
        registerForContextMenu(lv);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if(i==R.id.op1){
            Toast.makeText(this, i+"selected", Toast.LENGTH_SHORT).show();
        } else if (i==R.id.op2) {
            Toast.makeText(this, i+"selected", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}
    context xml:

<item android:id="@+id/op1"
        android:title="option1" />
<item android:id="@+id/op2"
        android:title="option2" />


        #popupmenu :

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                Toast.makeText(MainActivity.this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_item2:
                                Toast.makeText(MainActivity.this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_item3:
                                Toast.makeText(MainActivity.this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }
}

#spinner code :

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Spinner spinner;
    private List<String> entries;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);
        Button addButton = findViewById(R.id.addButton);

        entries = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, entries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString().trim();
                if (!newEntry.isEmpty()) {
                    entries.add(newEntry);
                    adapter.notifyDataSetChanged();
                    editText.getText().clear();
                }
            }
        });
    }
}



#intent
        --dailer :
public void onClick(View view) {
        String url = et1.getText().toString();
        if(url.length()==10) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + url));
        startActivity(intent);
        #intent web :


        String url = "https://www.example.com";

        Create a new Intent with ACTION_VIEW
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Set the data of the Intent to the URL
        intent.setData(Uri.parse(url));

        Start the activity with the Intent
        startActivity(intent);









        #calculator
        javafilemain:
public class MainActivity extends AppCompatActivity {
    EditText et1 , et2;
    TextView tv1 , tvans;
    Button  bt1 , bt2 , bt3 , bt4 , bt5 , bt6 , bt7 , bt8 , bt9 , bt0 ,btmul,btsub ,btdiv,btadd,btequal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et2.findViewById(R.id.et2);
        et1.findViewById(R.id.et1);
        tv1.findViewById(R.id.tv1);
        tvans.findViewById(R.id.tvans);
        bt0.findViewById(R.id.bt0);
        bt1.findViewById(R.id.bt1);
        bt2.findViewById(R.id.bt2 );
        bt3.findViewById(R.id.bt3);
        bt4.findViewById(R.id.bt4);
        bt5.findViewById(R.id.bt5);
        bt6.findViewById(R.id.bt6);
        bt7.findViewById(R.id.bt7);
        bt8.findViewById(R.id.bt8);
        bt9.findViewById(R.id.bt9);
        btmul.findViewById(R.id.btmul);
        btsub.findViewById(R.id.btsub);
        btdiv.findViewById(R.id.btdiv);
        btadd.findViewById(R.id.btadd);
        btequal.findViewById(R.id.btequal);

        btequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        # Get input numbers from EditText fields
                String strNumber1 = et1.getText().toString().trim();
                String strNumber2 = et2.getText().toString().trim();


                        # Convert input strings to integers
                int number1 = Integer.parseInt(strNumber1);
                int number2 = Integer.parseInt(strNumber2);

                        #Determine the operation being performed
                        # You can use a Spinner or directly input operation using another EditText
                String operation = "+"; // Default operation is addition
                int result = 0;
                String operationSymbol = "";
                switch (operation) {
                    case "+":
                        result = number1 + number2;
                        operationSymbol = "+";
                        break;
                    case "-":
                        result = number1 - number2;
                        operationSymbol = "-";
                        break;
                    case "*":
                        result = number1 * number2;
                        operationSymbol = "*";
                        break;
                    case "/":
                                #Add handling for division by zero
                        if (number2 != 0) {
                            result = number1 / number2;
                            operationSymbol = "/";
                        } else {
                            tvans.setText("Cannot divide by zero");
                            return;
                        }
                        break;
                           # Add more cases for other operations if needed
                    default:
                        tvans.setText("Invalid operation");
                        return;
                }

                        #Display the operation and result
                tv1.setText(String.format("%d %s %d =", number1, operationSymbol, number2));
                tvans.setText(String.valueOf(result));
            }
        });
    }
}
--calculatorxml :
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<LinearLayout
        android:id="@+id/ll"
                android:layout_width="match_parent"
                android:layout_height="300dp"
                android:orientation="vertical"
                android:gravity="center">
<EditText
            android:id="@+id/et1"
                    android:layout_width="wrap_content"
                    android:layout_height="50dp"
                    android:layout_margin="5dp"
                    android:ems="12"
                    android:textSize="20sp"
                    android:hint="enter no 1"
                    android:gravity="center"
                    />

<TextView
            android:id="@+id/tv1"
                    android:layout_width="wrap_content"
                    android:layout_height="50dp"
                    android:textSize="20sp"
                    android:layout_margin="5dp"
                    />
<EditText
            android:id="@+id/et2"
                    android:layout_width="wrap_content"
                    android:layout_height="50dp"
                    android:layout_margin="5dp"
                    android:ems="12"
                    android:textSize="20sp"
                    android:hint="enter no 2"
                    android:gravity="center"
                    />


</LinearLayout>
<GridLayout
        android:id="@+id/gl1"
                android:layout_width="wrap_content"
                android:layout_height="500dp"
                android:layout_alignParentBottom="true"
                android:rowCount="5"
                android:columnCount="3"
                android:layout_centerHorizontal="true"

                >
<Button
            android:id="@+id/bt1"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="1"
                    android:textSize="30dp"

                    />
<Button
            android:id="@+id/bt2"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="2"
                    android:textSize="30dp"
                    />
<Button
            android:id="@+id/bt3"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="3"
                    android:textSize="30dp"
                    />
<Button
            android:id="@+id/bt4"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="4"
                    android:textSize="30dp"
                    />

<Button
            android:id="@+id/bt5"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="5"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/bt6"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="6"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/bt7"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="7"
                    android:textSize="30sp"
                    />

<Button
            android:id="@+id/bt8"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="8"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/bt9"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="9"
                    android:textSize="30sp"

                    />
<Button
            android:id="@+id/bt0"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="0"
                    android:textSize="30sp"
                    />

<Button
            android:id="@+id/btadd"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="+"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/btsub"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="-"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/btmul"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="X"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/btdiv"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="/"
                    android:textSize="30sp"
                    />
<Button
            android:id="@+id/btequal"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:width="137dp"
                    android:height="100dp"
                    android:text="="
                    android:textSize="30sp"
                    />




</GridLayout>

<TextView
        android:id="@+id/tvans"
                android:layout_width="264dp"
                android:layout_height="92dp"
                android:layout_above="@id/gl1"
                android:layout_centerHorizontal="true"
                android:gravity="center"
                android:hint="answer"
                android:textSize="40dp" />

</RelativeLayout>