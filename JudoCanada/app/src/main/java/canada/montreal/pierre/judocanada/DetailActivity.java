package canada.montreal.pierre.judocanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        ActionBar actionBar = getActionBar();
//
//
//        actionBar.setDisplayHomeAsUpEnabled(true);

        tv = findViewById(R.id.textView);
        button  =  findViewById(R.id.button2);
        String str = "";

        Member member = (Member) getIntent().getParcelableExtra("member");

        if(member!=null&&(!member.getMemberNum().equals("NULL"))){
              str = "FirstName:" + member.getFirstName() + "\n\nLastName:" + member.getLastName()
                    + "\n\nBirthDate:" + member.getBirthDate()+ "\n\nMemberNum:" + member.getMemberNum()
                    + "\n\nSeasonYear:"+ member.getSeasonYear() + "\n\nClubName:"+ member.getClubName()
                    + "\n\nBranch:"+ member.getBranch() + "\n\nBeltLevel:"+ member.getBeltLevel();

            Configuration configuration = getResources().getConfiguration();

            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {


                str = "FirstName:" + member.getFirstName() + ", LastName:" + member.getLastName()
                        + "\nBirthDate:" + member.getBirthDate()+", MemberNum:" + member.getMemberNum()
                        + "\nSeasonYear:"+ member.getSeasonYear() + ", ClubName:"+ member.getClubName()
                        + "\nBranch:"+ member.getBranch() + ", BeltLevel:"+ member.getBeltLevel();

            }
        }else if(member!=null&&member.getMemberNum().equals("NULL")){

            str = this.getResources().getString(R.string.checkresult);

        }

        tv.setText(str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        DetailActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean bool = false;

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                bool = true;
        }

        return bool;
    }
}