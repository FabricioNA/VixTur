package com.example.pei2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class IC extends AppCompatActivity {

    String URLcp = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/penhaofconvent.jpg?alt=media&token=b404e942-cd37-4e16-a620-7ab34cf4b044";
    String URLcm= "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/imagem_2023-06-20_040626606.png?alt=media&token=adf8fbb7-d03b-4a60-ab75-732cb1fcd9c2";

    MapView mapViewCP, mapViewCM;

    GeoPoint ConventoPenha = new GeoPoint( -20.32935747422874, -40.28706905731488);
    GeoPoint CatedralMetro = new GeoPoint( -20.320015942228576, -40.337446785687604);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SwitchCompat VejaMaisCP = findViewById(R.id.VejaMaisCP);
        LinearLayout layoutCP = findViewById(R.id.layoutCP);
        layoutCP.setVisibility((View.GONE));

        SwitchCompat VejaMaisCM = findViewById(R.id.VejaMaisCM);
        LinearLayout layoutCM = findViewById(R.id.layoutCM);
        layoutCM.setVisibility((View.GONE));

        ImageView imageViewCP = findViewById(R.id.imageViewCP);
        ImageView imageViewCM = findViewById(R.id.imageViewCM);

        mapViewCP = findViewById(R.id.mapViewCP);
        mapViewCM = findViewById(R.id.mapViewCM);

        generateMap(mapViewCP, ConventoPenha, "Convento da Penha");
        generateMap(mapViewCM, CatedralMetro, "Catedral Metropolitana de Vitória");

        Glide.with(IC.this).load(URLcp).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewCP);
        Glide.with(IC.this).load(URLcm).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewCM);

        VejaMaisCP.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutCP.setVisibility(View.VISIBLE);
            } else {
                layoutCP.setVisibility(View.GONE);

            }
        });
        VejaMaisCM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutCM.setVisibility(View.VISIBLE);
            } else {
                layoutCM.setVisibility(View.GONE);

            }
        });
    }

    public void generateMap(MapView mapView, GeoPoint geoPoint, String marker) {
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
}