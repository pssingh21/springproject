package com.bway.springproject.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GalleryController {
	
	@RequestMapping(value="/gallery", method=RequestMethod.GET)
	public String gallery(Model model){
		List<String> imgList = Arrays.asList("02.jpg","03.jpg", "04.jpg", "002.jpg", "003.jpg", "004.jpg", "005.jpg", "006.jpg");
		model.addAttribute("imglist", imgList);
		return "gallery";
	}
}
