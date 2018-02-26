package droidmentor.PoliticTeens_Client.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import droidmentor.PoliticTeens_Client.Jundang;
import droidmentor.PoliticTeens_Client.R;
import droidmentor.PoliticTeens_Client.S;

/**
 * A simple {@link Fragment} subclass.
 */
public class JundangFormFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static final String REQUIRED = "입력해주세요";


    EditText just_name,just_category,just_idea;
    Button register;

    public JundangFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_jundang_form, container, false);
        just_name=(EditText)view.findViewById(R.id.just_name);
        just_category=(EditText)view.findViewById(R.id.just_category);
        just_idea=(EditText)view.findViewById(R.id.just_idea);
        register=(Button)view.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(just_name.getText().toString()))
                {
                    just_name.setError(REQUIRED);
                    return;
                }
                else if(TextUtils.isEmpty(just_category.getText().toString()))
                {
                    just_category.setError(REQUIRED);
                    return;
                }
                else if(TextUtils.isEmpty(just_idea.getText().toString()))
                {
                    just_idea.setError(REQUIRED);
                    return;
                }else{
                    S.just_name=just_name.getText().toString();
                    Log.d("정당이름"+ S.just_name,"ㅅㅂ");
                    S.just_category=just_category.getText().toString();
                    S.just_idea=just_idea.getText().toString();

                    Jundang jundang=new Jundang(S.just_name, S.just_category, S.just_idea);

                    // 사용자의 클럽아이디 가지고오기
                    databaseReference.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            S.club_id=dataSnapshot.child(S.userId).child("club").getValue(Integer.class);
                           Log.d("dddd",Integer.toString(S.club_id));
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                    databaseReference.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            S.club_id=dataSnapshot.child(S.userId).child("club").getValue(Integer.class);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                    // 사용자의 클럽아이디 가져오기
                    if(S.club_id != 0)
                    {
                        // 사용자가 가입된 정당이 있는지 확인
                        Toast.makeText(getContext(),"이미 가입된 정당이 있습니다.",Toast.LENGTH_LONG).show();
                    } else if(S.club_id == 0) {
                        // 가입된 정당이 없을때 디비 등록
                        //  databaseReference.child("club").child(S.just_name).push().setValue(jundang);

                        databaseReference.child("club").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                S.just_count=dataSnapshot.getChildrenCount();
                                databaseReference.child("users").child(S.userId).child("club").setValue(S.just_count);
                                databaseReference.child("club").child(S.just_name).child("club").setValue(S.just_count++);
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

                        databaseReference.child("club").child(S.just_name).child("category").setValue(S.just_category);
                        databaseReference.child("club").child(S.just_name).child("idea").setValue(S.just_idea);
                        databaseReference.child("club").child(S.just_name).child("level").setValue(S.just_level);
                        databaseReference.child("club").child(S.just_name).child("partisian").setValue(S.partisian);
                        databaseReference.child("club").child(S.just_name).child("name").setValue(S.userName);

                        Toast.makeText(getContext(), "새로운 정당 " + S.just_name + "를 만들었습니다!", Toast.LENGTH_LONG).show();
                        Log.d("시방 클럽 들어가기 성공~~", "ㅅㅂ");

                    }

                }
            }
        });

        return view;
    }

}
