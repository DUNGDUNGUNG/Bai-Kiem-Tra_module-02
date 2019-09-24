package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> provinces() {
        return countryService.findAll();
    }

    @GetMapping("/cities")
    public ModelAndView listCity(Pageable pageable) {
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("city/list", "cities", cities);
        return modelAndView;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("city/create", "city", new City());
        return modelAndView;
    }

    @PostMapping("/save-city")
    public ModelAndView saveCity(@Valid @ModelAttribute City city, BindingResult result) {
        if (result.hasFieldErrors()) {
            return new ModelAndView("city/create");
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("city/create", "city", new City());
        modelAndView.addObject("message", "Bạn vừa tạo thành phố mới thành công");
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/edit", "city", city);
        return modelAndView;
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@Valid @ModelAttribute City city, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasFieldErrors()) {
            return new ModelAndView("city/edit");
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        redirectAttributes.addFlashAttribute("message", "Bạn đã cập nhật thành công thành phố " + city.getName());
        return modelAndView;
    }

    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id) {
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/view", "city", city);
        return modelAndView;

    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("city/delete", "city", city);
        return modelAndView;
    }

    @PostMapping("/delete-city")
    public ModelAndView deleteCity(@ModelAttribute City city, RedirectAttributes redirectAttributes) {
        cityService.remove(city.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        redirectAttributes.addFlashAttribute("message", "Bạn đã xóa thành phố ");
        return modelAndView;
    }
}
