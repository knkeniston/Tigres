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

	public Tigress(final float x, final float y) {
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
	}
}
