package com.plcoding.image_preview

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun ImagePreview(
    image: Painter,
    modifier: Modifier = Modifier,
    description: String = "",
    contentDescription: String = "",
    onImageClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .shadow(15.dp, RoundedCornerShape(15.dp))
            .clickable { onImageClick() }
    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}


@Composable
fun ShowCustomMessage(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun ShowCustomTextBox(desc: String){
    Text(text = desc, modifier = Modifier
        .size(150.dp)
        .height(20.dp)
        .width(100.dp))
}


@Composable
fun generateForm(context:Context,
                 onFormSubmitCallback: (Context) -> Unit = {},
                 onFormbackCallback: (Context) -> Unit = {}){

    var consumerName by rememberSaveable { mutableStateOf("") }
    var businessPartner by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .border(5.dp, color = Color.Magenta)
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.padding(4.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Consumer Name",
                    style = MaterialTheme.typography.h5
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = consumerName,
                    onValueChange = { consumerName = it },
                    placeholder = { Text(text = "e.g. Consumer Name") },
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Business Partner",
                    style = MaterialTheme.typography.h5
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = businessPartner,
                    onValueChange = { businessPartner = it },
                    placeholder = { Text(text = "e.g. Business Partner") },
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Address",
                    style = MaterialTheme.typography.h5
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text(text = "e.g. Address") },
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = { onFormSubmitCallback(context) }) {
                    Text(
                        text = "Submit",
                        style = MaterialTheme.typography.button
                    )
                }
                Button(modifier = Modifier
                    .width(150.dp)
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = { onFormbackCallback(context) }, ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.button
                    )
                }
            }

        }
    }

}


@Composable
fun TodoView(vm: TodoViewModel, onBackButtonClick: () -> Unit = {}) {
    LaunchedEffect(Unit, block = {
        vm.getTodoList()
    })

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Text("Todos")
                    Button(onClick = {
                        onBackButtonClick()
                    }) {
                        Text("Click to Back")
                    }
                }
            })
        },
        content = {
            if(vm.errorMessage.isEmpty()){
                Column(modifier = Modifier.padding(15.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(vm.todoList){
                                todo ->
                            Column {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Box(   modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 0.dp, 16.dp, 0.dp) ){
                                        Checkbox(checked = todo.completed, onCheckedChange = null)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            modifier = Modifier.padding(start = 10.dp),
                                            text = todo.title, maxLines = 2,
                                            overflow = TextOverflow.Ellipsis)


                                    }
                                    Divider()
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                Text(vm.errorMessage)
            }
        }
    )


}



