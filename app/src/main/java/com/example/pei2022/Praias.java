package com.example.pei2022;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Objects;

public class Praias extends AppCompatActivity {

    String URLps = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/praiasecretgiga.png?alt=media&token=830b520f-8a9a-4453-a89b-f29a59a41783";
    String URLpf = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/foto%2002.JPG?alt=media&token=94371782-40ca-476e-bad5-393ef2942c55";
    String URLcj = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/curva%20da%20juremagiga.jpg?alt=media&token=b04497db-dadb-4a20-9d3b-476573d28abf";

    MapView mapViewCJ, mapViewPS, mapViewPF;

    GeoPoint CurvaJurema = new GeoPoint( -20.309074613578854, -40.2882398440651);
    GeoPoint PraiaSecreta = new GeoPoint( -20.65397702008593, -40.46622457463485);
    GeoPoint PraiaFrade = new GeoPoint( -20.303328905567227, -40.28550940347909);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praias);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SwitchCompat VejaMaisPS = findViewById(R.id.VejaMaisPS);
        LinearLayout layoutps = findViewById(R.id.layoutps);
        layoutps.setVisibility((View.GONE));

        SwitchCompat VejaMaisPF = findViewById(R.id.VejaMaisPF);
        LinearLayout layoutpf = findViewById(R.id.layoutpf);
        layoutpf.setVisibility((View.GONE));

        SwitchCompat VejaMaisCJ = findViewById(R.id.VejaMaisCJ);
        LinearLayout layoutcj = findViewById(R.id.layoutcj);
        layoutcj.setVisibility((View.GONE));

        ImageView imageViewPS = findViewById(R.id.imageViewPS);
        ImageView imageViewPF = findViewById(R.id.imageViewPF);
        ImageView imageViewCJ = findViewById(R.id.imageViewCJ);

        mapViewCJ = findViewById(R.id.mapViewCJ);
        mapViewPS = findViewById(R.id.mapViewPS);
        mapViewPF = findViewById(R.id.mapViewPF);

        generateMap(mapViewCJ, CurvaJurema, "Curva da Jurema");
        generateMap(mapViewPS, PraiaSecreta, "Praia Secreta");
        generateMap(mapViewPF, PraiaFrade, "Praia do Frade");


        Glide.with(Praias.this).load(URLps).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewPS);
        Glide.with(Praias.this).load(URLpf).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewPF);
        Glide.with(Praias.this).load(URLcj).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewCJ);

        VejaMaisPS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutps.setVisibility(View.VISIBLE);
                } else {
                layoutps.setVisibility(View.GONE);

            }
        });
        VejaMaisPF.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutpf.setVisibility(View.VISIBLE);
            } else {
                layoutpf.setVisibility(View.GONE);

            }
        });
        VejaMaisCJ.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutcj.setVisibility(View.VISIBLE);
            } else {
                layoutcj.setVisibility(View.GONE);

            }
        });
    }

    public void generateMap(MapView mapView, GeoPoint geoPoint, String marker ){
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(16.0);
        mapController.setCenter(geoPoint);

        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem(marker, "", geoPoint);
        Drawable m = home.getMarker(0);
        items.add(home);

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(mOverlay);
    }

    @Override
    public void onPause(){
        super.onPause();
        mapViewCJ.onPause();
        mapViewPS.onPause();
        mapViewPF.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        mapViewCJ.onResume();
        mapViewPS.onResume();
        mapViewPF.onResume();
    }


}

