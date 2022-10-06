package cz.mendelu.pef.compose.petstore.models

import java.io.Serializable

// Todo Základní položka. Obsahuje atributy z JSONu.
class Item (
    var key: String,
    var value: Double
) : Serializable {

}

