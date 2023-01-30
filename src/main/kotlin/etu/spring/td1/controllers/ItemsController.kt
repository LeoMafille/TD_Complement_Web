package etu.spring.td1.controllers

import etu.spring.td1.models.item
import etu.spring.td1.services.UIMessage
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@SessionAttributes("items")

class ItemsController {
    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") msg:UIMessage.Message?): String {
        return "index"
    }


    private fun getItemByName(nom:String, items:HashSet<item>):item?=items.find {nom == it.nom }

    private fun addMsg(resp:Boolean,attrs: RedirectAttributes,title:String,success:String,error:String) {
        if (resp) {
            attrs.addFlashAttribute("msg",
                    UIMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                    UIMessage.message(title, error, "error", "warning circle"))

        }
    }


    @get:ModelAttribute("items")
    val items: Set<item>
        get() {

            var items = HashSet<item>()
            items.add(item("Foo"))
            return items

        }

    @RequestMapping("new")
    fun newAction():String{
        return "newForm"
    }

    @PostMapping("/addNew")
    fun addNewAction(
            @ModelAttribute("nom") nom:String,
            @SessionAttribute("items") items:HashSet<item>,
            attrs:RedirectAttributes):RedirectView{
        addMsg(
                items.add(item(nom)),
                attrs,
                "Ajout",
                "$nom a été ajouté avec succès",
                "$nom est déjà dans la liste,<br>Il n'a pas été ajouté."
        )
        return RedirectView("/")
    }

    @GetMapping("/inc/{nom}")
    fun incAction(
            @PathVariable nom:String,
            @SessionAttribute("items") items:HashSet<item>,
            attrs:RedirectAttributes
    ):RedirectView{
        val item=getItemByName(nom,items)
        item?.evaluation =item!!.evaluation+1
        addMsg(
                item!=null,
                attrs,
                "Mise à jour",
                "$nom incrémenté",
                "$nom n'existe pas dans les items"
        )
        return RedirectView("/")
    }

    @GetMapping("/dec/{nom}")
    fun decAction(
            @PathVariable nom:String,
            @SessionAttribute("items") items:HashSet<item>,
            attrs:RedirectAttributes
    ):RedirectView{
        val item=getItemByName(nom,items)
        item?.evaluation =item!!.evaluation-1
        addMsg(
                item!=null,
                attrs,
                "Mise à jour",
                "$nom décrémenté",
                "$nom n'existe pas dans les items"
        )
        return RedirectView("/")
    }
}
