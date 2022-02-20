/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cancun.web.model.Room;
import com.cancun.web.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author anatoleabe
 */
@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Room> listRoom = roomService.getRooms();
        model.addAttribute("rooms", listRoom);

        return "home";
    }

    @GetMapping("/room/create")
    public String createRoom(Model model) {
        Room e = new Room();
        model.addAttribute("room", e);
        return "/room/create";
    }

    @GetMapping("/room/update/{id}")
    public String updateRoom(@PathVariable("id") final int id, Model model) {
        Room e = roomService.getRoom(id);
        model.addAttribute("room", e);
        return "/room/update";
    }

    @GetMapping("/room/delete/{id}")
    public ModelAndView deleteRoom(@PathVariable("id") final int id) {
        roomService.deleteRoom(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/room/save")
    public ModelAndView saveRoom(@ModelAttribute Room room) {
        roomService.saveRoom(room);
        return new ModelAndView("redirect:/");
    }
}
