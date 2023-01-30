package etu.spring.td1.models

data class item(var nom: String) {
    var evaluation:Int=0
        set(value) {
            if(value in 0..10) field=value
        }


    /*
    INUTILE CAR DATA CLASS LE FAIT TOUT SEUL
    override fun equals(other: Any?): Boolean {
        if(other===this) {
            return true
        }
        if(other !is item) {
            return false;
        }
        return other.nom == this.nom
    }

    override fun hashCode(): Int {
        return nom.hashCode()
    }*/
}