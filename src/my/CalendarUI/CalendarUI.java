package my.CalendarUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.RIGHT;
import static javax.swing.SwingConstants.TOP;

/**
 * Created by UX303L on 12.01.2017.
 */
public class CalendarUI extends JFrame  {
    JPanel p1, p2;
    JComboBox month;
    JComboBox year;
    int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String weekdays[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int Month;
    int Year;
    Container c3 = getContentPane();
    public CalendarUI(String title) {

        setTitle(title);
        p1 = new JPanel();
        p1.setSize(350, 30);
        p1.setLayout(new FlowLayout(1,20,1));
        Font font = new Font("Verdana",Font.ITALIC,16);

        month = new JComboBox();
        month.setPreferredSize(new Dimension(200, 30));


        month.setFont(font);
        for (int i = 0; i < months.length; i++) {
            month.addItem(months[i]);
        }

        month.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Number_of_month qwe = new Number_of_month(String.valueOf(month.getSelectedItem()));
                    Month = qwe.Get_month();
                    Year = Integer.valueOf(String.valueOf(year.getSelectedItem()));
                    drawCalendar(Month, Year);
                }
            }
        });

        year = new JComboBox();
        year.setFont(font);
        year.setPreferredSize(new Dimension(200, 30));
        for (int i = 1980; i <= 2099; i++) {
            year.addItem(i);
        }
        year.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Number_of_month qwe = new Number_of_month(String.valueOf(month.getSelectedItem()));
                    Month = qwe.Get_month();
                    Year = Integer.valueOf(String.valueOf(year.getSelectedItem()));
                    drawCalendar(Month, Year);
                }
            }
        });

        p1.add(month);
        p1.add(year);
        p2 = new JPanel();
        p2.setLayout(new GridLayout(0, 7, 5, 5));


        Calendar calendar = Calendar.getInstance();

        Month = calendar.get(Calendar.MONTH);
        Year = calendar.get(Calendar.YEAR);

        drawCalendar(Month, Year);
        year.setSelectedItem(Year);
        month.setSelectedItem(months[Month]);
        setContentPane(new BgPanel());
        c3 = getContentPane();
        c3.setLayout(new FlowLayout(1,0,0));
        p1.setOpaque(false);
        p2.setOpaque(false);
        add(p1);
        add(p2);
        setVisible(true);
        setBounds(50, 50, 525, 560);
        setSize(525, 560);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }
    class BgPanel extends JPanel {public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("D:\\Lay.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }}

    public static void main(String[] args) {
        CalendarUI frame = new CalendarUI("Calendar");

    }

    public void drawCalendar(int inputMonth, int inputYear) {

        p2.removeAll();


        for (int u=0;u<weekdays.length;u++){
            JLabel label = new JLabel(weekdays[u]);
            label.setHorizontalAlignment(CENTER);
            Dimension labelSize = new Dimension(5, 5);
            label.setPreferredSize(labelSize);

            p2.add(label);
        }

        Calendar c2 = new GregorianCalendar(inputYear, inputMonth, 0);
         int c = c2.get(Calendar.DAY_OF_WEEK)-1;
        if(c<7){
        for (int e = 0; e < c; e++) {
            JLabel label = new JLabel(" ");
            label.setHorizontalAlignment(RIGHT);
            Dimension labelSize = new Dimension(60, 60);
            label.setPreferredSize(labelSize);
            p2.add(label);
        }}

        Font font = new Font("Verdana", Font.ITALIC, 16);
        for (int i = 0; i < days[inputMonth]; i++) {

            JLabel label = new JLabel(Integer.toString(i + 1));
            label.setHorizontalAlignment(RIGHT);
            Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
            Dimension labelSize = new Dimension(60, 60);
            label.setPreferredSize(labelSize);
            label.setBorder(solidBorder);
            label.setVerticalAlignment(JLabel.TOP);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setFont(font);
            p2.add(label);
        }
        p2.revalidate();
        setTitle(months[inputMonth]+", "+inputYear);
        if (c==7){c3.setSize(525, 500);}
        if(c!=7){c3.setSize(525, 560);}

    }
}
