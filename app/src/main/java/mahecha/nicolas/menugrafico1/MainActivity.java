package mahecha.nicolas.menugrafico1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import mahecha.nicolas.menugrafico1.RS232.ServicioRs;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private MiServiceIBinder mServiceIBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ///////////////*************FRAGMENTOS***************////////////////


        Mapa1 mapa1 = new Mapa1();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, mapa1);

        Mapa1 map = new Mapa1();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor,map, "tag");
        ft.addToBackStack("tag");
        ft.commit();





        // CONFIGURACION SERVICE IBINDER

        final TextView texto = (TextView) findViewById(R.id.tx_ser);

        Button bt_Start_Service_IB = (Button) findViewById(R.id.bt_start_service_ibinder);
        bt_Start_Service_IB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MiServiceIBinder.class);
                bindService(intent, sConnectionIB, Context.BIND_AUTO_CREATE);
            }
        });

        Button bt_Resultado_IB = (Button) findViewById(R.id.bt_get_result);
        bt_Resultado_IB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mServiceIBinder != null) {
                    String resultado = String.valueOf(mServiceIBinder.getResultado());
                    texto.setText("Su resuldato es: " + resultado);
                }
            }
        });

        Button bt_Stop_Service_IB = (Button) findViewById(R.id.bt_stop_service_ibinder);
        bt_Stop_Service_IB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mServiceIBinder != null) {
                    mServiceIBinder.stopForeground(true);
                    unbindService(sConnectionIB);
                    mServiceIBinder = null;
                }
            }
        });





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Mapa1 frag1 = new Mapa1();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, frag1);

        if (id == R.id.nav_camera) {
            Mapa1 fragm = new Mapa1();
//            Bundle parametro = new Bundle();
//            parametro.putString("id_us",idusuar);
//            fragm.setArguments(parametro);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, fragm, "tag");
            ft.addToBackStack("tag");
            ft.commit();

        } else if (id == R.id.nav_gallery) {
            Prueba fragm = new Prueba();
//            Bundle parametro = new Bundle();
//            parametro.putString("id_us",idusuar);
//            fragm.setArguments(parametro);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contenedor, fragm, "tag");
            ft.addToBackStack("tag");
            ft.commit();

        } else if (id == R.id.nav_slideshow) {
//            Intent Rs232 = new Intent(getApplicationContext(),ServicioRs.class);
//            getApplicationContext().startService(Rs232);

            Intent intent = new Intent(this, ServicioRs.class);
            bindService(intent, sConnectionIB, Context.BIND_AUTO_CREATE);

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // CONFIGURACION INTERFACE SERVICECONNECTION IBINDER
    private ServiceConnection sConnectionIB = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MiServiceIBinder.MiBinderIBinder binder = (MiServiceIBinder.MiBinderIBinder) service;
            mServiceIBinder = binder.getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };


}
