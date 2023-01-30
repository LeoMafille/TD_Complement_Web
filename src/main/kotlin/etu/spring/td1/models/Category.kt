package etu.spring.td1.models

data class Category(val label:String) {
    private val items=HashSet<item>()

    operator fun get(itemName:String):item? {
        return items.find { it.nom == itemName }
    }

    fun ajoutItem(nom:String):Boolean {
        if(items.contains(item(nom))){
            return false
        }
        items.add(item(nom))
        return true
    }

    fun supprItem(nom:String):Boolean {
        return items.remove(item(nom))
    }
}