package com.example.jetpackcompose.masterclass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

val chipItems = listOf(
    "City Center",
    "Luxury",
    "Instant Booking",
    "Exclusive Deal",
    "Early Bird Discount",
    "Romantic Gateway",
    "24/7 Support",
)

private val offers = mapOf(
    R.drawable.bed to "2 Bed",
    R.drawable.breakfast to "Breakfast",
    R.drawable.cutlery to "Cutlery",
    R.drawable.pawprint to "Pet Friendly",
    R.drawable.serving_dish to "Dinner",
    R.drawable.snowflake to "Air Conditioning",
    R.drawable.television to "TV",
    R.drawable.wi_fi_icon to "Wifi",
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelBookingScreen(){
    LazyColumn(modifier = Modifier
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            BanneredImage(modifier = Modifier)
        }
        item {
            HorizontalDivider(
                modifier = Modifier.padding(16.dp),
                thickness = 2.dp, color = Color.Gray)
        }

        item() {
            FlowRow (modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterHorizontally)){
                chipItems.forEachIndexed { index, s ->
                    AssistChip(
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = s)

                        }
                    )
                }
            }
        }

        item {
            HorizontalDivider(modifier = Modifier.padding(16.dp), thickness = 2.dp, color = Color.Gray)
        }

        item {
            Text(
                text = """The advertisement features a vibrant and inviting design, showcasing the Hotel California Strawberry nestled in the heart of Los Angeles. Surrounded by the iconic Hollywood Sign, Griffith Park, and stunning beaches, the hotel is perfectly located for guests to explore L.A.’s best attractions.""",
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )
        }
        item{
            Text(
                text = "What we offer",
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                items(items = offers.entries.toList()){ (icons, value)->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(2.dp).background(Color.Gray.copy(alpha = 0.3f))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(icons),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                        Text(text = value,
                            fontSize = 12.sp)
                    }
                }
            }
        }

        item {
            Button(
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {},

            ) {
                Text(text = "Book Now")
            }
        }
    }

}

@Composable
fun BanneredImage(modifier: Modifier){
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.living_room),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    max = 250.dp
                ),
            contentScale = ContentScale.Crop
        )

        Row (
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(Color.White.copy(alpha = 0.7f)),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            val windowClass = currentWindowAdaptiveInfo().windowSizeClass
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hotel California Strawberry",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                labelText(modifier = Modifier,
                    label = "Pratap Vihar, Ghaziabad",
                    res = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                    }
                )
                labelText(modifier = Modifier,
                    label = "4.9(13K review)",
                    res = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                    }
                )

            }
            Row (horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(16.dp)){
                Text(text = buildAnnotatedString {
                    val multiplier = when (windowClass.windowWidthSizeClass) {
                        WindowWidthSizeClass.COMPACT -> 1f
                        WindowWidthSizeClass.MEDIUM -> 1.2f
                        WindowWidthSizeClass.EXPANDED -> 2.0f
                        else -> {
                            1f
                        }
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp * multiplier
                        )
                    ) {
                        append("\u20B9450/")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp * multiplier
                        )
                    ) {
                        append("night")
                    }
                })
            }

        }
    }
}

@Composable
fun labelText(
    modifier: Modifier,
    label: String,
    res: @Composable () -> Unit
){
    Row(modifier = modifier) {
        res()
        Text(
            text = label
        )
    }
}


@Preview
@Composable
private fun HotelBookingScreenPreview(){
    JetpackComposeTheme {
        HotelBookingScreen()

    }
}


@Preview(
    device = Devices.NEXUS_10
)
@Composable
private fun HotelBookingScreenTabletPreview() {
    JetpackComposeTheme {
        HotelBookingScreen()
    }
}