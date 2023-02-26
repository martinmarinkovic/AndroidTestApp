package com.martin.testapp.ui.screens.user_repo_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.martin.testapp.R
import com.martin.testapp.domain.user_repo_details.model.RepoDetails
import com.martin.testapp.ui.compose.DefaultErrorScreen
import com.martin.testapp.ui.compose.DefaultLoadingScreen
import com.martin.testapp.ui.screens.user_repo_details.compose_components.RepoTagItem

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UserRepoDetailsScreen(
    viewModel: UserRepoDetailsViewModel = hiltViewModel()
) {
    val userRepoDetailsUiState by viewModel.userRepoDetailsUiState.collectAsStateWithLifecycle()
    UserRepoDetailsScreenContent(userRepoDetailsUiState = userRepoDetailsUiState)
}

@Composable
private fun UserRepoDetailsScreenContent(
    userRepoDetailsUiState: UserRepoDetailsUiState
) {
    when(userRepoDetailsUiState) {
        is UserRepoDetailsUiState.Loading -> DefaultLoadingScreen()
        is UserRepoDetailsUiState.Successful -> SuccessfulStateScreenContent(repoDetails = userRepoDetailsUiState.repoDetails)
        is UserRepoDetailsUiState.Error -> DefaultErrorScreen(errorMessage = stringResource(id = R.string.error_message))
    }
}

@Composable
private fun SuccessfulStateScreenContent(
    repoDetails: RepoDetails
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item { RepoDetailsHeader(repoDetails = repoDetails) }
            repoDetails.repoTags?.let { repoTags ->
                items(repoTags) { tag -> RepoTagItem(tag = tag) }
            }
        }
    }
}

@Composable
private fun RepoDetailsHeader(
    repoDetails: RepoDetails,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.LightGray),
        ) {
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                UserAvatarImage(imageUrl = repoDetails.userDetails.avatarUrl)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserDetailsContent(repoDetails = repoDetails)
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                RepoDetailsContent(repoDetails = repoDetails)
            }
        }
    }
}

@Composable
private fun UserAvatarImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = imageUrl,
        modifier = modifier
            .size(200.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(10.dp),
        placeholder = painterResource(id = R.drawable.user_avatar_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}

@Composable
private fun UserDetailsContent(
    repoDetails: RepoDetails
){
    Text(
        text = repoDetails.username,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun RepoDetailsContent(
    repoDetails: RepoDetails
){
    Text(
        text = "${stringResource(id = R.string.user_repo_details_forks_number_text)}: ${repoDetails.forksCount}",
        modifier = Modifier.padding(10.dp),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
    )
    Text(
        text = "${stringResource(id = R.string.user_repo_details_watchers_number_text)}: ${repoDetails.watchersCount}",
        modifier = Modifier.padding(10.dp),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
    )
}