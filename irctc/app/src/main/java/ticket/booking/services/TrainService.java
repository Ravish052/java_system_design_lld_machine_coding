package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "../localDB/trains.json";

    public TrainService() throws IOException{
        File train = new File(TRAIN_DB_PATH);
        trainList = objectMapper.readValue(train, new TypeReference<List<Train>>() {});
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train ->validTrain(train, source, destination)).collect(Collectors.toList());
    }

    public void addTrain(Train newTrain){
        // check if train for same number exist
        Optional <Train> existingTrains = trainList.stream()
                .filter(train -> train.getTrainId().equals(newTrain.getTrainId()) )
                .findFirst();
        // if present, update it instead of adding a new one
        // if not present, add the new train
        if(existingTrains.isPresent()) {
            updateTrain(newTrain);
        }else{
            trainList.add(newTrain);
            saveTrainListToFIle();
        }

    }

    public void updateTrain(Train updatedTrain){
        // find index of the train with same id
        // if found replace the existing train with updated one
        // if not consider it as a new addition

        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();
        if (index.isPresent()){
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFIle();
        }else{
            addTrain(updatedTrain);
        }
    }

    private void saveTrainListToFIle(){
        try{
            objectMapper.writeValue(new File(TRAIN_DB_PATH),trainList );
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validTrain(Train train, String source, String Destination){
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(Destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}
