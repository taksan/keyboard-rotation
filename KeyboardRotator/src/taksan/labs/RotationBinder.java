package taksan.labs;

public interface RotationBinder extends RotationManager {
	public void setTurnChangeListener(TurnChangeListener listener, RotationTimeProvider timeProvider);
}
