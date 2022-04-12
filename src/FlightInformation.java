import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class FlightInformation implements Serializable {
    private static final long serialVersionUID=1L;

    private String origin;
    private String destination;
    private String airline;
    private ZonedDateTime departure;
    private ZonedDateTime arrival;
    private Double price;
    private Long duration;

    public FlightInformation(){
    }
    public FlightInformation(String origin, String destination, String airline, ZonedDateTime departure, ZonedDateTime arrival,Double price){
        super();
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.duration = this.departure.until(this.arrival, ChronoUnit.HOURS);
    }

    public String getOrigin(){
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline(){return airline;}
    public void setAirline(String airline){this.airline = airline;}

    public ZonedDateTime getDeparture(){return departure;}
    public void setDeparture(ZonedDateTime departure){this.departure = departure;}

    public ZonedDateTime getArrival(){return arrival;}
    public void setArrival(ZonedDateTime arrival){this.arrival = arrival;}

    public Double getPrice(){return price;}
    public void setPrice(Double price){this.price = price;}

    public Long getDuration(){return duration;}
    public void setDuration(Long duration){this.duration = duration;}

    public static String escreverCSV(FlightInformation fligthInformation) {
        return fligthInformation.getOrigin() + ";" +
                fligthInformation.getDestination() + ";" +
                fligthInformation.getAirline() + ";" +
                fligthInformation.getDeparture() + ";" +
                fligthInformation.getArrival() + ";" +
                fligthInformation.getPrice() + ";" +
                fligthInformation.getDuration();
    }

    @Override
    public String toString() {
        return "Flight informations [Origin=" + origin + "," +
                " Destination=" + destination + "," +
                " Airline=" + airline + "," +
                " Departure=" + departure + "," +
                " Arrival=" + arrival + "," +
                " price=" + price + "]";
    }

}
