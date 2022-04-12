import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    static IFilesManager filesManager = new FilesManagerJavaNio2();

    public static void main(String[] Args){
//        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss (zzz)");
//        String text = date.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);
//        SimpleDateFormat formatter = null;
//        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss xxx");



        String path = "in.csv";
        String pathOut = "out.csv";
        List<FlightInformation> list = new ArrayList<FlightInformation>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){ //instanciando direto no try
            String line = br.readLine();
            line = br.readLine();
            while(line != null){
                String[] vect = line.split(";");
                String origin = vect[0];
                String destination = vect[1];
                String airline = vect[2];
                TemporalAccessor departureParse = formatter.parse(vect[3]);  //convertendo string para LocalDateTime
                ZonedDateTime departure = ZonedDateTime.from(departureParse);  //convertendo string para LocalDateTime

                TemporalAccessor arrivalParse = formatter.parse(vect[4]);
                ZonedDateTime arrival = ZonedDateTime.from(arrivalParse);
                Double price = Double.parseDouble(vect[5]);

                FlightInformation flightInformation = new FlightInformation(origin,destination,airline,departure,arrival,price);
                list.add(flightInformation);

                line = br.readLine();
            }

            System.out.println("FLIGHT INFORMATION: ");
            for(FlightInformation p : list){
                System.out.println(p);
            }
        }catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        writeFlightsWhithDuration(list, pathOut);
  }
    public static void writeFlightsWhithDuration(List<FlightInformation> list, String pathOut) {
        List<String> flightsInformationList = new ArrayList<>();
        flightsInformationList.add("origin;destination;airline;departure;arrival;price;duration");
        for (FlightInformation flightInformation : list.stream()
                .sorted(Comparator.comparing(FlightInformation::getOrigin)
                        .thenComparing(FlightInformation::getDestination)
                        .thenComparing(FlightInformation::getDuration)
                        .thenComparing(FlightInformation::getPrice)
                        .thenComparing(FlightInformation::getAirline))
                .collect(Collectors.toList())) {
            flightsInformationList.add(FlightInformation.escreverCSV(flightInformation));
        }

        filesManager.writeLines(pathOut, flightsInformationList, false);
    }
}
