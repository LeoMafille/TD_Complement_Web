package etu.spring.td1.models

data class Category(val label:String) {
    private val items=HashSet<item>()

    operator fun get(itemName:String):item? {
        return items.find { it.nom == itemName }
    }
}