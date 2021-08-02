package com.barberia.app.HelperFunction;

import com.barberia.app.dto.TimeAndCountDto;
import com.barberia.app.models.Booking;
import com.barberia.app.models.Salon;
import com.barberia.app.services.BookingService;
import com.barberia.app.services.SalonService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MyFunction {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private SalonService salonService;


    public List<Time> listTimes = new ArrayList<Time>();


    public static int countTotalTimeSlot(LocalTime start, LocalTime end, int totalMinuteInOneTimeSlot){
        int startTime =start.getHour();
        int endTime =end.getHour();
        float temp =  (float)totalMinuteInOneTimeSlot / 60;
        float total_time_slot = (endTime - startTime)/ temp;
        return (int)Math.floor(total_time_slot);
    }

    // trả về danh sách thời gian đã full
    public static List<LocalDateTime> getBookingsThatHaveMoreThanNumberOfBookingInOneTimeSlot (List<Booking> onlineBookings, int numberOfTurnInOneTimeSlot){
        List<LocalDateTime> listOfTimeOfOnlineBooking = new  ArrayList<LocalDateTime>();
        Map<LocalDateTime, Integer> result = new HashMap<LocalDateTime, Integer>();
        List<LocalDateTime> listTimeIsFull = new ArrayList<LocalDateTime>();

        for(Booking b: onlineBookings){
            listOfTimeOfOnlineBooking.add(b.getChosenTimeSlot());
        }

        for(LocalDateTime ldt : listOfTimeOfOnlineBooking){
            Integer j = result.get(ldt);
            result.put(ldt, (j==null)?1:j+1);
        }

        for (Map.Entry<LocalDateTime, Integer> val : result.entrySet()) {
            if(val.getValue()>=numberOfTurnInOneTimeSlot){
                listTimeIsFull.add(val.getKey());
            }
        }

        Collections.sort(listTimeIsFull);

        return listTimeIsFull;

    }
}
