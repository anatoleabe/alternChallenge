/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cancun.web.model.Reservation;
import com.cancun.web.model.Room;
import com.cancun.web.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author anatoleabe
 */
@Controller

public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping("admin/reservations")
    @GetMapping
    public String index(Model model) {
        Iterable<Reservation> listReservations = reservationService.getReservations();
        model.addAttribute("reservations", listReservations);

        return "reservation/index";
    }
    
    @GetMapping("/reservation/notification/{id}")
    public String notification(@PathVariable("id") final int id, Model model) {
        Reservation r = reservationService.getReservation(id);
        model.addAttribute("reservation", r);
        return "/reservation/notification";
    }
}
