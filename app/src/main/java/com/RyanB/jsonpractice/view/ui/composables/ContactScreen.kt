package com.RyanB.jsonpractice.view.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.RyanB.jsonpractice.view.ui.theme.JsonPracticeTheme
import com.RyanB.jsonpractice.viewmodel.ContactViewModel


@Composable
fun ContactScreen(modifier: Modifier = Modifier, viewModel: ContactViewModel = viewModel()) {
    val contact by viewModel.contactInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val idInput by viewModel.idInput.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$idInput",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            Button(onClick = { viewModel.increaseIdInput() },
                modifier = Modifier.padding(end = 8.dp)) {
                Text("+")
            }
            Button(onClick = { viewModel.decreaseIdInput() }) {
                Text("-")
            }
        }

        Row {
            Button(onClick = { viewModel.fetchUser() }) {
                Text("Fetch Contact")
            }
            Button(onClick = { viewModel.fetchUserById(idInput) }) {
                Text("Fetch Contact by ID")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
        }

        contact?.let {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("ID: ${it.id}")
                    Text("Name: ${it.name}")
                    Text("Username: ${it.username}")
                    Text("Email: ${it.email}")
                    Text("Phone: ${it.phone}")
                    Text("Website: ${it.website}")
                    Text("Address: ${it.address.street}, ${it.address.city}")
                    Text("Company: ${it.company.name}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    JsonPracticeTheme {
        ContactScreen()
    }
}
