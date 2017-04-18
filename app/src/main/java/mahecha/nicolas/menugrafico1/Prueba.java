package mahecha.nicolas.menugrafico1;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import mahecha.nicolas.menugrafico1.RS232.ServicioRs;
import mahecha.nicolas.menugrafico1.MiServiceIBinder.MiBinderIBinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Prueba extends Fragment {


    private MiServiceIBinder mServiceIBinder;

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

       // testin();

        // CONFIGURACION SERVICE IBINDER

        test.setText("entra");





            Button bt_Resultado_IB = (Button)v.findViewById(R.id.bt_get_result);
            bt_Resultado_IB.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mServiceIBinder != null) {
                        String resultado = String.valueOf(mServiceIBinder.getResultado());
                        test.setText("Su resuldato es: " + resultado);
                    }
                }
            });





        return v;
    }

    private void testin() {

       // test.setText("probando");



    }





}
