package cz.mendelu.pef.compose.petstore.models

import java.io.Serializable

// Todo Obálka nad základní položkou. Je to vlastně ten výsledek, co chcete.
class Data : Serializable {
    var data: MutableList<Item>? = null
}