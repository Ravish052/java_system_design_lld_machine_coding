package ticket.booking.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.IOException;
import java.util.List;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "../localDB/trains.json";

    public TrainService() throws IOException{

    }

    public List<Train> searchTrains(String source, String Destination){}

    public void addTrain(Train newTrain){}

    public void updateTrain(Train updatedTrain){}

    private void saveTrainListToFIle(){}

    private boolean validTrain(Train train, String source, String Destination){

    }
}
