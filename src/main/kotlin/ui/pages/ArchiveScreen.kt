package io.github.smfdrummer.medal_app_desktop.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import arrow.optics.copy
import components.MedalTheme
import components.components.*
import components.components.card.Card
import components.components.card.CardDefaults
import components.components.card.OutlinedCard
import components.components.snackbar.SnackbarManager
import components.components.textfield.OutlinedTextField
import components.components.textfield.UnderlinedTextField
import components.contentColorFor
import data.AppSettings
import data.SettingsDataStore
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.ArchiveViewModel
import io.github.smfdrummer.network.decryptNumber
import io.github.smfdrummer.network.decryptResponse
import io.github.smfdrummer.network.encryptNumber
import io.github.smfdrummer.utils.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*
import org.intellij.lang.annotations.Language
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArchiveScreen() {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val archive by archiveViewModel.archive.collectAsState()
    val origin by archiveViewModel.origin.collectAsState()
    val context by archiveViewModel.context.collectAsState()
    val isCredentialValid by archiveViewModel.isCredentialValid.collectAsState()

    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    var editDialogVisible by remember { mutableStateOf(false) }

    if (editDialogVisible) {
        var v203 by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { editDialogVisible = false },
            onConfirmClick = { editDialogVisible = false },
            dismissButtonText = null,
            confirmButtonText = null,
            title = "编辑存档",
            text = "在此处编辑存档缓存，或覆盖更新"
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = v203,
                    onValueChange = { v203 = it },
                    label = { Text("V203响应体") },
                    isError = !v203.isValidJson()
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = context.variables["pi"]?.jsonPrimitive?.content ?: "",
                            onValueChange = { },
                            readOnly = true,
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            prefix = { Text("pi") },
                        )
                        OutlinedTextField(
                            value = context.variables["sk"]?.jsonPrimitive?.content ?: "",
                            onValueChange = { },
                            readOnly = true,
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            prefix = { Text("sk") },
                        )
                        OutlinedTextField(
                            value = context.variables["ui"]?.jsonPrimitive?.content ?: "",
                            onValueChange = { },
                            readOnly = true,
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            prefix = { Text("ui") },
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = archive?.p?.toString() ?: "",
                            onValueChange = { },
                            readOnly = true,
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            label = { Text("p") },
                        )
                        OutlinedTextField(
                            value = archive?.s?.toString() ?: "",
                            onValueChange = { },
                            readOnly = true,
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            label = { Text("s") },
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            text = "重新输入",
                            onClick = { v203 = "" },
                            enabled = v203.isNotEmpty(),
                            modifier = Modifier.weight(1f),
                            variant = ButtonVariant.Destructive
                        )
                        Button(
                            text = "解析更新",
                            onClick = {
                                runCatching {
                                    v203.decryptResponse().parseObject().getJsonObject("d")?.getJsonObject("pr")
                                        ?.apply {
                                            archiveViewModel.modifyContext(
                                                "p" to get("p")!!,
                                                "s" to get("s")!!,
                                            )
                                            archiveViewModel.apply {
                                                setArchive()
                                                setOrigin()
                                            }
                                        }
                                }.onSuccess {
                                    editDialogVisible = false
                                    SnackbarManager.showSnackbar("更新成功")
                                }.onFailure {
                                    SnackbarManager.showSnackbar("错误：${it.message}")
                                }
                            },
                            enabled = v203.isNotEmpty() && v203.isValidJson(),
                            modifier = Modifier.weight(1f),
                            variant = ButtonVariant.Primary
                        )
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (archive == null) {
            ObtainArchive()
        } else {
            ModifyArchive()
        }

        val containerColor = if (isCredentialValid) MedalTheme.colors.success else MedalTheme.colors.error

        Column(
            modifier = Modifier.padding(16.dp).align(Alignment.BottomEnd),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            archive?.let {
                origin?.let {
                    if (archive != origin) {
                        ExtendedFloatingActionButton(
                            containerColor = containerColor,
                            contentColor = contentColorFor(containerColor),
                            text = { Text(if (isCredentialValid) "确认更改" else "更新凭据") },
                            icon = { Icon(if (isCredentialValid) Icons.Rounded.Check else Icons.Rounded.Error) },
                            onClick = {
                                if (isCredentialValid) archiveViewModel.requestArchive(settings.channel)
                                    .invokeOnCompletion {
                                        SnackbarManager.showSnackbar("执行完成，请登录游戏查看")
                                    }
                            }
                        )
                    }
                }
            }

            ExtendedFloatingActionButton(
                containerColor = MedalTheme.colors.surface,
                text = { Text("编辑存档") },
                icon = { Icon(Icons.Rounded.Edit) },
                onClick = { editDialogVisible = true }
            )
        }
    }
}

@Composable
fun ModifyArchive() {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()
    val isCredentialValid by archiveViewModel.isCredentialValid.collectAsState()

    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    var clearAllVisible by remember { mutableStateOf(false) }
    var clearVirtualVisible by remember { mutableStateOf(false) }

    if (clearAllVisible) {
        AlertDialog(
            onDismissRequest = { clearAllVisible = false },
            onConfirmClick = { clearAllVisible = false; archiveViewModel.clearAll() },
            title = "更换存档",
            text = "确认更换存档吗？"
        )
    }

    if (clearVirtualVisible) {
        AlertDialog(
            onDismissRequest = { clearVirtualVisible = false },
            onConfirmClick = {
                clearVirtualVisible = false

                if (isCredentialValid) {
                    archiveViewModel.apply {
                        modifyArchive {
                            it.copy {
                                path("sd.psla") { buildJsonArray { } } // 清空植物阶数列表
                                path("sd.mtrl") { buildJsonArray { } } // 清空道具列表
                                path("sd.asp") { buildJsonArray { } } // 清空挂件碎片列表
                                path("sd.ppr") { buildJsonArray { } } // 清空植物碎片列表
                                path("sd.lpapi") { buildJsonArray { } } // 清空装扮碎片列表
                            }
                        }
                        requestArchive(settings.channel)
                            .invokeOnCompletion {
                                clearAll()
                                SnackbarManager.showSnackbar("执行完成，请重新登录")
                            }
                    }
                } else SnackbarManager.showSnackbar("请更新凭据后再试")
            },
            title = "清除虚拟物品",
            text = "确认清除虚拟物品吗？清除后需要重新登录！"
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(Modifier.height(16.dp))
        Text("你好，${origin?.p?.asObject?.getJsonObject("sd")?.getString("n")}", style = MedalTheme.typography.h1)
        HorizontalDivider()

        CredentialCard()

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            NameCard(Modifier.weight(1f))
            CoinCard(Modifier.weight(1f))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            UrnCard(Modifier.weight(1f))
            UnlockHsCard(Modifier.weight(1f))
            PslaCard(Modifier.weight(1f))
            WmedCard(Modifier.weight(1f))
        }

        PrCard(Modifier.fillMaxWidth())
        LpaeiCard(Modifier.fillMaxWidth())
        PasiCard(Modifier.fillMaxWidth())

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                text = "换个存档",
                variant = ButtonVariant.DestructiveGhost,
                onClick = { clearAllVisible = true },
            )
            Button(
                text = "清除虚拟物品",
                variant = ButtonVariant.PrimaryGhost,
                onClick = { clearVirtualVisible = true },
            )
        }
    }
}

@Composable
fun LpaeiCard(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    @Language("JSON")
    val preset = """
        [
            { "peapid": 111029, "peaaid": 31110292 },
            { "peapid": 111019, "peaaid": 31110192 },
            { "peapid": 1045, "peaaid": 30010451 },
            { "peapid": 111022, "peaaid": 32001341 },
            { "peapid": 111016, "peaaid": 31110164 },
            { "peapid": 111030, "peaaid": 31110302 },
            { "peapid": 111035, "peaaid": 31110354 },
            { "peapid": 111045, "peaaid": 31110453 },
            { "peapid": 111067, "peaaid": 31110672 },
            { "peapid": 111070, "peaaid": 31110703 },
            { "peapid": 111075, "peaaid": 31110753 },
            { "peapid": 200007, "peaaid": 32000073 },
            { "peapid": 200021, "peaaid": 32000212 },
            { "peapid": 200034, "peaaid": 32000343 },
            { "peapid": 200037, "peaaid": 32000372 },
            { "peapid": 200038, "peaaid": 32000382 },
            { "peapid": 200051, "peaaid": 32000513 },
            { "peapid": 200053, "peaaid": 32000532 },
            { "peapid": 200057, "peaaid": 32000572 },
            { "peapid": 200058, "peaaid": 32000583 },
            { "peapid": 200060, "peaaid": 32000601 },
            { "peapid": 200061, "peaaid": 32000611 },
            { "peapid": 200066, "peaaid": 32000662 },
            { "peapid": 200073, "peaaid": 32000733 },
            { "peapid": 200079, "peaaid": 32000792 },
            { "peapid": 200083, "peaaid": 32000832 },
            { "peapid": 200091, "peaaid": 32000911 },
            { "peapid": 200102, "peaaid": 32001021 },
            { "peapid": 1008, "peaaid": 30010082 },
            { "peapid": 1009, "peaaid": 30010094 },
            { "peapid": 1070, "peaaid": 30010703 },
            { "peapid": 111002, "peaaid": 31110023 },
            { "peapid": 111014, "peaaid": 111214 },
            { "peapid": 111015, "peaaid": 111215 },
            { "peapid": 1001, "peaaid": 30010013 },
            { "peapid": 1002, "peaaid": 30010025 },
            { "peapid": 1003, "peaaid": 30010032 },
            { "peapid": 1004, "peaaid": 30010042 },
            { "peapid": 1005, "peaaid": 30010053 },
            { "peapid": 1006, "peaaid": 30010062 },
            { "peapid": 1007, "peaaid": 30010072 },
            { "peapid": 1012, "peaaid": 30010124 },
            { "peapid": 1013, "peaaid": 30010131 },
            { "peapid": 1014, "peaaid": 1214 },
            { "peapid": 1016, "peaaid": 1216 },
            { "peapid": 1019, "peaaid": 30010193 },
            { "peapid": 1020, "peaaid": 30010201 },
            { "peapid": 1021, "peaaid": 30010213 },
            { "peapid": 1023, "peaaid": 30010231 },
            { "peapid": 1024, "peaaid": 30010244 },
            { "peapid": 1025, "peaaid": 1225 },
            { "peapid": 1027, "peaaid": 1227 },
            { "peapid": 1029, "peaaid": 30010292 },
            { "peapid": 1030, "peaaid": 30010302 },
            { "peapid": 1031, "peaaid": 30010313 },
            { "peapid": 1033, "peaaid": 30010331 },
            { "peapid": 1037, "peaaid": 30010373 },
            { "peapid": 1039, "peaaid": 30010393 },
            { "peapid": 1042, "peaaid": 30010421 },
            { "peapid": 1043, "peaaid": 30010431 },
            { "peapid": 1044, "peaaid": 1244 },
            { "peapid": 1049, "peaaid": 30010491 },
            { "peapid": 1052, "peaaid": 30010521 },
            { "peapid": 1055, "peaaid": 30010551 },
            { "peapid": 1058, "peaaid": 30010581 },
            { "peapid": 1069, "peaaid": 1269 },
            { "peapid": 1071, "peaaid": 30010711 },
            { "peapid": 1072, "peaaid": 30010721 },
            { "peapid": 1081, "peaaid": 30010812 },
            { "peapid": 1082, "peaaid": 30010821 },
            { "peapid": 1083, "peaaid": 30010831 },
            { "peapid": 1086, "peaaid": 30010861 },
            { "peapid": 1093, "peaaid": 30010932 },
            { "peapid": 1099, "peaaid": 30010991 },
            { "peapid": 111001, "peaaid": 111201 },
            { "peapid": 111003, "peaaid": 111203 },
            { "peapid": 111004, "peaaid": 31110041 },
            { "peapid": 111017, "peaaid": 31110171 },
            { "peapid": 111023, "peaaid": 31110231 },
            { "peapid": 111033, "peaaid": 31110331 },
            { "peapid": 111038, "peaaid": 31110381 },
            { "peapid": 111039, "peaaid": 111239 },
            { "peapid": 111040, "peaaid": 31110401 },
            { "peapid": 111043, "peaaid": 31110431 },
            { "peapid": 111044, "peaaid": 31110441 },
            { "peapid": 111086, "peaaid": 111286 },
            { "peapid": 111021, "peaaid": 31110211 },
            { "peapid": 111063, "peaaid": 31110632 },
            { "peapid": 111089, "peaaid": 31110892 },
            { "peapid": 111090, "peaaid": 31110903 },
            { "peapid": 200001, "peaaid": 32000010 },
            { "peapid": 200101, "peaaid": 32001011 },
            { "peapid": 200100, "peaaid": 32001000 },
            { "peapid": 200098, "peaaid": 32000982 },
            { "peapid": 200095, "peaaid": 32000951 },
            { "peapid": 200094, "peaaid": 32000941 },
            { "peapid": 200092, "peaaid": 32000921 },
            { "peapid": 200088, "peaaid": 32000881 },
            { "peapid": 200074, "peaaid": 32000741 },
            { "peapid": 200009, "peaaid": 32000093 },
            { "peapid": 200069, "peaaid": 32000692 },
            { "peapid": 200024, "peaaid": 32000241 },
            { "peapid": 200027, "peaaid": 32000270 },
            { "peapid": 200093, "peaaid": 32000930 },
            { "peapid": 200055, "peaaid": 32000550 },
            { "peapid": 200011, "peaaid": 32000110 },
            { "peapid": 111062, "peaaid": 111262 },
            { "peapid": 111048, "peaaid": 111248 },
            { "peapid": 111046, "peaaid": 111246 },
            { "peapid": 1028, "peaaid": 1228 },
            { "peapid": 200030, "peaaid": 32000300 },
            { "peapid": 111084, "peaaid": 31110841 },
            { "peapid": 111087, "peaaid": 111287 },
            { "peapid": 200039, "peaaid": 32000392 },
            { "peapid": 200049, "peaaid": 32000490 },
            { "peapid": 111071, "peaaid": 111271 },
            { "peapid": 111085, "peaaid": 111285 },
            { "peapid": 200005, "peaaid": 32000050 },
            { "peapid": 200010, "peaaid": 32000100 },
            { "peapid": 200012, "peaaid": 32000120 },
            { "peapid": 200019, "peaaid": 32000190 },
            { "peapid": 200023, "peaaid": 32000230 },
            { "peapid": 200028, "peaaid": 32000280 },
            { "peapid": 200029, "peaaid": 32000290 },
            { "peapid": 200031, "peaaid": 32000310 },
            { "peapid": 200033, "peaaid": 32000330 },
            { "peapid": 200035, "peaaid": 32000350 },
            { "peapid": 200041, "peaaid": 32000410 },
            { "peapid": 200045, "peaaid": 32000450 },
            { "peapid": 200046, "peaaid": 32000460 },
            { "peapid": 200047, "peaaid": 32000470 },
            { "peapid": 200054, "peaaid": 32000540 },
            { "peapid": 200056, "peaaid": 32000560 },
            { "peapid": 200059, "peaaid": 32000590 },
            { "peapid": 200062, "peaaid": 32000620 },
            { "peapid": 200063, "peaaid": 32000630 },
            { "peapid": 200065, "peaaid": 32000650 },
            { "peapid": 200070, "peaaid": 32000700 },
            { "peapid": 200075, "peaaid": 32000750 },
            { "peapid": 200077, "peaaid": 32000770 },
            { "peapid": 200082, "peaaid": 32000820 },
            { "peapid": 200085, "peaaid": 32000850 },
            { "peapid": 200090, "peaaid": 32000900 },
            { "peapid": 200096, "peaaid": 32000960 },
            { "peapid": 200097, "peaaid": 32000970 },
            { "peapid": 200127, "peaaid": 32001270 },
            { "peapid": 200128, "peaaid": 32001280 },
            { "peapid": 200133, "peaaid": 32001330 },
            { "peapid": 1011, "peaaid": 1211 },
            { "peapid": 1018, "peaaid": 1218 },
            { "peapid": 1034, "peaaid": 1234 },
            { "peapid": 1053, "peaaid": 1253 },
            { "peapid": 1056, "peaaid": 1256 },
            { "peapid": 1057, "peaaid": 1257 },
            { "peapid": 1064, "peaaid": 1264 },
            { "peapid": 1066, "peaaid": 1266 },
            { "peapid": 1079, "peaaid": 1279 },
            { "peapid": 1094, "peaaid": 1294 },
            { "peapid": 111006, "peaaid": 111206 },
            { "peapid": 111041, "peaaid": 111241 },
            { "peapid": 111064, "peaaid": 111264 },
            { "peapid": 111065, "peaaid": 111265 },
            { "peapid": 200003, "peaaid": 32000030 },
            { "peapid": 1041, "peaaid": 1241 },
            { "peapid": 1077, "peaaid": 1277 },
            { "peapid": 200016, "peaaid": 32000160 },
            { "peapid": 200126, "peaaid": 32001260 },
            { "peapid": 1026, "peaaid": 1226 },
            { "peapid": 200014, "peaaid": 32000140 },
            { "peapid": 200050, "peaaid": 32000500 },
            { "peapid": 111047, "peaaid": 111247 },
            { "peapid": 111081, "peaaid": 111281 },
            { "peapid": 111082, "peaaid": 111282 },
            { "peapid": 111088, "peaaid": 31110882 },
            { "peapid": 200000, "peaaid": 32000000 },
            { "peapid": 200002, "peaaid": 32000020 },
            { "peapid": 200004, "peaaid": 32000040 },
            { "peapid": 200006, "peaaid": 32000060 },
            { "peapid": 200008, "peaaid": 32000080 },
            { "peapid": 200015, "peaaid": 32000150 },
            { "peapid": 200022, "peaaid": 32000220 },
            { "peapid": 200025, "peaaid": 32000250 },
            { "peapid": 200026, "peaaid": 32000260 },
            { "peapid": 200044, "peaaid": 32000440 },
            { "peapid": 200052, "peaaid": 32000520 },
            { "peapid": 200067, "peaaid": 32000670 },
            { "peapid": 200068, "peaaid": 32000680 },
            { "peapid": 200071, "peaaid": 32000710 },
            { "peapid": 200072, "peaaid": 32000720 },
            { "peapid": 200078, "peaaid": 32000780 },
            { "peapid": 200084, "peaaid": 32000840 },
            { "peapid": 200086, "peaaid": 32000860 },
            { "peapid": 200099, "peaaid": 32000990 },
            { "peapid": 1010, "peaaid": 1210 },
            { "peapid": 1015, "peaaid": 1215 },
            { "peapid": 1050, "peaaid": 30010501 },
            { "peapid": 1063, "peaaid": 1263 },
            { "peapid": 1078, "peaaid": 1278 },
            { "peapid": 1090, "peaaid": 1290 },
            { "peapid": 111027, "peaaid": 111227 },
            { "peapid": 111091, "peaaid": 111291 },
            { "peapid": 1032, "peaaid": 1232 },
            { "peapid": 1074, "peaaid": 1274 },
            { "peapid": 1098, "peaaid": 1298 },
            { "peapid": 111008, "peaaid": 111208 },
            { "peapid": 111009, "peaaid": 111209 },
            { "peapid": 111012, "peaaid": 111212 },
            { "peapid": 200043, "peaaid": 32000430 },
            { "peapid": 111076, "peaaid": 111276 },
            { "peapid": 200013, "peaaid": 32000130 },
            { "peapid": 111074, "peaaid": 111274 },
            { "peapid": 111079, "peaaid": 31110791 },
            { "peapid": 200032, "peaaid": 32000320 },
            { "peapid": 200048, "peaaid": 32000480 },
            { "peapid": 200080, "peaaid": 32000800 },
            { "peapid": 1089, "peaaid": 1289 },
            { "peapid": 111028, "peaaid": 111228 },
            { "peapid": 111037, "peaaid": 111237 },
            { "peapid": 111053, "peaaid": 111253 },
            { "peapid": 1017, "peaaid": 1217 },
            { "peapid": 1047, "peaaid": 1247 },
            { "peapid": 1051, "peaaid": 1251 },
            { "peapid": 1062, "peaaid": 1262 },
            { "peapid": 111036, "peaaid": 111236 },
            { "peapid": 111051, "peaaid": 111251 },
            { "peapid": 111056, "peaaid": 111256 },
            { "peapid": 111068, "peaaid": 111268 },
            { "peapid": 111024, "peaaid": 111224 },
            { "peapid": 111034, "peaaid": 111234 },
            { "peapid": 111069, "peaaid": 111269 },
            { "peapid": 111073, "peaaid": 111273 },
            { "peapid": 111078, "peaaid": 111278 },
            { "peapid": 1035, "peaaid": 1235 },
            { "peapid": 111010, "peaaid": 31110101 },
            { "peapid": 200017, "peaaid": 32000170 },
            { "peapid": 1022, "peaaid": 1222 },
            { "peapid": 1076, "peaaid": 1276 },
            { "peapid": 1084, "peaaid": 1284 },
            { "peapid": 1085, "peaaid": 1285 },
            { "peapid": 1088, "peaaid": 1288 },
            { "peapid": 1092, "peaaid": 1292 },
            { "peapid": 1095, "peaaid": 1295 },
            { "peapid": 1040, "peaaid": 1240 },
            { "peapid": 1068, "peaaid": 1268 },
            { "peapid": 1075, "peaaid": 1275 },
            { "peapid": 1080, "peaaid": 1280 },
            { "peapid": 1091, "peaaid": 1291 },
            { "peapid": 111013, "peaaid": 111213 },
            { "peapid": 111025, "peaaid": 111225 },
            { "peapid": 111026, "peaaid": 111226 },
            { "peapid": 111054, "peaaid": 111254 },
            { "peapid": 111058, "peaaid": 111258 },
            { "peapid": 111066, "peaaid": 111266 },
            { "peapid": 1060, "peaaid": 1260 },
            { "peapid": 111018, "peaaid": 111218 },
            { "peapid": 1059, "peaaid": 1259 },
            { "peapid": 1061, "peaaid": 1261 },
            { "peapid": 111020, "peaaid": 111220 },
            { "peapid": 111049, "peaaid": 111249 },
            { "peapid": 111072, "peaaid": 111272 },
            { "peapid": 200064, "peaaid": 32000640 },
            { "peapid": 1036, "peaaid": 1236 },
            { "peapid": 1073, "peaaid": 1273 },
            { "peapid": 200020, "peaaid": 32000200 },
            { "peapid": 200076, "peaaid": 32000760 },
            { "peapid": 1054, "peaaid": 1254 },
            { "peapid": 1065, "peaaid": 1265 },
            { "peapid": 111031, "peaaid": 111231 },
            { "peapid": 111032, "peaaid": 111232 },
            { "peapid": 111042, "peaaid": 111242 },
            { "peapid": 111060, "peaaid": 111260 },
            { "peapid": 111061, "peaaid": 111261 },
            { "peapid": 200018, "peaaid": 32000180 },
            { "peapid": 111011, "peaaid": 31110111 },
            { "peapid": 1067, "peaaid": 1267 },
            { "peapid": 1097, "peaaid": 1297 },
            { "peapid": 111050, "peaaid": 111250 },
            { "peapid": 111055, "peaaid": 111255 },
            { "peapid": 111052, "peaaid": 111252 }
        ]
    """.trimIndent().parseArray(JsonFeature.IsLenient).toList()

    var enabled by remember { mutableStateOf(false) }
    var peapid by remember { mutableStateOf("") }
    var peaaid by remember { mutableStateOf("") }
    var lpaei by remember { mutableStateOf(listOf<JsonElement>()) }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.background
    val accordionState = rememberAccordionState()

    LaunchedEffect(lpaei) {
        if (lpaei.isNotEmpty() && !accordionState.expanded) {
            accordionState.toggle()
        }
    }

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        )
    ) {
        Accordion(
            state = accordionState,
            headerContent = {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("植物装扮", style = MedalTheme.typography.h2)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        UnderlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = peapid,
                            onValueChange = { peapid = it },
                            label = { Text("peapid") },
                            enabled = !enabled,
                            isError = peapid.toIntOrNull() == null
                        )
                        UnderlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = peaaid,
                            onValueChange = { peaaid = it },
                            label = { Text("peaaid") },
                            enabled = !enabled,
                            isError = peaaid.toIntOrNull() == null
                        )
                        Button(
                            text = "添加",
                            enabled = !enabled && peapid.isNotEmpty() && peaaid.isNotEmpty() && peapid.toIntOrNull() != null && peaaid.toIntOrNull() != null,
                            onClick = {
                                lpaei = lpaei + buildJsonObject {
                                    put("peapid", peapid.toInt())
                                    put("peaaid", peaaid.toInt())
                                }
                                peapid = ""
                                peaaid = ""
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            text = "添加预设值",
                            enabled = !enabled,
                            variant = ButtonVariant.PrimaryOutlined,
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    lpaei = lpaei + preset.filter { newItem ->
                                        !lpaei.any { existingItem ->
                                            existingItem.asObject?.getInt("peapid") == newItem.asObject?.getInt("peapid") &&
                                                    existingItem.asObject?.getInt("peaaid") == newItem.asObject?.getInt(
                                                "peaaid"
                                            )
                                        }
                                    }
                                }
                            }
                        )

                        Button(
                            modifier = Modifier.weight(1f),
                            text = if (enabled) "已覆盖" else "确认覆盖",
                            variant = if (enabled) ButtonVariant.Secondary else ButtonVariant.PrimaryOutlined,
                            onClick = {
                                archiveViewModel.modifyArchive {
                                    it.copy {
                                        path("sd.lpaei") {
                                            if (enabled) {
                                                origin?.p?.asObject
                                                    ?.getJsonObject("sd")
                                                    ?.getJsonArray("lpaei")
                                                    ?: buildJsonArray { }
                                            } else buildJsonArray {
                                                lpaei.forEach { element -> add(element) }
                                            }
                                        }
                                    }
                                }
                                enabled = !enabled
                            }
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (lpaei.isNotEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.surface,
                            contentColor = if (enabled) MedalTheme.colors.onSuccess else MedalTheme.colors.onSurface
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("peapid")
                            Text("peaaid")
                            IconButton(
                                onClick = { lpaei = emptyList() },
                                variant = IconButtonVariant.DestructiveElevated,
                                enabled = !enabled
                            ) {
                                Icon(Icons.Rounded.Delete)
                            }
                        }
                    }
                }
                lpaei.forEachIndexed { index, element ->
                    if (index != 0) HorizontalDivider()
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(element.asObject?.getInt("peapid").toString())
                        Text(element.asObject?.getInt("peaaid").toString())
                        IconButton(
                            onClick = { lpaei = lpaei.filterIndexed { i, _ -> i != index } },
                            variant = IconButtonVariant.DestructiveGhost,
                            enabled = !enabled
                        ) {
                            Icon(Icons.Rounded.Delete)
                        }
                    }
                }
                if (lpaei.isNotEmpty()) Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun NameCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    val originN = origin?.p?.asObject?.getJsonObject("sd")?.getString("n") ?: ""
    var n by remember { mutableStateOf(originN) }

    var enabled by remember { mutableStateOf(false) }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.background

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor),
        ),
        onClick = {
            if (n.isNotEmpty()) {
                archiveViewModel.modifyArchive {
                    it.copy {
                        path("sd.n") {
                            if (enabled) primitive { originN } else primitive { n }
                        }
                    }
                }
                enabled = !enabled
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("游戏昵称${if (enabled) " - 已修改" else ""}", style = MedalTheme.typography.h2)
            UnderlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = n,
                onValueChange = { n = it },
                isError = n.isEmpty(),
                placeholder = { Text("在此处键入你喜欢的名字...") },
                readOnly = enabled,
            )
        }
    }
}

@Composable
fun CoinCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    val originC = (origin?.p?.asObject?.getJsonObject("sd")?.getInt("c") ?: 106496).decryptNumber()
    var c by remember { mutableStateOf("$originC") }

    var enabled by remember { mutableStateOf(false) }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.background

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor),
        ),
        onClick = {
            if (c.toIntOrNull() != null) {
                archiveViewModel.modifyArchive {
                    it.copy {
                        path("sd.c") {
                            if (enabled) primitive { originC.encryptNumber() } else primitive {
                                c.toInt().encryptNumber()
                            }
                        }
                    }
                }
                enabled = !enabled
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("金币${if (enabled) " - 已修改" else ""}", style = MedalTheme.typography.h2)
            UnderlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = c,
                onValueChange = { c = it },
                isError = c.toIntOrNull() == null,
                placeholder = { Text("请输入金币数量...") },
                readOnly = enabled,
            )
        }
    }
}

@Composable
fun UrnCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val archive by archiveViewModel.archive.collectAsState()

    val enabled by remember(archive) {
        derivedStateOf {
            archive?.p?.asObject
                ?.getJsonObject("sd")
                ?.getJsonArray("urn")
                ?.contains(primitive { 20 }) == true
        }
    }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.error

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        onClick = {
            archiveViewModel.modifyArchive {
                it.copy {
                    path("sd.urn") {
                        buildJsonArray {
                            if (!enabled) add(20)
                        }
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("双倍速", style = MedalTheme.typography.h2)
            Text(if (enabled) "开启" else "关闭", style = MedalTheme.typography.body1)
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun UnlockHsCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val archive by archiveViewModel.archive.collectAsState()
    val origin by archiveViewModel.origin.collectAsState()

    val targetList = buildList {
        add(primitive { 0 })
        for (i in 25000..26000) add(primitive { i })
    }

    val enabled by remember(archive) {
        derivedStateOf {
            archive?.p?.asObject
                ?.getJsonObject("sd")
                ?.getJsonArray("unlockhs")
                ?.containsAll(targetList) == true
        }
    }

    val containerColor = if (enabled) MedalTheme.colors.success else MaterialTheme.colorScheme.tertiaryContainer

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        onClick = {
            archiveViewModel.modifyArchive {
                it.copy {
                    path("sd.unlockhs") {
                        if (enabled) {
                            origin?.p?.asObject
                                ?.getJsonObject("sd")
                                ?.getJsonArray("unlockhs") ?: buildJsonArray { }
                        } else buildJsonArray {
                            addAll(targetList)
                        }
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("全头像", style = MedalTheme.typography.h2)
            Text(if (enabled) "全部获取" else "部分获取", style = MedalTheme.typography.body1)
        }
    }
}

@Composable
fun PslaCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val archive by archiveViewModel.archive.collectAsState()
    val origin by archiveViewModel.origin.collectAsState()

    val enabled by remember(archive) {
        derivedStateOf {
            archive?.p?.asObject
                ?.getJsonObject("sd")
                ?.getJsonArray("psla")
                ?.let { psla ->
                    psla.firstOrNull {
                        it.asObject?.getInt("icpi") == 428
                    }?.asObject?.let { obj ->
                        when (obj.getInt("icl")) {
                            5 -> true
                            else -> false
                        }
                    }
                }
        }
    }

    val containerColor = when (enabled) {
        null -> MedalTheme.colors.error
        true -> MedalTheme.colors.success
        false -> MaterialTheme.colorScheme.tertiaryContainer
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        onClick = {
            archiveViewModel.modifyArchive {
                it.copy {
                    path("sd.psla") { psla ->
                        if (enabled != true) buildJsonArray {
                            psla.asArray?.filterNot { obj -> obj.asObject?.getInt("icpi") == 428 }
                                ?.forEach { element -> add(element) }
                            addJsonObject {
                                put("icpi", 428)
                                put("icl", 5)
                            }
                        } else {
                            origin?.p?.asObject
                                ?.getJsonObject("sd")
                                ?.getJsonArray("psla")
                                ?: buildJsonArray { }
                        }
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("豌豆藤蔓", style = MedalTheme.typography.h2)
            Text(
                when (enabled) {
                    null -> "未拥有"
                    true -> "已拥有五阶"
                    false -> "未达到五阶"
                }, style = MedalTheme.typography.body1
            )
        }
    }
}

@Composable
fun WmedCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    val wmed = """
        [{"w":1,"e":[{"i":6,"h":1},{"i":1,"h":1},{"i":2,"h":1},{"i":3,"h":1},{"i":4,"h":1}],"r":"false"},{"w":2,"e":[{"i":1,"h":1},{"i":12,"h":1},{"i":2,"h":1},{"i":46,"h":1},{"i":3,"h":1},{"i":15,"h":1},{"i":40,"h":1},{"i":7,"h":1},{"i":6,"h":1},{"i":5,"h":1},{"i":35,"h":1},{"i":14,"h":1},{"i":8,"h":1},{"i":9,"h":1},{"i":10,"h":1},{"i":4,"h":1},{"i":20,"h":1},{"i":22,"h":1},{"i":84,"h":1}],"r":"false"},{"w":3,"e":[{"i":40,"h":1},{"i":1,"h":1},{"i":11,"h":1},{"i":2,"h":1},{"i":64,"h":1},{"i":3,"h":1},{"i":4,"h":1},{"i":5,"h":1},{"i":6,"h":1},{"i":13,"h":1},{"i":7,"h":1},{"i":8,"h":1},{"i":9,"h":1},{"i":16,"h":1},{"i":10,"h":1},{"i":48,"h":1},{"i":49,"h":1},{"i":50,"h":1},{"i":52,"h":1},{"i":53,"h":1},{"i":54,"h":1},{"i":55,"h":1},{"i":56,"h":1},{"i":57,"h":1},{"i":58,"h":1},{"i":59,"h":1},{"i":60,"h":1},{"i":61,"h":1},{"i":62,"h":1},{"i":63,"h":1}],"r":"false"},{"w":4,"e":[{"i":26,"h":1},{"i":1,"h":1},{"i":11,"h":1},{"i":2,"h":1},{"i":111,"h":1},{"i":3,"h":1},{"i":4,"h":1},{"i":12,"h":1},{"i":5,"h":1},{"i":6,"h":1},{"i":7,"h":1},{"i":8,"h":1},{"i":9,"h":1},{"i":13,"h":1},{"i":10,"h":1},{"i":84,"h":1},{"i":85,"h":1},{"i":86,"h":1},{"i":87,"h":1},{"i":88,"h":1},{"i":89,"h":1},{"i":90,"h":1},{"i":91,"h":1},{"i":92,"h":1},{"i":93,"h":1},{"i":94,"h":1},{"i":95,"h":1},{"i":96,"h":1},{"i":97,"h":1},{"i":110,"h":1}],"r":"false"},{"w":5,"e":[{"i":44,"h":1},{"i":1,"h":1},{"i":2,"h":1},{"i":85,"h":1},{"i":3,"h":1},{"i":11,"h":1},{"i":40,"h":1},{"i":7,"h":1},{"i":6,"h":1},{"i":76,"h":1},{"i":5,"h":1},{"i":35,"h":1},{"i":8,"h":1},{"i":9,"h":1},{"i":10,"h":1},{"i":4,"h":1},{"i":20,"h":1},{"i":22,"h":1},{"i":19,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":26,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":42,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":31,"h":1},{"i":84,"h":1}],"r":"false"},{"w":14,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":16,"h":1},{"i":14,"h":1},{"i":15,"h":1},{"i":17,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":21,"h":1},{"i":22,"h":1},{"i":24,"h":1},{"i":34,"h":1},{"i":25,"h":1},{"i":27,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":32,"h":1},{"i":33,"h":1},{"i":35,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":38,"h":1},{"i":39,"h":1},{"i":40,"h":1},{"i":41,"h":1},{"i":42,"h":1},{"i":50,"h":1},{"i":51,"h":1},{"i":52,"h":1}],"r":"false"},{"w":6,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":35,"h":1},{"i":41,"h":1},{"i":69,"h":1},{"i":42,"h":1},{"i":43,"h":1},{"i":44,"h":1},{"i":46,"h":1},{"i":47,"h":1},{"i":48,"h":1},{"i":49,"h":1},{"i":50,"h":1},{"i":37,"h":1},{"i":51,"h":1},{"i":52,"h":1},{"i":53,"h":1},{"i":54,"h":1},{"i":55,"h":1},{"i":56,"h":1},{"i":60,"h":1},{"i":61,"h":1},{"i":62,"h":1},{"i":63,"h":1},{"i":64,"h":1},{"i":65,"h":1},{"i":66,"h":1},{"i":67,"h":1},{"i":68,"h":1}],"r":"false"},{"w":8,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":35,"h":1},{"i":41,"h":1},{"i":86,"h":1},{"i":42,"h":1},{"i":43,"h":1},{"i":40,"h":1},{"i":44,"h":1},{"i":72,"h":1},{"i":46,"h":1},{"i":47,"h":1},{"i":36,"h":1},{"i":48,"h":1},{"i":71,"h":1},{"i":49,"h":1},{"i":73,"h":1},{"i":74,"h":1},{"i":75,"h":1},{"i":76,"h":1},{"i":50,"h":1},{"i":77,"h":1},{"i":78,"h":1},{"i":79,"h":1},{"i":80,"h":1},{"i":51,"h":1},{"i":52,"h":1},{"i":53,"h":1},{"i":54,"h":1},{"i":68,"h":1}],"r":"false"},{"w":12,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":14,"h":1},{"i":15,"h":1},{"i":17,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":20,"h":1},{"i":21,"h":1},{"i":22,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":26,"h":1},{"i":27,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":32,"h":1},{"i":33,"h":1},{"i":34,"h":1},{"i":35,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":38,"h":1},{"i":39,"h":1},{"i":40,"h":1},{"i":41,"h":1},{"i":42,"h":1}],"r":"false"},{"w":11,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":59,"h":1},{"i":14,"h":1},{"i":15,"h":1},{"i":17,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":21,"h":1},{"i":22,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":27,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":32,"h":1},{"i":33,"h":1},{"i":61,"h":1},{"i":34,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":39,"h":1},{"i":48,"h":1},{"i":40,"h":1},{"i":42,"h":1},{"i":43,"h":1},{"i":45,"h":1},{"i":56,"h":1}],"r":"false"},{"w":17,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":15,"h":1},{"i":16,"h":1},{"i":17,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":20,"h":1},{"i":21,"h":1},{"i":22,"h":1},{"i":23,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":26,"h":1},{"i":27,"h":1},{"i":28,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":31,"h":1}],"r":"false"},{"w":7,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":35,"h":1},{"i":41,"h":1},{"i":40,"h":1},{"i":42,"h":1},{"i":75,"h":1},{"i":43,"h":1},{"i":45,"h":1},{"i":44,"h":1},{"i":46,"h":1},{"i":47,"h":1},{"i":48,"h":1},{"i":49,"h":1},{"i":50,"h":1},{"i":51,"h":1},{"i":52,"h":1},{"i":53,"h":1},{"i":73,"h":1},{"i":54,"h":1},{"i":55,"h":1},{"i":58,"h":1},{"i":56,"h":1},{"i":60,"h":1},{"i":61,"h":1},{"i":62,"h":1},{"i":74,"h":1},{"i":69,"h":1},{"i":70,"h":1},{"i":71,"h":1},{"i":72,"h":1},{"i":68,"h":1}],"r":"false"},{"w":15,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":16,"h":1},{"i":14,"h":1},{"i":15,"h":1},{"i":17,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":21,"h":1},{"i":22,"h":1},{"i":24,"h":1},{"i":34,"h":1},{"i":25,"h":1},{"i":27,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":32,"h":1},{"i":33,"h":1},{"i":35,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":38,"h":1},{"i":39,"h":1},{"i":40,"h":1},{"i":41,"h":1},{"i":42,"h":1}],"r":"false"},{"w":13,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":13,"h":1},{"i":75,"h":1},{"i":14,"h":1},{"i":15,"h":1},{"i":17,"h":1},{"i":12,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":21,"h":1},{"i":26,"h":1},{"i":22,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":27,"h":1},{"i":76,"h":1},{"i":29,"h":1},{"i":30,"h":1},{"i":32,"h":1},{"i":33,"h":1},{"i":34,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":38,"h":1},{"i":39,"h":1},{"i":40,"h":1},{"i":41,"h":1},{"i":43,"h":1},{"i":51,"h":1}],"r":"false"},{"w":16,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":12,"h":1},{"i":13,"h":1},{"i":17,"h":1},{"i":14,"h":1},{"i":16,"h":1},{"i":15,"h":1},{"i":18,"h":1},{"i":19,"h":1},{"i":22,"h":1},{"i":24,"h":1}],"r":"false"},{"w":9,"e":[{"i":11,"h":1},{"i":1,"h":1},{"i":35,"h":1},{"i":41,"h":1},{"i":82,"h":1},{"i":42,"h":1},{"i":43,"h":1},{"i":44,"h":1},{"i":72,"h":1},{"i":45,"h":1},{"i":46,"h":1},{"i":47,"h":1},{"i":48,"h":1},{"i":71,"h":1},{"i":49,"h":1},{"i":73,"h":1},{"i":74,"h":1},{"i":75,"h":1},{"i":76,"h":1},{"i":50,"h":1},{"i":77,"h":1},{"i":78,"h":1},{"i":79,"h":1},{"i":80,"h":1},{"i":51,"h":1},{"i":52,"h":1},{"i":53,"h":1},{"i":55,"h":1},{"i":68,"h":1}],"r":"false"},{"w":10,"e":[{"i":44,"h":1},{"i":1,"h":1},{"i":88,"h":1},{"i":2,"h":1},{"i":3,"h":1},{"i":86,"h":1},{"i":40,"h":1},{"i":7,"h":1},{"i":6,"h":1},{"i":90,"h":1},{"i":5,"h":1},{"i":35,"h":1},{"i":91,"h":1},{"i":8,"h":1},{"i":9,"h":1},{"i":10,"h":1},{"i":4,"h":1},{"i":20,"h":1},{"i":85,"h":1},{"i":22,"h":1},{"i":19,"h":1},{"i":24,"h":1},{"i":25,"h":1},{"i":26,"h":1},{"i":89,"h":1},{"i":36,"h":1},{"i":37,"h":1},{"i":42,"h":1},{"i":29,"h":1},{"i":30,"h":1}],"r":"false"}]
    """.trimIndent().parseArray(JsonFeature.IsLenient)

    var enabled by remember { mutableStateOf(false) }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.background

    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        onClick = {
            archiveViewModel.modifyArchive {
                it.copy {
                    path("sd.wmed") {
                        if (enabled) {
                            origin?.p?.asObject
                                ?.getJsonObject("sd")
                                ?.getJsonArray("wmed")
                                ?: buildJsonArray { }
                        } else wmed
                    }
                }
            }
            enabled = !enabled
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("满星通关", style = MedalTheme.typography.h2)
            Text(if (enabled) "已覆盖" else "未覆盖", style = MedalTheme.typography.body1)
        }
    }
}

@Composable
fun PrCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    val pr = origin?.p?.asObject
        ?.getJsonObject("sd")
        ?.getJsonArray("pr")
        ?: buildJsonArray { }

    var i by remember {
        mutableIntStateOf(
            pr.firstOrNull { it.asObject?.getString("n") == "poweruptacticalcuke" }
                ?.asObject?.getInt("i") ?: 0
        )
    }

    var enabled by remember { mutableStateOf(false) }

    val containerColor = if (enabled) MedalTheme.colors.success else MedalTheme.colors.background

    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        ),
        onClick = {
            archiveViewModel.modifyArchive {
                it.copy {
                    path("sd.pr") {
                        if (enabled) pr else buildJsonArray {
                            pr.filterNot { obj -> obj.asObject?.getString("n") == "poweruptacticalcuke" }
                                .forEach { element -> add(element) }
                            addJsonObject {
                                put("n", "poweruptacticalcuke")
                                put("i", i)
                            }
                        }
                    }
                }
            }
            enabled = !enabled
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("战术黄瓜", style = MedalTheme.typography.h2)

            Row(
                modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(if (enabled) "已覆盖" else "未覆盖", style = MedalTheme.typography.body1)
                Text("$i", style = MedalTheme.typography.body1)
            }

            Slider(
                value = i.toFloat(),
                onValueChange = { i = it.toInt() },
                modifier = Modifier.fillMaxWidth(),
                valueRange = 0f..20f,
                steps = 20,
                enabled = !enabled,
                colors = SliderDefaults.colors(
                    inactiveTrackColor = MedalTheme.colors.primary
                )
            )
        }
    }
}

@Composable
fun CredentialCard() {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val context by archiveViewModel.context.collectAsState()

    var pi by remember { mutableStateOf(context.variables["pi"]?.takeIf { it !is JsonNull }?.jsonPrimitive?.content) }
    var sk by remember { mutableStateOf(context.variables["sk"]?.takeIf { it !is JsonNull }?.jsonPrimitive?.content) }
    var ui by remember { mutableStateOf(context.variables["ui"]?.takeIf { it !is JsonNull }?.jsonPrimitive?.content) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("凭据信息", style = MedalTheme.typography.h2)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                UnderlinedTextField(
                    modifier = Modifier.weight(2f),
                    singleLine = true,
                    value = pi ?: "",
                    onValueChange = { pi = it },
                    prefix = { Text("pi", modifier = Modifier.padding(horizontal = 8.dp)) },
                )
                UnderlinedTextField(
                    modifier = Modifier.weight(3f),
                    singleLine = true,
                    value = sk ?: "",
                    onValueChange = { sk = it },
                    prefix = { Text("sk", modifier = Modifier.padding(horizontal = 8.dp)) },
                )
                UnderlinedTextField(
                    modifier = Modifier.weight(2f),
                    singleLine = true,
                    value = ui ?: "",
                    onValueChange = { ui = it },
                    prefix = { Text("ui", modifier = Modifier.padding(horizontal = 8.dp)) },
                )
                Button(
                    text = "更新",
                    enabled = !pi.isNullOrEmpty() && !sk.isNullOrEmpty() && !ui.isNullOrEmpty(),
                    onClick = {
                        archiveViewModel.modifyContext(
                            "pi" to primitive { pi },
                            "sk" to primitive { sk },
                            "ui" to primitive { ui }
                        )
                        SnackbarManager.showSnackbar("凭据已更新")
                    }
                )
            }
        }
    }
}

@Composable
fun ObtainArchive() {
    var phoneOrUserId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    val archiveViewModel = koinViewModel<ArchiveViewModel>()

    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(112.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("登录获取存档信息", style = MedalTheme.typography.h1)
        OutlinedTextField(
            value = phoneOrUserId,
            onValueChange = { phoneOrUserId = it },
            label = { Text("手机号或拓维官服8位userId") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("登录密码") },
        )
        Button(
            text = "登录并获取存档",
            enabled = phoneOrUserId.isNotEmpty() && password.isNotEmpty() && !loading,
            loading = loading,
            onClick = {
                loading = true
                archiveViewModel.obtainArchive(
                    settings.channel,
                    phoneOrUserId,
                    password,
                ).invokeOnCompletion {
                    loading = false
                }
            }
        )
    }
}

@Composable
fun PasiCard(modifier: Modifier = Modifier) {
    val archiveViewModel = koinViewModel<ArchiveViewModel>()
    val origin by archiveViewModel.origin.collectAsState()

    val originPasi = origin?.p?.asObject
        ?.getJsonObject("sd")
        ?.getJsonArray("pasi")
        ?: buildJsonArray { }

    fun selectPacidLPair(pasi: JsonArray): Pair<Long, Int>? {
        // Step 1: 筛选 pact 在枚举中的
        val validItems = pasi.mapNotNull { it.jsonObject }
            .filter { item ->
                val pact = item["pact"]?.jsonPrimitive?.contentOrNull
                pact != null && Accessory.fromPact(pact) != null
            }

        if (validItems.isEmpty()) return null

        // Step 2: 找出 l 最大值
        val maxL = validItems.maxOfOrNull { it["l"]?.jsonPrimitive?.intOrNull ?: -1 } ?: -1
        val maxLItems = validItems.filter { it["l"]?.jsonPrimitive?.intOrNull == maxL }

        // Step 3: 找 pacid 出现最多的
        val pacidCountMap = maxLItems
            .mapNotNull { it["pacid"]?.jsonPrimitive?.longOrNull }
            .groupingBy { it }
            .eachCount()

        val maxCount = pacidCountMap.values.maxOrNull() ?: return null
        val candidates = pacidCountMap.filterValues { it == maxCount }.keys

        // 优先选 pact == super_clock_7 的 pacid
        val preferred = maxLItems.firstOrNull {
            it["pact"]?.jsonPrimitive?.contentOrNull == "super_clock_7" &&
                    it["pacid"]?.jsonPrimitive?.longOrNull in candidates
        }

        val finalPacid = preferred?.get("pacid")?.jsonPrimitive?.longOrNull
            ?: candidates.firstOrNull()

        return finalPacid?.let { it to maxL }
    }

    val pacidL by remember { mutableStateOf(selectPacidLPair(originPasi)) }
    var enabled by remember { mutableStateOf(if (pacidL == null) null else false) }

    val containerColor = when (enabled) {
        true -> MedalTheme.colors.success
        false -> MedalTheme.colors.background
        null -> MedalTheme.colors.secondary
    }

    val accordionState = rememberAccordionState(enabled = enabled != null)

    val pasiMap = remember { mutableStateMapOf<Accessory, String>() }

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(containerColor)
        )
    ) {
        Accordion(
            state = accordionState,
            headerContent = {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("植物挂件", style = MedalTheme.typography.h2)
                        Text("点击展开挂件列表", style = MedalTheme.typography.body1)
                    }

                    Button(
                        text = when (enabled) {
                            true -> "已覆盖"
                            false -> "未修改"
                            null -> "不满足条件"
                        },
                        variant = if (enabled == false) ButtonVariant.Primary else ButtonVariant.Secondary,
                        onClick = {
                            if (pacidL != null) {
                                val addedItems = buildList {
                                    pasiMap.forEach { (accessory, countStr) ->
                                        val count = countStr.toIntOrNull() ?: 0
                                        repeat(count) {
                                            add(
                                                buildJsonObject {
                                                    put("pacid", pacidL!!.first)
                                                    put("pact", accessory.pact)
                                                    put("l", pacidL!!.second)
                                                }
                                            )
                                        }
                                    }
                                }

                                val pasi = buildJsonArray {
                                    originPasi.forEach { add(it) }
                                    addedItems.forEach { add(it) }
                                }

                                archiveViewModel.modifyArchive {
                                    it.copy {
                                        path("sd.pasi") {
                                            if (enabled == true) originPasi else pasi
                                        }
                                    }
                                }
                                enabled = !enabled!!
                            }
                        }
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (enabled != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = if (enabled == true) MedalTheme.colors.success else MedalTheme.colors.surface,
                            contentColor = if (enabled == true) MedalTheme.colors.onSuccess else MedalTheme.colors.onSurface
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("挂件名称")
                            Text("持有数量")
                            Text("添加数量")
                            Spacer(Modifier.width(16.dp))
                        }
                    }
                }
                Accessory.entries.forEachIndexed { index, accessory ->
                    if (index != 0) HorizontalDivider()

                    val ownedCount = originPasi.count {
                        val pact = it.jsonObject["pact"]?.jsonPrimitive?.contentOrNull
                        Accessory.fromPact(pact ?: "") == accessory
                    }

                    val countText = pasiMap.getOrPut(accessory) { "0" }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(accessory.displayName, modifier = Modifier.weight(1f))
                        Text(ownedCount.toString(), modifier = Modifier.weight(1f))
                        UnderlinedTextField(
                            value = countText,
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() }) {
                                    pasiMap[accessory] = newValue
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(Modifier.height(2.dp))
            }
        }
    }
}

enum class Accessory(val displayName: String, val pact: String) {
    SUPER_CLOCK("紫手套", "super_clock"),
    SUPER_CLOCK_1("红蜡烛", "super_clock_1"),
    SUPER_CLOCK_2("白蜡烛", "super_clock_2"),
    SUPER_CLOCK_3("太阳锅盔", "super_clock_3"),
    SUPER_CLOCK_4("月亮锅盔", "super_clock_4"),
    SUPER_CLOCK_5("公主冰冠", "super_clock_5"),
    SUPER_CLOCK_6("女王冰冠", "super_clock_6"),
    SUPER_CLOCK_7("牛仔手套", "super_clock_7"),
    SUPER_CLOCK_8("聚能电池", "super_clock_8"),
    SUPER_CLOCK_9("节能电池", "super_clock_9"),
    SUPER_CLOCK_10("强效杀虫剂", "super_clock_10"),
    SUPER_CLOCK_11("杀虫剂", "super_clock_11"),
    SUPER_CLOCK_12("大爆竹", "super_clock_12"),
    SUPER_CLOCK_13("爆竹", "super_clock_13"),
    SUPER_CLOCK_14("小时钟", "super_clock_14"),
    SUPER_CLOCK_15("加速时钟", "super_clock_15"),
    SUPER_CLOCK_16("警用电击棍", "super_clock_16"),
    SUPER_CLOCK_17("电击棍", "super_clock_17"),
    PAINKILLER_1("止疼剂", "painkiller_1"),
    PAINKILLER_2("止疼片", "painkiller_2"),
    SLINGSHOT_1("金属弹弓", "slingshot_1"),
    SLINGSHOT_2("木质弹弓", "slingshot_2"),
    MAGIC_BOOK_1("魔法书", "magic_book_1"),
    MAGIC_BOOK_2("高级魔法书", "magic_book_2"),
    SUN_GEAR_1("阳光齿轮", "sun_gear_1"),
    TRAVEL_TOGETHER_1("时光胶囊", "travel_together_1"),
    HERO_CAPE_1("降魔披风", "hero_cape_1");

    companion object {
        fun fromPact(pact: String): Accessory? = entries.find { it.pact == pact }
        fun fromName(name: String): Accessory? = entries.find { it.displayName == name }
    }
}

