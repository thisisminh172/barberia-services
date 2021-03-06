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
                    MessageDto messageDto = new MessageDto("S??? ??i???n tho???i ho???c m???t kh???u kh??ng ch??nh x??c!", true);
                    redirectAttributes.addFlashAttribute("MessageDto", messageDto);
                    return "redirect:/login";
                }
            }else{
                MessageDto messageDto = new MessageDto("S??? ??i???n tho???i ch??a ???????c ????ng k??!", true);
                redirectAttributes.addFlashAttribute("MessageDto", messageDto);
                return "redirect:/login";
            }

        }else{
            MessageDto messageDto = new MessageDto("S??? ??i???n tho???i kh??ng ch??nh x??c!", true);
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
        MessageDto messageDto = (MessageDto) model.getAttribute("phoneCheckMessage");
        if(messageDto!=null){
            model.addAttribute("errorMessage", messageDto.getMessage());
            model.addAttribute("error", messageDto.isAvailable());
        }
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "client/customer_register";
    }

    // REGISTER
    @PostMapping("/customer-register")
    public String register(Customer customer, RedirectAttributes redirectAttributes, HttpSession session){
        Customer findCustomer = customerService.findByPhoneNumber(customer.getPhoneNumber());
        if(findCustomer==null){
            System.out.println("dang ky thanh cong");
            Customer newCustomer = new Customer();
            newCustomer.setMembership(true);
            newCustomer.setPassword(customer.getPassword());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setNickName(customer.getNickName());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            Customer newCustomer1 = customerService.save(newCustomer);

            session.setAttribute("PHONENUMBER", newCustomer1.getPhoneNumber());
            session.setAttribute("CUSTOMERID", newCustomer1.getId());
            session.setAttribute("ISLOGIN", true);
            return "redirect:/";


        }
        if(findCustomer!=null && findCustomer.getPassword().length()==0){
            System.out.println("dang ky thanh cong");
            findCustomer.setMembership(true);
            findCustomer.setPassword(customer.getPassword());
            findCustomer.setEmail(customer.getEmail());
            findCustomer.setNickName(customer.getNickName());
            findCustomer.setPhoneNumber(customer.getPhoneNumber());
            Customer newCustomer = customerService.save(findCustomer);

            session.setAttribute("PHONENUMBER", newCustomer.getPhoneNumber());
            session.setAttribute("CUSTOMERID", newCustomer.getId());
            session.setAttribute("ISLOGIN", true);
            return "redirect:/";
        } else{
            MessageDto messageDto = new MessageDto("S??? ??i???n tho???i n??y ???? t???n t???i", true);
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
