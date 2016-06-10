package kab.com.retrofit2vs2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,MyCallback {
    ViewGroup mViewGroup;
    private GetData mData;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mGPS;
    private ProgressBar mBar;
    private TextView mTxtResult;
    Fragment mFragCardFull;
    Fragment mFragCard;
    FragmentTransaction mFTrans;


    @Override
    public void onConnected(Bundle connectionHint) {
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
            Log.e("onConnected", "Error " + e.toString());

        }
        if (mLastLocation != null) {
            Log.e("getLatitude", String.valueOf(mLastLocation.getLatitude()));
            Log.e("getLongitude", String.valueOf(mLastLocation.getLongitude()));
            mGPS = mLastLocation.getLatitude() + "," + mLastLocation.getLongitude();
        } else {
            mGPS = "50.0783,14.436";
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewGroup = (ViewGroup) this.findViewById(android.R.id.content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLocation();
        mFragCardFull = new Fragment_full_card();
        mFragCard = new Fragment_card();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mBar = (ProgressBar) findViewById(R.id.pBar);
        mTxtResult = (TextView) findViewById(R.id.txtErrors);

        mData = new GetData(getApplicationContext(),this);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCurrentFragment()==0) {
                    mData.getAirport(mGPS);
                    mBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getLocation() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mTxtResult.setText(connectionResult.toString());
    }

    @Override
    public void callbackCall(List<Airport> air) {
        createRecyclerViewTemplate(air);
    }

    public void createRecyclerViewTemplate(List<Airport> air) {
        createFragmentCard();
        mBar.setVisibility(View.INVISIBLE);
        mTxtResult.setText("");
    }

    @Override
    public void callbackError(String err) {
        mTxtResult.setText(err);
    }

    @Override
    public void callbackFragment(String sity) {
        mData = new GetData(getApplicationContext(), this,ServiceConnector.API_BASE_URL2);
        mData.getAuth(sity);
    }

    @Override
    public void callbackGetDataToFragment(List<Data> data) {
        createFragmentFullCard();
    }

    public int checkCurrentFragment() {

        Fragment myFragment1 =  getFragmentManager().findFragmentByTag("MY_FRAGMENT1");
        Fragment myFragment2 =  getFragmentManager().findFragmentByTag("MY_FRAGMENT2");

       if (myFragment1 != null && myFragment1.isVisible()) {
            return 1;
        }
        if (myFragment2 != null && myFragment2.isVisible()) {
            return 2;
        }
        return 0;
    }

    public void createFragmentFullCard() {
        mTxtResult.setText("");
        mFTrans = getFragmentManager().beginTransaction();
        mFTrans.replace(R.id.fl1, mFragCardFull, "MY_FRAGMENT2");
        mFTrans.addToBackStack(null);
        mFTrans.commit();
    }

    public void deleteFragmentFullCard() {
        mTxtResult.setText("");
        mFTrans = getFragmentManager().beginTransaction();
        mFTrans.remove(mFragCardFull);
        mFTrans.commit();
    }

    public void createFragmentCard() {
        mTxtResult.setText("");
        mFTrans = getFragmentManager().beginTransaction();
        mFTrans.replace(R.id.fl1, mFragCard, "MY_FRAGMENT1");
        mFTrans.addToBackStack(null);
        mFTrans.commit();
    }

    public void deleteFragmentCard() {
        mTxtResult.setText("");
        mFTrans = getFragmentManager().beginTransaction();
        mFTrans.remove(mFragCard);
        mFTrans.commit();
    }

    @Override
    public void onBackPressed() {
            if (checkCurrentFragment()==1) {
                this.finish();
            } else {
                super.onBackPressed();
            }
    }

}
