package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Tigress extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.TIGRESS_LEFTIMG_RSC,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC
		};
	
	private boolean holdingCub;
	private boolean flowered;
	private boolean eaten;
	private int powerTime;

	public Tigress(final float x, final float y) {
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		holdingCub = false;
		flowered = false;
		eaten = false;
		powerTime = 0;
	}
	
	/**
	 * @param status: true if holding cub, false if not
	 */
	public void setHolding(boolean status) {
		holdingCub = status;
	}
	
	/**
	 * @return holdingCub: true if holding cub, false if not
	 */
	public boolean getHolding() {
		return holdingCub;
	}
	
	/**
	 * @return true if powered up with meat, false if not
	 */
	public boolean poweredUp() {
		return flowered || eaten;
	}
	
	/**
	 * @return powerTime: amount of time left for a tigress's power up
	 */
	public int getPowerTime() {
		return powerTime;
	}
	
}
