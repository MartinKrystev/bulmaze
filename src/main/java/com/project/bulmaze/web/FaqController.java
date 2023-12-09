package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.AddFaqDTO;
import com.project.bulmaze.model.dto.EditFaqDTO;
import com.project.bulmaze.model.dto.FirstAnswerDTO;
import com.project.bulmaze.model.dto.UserRegisterDTO;
import com.project.bulmaze.model.entity.FaqEntity;
import com.project.bulmaze.service.FaqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FaqController {
    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faq")
    public String getFAQ(Model model) {
        List<FaqEntity> faqEntities = this.faqService.allFaqs();
        model.addAttribute("faqEntities", faqEntities);
        return "faq";
    }

    @GetMapping("/faq/add")
    public String getAddFAQ() {
        return "faq-add";
    }

    @PostMapping("/faq/add")
    public String postAddFAQ(@Valid @ModelAttribute(name = "addFaqDTO") AddFaqDTO addFaqDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.faqService.addNewFaq(addFaqDTO)) {
            redirectAttributes.addFlashAttribute("addFaqDTO", addFaqDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addFaqDTO", bindingResult);
            return "redirect:/faq/add";
        }
        return "faq-edited";
    }

    @GetMapping("/faq/edit{id}")
    public String getEditFAQ(@PathVariable Long id, Model model) {
        EditFaqDTO editFaqDTO = this.faqService.findById(id);
        model.addAttribute("editFaqDTO", editFaqDTO);
        return "faq-edit";
    }

    @GetMapping("/faq/save{id}")
    public String getSaveEditedFAQ(@PathVariable Long id) {
        return "faq-edit";
    }

    @PostMapping("/faq/save{id}")
    public String postSaveEditedFAQ(@PathVariable Long id, @Valid @ModelAttribute(name = "editFaqDTO") EditFaqDTO editFaqDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.faqService.saveEditedFAQ(editFaqDTO)) {
            redirectAttributes.addFlashAttribute("editFaqDTO", editFaqDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.editFaqDTO", bindingResult);
            return "redirect:/faq/save{id}";
        }
        return "faq-edited";
    }

    @GetMapping("/faq/delete{id}")
    public String deleteFAQ(@PathVariable Long id) {
        this.faqService.deleteFAQ(id);
        return "faq-edited";
    }


    //Model Attributes
    @ModelAttribute(name = "addFaqDTO")
    public AddFaqDTO initAddFaqDTO() {
        return new AddFaqDTO();
    }
    @ModelAttribute(name = "editFaqDTO")
    public EditFaqDTO initEditFaqDTO() {
        return new EditFaqDTO();
    }
}
