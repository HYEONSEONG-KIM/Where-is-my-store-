package com.wims.whereismystore.Activity.Fragments;


import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wims.whereismystore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Fragment2 extends Fragment {
    //현황기능 class

    //private SupportMapFragment mapFragment;
    private GoogleMap map;
    private View view;
    private Spinner localspin;
    private Spinner districtspin;
    private ArrayAdapter<CharSequence> loadspin, diadspin;
    private String province, city;
    private String address_string;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);

        localspin = (Spinner) view.findViewById(R.id.load_local);
        districtspin = (Spinner) view.findViewById(R.id.load_district);
        Button address_button = (Button) view.findViewById(R.id.address_button);

        loadspin = ArrayAdapter.createFromResource(getActivity(), R.array.local, R.layout.support_simple_spinner_dropdown_item);
        loadspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        localspin.setAdapter(loadspin);
        localspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (loadspin.getItem(position).equals("서울특별시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.서울특별시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("부산광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.부산광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("대구광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대구광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("인천광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.인천광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("광주광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.광주광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("대전광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대전광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("울산광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.울산광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경기도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경기도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("강원도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.강원도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("충청북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("충청남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("전라북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("전라남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경상북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경상남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("제주특별자치도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.제주특별자치도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.세종특별자치도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                }

                // 하위 스피너 선택 시 :D
                districtspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        city = (String) districtspin.getSelectedItem();
                        province = (String) localspin.getSelectedItem();
                        address_string = province + " " + city;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Geocoder geocoder = new Geocoder(getContext());
        final SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                // 서울 여의도에 대한 위치 설정
                LatLng seoul = new LatLng(37.52487, 126.92723);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 13));
            }
        });
        mapFragment.getView().setVisibility(View.GONE);


        address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddrssTrans().execute(address_string);
                new JsonParse().execute("http://220.66.144.32/wims/test.php",address_string);
                mapFragment.getView().setVisibility(View.VISIBLE);
//                    }
//                }
            }
        });
        return view;
    }


    public class JsonParse extends AsyncTask<String, Void, String> {
        String TAG = "JsonParseTest";

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String address = "address="+strings[1] + "%";
            Log.i("OutPutData", address);
            try {
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) serverURL.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(address.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null && !isCancelled())
                    sb.append(line);

                bufferedReader.close();
                Log.d(TAG, sb.toString().trim());
                httpURLConnection.disconnect();
                return sb.toString().trim();
            } catch (Exception e) {
                Log.d(TAG, "InsertData:Error", e);
                String errorString = e.toString();

                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String fromdoInBackgroundString) {
            //super.onPostExecute(fromdoInBackgroundString);
            if (fromdoInBackgroundString == null) {
                Log.i("Jsonparse String 에러", "error");
            } else {
                String jsonString = fromdoInBackgroundString;
                Log.i("jsonString",jsonString);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String name = item.getString("개방서비스명");
                        String state = item.getString("영업상태명");
                        String jaddress = item.getString("도로명전체주소");
                        Log.i("jsonString_name", name);
                        Log.i("jsonString_state", state);
                        Log.i("jsonString_address", jaddress);
                        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getActivity(), name + " | " + state + " | " + latS + " | " + langS, Toast.LENGTH_SHORT).show();
                        //  double lat = Double.parseDouble(latS);
                        // double lang = Double.parseDouble(langS);

                        new MapPoint().execute(jaddress, name, state);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MapPoint extends AsyncTask<String, Void, LatLng> {
        String Mstate = "";
        String Mname = "";

        @Override
        protected LatLng doInBackground(String... strings) {
            String address = strings[0];
            String name = strings[1];
            String state = strings[2];
            Mname = name;
            Mstate = state;
            Geocoder geo = new Geocoder(getActivity());
            List<Address> list = null;
            try {
                list = geo.getFromLocationName(address, // 지역 이름
                        30); // 읽을 개수
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
            }

            if (list != null) {
                if (list.size() == 0) {
                } else {
                    // 해당되는 주소로 인텐트 날리기
                    Address addr = list.get(0);
                    double lat = addr.getLatitude();
                    double lon = addr.getLongitude();
                    LatLng latlng = new LatLng(lat, lon);
                    return latlng;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(LatLng latlng) {
            LatLng point = latlng;
            Log.i("MapPoint_name",Mname);
            Log.i("MapPoint_state",Mstate);
            if (point != null) {
                MarkerOptions markerOptions;
                if(Mstate.equals("페업"))
                    markerOptions = new MarkerOptions().position(point).title(Mname).snippet(Mstate);
                else
                    markerOptions = new MarkerOptions().position(point).title(Mname).snippet(Mstate).icon(BitmapDescriptorFactory.defaultMarker(200f));
                map.addMarker(markerOptions).showInfoWindow();
            }
        }
    }

    public class AddrssTrans extends AsyncTask<String, Void, LatLng> {
        @Override
        protected LatLng doInBackground(String... strings) {
            String address = strings[0];
            Geocoder geo = new Geocoder(getActivity());
            List<Address> list = null;
            try {
                list = geo.getFromLocationName(address, // 지역 이름
                        10); // 읽을 개수
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
            }

            if (list != null) {
                if (list.size() == 0) {
                } else {
                    // 해당되는 주소로 인텐트 날리기
                    Address addr = list.get(0);
                    double lat = addr.getLatitude();
                    double lon = addr.getLongitude();
                    LatLng latlng = new LatLng(lat, lon);
                    return latlng;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(LatLng latlng) {
            LatLng point = latlng;
            if(point != null)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
        }
    }
}