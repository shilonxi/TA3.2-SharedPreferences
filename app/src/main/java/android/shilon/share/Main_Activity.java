package android.shilon.share;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Activity extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private CheckBox checkBox;
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        checkBox=findViewById(R.id.checkBox);
        button=findViewById(R.id.button);
        sharedPreferences=getSharedPreferences("ME",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String text=sharedPreferences.getString("USER","");
        if (text!=null) {
            name.setText(text);
            pass.setText(sharedPreferences.getString("KEY",""));
            checkBox.setChecked(true);
        } else
            checkBox.setChecked(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString().trim();
                String key=pass.getText().toString().trim();
                if ("ecnu".equals(user)&&"2019".equals(key))
                {
                    if (checkBox.isChecked()) {
                        editor.putString("USER",user);
                        editor.putString("KEY",key);
                        editor.commit();
                    } else {
                        editor.remove("USER");
                        editor.remove("KEY");
                        editor.commit();
                    }
                    Toast.makeText(Main_Activity.this,"success",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Main_Activity.this,"fail",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
