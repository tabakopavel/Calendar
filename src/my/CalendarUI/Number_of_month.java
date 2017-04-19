package my.CalendarUI;

/**
 * Created by UX303L on 13.01.2017.
 */
public class Number_of_month {
  private  String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
String s;

     Number_of_month  (String s){
      this.s=s;
  }

  int Get_month(){
      int m=0;
      for(int i=0;i<months.length;i++){

          if (s.equals(months[i])){
              break;
          }
          m++;
      }
      return m;
  }
}
