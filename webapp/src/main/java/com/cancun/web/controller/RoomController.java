/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.controller;

import static com.cancun.web.Constant.DATE_FORMAT;
import com.cancun.web.model.AvailableRoom;
import com.cancun.web.model.Customer;
import com.cancun.web.model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cancun.web.model.Room;
import com.cancun.web.service.ReservationService;
import com.cancun.web.service.CustomerService;
import com.cancun.web.service.RoomService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author anatoleabe
 */
@Controller
@RequiredArgsConstructor
public class RoomController {

    @Autowired
    RoomService roomService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String home(Model model) {
//        Iterable<Room> listRoom = roomService.getRooms();
//        model.addAttribute("rooms", listRoom);

        return "home";
    }
    
    @RequestMapping("admin/rooms")
    @GetMapping
    public String roomlist(Model model) {
        Iterable<Room> listRoom = roomService.getRooms();
        model.addAttribute("rooms", listRoom);

        return "admin/room/index";
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
        if (room.getId() == null) {
            room.setCreation(new Date());
        }
        room.setLastModified(new Date());
        roomService.saveRoom(room);
        return new ModelAndView("redirect:/admin/rooms");
    }

    @GetMapping("/room/check")
    public String checkAvailableRooms(Model model, @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Date start, @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Date end) {
        Iterable<AvailableRoom> rooms = reservationService.checkAvailableRooms(start, end);
        model.addAttribute("rooms", rooms);
        return "home";
    }

    @GetMapping("/room/reserve/{id}")
    public String reserverRoom(@PathVariable("id") final int id, Model model) {
        Room e = roomService.getRoom(id);
        Customer c = new Customer();
        Reservation reservation = new Reservation();
        reservation.setRoomId(e);
        reservation.setCustomerId(c);
        
        model.addAttribute("reservation", reservation);
        return "/room/booking";
    }

    @PostMapping("/room/reserve")
    public ModelAndView reserveRoom(@ModelAttribute Reservation reservation) {
        Customer customer = reservation.getCustomerId();
        customer.setCreation(new Date());
        customer.setLastModified(new Date());
        customer = customerService.saveCustomer(customer);
        
        if (reservation.getId() == null) {
            reservation.setCreation(new Date());
        }
        reservation.setLastModified(new Date());
        reservation.setCustomerId(customer);
        reservation.setCancelled(false);
        reservation.setQuantity(1);//Just for the test. Can be improved.
        reservation =  reservationService.saveReservation(reservation);
        return new ModelAndView("redirect:/reservation/notification/"+reservation.getId());
    }
}
