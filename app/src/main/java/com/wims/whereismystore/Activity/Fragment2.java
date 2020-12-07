package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
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

import com.example.Final_Project.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Fragment2 extends Fragment  {
    //현황기능 class

    private SupportMapFragment mapFragment;
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
        Spinner();
        final Geocoder geocoder = new Geocoder(getActivity());

        final SupportMapFragment mapFragment = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                // 서울 여의도에 대한 위치 설정
                LatLng seoul = new LatLng(37.52487, 126.92723);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,13));
            }
        });

        mapFragment.getView().setVisibility(View.GONE);

        Button address_button = (Button) view.findViewById(R.id.address_button);
        address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 주소입력후 지도2버튼 클릭시 해당 위도경도값의 지도화면으로 이동
                List<Address> list = null;

                String str = address_string;
                try {
                    list = geocoder.getFromLocationName
                            (str, // 지역 이름
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
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
                        mapFragment.getView().setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        return view;
    }

    private void Spinner() {
        localspin = (Spinner) view.findViewById(R.id.load_local);
        districtspin = (Spinner) view.findViewById(R.id.load_district);

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
    }
}
