package com.example.cbocs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NotesFragment extends Fragment {
    EditText notes;
    Button confirm, back;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.notes_fragment, container, false);
        notes = fragView.findViewById(R.id.notesText);
        confirm = fragView.findViewById(R.id.confirm);
        back = fragView.findViewById(R.id.back);

        configConfirm();
        configBack();
        return fragView;
    }

    void configConfirm(){
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OrderHolder oH = new OrderHolder();
                String notesAdd = notes.getText().toString();

                try{
                    oH.myInstance().orderList.add(new OrderCard(oH.myInstance().guestCount,
                            oH.myInstance().orderName,
                            oH.myInstance().side1,
                            oH.myInstance().side2,
                            oH.myInstance().side3,
                            oH.myInstance().bread,
                            notesAdd));
                }
                catch(Exception e){
                    oH.myInstance().orderList.add(new OrderCard(1,
                            oH.myInstance().orderName,
                            oH.myInstance().side1,
                            oH.myInstance().side2,
                            oH.myInstance().side3,
                            oH.myInstance().bread,
                            notesAdd));
                }

                if(true) {


                    oH.myInstance().noa.notifyDataSetChanged();
                    Intent i = oH.myInstance().ano.getIntent();
                    oH.myInstance().ano.setResult(420, i);
                    getActivity().finish();
                }
                else{
                    //Do Nothing
                }
            }
        });
    }

    void configBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will need to go back to previous fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new BreadSelector());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

}
