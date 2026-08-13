package com.example.decouvertemada
// MADY MAROMIHA Yoann Ismael Ulrich N*12 L2 ge-it
// tsy tena sarin'ilay lieu no ato mr fa tena tsy aiko manova anio, za sy ny IA no nanao azy tsoriko ny marina.ilay allee de baobab sy andasibe
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.decouvertemada.ui.theme.DecouverteMadaTheme

data class Place(
    val id: Int,
    val nom: String,
    val region: String,
    val description: String,
    val activites: String,
    val imageUrl: String
)

class PlaceUiState(val place: Place) {
    var likes by mutableStateOf(0)
        private set
    var dislikes by mutableStateOf(0)
        private set

    fun like() {
        likes++
    }

    fun dislike() {
        dislikes++
    }
}

private fun demoPlaces(): List<Place> = listOf(
    Place(
        id = 1,
        nom = "Allée des Baobabs",
        region = "Menabe",
        description = "Un alignement mythique de baobabs centenaires baigné par la lumière du coucher de soleil.",
        activites = "Photographie, coucher de soleil, balade à pied",
        imageUrl = "https://picsum.photos/seed/mada-baobab/900/1200"
    ),
    Place(
        id = 2,
        nom = "Parc National de l'Isalo",
        region = "Ihorombe",
        description = "Un massif de grès sculpté par l'érosion, avec canyons, piscines naturelles et faune endémique.",
        activites = "Randonnée, baignade, observation de lémuriens",
        imageUrl = "https://picsum.photos/seed/mada-isalo/900/1200"
    ),
    Place(
        id = 3,
        nom = "Nosy Be",
        region = "Diana",
        description = "L'île aux parfums, réputée pour ses plages de sable blanc et ses eaux turquoise.",
        activites = "Plongée, snorkeling, excursion en mer",
        imageUrl = "https://picsum.photos/seed/mada-nosybe/900/1200"
    ),
    Place(
        id = 4,
        nom = "Tsingy de Bemaraha",
        region = "Melaky",
        description = "Un labyrinthe rocheux classé à l'UNESCO, entre forêts sèches et formations calcaires géantes.",
        activites = "Via ferrata, randonnée, exploration de grottes",
        imageUrl = "https://picsum.photos/seed/mada-tsingy/900/1200"
    ),
    Place(
        id = 5,
        nom = "Andasibe-Mantadia",
        region = "Alaotra-Mangoro",
        description = "Forêt tropicale humide célèbre pour le chant matinal de l'Indri indri.",
        activites = "Observation de lémuriens, nuit en forêt, ornithologie",
        imageUrl = "https://picsum.photos/seed/mada-andasibe/900/1200"
    ),
    Place(
        id = 6,
        nom = "Ranomafana",
        region = "Haute Matsiatra",
        description = "Parc national montagneux réputé pour sa biodiversité exceptionnelle et ses sources chaudes.",
        activites = "Trek en forêt, thermalisme, observation de faune",
        imageUrl = "https://picsum.photos/seed/mada-ranomafana/900/1200"
    ),
    Place(
        id = 7,
        nom = "Rova d'Antananarivo",
        region = "Analamanga",
        description = "Ancien palais royal dominant la capitale, chargé d'histoire et de mémoire nationale.",
        activites = "Visite culturelle, panorama sur la ville",
        imageUrl = "https://picsum.photos/seed/mada-rova/900/1200"
    ),
    Place(
        id = 8,
        nom = "Île Sainte-Marie",
        region = "Analanjirofo",
        description = "Île paradisiaque bordée de cocotiers, célèbre pour l'observation des baleines à bosse.",
        activites = "Observation des baleines, plage, plongée",
        imageUrl = "https://picsum.photos/seed/mada-saintemarie/900/1200"
    )
)

class PlacesViewModel : ViewModel() {

    val places: List<PlaceUiState> = demoPlaces().map { PlaceUiState(it) }

    var selectedPlace by mutableStateOf<PlaceUiState?>(null)
        private set


    val totalLikes: Int
        get() = places.sumOf { it.likes }

    val totalDislikes: Int
        get() = places.sumOf { it.dislikes }

    fun onLike(state: PlaceUiState) {
        state.like()
    }

    fun onDislike(state: PlaceUiState) {
        state.dislike()
    }

    fun openDetails(state: PlaceUiState) {
        selectedPlace = state
    }

    fun closeDetails() {
        selectedPlace = null
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecouverteMadaTheme {
                DecouverteMadaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecouverteMadaApp(viewModel: PlacesViewModel = viewModel()) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppHeader(
                totalLikes = viewModel.totalLikes,
                totalDislikes = viewModel.totalDislikes
            )
        }
    ) { innerPadding ->
        PlacesGrid(
            places = viewModel.places,
            contentPadding = innerPadding,
            onCardClick = viewModel::openDetails,
            onLike = viewModel::onLike,
            onDislike = viewModel::onDislike
        )
    }

    val selected = viewModel.selectedPlace
    if (selected != null) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = viewModel::closeDetails,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            PlaceDetailContent(
                state = selected,
                onLike = { viewModel.onLike(selected) },
                onDislike = { viewModel.onDislike(selected) }
            )
        }
    }
}

@Composable
fun AppHeader(totalLikes: Int, totalDislikes: Int) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Découverte Mada",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Les plus beaux sites de Madagascar",
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                    fontSize = 12.sp
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GlobalCounterBadge(
                    icon = Icons.Filled.ThumbUp,
                    count = totalLikes
                )
                GlobalCounterBadge(
                    icon = Icons.Filled.ThumbDown,
                    count = totalDislikes
                )
            }
        }
    }
}

@Composable
private fun GlobalCounterBadge(icon: androidx.compose.ui.graphics.vector.ImageVector, count: Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.16f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = count.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PlacesGrid(
    places: List<PlaceUiState>,
    contentPadding: PaddingValues,
    onCardClick: (PlaceUiState) -> Unit,
    onLike: (PlaceUiState) -> Unit,
    onDislike: (PlaceUiState) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            top = contentPadding.calculateTopPadding() + 12.dp,
            bottom = contentPadding.calculateBottomPadding() + 12.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(places, key = { it.place.id }) { state ->
            PlaceCard(
                state = state,
                onClick = { onCardClick(state) },
                onLike = { onLike(state) },
                onDislike = { onDislike(state) }
            )
        }
    }
}

@Composable
fun PlaceCard(
    state: PlaceUiState,
    onClick: () -> Unit,
    onLike: () -> Unit,
    onDislike: () -> Unit
) {
    val place = state.place

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.72f),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.nom,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.35f),
                                Color.Black.copy(alpha = 0.78f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = place.nom,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = place.region,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = place.description,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 10.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = place.activites,
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CardActionButton(
                        icon = Icons.Filled.ThumbUp,
                        count = state.likes,
                        tint = MaterialTheme.colorScheme.secondary,
                        onClick = onLike
                    )
                    CardActionButton(
                        icon = Icons.Filled.ThumbDown,
                        count = state.dislikes,
                        tint = Color.White,
                        onClick = onDislike
                    )
                }
            }
        }
    }
}

@Composable
private fun CardActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: Int,
    tint: Color,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.Black.copy(alpha = 0.35f),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(13.dp)
            )
            Text(
                text = count.toString(),
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PlaceDetailContent(
    state: PlaceUiState,
    onLike: () -> Unit,
    onDislike: () -> Unit
) {
    val place = state.place

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(bottom = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.nom,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = place.nom,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "  ${place.region}",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

            DetailSection(
                icon = Icons.Filled.Info,
                title = "Description",
                content = place.description
            )

            DetailSection(
                icon = Icons.Filled.DirectionsWalk,
                title = "Activités",
                content = place.activites
            )

            DetailSection(
                icon = Icons.Filled.Park,
                title = "Région",
                content = place.region
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DetailInteractionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.ThumbUp,
                    label = "J'aime",
                    count = state.likes,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    onClick = onLike
                )
                DetailInteractionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.ThumbDown,
                    label = "Je n'aime pas",
                    count = state.dislikes,
                    containerColor = MaterialTheme.colorScheme.outline,
                    onClick = onDislike
                )
            }
        }
    }
}

@Composable
private fun DetailSection(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String
) {
    Row(modifier = Modifier.padding(bottom = 14.dp)) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(34.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = content,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun DetailInteractionButton(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    count: Int,
    containerColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = containerColor.copy(alpha = 0.12f),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = containerColor,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = count.toString(),
                    fontWeight = FontWeight.Bold,
                    color = containerColor
                )
            }
            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecouverteMadaAppPreview() {
    DecouverteMadaTheme {
        DecouverteMadaApp()
    }
}
