package com.barberia.app.clientControllers;

import com.barberia.app.HelperFunction.MyFunction;
import com.barberia.app.dto.MyLocalDateTime;
import com.barberia.app.dto.PairDateAndDateTime;
import com.barberia.app.models.*;
import com.barberia.app.services.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClientBookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private SalonService salonService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookingDetailService bookingDetailService;

    @RequestMapping("/booking")
    public String showBooking(Model model) {
        // GET customer from process controller
        Customer customer = (Customer) model.getAttribute("customer");
        if(customer == null){
            return "redirect:/";
        }
        Salon salon = salonService.findById(1l).get();
        List<Service> services = serviceService.findAll();
        int totalTimeSlot = MyFunction.countTotalTimeSlot(salon.getOpenedHour(), salon.getClosedHour(), salon.getMinuteInOneTimeSlot());
        int minuteInOneTimeSlot = salon.getMinuteInOneTimeSlot();
        LocalTime startTime = salon.getOpenedHour();

        List<PairDateAndDateTime> listPair = new ArrayList<PairDateAndDateTime>();

        List<LocalDate> listDays = new ArrayList<>();
        LocalDate today = LocalDate.now();
        listDays.add(today);
        // LỌC những time đã full
        List<Booking> onlineBookings = bookingService.findOnlineBooking();

        List<LocalDateTime> listTimeIsFull = MyFunction.getBookingsThatHaveMoreThanNumberOfBookingInOneTimeSlot(onlineBookings, salon.getNumberOfTurnInOneTimeSlot());

        for (int i = 0; i < 5; i++) {
            LocalDate nextDay = today.plusDays(1);
            listDays.add(nextDay);
            List<MyLocalDateTime> listOfDateTime = new ArrayList<MyLocalDateTime>();
            LocalTime tempTime = startTime;
            for(int k = 0; k< totalTimeSlot; k++){
                MyLocalDateTime newMyLocalDateTime = new MyLocalDateTime();
                newMyLocalDateTime.setChosenTime(today.atTime(tempTime));
                newMyLocalDateTime.setFull(false);
                listOfDateTime.add(newMyLocalDateTime);

                tempTime = tempTime.plusMinutes(minuteInOneTimeSlot);
            }
            PairDateAndDateTime pair = new PairDateAndDateTime();
            pair.setKey(today);
            pair.setValue(listOfDateTime);
            listPair.add(pair);
            today = today.plusDays(1);
        }

        for(PairDateAndDateTime p : listPair){
            for(MyLocalDateTime d : p.getValue()){
                for (int k = 0;k< listTimeIsFull.size();k++){
                    if(d.getChosenTime().isEqual(listTimeIsFull.get(k))){
                        d.setFull(true);
                    }
                }
            }
        }

        System.out.println(listTimeIsFull.get(0) +"  dfsaf");
        System.out.println(listTimeIsFull.get(1) + " fasdfsa ");




//        for(int i = 0; i< listPair.size();i++){
//            for(int j = 0; j<listPair.get(i).getValue().size(); j++){
//                for (int k = 0;k< listTimeIsFull.size();k++){
//                    if(listPair.get(i).getValue().get(j).getChosenTime().isEqual(listTimeIsFull.get(k))){
//                        listPair.get(i).getValue().get(j).setFull(true);
//                    }
//                }
//            }
//        }

        for(PairDateAndDateTime p : listPair){
            System.out.println(p.getKey());
            for(MyLocalDateTime t: p.getValue()){

                System.out.println(t.getChosenTime() + " "+ t.isFull());

            }
        }


        model.addAttribute("salon", salon);
        model.addAttribute("customer", customer);
        model.addAttribute("services", services);
        model.addAttribute("listDays", listDays);
        model.addAttribute("listPair", listPair);
        return "client/booking";
    }

    @RequestMapping(value = "/booking/get-customer", method = RequestMethod.POST)
    public String process(@RequestParam(value = "phoneNumber") String phoneNumber, RedirectAttributes redirectAttributes){

        // find the customer with the phonenumber
        // If there is no customer with this phone number it will create new customer
        Customer findCustomer = customerService.findByPhoneNumber(phoneNumber);
        if(findCustomer == null){
            Customer newCustomer = new Customer();
            newCustomer.setPhoneNumber(phoneNumber);
            newCustomer.setMembership(false);
            customerService.save(newCustomer);
        }

        Customer customer = customerService.findByPhoneNumber(phoneNumber);
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/booking";
    }

    @PostMapping("/booking/process-booking")
    public String processBooking(@RequestParam(required = false,value = "salonId") Long salonId,@RequestParam(required = false,value = "customerId") Long customerId,@RequestParam(required = false,value = "serviceItems") List<Long> serviceItems,@RequestParam(required = false,value = "chosenTimeSlot") String chosenTimeSlot,@RequestParam(required = false,value = "description") String description){

        System.out.println(salonId);
        System.out.println(customerId);
        System.out.println(serviceItems);
        System.out.println(chosenTimeSlot);
        System.out.println(description);

        Customer customer = customerService.findById(customerId).get();
        Salon salon = salonService.findById(salonId).get();

        Booking booking = new Booking();
        booking.setOnlineBooking(true);
        booking.setCustomer(customer);
        booking.setSalon(salon);
        booking.setChosenTimeSlot(LocalDateTime.parse(chosenTimeSlot));
        booking.setDescription(description);
        booking.setStatus("online");//walk-in or online
        Booking newBooking = bookingService.save(booking);

        // Set booking details
        for(int i = 0; i< serviceItems.size();i++){
            Service chosenService = serviceService.findById(serviceItems.get(i)).get();
            BookingDetail newBookingDetail = new BookingDetail();
            newBookingDetail.setBooking(newBooking);
            newBookingDetail.setService(chosenService);
            bookingDetailService.save(newBookingDetail);
            System.out.println("them thanh cong");
        }

        List<BookingDetail> bookingDetails = bookingDetailService.findByBookingId(newBooking.getId());

        for (BookingDetail bd : bookingDetails){
            System.out.println(bd.getBooking().getChosenTimeSlot());
            System.out.println(bd.getService().getServiceName());
        }




        // gui thong tin confirm and done
        //***************chua lam
        return "client/home";
    }


}
