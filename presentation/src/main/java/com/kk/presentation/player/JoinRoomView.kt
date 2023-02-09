import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkTextField
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R


@Composable
fun JoinRoomView(
    onBackHome : () -> Unit,
    navigateToWaitingRoom : () -> Unit
) {
    var nameValue by remember { mutableStateOf("") }
    var codeValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        KkTitle(
            label = stringResource(R.string.jg_join_room_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 40.dp)
        )
        KkBody(label = stringResource(R.string.jr_name))
        Spacer(modifier = Modifier.size(10.dp))
        KkTextField(
            value = nameValue,
            onValueChange = {
                nameValue = it
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))
        KkBody(label = stringResource(R.string.jr_code))
        Spacer(modifier = Modifier.size(10.dp))
        KkTextField(
            value = codeValue,
            onValueChange = {
                codeValue = it.uppercase()
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        KkButton(
            onClick = { /*TODO*/ },
            label = stringResource(R.string.jr_scan_code),
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))
        KkButton(
            onClick = { navigateToWaitingRoom.invoke() },
            label = stringResource(R.string.jr_join_room),
            modifier = Modifier
                .padding(vertical = 50.dp)
                .fillMaxWidth()
        )
    }
}