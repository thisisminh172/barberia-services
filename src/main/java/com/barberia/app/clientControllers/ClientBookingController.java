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
import org.springframework.web.bind.annotation.*;
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
        List<Service> services = serviceService.findAllByAvailable(true);
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
                newMyLocalDateTime.setPassed(false);
                listOfDateTime.add(newMyLocalDateTime);

                tempTime = tempTime.plusMinutes(minuteInOneTimeSlot);
            }
            PairDateAndDateTime pair = new PairDateAndDateTime();
            pair.setKey(today);
            pair.setValue(listOfDateTime);
            listPair.add(pair);
            today = today.plusDays(1);
        }

        int currentHourCustomerBooking = LocalDateTime.now().plusHours(2).getHour();



        for(PairDateAndDateTime p : listPair){
            for(MyLocalDateTime d : p.getValue()){
                for (int k = 0;k< listTimeIsFull.size();k++){
                    if(d.getChosenTime().isEqual(listTimeIsFull.get(k))){
                        d.setFull(true);

                    }

                }
//                System.out.println(d.getChosenTime().getHour()+" "+d.isPassed()+" "+(d.getChosenTime().getHour() < currentHourCustomerBooking)+" "+currentHourCustomerBooking);
            }
        }
        for(MyLocalDateTime d : listPair.get(0).getValue()){
            if(d.getChosenTime().getHour() < currentHourCustomerBooking){
                d.setPassed(true);
            }

        }
//        for(MyLocalDateTime d : listPair.get(0).getValue()){
//            System.out.println(d.getChosenTime()+" "+d.isPassed());
//
//        }






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
            newCustomer.setPassword("");
            newCustomer.setMembership(false);
            customerService.save(newCustomer);
        }

        Customer customer = customerService.findByPhoneNumber(phoneNumber);
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/booking";
    }

    @PostMapping("/booking/process-booking")
    public String processBooking(@RequestParam(required = false,value = "salonId") Long salonId,@RequestParam(required = false,value = "customerId") Long customerId,@RequestParam(required = false,value = "serviceItems") List<Long> serviceItems,@RequestParam(required = false,value = "chosenTimeSlot") String chosenTimeSlot,@RequestParam(required = false,value = "description") String description, Model model){

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
        // LIST OF SERVICES
        List<Service> serviceList = new ArrayList<Service>();
        // TOTAL BILL
        double total = 0;
        // Set booking details
        for(int i = 0; i< serviceItems.size();i++){
            Service chosenService = serviceService.findById(serviceItems.get(i)).get();
            // use for data
            total += chosenService.getOriginalPrice();
            serviceList.add(chosenService);
            // end use for data

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
        model.addAttribute("phoneNumber", customer.getPhoneNumber());
        model.addAttribute("chosenTimeSlot", LocalDateTime.parse(chosenTimeSlot));
        model.addAttribute("services", serviceList);
        model.addAttribute("total", total);



        // gui thong tin confirm and done
        //***************chua lam
        return "client/confirm-booking";
    }

    @GetMapping("/booking/confirm-booking")
    public String checkData(Model model){
        if(model.getAttribute("phoneNumber") == null && model.getAttribute("chosenTimeSlot")==null && model.getAttribute("services")==null&& model.getAttribute("total")==null){
            return "redirect:/";
        }else{
            return "client/confirm-booking";
        }
    }


}
