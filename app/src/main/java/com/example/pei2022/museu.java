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

public class museu extends AppCompatActivity {

    String URLpa = "https://firebasestorage.googleapis.com/v0/b/pei2022.appspot.com/o/anchieta.jpg?alt=media&token=f12844a8-a005-4f4c-be78-75becc25af6f";

    MapView mapViewPA;

    GeoPoint PalacioAnchieta = new GeoPoint( -20.32119605053253, -40.33970503906105);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museu);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SwitchCompat VejaMaisPA = findViewById(R.id.VejaMaisPA);
        LinearLayout layoutpa = findViewById(R.id.layoutPA);
        layoutpa.setVisibility((View.GONE));

        ImageView imageViewPA = findViewById(R.id.imageViewPA);

        mapViewPA = findViewById(R.id.mapViewPA);

        generateMap(mapViewPA, PalacioAnchieta, "Palácio do Anchieta");

        Glide.with(museu.this).load(URLpa).placeholder(R.drawable.progress_bar).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageViewPA);

        VejaMaisPA.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                layoutpa.setVisibility(View.VISIBLE);
            } else {
                layoutpa.setVisibility(View.GONE);

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