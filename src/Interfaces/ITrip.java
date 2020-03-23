package Interfaces;

public interface ITrip {
	public enum TripType {
		Stop, Non_Stop, Many_Stops
	};

	double price = 0.0;
	int distance = 0;
	TripType getType();
	void setType(TripType newType);
}
