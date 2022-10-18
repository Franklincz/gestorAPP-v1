package com.example.gestorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gestorapp.Entities.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;// hamburge
TextView user,gmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.content,new HomeFragment()).commit();
      // getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("my_fragment").commit();
        setTitle("Home");

        //setup toolbar
        setSupportActionBar(toolbar);
        toggle=setUpdrawerToogle();
        mDrawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        user = (TextView)header.findViewById(R.id.usuario);
       gmail = (TextView)header.findViewById(R.id.gmailusuario);
        User userActive = (User) getIntent().getSerializableExtra("user");
        user.setText(userActive.getNombres()+" "+userActive.getApellidos());
        gmail.setText(userActive.getCorreo());
    }

    private ActionBarDrawerToggle setUpdrawerToogle(){
        return new ActionBarDrawerToggle(this,
                mDrawerLayout,toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
    }


    @Override
    protected void  onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectItemNav(item);
        return true;
    }
    private void selectItemNav(MenuItem item){
        FragmentManager fm= getSupportFragmentManager();

        FragmentTransaction ft= fm.beginTransaction();
        //getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("my_fragment").commit();
        switch (item.getItemId()){
            case (R.id.nav_puntos_venta):
                ft.replace(R.id.content,new HomeFragment()).commit();
                break;
            case (R.id.nav_soporte):
                ft.replace(R.id.content,new SoporteFragment()).commit();
                break;
            case (R.id.nav_cerrar_sesion):
                finish();
                break;

        }
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();//ocultar drawer cada vez


        //return fragment
     /*   @Override
        public void onBackPressed() {
            final Myfragment fragment = (Myfragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);

            if (fragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
                super.onBackPressed();
            }
        }
*/

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}