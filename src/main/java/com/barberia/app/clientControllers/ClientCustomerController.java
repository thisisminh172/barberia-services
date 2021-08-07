package com.barberia.app.clientControllers;

import com.barberia.app.dto.MessageDto;
import com.barberia.app.models.Booking;
import com.barberia.app.models.Customer;
import com.barberia.app.services.BookingService;
import com.barberia.app.services.CustomerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClientCustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookingService bookingService;

    // LOGIN
    @RequestMapping("/login")
    public String showLogin(Model model) {
        MessageDto messageDto =(MessageDto) model.getAttribute("MessageDto");

        if(messageDto==null){
            return "client/login";
        }else{

            model.addAttribute("loginError", messageDto.getMessage());
            model.addAttribute("error", messageDto.isAvailable());
            return "client/login";
        }
    }


    // CHECK LOGIN
    @PostMapping("/check-login")
    public String checkLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session){
        Customer findCustomer = customerService.findByPhoneNumber(phoneNumber);
        System.out.println(findCustomer.getPhoneNumber());
        if(findCustomer!=null){
            if(findCustomer.getPassword().length()!=0){
                if(findCustomer.getPassword().equals(password)){
                    System.out.println("login thanh cong");
                    session.setAttribute("PHONENUMBER", findCustomer.getPhoneNumber());
                    session.setAttribute("CUSTOMERID", findCustomer.getId());
                    session.setAttribute("ISLOGIN", true);
                    return "redirect:/";
                }else{
                    MessageDto messageDto = new MessageDto("Số điện thoại hoặc mật khẩu không chính xác!", true);
                    redirectAttributes.addFlashAttribute("MessageDto", messageDto);
                    return "redirect:/login";
                }
            }else{
                MessageDto messageDto = new MessageDto("Số điện thoại chưa được đăng ký!", true);
                redirectAttributes.addFlashAttribute("MessageDto", messageDto);
                return "redirect:/login";
            }

        }else{
            MessageDto messageDto = new MessageDto("Số điện thoại không chính xác!", true);
            redirectAttributes.addFlashAttribute("MessageDto", messageDto);
            return "redirect:/login";
        }
    }

    // LOG OUT
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("PHONENUMBER");
        session.removeAttribute("CUSTOMERID");
        session.removeAttribute("ISLOGIN");
        return "redirect:/login";
    }

    // toi trang dang ky
    @GetMapping("/customer-new")
    public String goRegisterPage(Model model){
//        MessageDto messageDto = (MessageDto) model.getAttribute("phoneCheckMessage");
//        if(messageDto!=null){
//            model.addAttribute("errorMessage", messageDto.getMessage());
//            model.addAttribute("error", messageDto.isAvailable());
//        }
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "client/customer_register";
    }

    // REGISTER
    @PostMapping("/customer-register")
    public String register(Customer customer, RedirectAttributes redirectAttributes, HttpSession session){
        if(customerService.findByPhoneNumber(customer.getPhoneNumber())==null){
            System.out.println("dang ky thanh cong");
            customer.setMembership(true);
            Customer newCustomer = customerService.save(customer);

            session.setAttribute("PHONENUMBER", newCustomer.getPhoneNumber());
            session.setAttribute("CUSTOMERID", newCustomer.getId());
            session.setAttribute("ISLOGIN", true);
            return "redirect:/";


        }else{
            MessageDto messageDto = new MessageDto("Số điện thoại này đã tồn tại", true);
            redirectAttributes.addFlashAttribute("phoneCheckMessage", messageDto);
            return "redirect:/customer-new";
        }
    }

    // CUSTOMER DETAIL
    @GetMapping("/customer-info")
    public String goCustomerInfo(long id, Model model){
        Customer customer = customerService.findById(id).get();
        List<Booking> bookings = bookingService.findByCustomerId(id);
        model.addAttribute("customer",customer);
        model.addAttribute("bookings",bookings);
        return "client/customer_info";
    }
    // CUSTOMER UPDATE PAGE
    @GetMapping("/customer-update")
    public String goCustomerUpdatePage(long id, Model model){
        Customer customer = customerService.findById(id).get();

        model.addAttribute("customer",customer);

        return "client/customer_update";
    }

    // UPDATE CUSTOMER
    @RequestMapping(value = "/customer-save", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateCustomer(Customer customer){

        customerService.save(customer);
        return "redirect:/";
    }

    // PASSWORD CHANGE
//    @RequestParam(value = "id") long id,@RequestParam(value = "phoneNumber") String phoneNumber,@RequestParam(value = "password") String password,@RequestParam(value = "nickName") String nickName,@RequestParam(value = "email") String email





}
