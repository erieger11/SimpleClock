//package SimpleClock;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
public class SimpleClock extends JFrame {
    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;
    JButton b;
    JButton c;
    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    static String time;
    String day;
    String date;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(450, 300);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 45));
        timeLabel.setBackground(Color.WHITE);
        timeLabel.setForeground(Color.RED);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 30));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 26));

        b = new JButton("Click to switch to 24-hour");
        b.setBounds(60, 45, 40, 40);

        this.getContentPane().add(b);

        c = new JButton("Click to switch to GMT");
        c.setBounds(60, 45, 40, 40);
        this.getContentPane().add(c);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeFormat.toPattern().equals("hh:mm:ss a")) {
                    timeFormat = new SimpleDateFormat("HH:mm");
                    b.setText("Click to switch to 12-Hour");
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    b.setText("Click to switch to 24-Hour");
                }
            }
        });

        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeFormat.getTimeZone().getID().equals(TimeZone.getDefault().getID())) {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    c.setText("Click to switch to Local Time");
                } else {
                    timeFormat.setTimeZone(TimeZone.getDefault());
                    c.setText("Click to switch to GMT");
                }
            }
        });


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);
        setTimer();
    }
        public void setTimer() {
            while (!Thread.currentThread().isInterrupted()) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            JFrame addedMore = new SimpleClock();
        }
    }
