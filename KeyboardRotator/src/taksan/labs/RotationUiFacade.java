package taksan.labs;

import android.content.Context;

public interface RotationUiFacade {

	Context getContext();

	void setOnClickListener(RotationClickListener rotationClickListener);

	void configure();

	void setPlayerName(String player);

	void startRotation();

	void stopRotation();

	Integer getStoredRotationTime();

	void updateTimeLabel();

	int getRotationPeriod();
	
}
