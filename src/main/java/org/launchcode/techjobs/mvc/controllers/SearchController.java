package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private static List<Job> jobs = new ArrayList<>();

    @GetMapping(value = "")
    public String search(Model model) {
        jobs.clear(); // added to remove previous search results when reloading the search page
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchTerm.equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Search Results"); // TODO - TITLE NEEDS TO BE FIXED FOR RESPONSIVENESS
        }
        System.out.println(jobs);

        //TODO - need to figure out how to show search nav on search/results
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);

        return "list-jobs";
        //return "redirect:"; // this code WORKS for displaying proper results and search nav on localhost:8080/search
    }


}
