package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.StoryDTO;
import com.project.bulmaze.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/story{id}")
    public String getStory(@PathVariable Long id, Model model) {
        StoryDTO story = this.storyService.getStory(id);
        model.addAttribute("story", story);

        return "story";
    }
}
