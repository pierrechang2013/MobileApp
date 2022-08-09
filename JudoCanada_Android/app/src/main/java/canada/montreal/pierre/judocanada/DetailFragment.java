package canada.montreal.pierre.judocanada;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    TextView tv;
    Button button;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail, null);
        setHasOptionsMenu(true);


        tv = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.button2);
        String str = "";


        Bundle bundle = getArguments();
        Member member = (Member)  bundle.getParcelable("member");

        if (member != null && (!member.getMemberNum().equals("NULL"))) {
            str = "FirstName:" + member.getFirstName() + "\n\nLastName:" + member.getLastName()
                    + "\n\nBirthDate:" + member.getBirthDate() + "\n\nMemberNum:" + member.getMemberNum()
                    + "\n\nSeasonYear:" + member.getSeasonYear() + "\n\nClubName:" + member.getClubName()
                    + "\n\nP/T:" + member.getBranch() + "\n\nBeltLevel:" + member.getBeltLevel();

            Configuration configuration = getResources().getConfiguration();

            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {


                str = "FirstName:" + member.getFirstName() + ", LastName:" + member.getLastName()
                        + "\nBirthDate:" + member.getBirthDate() + ", MemberNum:" + member.getMemberNum()
                        + "\nSeasonYear:" + member.getSeasonYear() + ", ClubName:" + member.getClubName()
                        + "\nBranch:" + member.getBranch() + ", BeltLevel:" + member.getBeltLevel();

            }
        } else if (member != null && member.getMemberNum().equals("NULL")) {

            str = this.getResources().getString(R.string.checkresult);

        }

        tv.setText(str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).setFragment(new LoginFragment());
            }
        });

        return view;
    }
}