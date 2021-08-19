package com.barberia.app.services;

import com.barberia.app.dto.BookingReportDto;
import com.barberia.app.dto.CustomerReportDto;
import com.barberia.app.dto.FeedbackReportDto;
import com.barberia.app.dto.PaymentReportDto;
import com.barberia.app.models.Booking;
import com.barberia.app.models.Customer;
import com.barberia.app.models.Feedback;
import com.barberia.app.models.Payment;
import com.barberia.app.repositories.BookingRepository;
import com.barberia.app.repositories.CustomerRepository;
import com.barberia.app.repositories.FeedbackRepository;
import com.barberia.app.repositories.PaymentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public String exportPaymentReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\project_4\\barberia-services\\reports\\payments";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentReportDto> paymentReportDtos = new ArrayList<PaymentReportDto>();
        for(int i = 0; i< payments.size();i++){

            PaymentReportDto newPaymentReport = new PaymentReportDto();
            newPaymentReport.setId(payments.get(i).getId());
            newPaymentReport.setEmployeeName(payments.get(i).getTurn().getEmployee().getFirstName());
            newPaymentReport.setDatetime(payments.get(i).getTurn().getBooking().getChosenTimeSlot().format(formatter));
            String method = payments.get(i).getPaymentMethod().equalsIgnoreCase("cash")?"CASH":"MOMO wallet";
            newPaymentReport.setPaymentMethod(method);
            double totalPrice = payments.get(i).getTotalPrice();
            newPaymentReport.setTotalPrice(totalPrice);
            paymentReportDtos.add(newPaymentReport);
        }
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:paymentlist2.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paymentReportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Lê Minh");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\paymentlist.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\paymentlist.pdf");
        }
        return "Report generated in path: "+path;
    }

    public String exportFeedbackReport(String reportFormat) throws FileNotFoundException, JRException{
        String path = "D:\\project_4\\barberia-services\\reports\\feedbacks";
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackReportDto> feedbackReportDtos = new ArrayList<FeedbackReportDto>();
        for (int i = 0; i< feedbacks.size();i++){
            FeedbackReportDto feedbackReportDto = new FeedbackReportDto();
            feedbackReportDto.setId(feedbacks.get(i).getId());
            feedbackReportDto.setName(feedbacks.get(i).getFullName());
            feedbackReportDto.setEmail(feedbacks.get(i).getEmail());
            feedbackReportDto.setPhoneNumber(feedbacks.get(i).getPhoneNumber());
            feedbackReportDto.setComment(feedbacks.get(i).getComment());
            feedbackReportDtos.add(feedbackReportDto);
        }
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:feedback.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(feedbackReportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Thiên Ân");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\feedback.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\feedback.pdf");
        }

        return "Report generated in path: "+path;
    }

    public String exportCancelBookingReport(String reportFormat) throws FileNotFoundException, JRException{
        String path = "D:\\project_4\\barberia-services\\reports\\bookings";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Booking> bookings = bookingRepository.findByStatus("cancel");
        List<BookingReportDto> bookingReportDtos = new ArrayList<BookingReportDto>();
        for (int i = 0; i< bookings.size();i++){
            BookingReportDto bookingReportDto = new BookingReportDto();
            bookingReportDto.setId(bookings.get(i).getId());
            bookingReportDto.setCustomerName(bookings.get(i).getCustomer().getNickName());
            bookingReportDto.setTime(bookings.get(i).getChosenTimeSlot().format(formatter));
            bookingReportDto.setPhoneNumber(bookings.get(i).getCustomer().getPhoneNumber());
            bookingReportDto.setStatus(bookings.get(i).getStatus());
            bookingReportDtos.add(bookingReportDto);
        }
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:cancelbooking.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookingReportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Minh Thoại");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\cancelbooking.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\cancelbooking.pdf");
        }

        return "Report generated in path: "+path;
    }

    public String exportCustomerReport(String reportFormat) throws FileNotFoundException, JRException{
        String path = "D:\\project_4\\barberia-services\\reports\\customers";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Customer> customers = customerRepository.findByMembershipTrue();
        List<CustomerReportDto> customerReportDtos = new ArrayList<CustomerReportDto>();
        for (int i = 0; i< customers.size();i++){
            CustomerReportDto customerReportDto = new CustomerReportDto();
            customerReportDto.setId(customers.get(i).getId());
            customerReportDto.setCustomerName(customers.get(i).getNickName());
            customerReportDto.setPhoneNumber(customers.get(i).getPhoneNumber());
            customerReportDto.setEmail(customers.get(i).getEmail());
            customerReportDtos.add(customerReportDto);
        }
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:customer.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customerReportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Vinh Hiển");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\customer.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\customer.pdf");
        }

        return "Report generated in path: "+path;
    }
}
