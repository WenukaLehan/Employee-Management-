
package main;

import java.time.LocalDate;
import java.time.LocalTime;
import raven.alerts.MessageAlerts;


public class main {
    public static void main(String args[]){
        load lj= new load();
        lj.setVisible(true);
        
        
        try{
           
            for(int x=0;x<100;x++){
                
                //lj.num.setText("Loading.."+x+"%");
                lj.bar.setValue(x);
                Thread.sleep(100);
            }
            loading la= new loading();
            la.setVisible(true);
            lj.hide();
            
            while(true){
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            
            int hour = currentTime.getHour();
            int minute = currentTime.getMinute();
            int second = currentTime.getSecond();
            
            la.date.setText(currentDate+"  "+ hour+":"+ minute+":"+ second);
            Thread.sleep(1000);
            }
 
        }catch(Exception e){
            
        }
        
    }
    
    
}
