package com.example.pei2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
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

public class Belezas_Naturais extends AppCompatActivity {

    String URLmm = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/morro%20do%20moreno.jpg?alt=media&token=5b5bb263-7c28-4a74-bdf2-e1ed8d6dd4cb";
    String URLfg = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/Parque_da_Fonte_Grande_02.jpg?alt=media&token=ffb77013-c788-45af-a2d2-506d870aaa72";

    MapView mapViewMM, mapViewFG;

    GeoPoint MorroMoreno = new GeoPoint( -20.326306781027686, -40.27746350340441);
    GeoPoint FonteGrande = new GeoPoint( -20.300588887223064, -40.33833343425778);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belezas_naturais);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SwitchCompat VejaMaisMM = findViewById(R.id.VejaMaisMM);
        SwitchCompat VejaMaisFG = findViewById(R.id.VejaMaisFG);
        LinearLayout layoutmm = findViewById(R.id.layoutmm);
        LinearLayout layoutfg = findViewById(R.id.layoutfg);
        layoutmm.setVisibility((View.GONE));
        layoutfg.setVisibility((View.GONE));

        ImageView imageViewMM = findViewById(R.id.imageViewMM);
        ImageView imageViewFG = findViewById(R.id.imageViewFG);

        mapViewMM = findViewById(R.id.mapViewMM);
        mapViewFG = findViewById(R.id.mapViewFG);

        generateMap(mapViewMM, MorroMoreno, "Morro do Moreno");
        generateMap(mapViewFG, FonteGrande, "Mirante Fonte Grande");

        Glide.with(Belezas_Naturais.this).load(URLmm).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewMM);
        Glide.with(Belezas_Naturais.this).load(URLfg).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewFG);

        VejaMaisMM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutmm.setVisibility(View.VISIBLE);
            } else {
                layoutmm.setVisibility(View.GONE);

            }
        });
        VejaMaisFG.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutfg.setVisibility(View.VISIBLE);
            } else {
                layoutfg.setVisibility(View.GONE);

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
}