package com.example.pei2022;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class Parques extends AppCompatActivity {

    String URLpc = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/stoneofonion.jpg?alt=media&token=c0b61f30-f1c0-49e3-b80d-f59cfb6155ce";
    String URLpb = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/parque-botanico-vale.jpg?alt=media&token=847f57d4-c57f-417c-8067-14c9e6baf0da";

    MapView mapViewPC, mapViewPB;

    GeoPoint PedraCebola = new GeoPoint( -20.276551785138537, -40.29771314580953);
    GeoPoint BotanicoVale = new GeoPoint( -20.25819958112037, -40.26018290348023);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parques);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SwitchCompat VejaMaisPC = findViewById(R.id.VejaMaisPC);
        LinearLayout layoutpc = findViewById(R.id.layoutpc);
        layoutpc.setVisibility((View.GONE));

        SwitchCompat VejaMaisPB = findViewById(R.id.VejaMaisPB);
        LinearLayout layoutpb = findViewById(R.id.layoutpb);
        layoutpb.setVisibility((View.GONE));

        ImageView imageViewPC = findViewById(R.id.imageViewPC);
        ImageView imageViewPB = findViewById(R.id.imageViewPB);

        mapViewPC = findViewById(R.id.mapViewPC);
        mapViewPB = findViewById(R.id.mapViewPB);

        generateMap(mapViewPC, PedraCebola, "Parque Pedra da Cebola");
        generateMap(mapViewPB, BotanicoVale, "Parque Botânico Vale");

        Glide.with(Parques.this).load(URLpc).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewPC);
        Glide.with(Parques.this).load(URLpb).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewPB);

        VejaMaisPC.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutpc.setVisibility(View.VISIBLE);
            } else {
                layoutpc.setVisibility(View.GONE);

            }
        });
        VejaMaisPB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutpb.setVisibility(View.VISIBLE);
            } else {
                layoutpb.setVisibility(View.GONE);

            }
        });
    }
    public void generateMap(MapView mapView, GeoPoint geoPoint, String marker ) {
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