package com.barberia.app.HelperFunction;

import com.barberia.app.models.Booking;
import com.barberia.app.models.Salon;
import com.barberia.app.services.BookingService;
import com.barberia.app.services.SalonService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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


}
