package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Cub extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.CUB_IMG_RSC/*,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC*/
		};

	public Cub(final float x, final float y) {
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
	}
}
