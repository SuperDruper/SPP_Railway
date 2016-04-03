package code.dao.daointerface;

import code.model.Race;
import code.model.Train;
import code.model.TrainType;
import code.dao.IDao;

import java.util.Collection;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface ITrainDao extends IDao<Train, Integer>  {
    public List<Train> getTrainsWithType(TrainType trainType);

    //I am not sure, but on my mind
    public Collection<Race> getRacesForTrain(Train train);
    public Train getTrainByID(Integer id);
    public Integer getCarriageAmountForTrain(Train train);
    public TrainType getTypeForTrain(Train train);
}
