package taksan.labs.test;

import taksan.labs.RotationTimeProvider;

public class RotationTimeProviderMock implements
		RotationTimeProvider {
	
	
	private int rotationTime = 42000;

	@Override
	public int getRotationPeriod() {
		return rotationTime;
	}

	public void setRotationPeriod(int rotationTime) {
		this.rotationTime = rotationTime;
	}
}