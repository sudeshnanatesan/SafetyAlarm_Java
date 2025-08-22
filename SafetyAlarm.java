import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafetyAlarm {
    private List<EmergencyContact> emergencyContacts;
    private boolean alarmActive;
    private Location currentLocation;
    private boolean panicMode;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SafetyAlarm() {
        this.emergencyContacts = new ArrayList<>();
        this.alarmActive = false;
        this.currentLocation = new Location(0.0, 0.0); // Default location
        this.panicMode = false;
        System.out.println("Safety Alarm System Initialized at: " + getCurrentTime());
    }

    // Inner classes for data management
    static class EmergencyContact {
        private String name;
        private String phoneNumber;

        public EmergencyContact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() { return name; }
        public String getPhoneNumber() { return phoneNumber; }
    }

    static class Location {
        private double latitude;
        private double longitude;

        public Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return String.format("%.6f°N, %.6f°E", latitude, longitude);
        }
    }

    private String getCurrentTime() {
        return dateFormat.format(new Date());
    }

    // Core functionality methods
    public void addEmergencyContact(String name, String phoneNumber) {
        emergencyContacts.add(new EmergencyContact(name, phoneNumber));
        System.out.println("\n✅ Emergency contact added: " + name);
        System.out.println("   Phone: " + phoneNumber);
        System.out.println("   Time: " + getCurrentTime());
    }

    public void updateLocation(double latitude, double longitude) {
        this.currentLocation = new Location(latitude, longitude);
        System.out.println("\n📍 Location updated at " + getCurrentTime());
        System.out.println("   New location: " + currentLocation);
    }

    public void triggerPanicMode() {
        this.panicMode = true;
        this.alarmActive = true;
        System.out.println("\n============================================");
        System.out.println("⚠️ PANIC MODE ACTIVATED at " + getCurrentTime() + " ⚠️");
        System.out.println("============================================\n");
        alertEmergencyContacts();
        sendLocationToAuthorities();
        startAlarmSound();
    }

    public void deactivatePanicMode() {
        if (!this.panicMode) {
            System.out.println("\nℹ️ Panic mode is not currently active.");
            return;
        }
        this.panicMode = false;
        this.alarmActive = false;
        System.out.println("\n✅ Panic mode deactivated at " + getCurrentTime());
        System.out.println("   All alerts have been cancelled.");
        System.out.println("   Location: " + currentLocation);
    }

    private void alertEmergencyContacts() {
        if (emergencyContacts.isEmpty()) {
            System.out.println("⚠️ WARNING: No emergency contacts found!");
            return;
        }
        System.out.println("\nAlerting emergency contacts at " + getCurrentTime() + ":");
        System.out.println("----------------------------------------");
        for (EmergencyContact contact : emergencyContacts) {
            System.out.println("📱 Sending alert to " + contact.getName() + 
                             " (" + contact.getPhoneNumber() + ")");
            System.out.println("   Message: Emergency! User needs immediate assistance!");
            System.out.println("   Location: " + currentLocation);
            System.out.println("----------------------------------------");
        }
    }

    private void sendLocationToAuthorities() {
        System.out.println("\n📍 Sending current location to authorities:");
        System.out.println("Time: " + getCurrentTime());
        System.out.println("Location: " + currentLocation);
        System.out.println("Status: Alert Transmitted to Local Emergency Services");
    }

    private void startAlarmSound() {
        System.out.println("🔊 ALARM SOUNDING - LOUD SIREN ACTIVATED 🔊");
        // In a real implementation, this would trigger an actual sound
        for (int i = 0; i < 3; i++) {
            System.out.println("BEEP! BEEP! BEEP!");
            try {
                Thread.sleep(1000); // Simulate alarm sound with 1-second intervals
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        SafetyAlarm safetySystem = new SafetyAlarm();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n🚨 Safety Alarm System 🚨");
        System.out.println("==========================");

        // Add some demo emergency contacts
        safetySystem.addEmergencyContact("Police", "911");
        safetySystem.addEmergencyContact("Emergency Contact 1", "123-456-7890");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Emergency Contact");
            System.out.println("2. Update Location");
            System.out.println("3. Trigger Panic Mode");
            System.out.println("4. Deactivate Panic Mode");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    safetySystem.addEmergencyContact(name, phone);
                    break;

                case 2:
                    try {
                        System.out.print("Enter latitude: ");
                        double lat = scanner.nextDouble();
                        System.out.print("Enter longitude: ");
                        double lon = scanner.nextDouble();
                        safetySystem.updateLocation(lat, lon);
                    } catch (Exception e) {
                        System.out.println("❌ Invalid coordinates! Please enter valid numbers.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;

                case 3:
                    safetySystem.triggerPanicMode();
                    break;

                case 4:
                    safetySystem.deactivatePanicMode();
                    break;

                case 5:
                    System.out.println("\n👋 Exiting Safety Alarm System");
                    System.out.println("   Goodbye! Stay safe!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid option! Please choose 1-5.");
            }
        }
    }
}
