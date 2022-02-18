package com.example.isomanhealthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView head2, head3;

    Button logout;
    Button edit;
    ImageView btnBack;
    SharedPreferences sharedPreferences;
    View v;
    RecyclerView recyclerView;
    List<AkunClass> eList;
    AkunAdapter akunAdapter;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        eList = new ArrayList<>();
        logout = (Button) getActivity().findViewById(R.id.btnLogout);
        sharedPreferences = this.getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        String url = getString(R.string.api_server)+"/user";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(getContext(), url);
                http.setToken(true);
                http.send();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String nama = response.getString("name");
                                String email = response.getString("email");
                                String jk = response.getString("jenis_kelamin");
                                String tgl = response.getString("tgl_lahir");
                                String alamat = response.getString("alamat");
                                String mulai_isolasi = response.getString("mulai_isolasi");
                                String golDarah = response.getString("golongan_darah");
                                String bb = response.getString("berat_badan");
                                String statusVaksin = response.getString("status_vaksin");
                                String created_at = response.getString("created_at");
                                head2 = v.findViewById(R.id.head2);
                                head2.setText(nama);

                                eList.add(new AkunClass("Nama Lengkap", nama));
                                eList.add(new AkunClass("Jenis Kelamin", jk));
                                eList.add(new AkunClass("Tanggal Lahir", tgl));
                                eList.add(new AkunClass("Alamat", alamat));
                                eList.add(new AkunClass("Mulai Isolasi", mulai_isolasi));
                                eList.add(new AkunClass("Golongan Darah", golDarah));
                                eList.add(new AkunClass("Berat Badan", bb));
                                eList.add(new AkunClass("Status Vaksin", statusVaksin));
                                akunAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_akun, container, false);
        recyclerView = v.findViewById(R.id.recyclerViewId);

        akunAdapter = new AkunAdapter(eList, getContext());
        recyclerView.setAdapter(akunAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        long date = System.currentTimeMillis();
        Locale locale = new Locale("id", "ID");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale);
        String dateString = sdf.format(date);
        head3 = v.findViewById(R.id.head3);
        head3.setText(dateString);
        edit = (Button) v.findViewById(R.id.btnEdit);

        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutId, new EditAkunFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnBack = (ImageView) v.findViewById(R.id.kembali);
//        final BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView navView = getActivity().findViewById(R.id.bottomNavigationView);
                MenuItem panduanItem = navView.getMenu().getItem(0);
                navView.setSelectedItemId(panduanItem.getItemId());
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutId, new PanduanFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;
    }

    public void logoutBtn(View view) { logout(); }

    private void logout() {
        String url = getString(R.string.api_server)+"/logout";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(getContext(), url);
                http.setMethod("post");
                http.setToken(true);
                http.send();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(LoginActivity.session_status, false);
                            editor.commit();

                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}