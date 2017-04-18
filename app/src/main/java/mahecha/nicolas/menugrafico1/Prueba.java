package mahecha.nicolas.menugrafico1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import mahecha.nicolas.menugrafico1.RS232.ServicioRs;


/**
 * A simple {@link Fragment} subclass.
 */
public class Prueba extends Fragment {




    TextView test;
    public Prueba() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_prueba, container, false);
        test = (TextView)v.findViewById(R.id.testo) ;

        testin();
        return v;
    }

    private void testin() {

        test.setText("probando");


    }


}
