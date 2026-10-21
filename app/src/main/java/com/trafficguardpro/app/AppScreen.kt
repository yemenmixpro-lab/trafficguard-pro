package com.trafficguardpro.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trafficguardpro.app.data.PremiumManager
import com.trafficguardpro.app.ui.theme.TrafficBlue
import com.trafficguardpro.app.ui.theme.TrafficGreen
import com.trafficguardpro.app.ui.theme.TrafficGuardProTheme
import com.trafficguardpro.app.ui.theme.TrafficRed

@Composable
fun AppScreen() {
    val premiumManager = remember { PremiumManager() }
    var isPremium by remember { mutableStateOf(false) }
    var showAds by remember { mutableStateOf(true) }

    val stats = listOf(
        "Live traffic" to "2.8 GB / day",
        "Blocked threats" to "24 items",
        "Tracks active" to "7 apps",
        "Protection" to "Strong",
    )

    val features = listOf(
        "Real-time network monitoring",
        "App usage insights",
        "Premium firewall controls",
        "Unlimited alerts and reports",
    )

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    TrafficBlue,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                )
                            )
                        )
                        .padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "TrafficGuard Pro",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                            )
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.18f)),
                                shape = RoundedCornerShape(20.dp),
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = if (isPremium || premiumManager.isPremium) Icons.Default.Star else Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = Color.White,
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(
                                        text = if (isPremium || premiumManager.isPremium) "Premium" else "Starter",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                            }
                        }

                        Text(
                            text = "Secure your device and monitor suspicious traffic in real time.",
                            color = Color.White.copy(alpha = 0.9f),
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Button(
                                onClick = {
                                    isPremium = true
                                    premiumManager.activatePremium()
                                    showAds = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = TrafficGreen,
                                ),
                            ) {
                                Text("Upgrade")
                            }
                            Button(
                                onClick = { showAds = !showAds },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White.copy(alpha = 0.12f),
                                ),
                            ) {
                                Text("Toggle ads")
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Dashboard",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            items(stats) { item ->
                val (label, value) = item
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text(text = label, style = MaterialTheme.typography.bodyMedium)
                            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        }
                        Icon(
                            imageVector = when (label) {
                                "Live traffic" -> Icons.Default.NetworkCheck
                                "Blocked threats" -> Icons.Default.Shield
                                "Tracks active" -> Icons.Default.TrendingUp
                                else -> Icons.Default.NotificationsActive
                            },
                            contentDescription = null,
                            tint = TrafficBlue,
                        )
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = "Premium features",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        features.forEach { feature ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = TrafficGreen,
                                )
                                Spacer(modifier = Modifier.padding(6.dp))
                                Text(feature, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }

            if (showAds) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF2D2D2D),
                                        Color(0xFF1A1A1A),
                                    )
                                )
                            )
                            .padding(20.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Ad space",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Google AdMob / banner placeholder",
                                color = Color.White.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
                        .padding(18.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = TrafficRed,
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = if (isPremium || premiumManager.isPremium) "Premium plan active" else "Upgrade to remove ads and unlock full monitoring",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }
    }
}
