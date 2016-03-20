package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.Train;
import org.apache.struts.model.TrainType;
import org.apache.struts.model.User;

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
