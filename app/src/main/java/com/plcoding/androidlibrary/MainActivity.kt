package com.plcoding.androidlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.androidlibrary.ui.theme.AndroidLibraryTheme
import com.plcoding.image_preview.ImagePreview
import com.plcoding.image_preview.ShowCustomMessage
import com.plcoding.image_preview.ShowCustomTextBox


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AndroidLibraryTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    //ShowCustomTextBox(desc="Name")
                    ImagePreview(image = painterResource(id = R.drawable.kermit),
                       description = "Hello  !!",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(150.dp)
                        )
                    ImagePreview(image = painterResource(id = R.drawable.kermit),
                        description = "Hello   2!!",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(150.dp)
                    )
                }
            }

        }
        //setContent{
            //ShowCustomMessage(context = this, message = "Hello Testing !!")
            //ShowCustomTextBox(desc="Name")
        //}
    }
}
