package com.example.excelreherseal

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.excelreherseal.ui.theme.ExcelRehersealTheme
import com.opencsv.CSVReader
import java.io.FileReader


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExcelRehersealTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExcelActivity()
                }
            }
        }
    }
}



@Composable
fun ExcelActivity() {

    // Gerekli izinleri kontrol et
    if (ActivityCompat.checkSelfPermission(
            LocalContext.current,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            LocalContext.current as Activity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        return
    }

    // Excel dosyasını aç
    val filePath = Environment.getExternalStorageDirectory().absolutePath + "/Download/deneme.csv"


    val reader = CSVReader(FileReader(filePath))

    // Excel dosyasından verileri oku
    val data = reader.readAll()

    // Verileri uygulamada göster
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        for (row in data) {
            Text(text = row.joinToString(","))
            Log.d("deneme",row.joinToString(","))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    // Excel dosyasına veri ekle
    Button(
        modifier = Modifier
            //.fillMaxWidth()
            .width(500.dp)
            .padding(200.dp),
        onClick = {
            // TODO: Excel dosyasına veri ekle
        }
    ) {
        Text("Excel dosyasına veri ekle")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExcelRehersealTheme {
        ExcelActivity()
    }
}