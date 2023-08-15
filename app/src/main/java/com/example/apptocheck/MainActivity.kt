package com.example.apptocheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.apptocheck.ui.theme.AppToCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppToCheckTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IntentTestScreen()
                }
            }
        }
    }
}

val fieldIdList = listOf(
    "99402005-fe6d-4210-bf5e-b741b878bdd7",
    "993e7c09-0df8-4d2c-a4d8-0e71d21d8669",
    "993e7c06-97f2-4429-9482-eb111bb0d343",
    "993e7c04-aba8-421c-aac6-08baf4e92e52",
    "993e7c09-0f87-4ca5-95ec-d6218fa504cb",
    "993e7c08-394b-41e0-8bb0-74b271927dbd",
    "998c6fd5-2c7e-420e-95eb-c1cb55c3c18f",
    "993e7c08-d07d-4865-8442-da40c85b9938",
    "998e6d3f-147f-4cfa-a4b6-f5eabc5e6ced",
    "998e6d7d-a17f-4d4c-83b0-ecd28d5d1f1a",
    "993e7c08-cf18-4a36-8caa-49011dde4d60",
    "993e7c08-ff88-458f-b24e-ef70657c3667",
    "993e7c08-d0f5-4bc1-84a4-7d648cdf3c32",
)

val taskIdList = listOf(
    "993e7c10-2272-4bf9-bc7d-2f667c9824d6",
    "99401717-15f0-4ec4-954d-ce1f998d203e",
    "99402449-8a58-4932-b5cc-88e986071f8a",
    "9941e70d-b3e9-490d-adca-67593cca142c",
    "994239ae-7d4b-46c3-a58f-432e69e2f5f2",
    "99426722-94ce-437a-97e3-3a5ccb3d9fe5",
    "9947d8c7-c0a2-4860-97d5-24ac339c182c",
    "995638a1-5655-41c9-ad1e-fb840420e826",
)

val taskIdList2 = listOf(
    "994be970-8230-44e3-9142-8d45fe3b6c27",
    "994be970-80f4-442d-8ad4-c700b389e836",
    "994be970-7e69-4bc2-8c12-b0a953afa693",
    "994be970-d268-452e-8679-f543931d5928",
    "994e6dbe-d9e1-43e0-ae7d-f4c40944775b",
    "994c8d34-fba1-4f9a-a26b-3c8425f3151c",
    "994be971-0aa9-4cc5-8829-97605fcde76a",
    "994be970-c5dd-4e78-a3b4-1903339b200e",
    "994be970-c9c2-479d-ba44-7fb324f9b72b",
    "994be970-d1fe-497d-9d0d-51940cfa7e67",
    "994be971-4037-4f82-8ef5-1cd4425dad3f"
)


val fieldIdList2 = listOf(
    "994be964-905a-4835-81cd-14a7881b761c",
    "994be964-9226-4a1a-99d1-040923f47241",
    "99809e96-cca3-4af9-8b52-58d4cb067b19",
    "994be964-a4fc-4351-a00e-b9e557467b7d",
    "994be964-b8a9-4f94-bb81-f4e2365e989a",
    "994be964-ba8b-4025-bf41-3fb2cb518d33",
    "994be964-93f3-4faf-907d-7b7a48153aa1",
    "994be964-9547-41be-a516-5f73a758da3b",
    "994be964-9712-4578-b167-b591d272ece7",
    "994be964-9867-4b0b-bcd1-50f508ac6487",
    "994be964-9aaa-4ef0-8e8d-2cb762eed306",
    "994be964-a65c-4035-b7e1-d2244735c841"
)

@Composable
fun IntentTestScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var indexTask = 0
    var indexField = 0

    Column(
        modifier = modifier.then(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val entryTask = remember { Pair("Task", "task") }
        val entryField = remember { Pair("Field", "field") }
//        val entryFarm = remember { Pair("Farm", "farm") }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Fields для теста")

        Spacer(modifier = Modifier.height(10.dp))

        IdListSpinner(items = fieldIdList, onClick = { indexField = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.size(200.dp, 45.dp),
            onClick = {
                val intent = prepareIntent(entryField.second, fieldIdList[indexField])
//                Log.d("taskId", "taskId ${fieldIdList[indexField]}")
//                Log.d("taskId", "taskId ${intent}")
                startActivity(context, intent, null)
            },
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "send field Intent")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Tasks для теста")

        Spacer(modifier = Modifier.height(10.dp))

        IdListSpinner(items = taskIdList, onClick = { indexTask = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.size(200.dp, 45.dp),
            onClick = {
                val intent = prepareIntent(entryTask.second, taskIdList[indexTask])
//                Log.d("taskId", "taskId ${taskIdList[indexTask]}")
//                Log.d("taskId", "taskId ${intent}")
                startActivity(context, intent, null)
            },
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "send task Intent")
        }


        Spacer(modifier = Modifier.height(120.dp))

        Text(text = "Fields для продакшена")

        Spacer(modifier = Modifier.height(10.dp))

        IdListSpinner(items = fieldIdList2, onClick = { indexField = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.size(200.dp, 45.dp),
            onClick = {
                val intent = prepareIntent(entryField.second, fieldIdList2[indexField])
//                Log.d("taskId", "taskId ${fieldIdList[indexField]}")
//                Log.d("taskId", "taskId ${intent}")
                startActivity(context, intent, null)
            },
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "send field Intent")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Tasks для продакшена")

        Spacer(modifier = Modifier.height(10.dp))

        IdListSpinner(items = taskIdList2, onClick = { indexTask = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.size(200.dp, 45.dp),
            onClick = {
                val intent = prepareIntent(entryTask.second, taskIdList2[indexTask])
//                Log.d("taskId", "taskId ${taskIdList[indexTask]}")
//                Log.d("taskId", "taskId ${intent}")
                startActivity(context, intent, null)
            },
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "send task Intent")
        }
    }
}

@Composable
private fun IdListSpinner(
    items: List<String>,
    onClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.Gray.copy(0.5f))
                .padding(10.dp),
            text = " $selectedIndex  ${items[selectedIndex]}"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        onClick(index)
                        expanded = false
                    },
                    text = { Text("$index  $s") },
                )

                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppToCheckTheme {
        IntentTestScreen()
    }
}

fun prepareIntent(type: String, idValue: String): Intent {
    return Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse("app://open.profitland.app/map/$type")
        putExtra("id", idValue)
    }
}
