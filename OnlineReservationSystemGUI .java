import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OnlineReservationSystemGUI extends JFrame {

    private List<Reservation> reservations = new ArrayList<>();
    private JTextArea reservationListTextArea;
    private JTextField guestNameTextField;
    private JComboBox<String> roomComboBox;
    private JTextField checkInDateTextField;
    private JTextField checkOutDateTextField;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public OnlineReservationSystemGUI() {
        setTitle("Online Hotel Room Reservation System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        reservationListTextArea = new JTextArea(15, 40);
        reservationListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservationListTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        JLabel guestNameLabel = new JLabel("Guest Name:");
        inputPanel.add(guestNameLabel);

        guestNameTextField = new JTextField(20);
        inputPanel.add(guestNameTextField);

        JLabel roomLabel = new JLabel("Select Room:");
        inputPanel.add(roomLabel);

        roomComboBox = new JComboBox<>(new String[]{"101", "102", "103"}); // Example room numbers
        inputPanel.add(roomComboBox);

        JLabel checkInDateLabel = new JLabel("Check-In Date (dd-MM-yyyy):");
        inputPanel.add(checkInDateLabel);

        checkInDateTextField = new JTextField(12);
        inputPanel.add(checkInDateTextField);

        JLabel checkOutDateLabel = new JLabel("Check-Out Date (dd-MM-yyyy):");
        inputPanel.add(checkOutDateLabel);

        checkOutDateTextField = new JTextField(12);
        inputPanel.add(checkOutDateTextField);

        JButton reserveButton = new JButton("Make Reservation");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });
        inputPanel.add(reserveButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void makeReservation() {
        String guestName = guestNameTextField.getText().trim();
        String roomNumber = (String) roomComboBox.getSelectedItem();
        String checkInDateString = checkInDateTextField.getText().trim();
        String checkOutDateString = checkOutDateTextField.getText().trim();

        Date checkInDate, checkOutDate;
        try {
            checkInDate = dateFormat.parse(checkInDateString);
            checkOutDate = dateFormat.parse(checkOutDateString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd-MM-yyyy.");
            return;
        }

        Reservation reservation = new Reservation(guestName, roomNumber, checkInDate, checkOutDate);
        reservations.add(reservation);
        updateReservationList();
        guestNameTextField.setText("");
        checkInDateTextField.setText("");
        checkOutDateTextField.setText("");
    }

    private void updateReservationList() {
        reservationListTextArea.setText("");
        for (Reservation reservation : reservations) {
            reservationListTextArea.append(reservation.getGuestName() + " - Room: " + reservation.getRoomNumber() +
                    " - Check-in: " + dateFormat.format(reservation.getCheckInDate()) +
                    " - Check-out: " + dateFormat.format(reservation.getCheckOutDate()) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineReservationSystemGUI().setVisible(true);
            }
        });
    }
}

class Reservation {
    private String guestName;
    private String roomNumber;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(String guestName, String roomNumber, Date checkInDate, Date checkOutDate) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}
