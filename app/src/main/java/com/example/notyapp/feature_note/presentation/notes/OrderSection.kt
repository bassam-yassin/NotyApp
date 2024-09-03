package com.example.notyapp.feature_note.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notyapp.feature_note.domain.util.OrderNote
import com.example.notyapp.feature_note.domain.util.OrderType
import com.example.notyapp.feature_note.presentation.DefaultRadioButton

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    orderNote: OrderNote = OrderNote.Date(OrderType.Descending),
    onOrderChange: (OrderNote) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultRadioButton(
                text = "Title",
                selected = orderNote is OrderNote.Title,
                onSelect = { onOrderChange(OrderNote.Title(orderNote.orderType)) }
            )
            Spacer(Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = orderNote is OrderNote.Date,
                onSelect = { onOrderChange(OrderNote.Date(orderNote.orderType)) }
            )
            Spacer(Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = orderNote is OrderNote.Color,
                onSelect = { onOrderChange(OrderNote.Color(orderNote.orderType)) }
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultRadioButton(
                text = "Ascending",
                selected = orderNote.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(orderNote.copy(OrderType.Ascending)) }
            )
            Spacer(Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = orderNote.orderType is OrderType.Descending,
                onSelect = { onOrderChange(orderNote.copy(OrderType.Descending)) }
            )
        }

    }

}