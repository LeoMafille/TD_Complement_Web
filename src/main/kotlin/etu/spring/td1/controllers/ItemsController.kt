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
    fun addNewAction(@ModelAttribute("nom") nom:String, @SessionAttribute("items") items:HashSet<item>, attrs:RedirectAttributes):RedirectView {

        if(items.add(item(nom))) {
            attrs.addFlashAttribute("msg", UIMessage.message("Ajout d'item", "$nom a été inséré"))

        } else {
            attrs.addFlashAttribute("msg", UIMessage.message("Ajout d'item", "$nom est  déjà dans la liste <br> Il n'a pas été ajouté", "error", "warning circle"))
        }
        return RedirectView("/")
    }
}
