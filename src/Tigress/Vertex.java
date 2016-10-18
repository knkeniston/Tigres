package Tigress;

import java.util.HashSet;
import java.util.Set;

import jig.Entity;
import jig.ResourceManager;

public class Vertex extends Entity {
	
	Set<Vertex> neighbors;

	/**
	 * Constructs the moving entity.
	 * @param x: position's x coordinate
	 * @param y: position's y coordinate
	 * @param facingImages: left, right, front, back images of entity
	 * @param facing: starting direction entity is facing
	 */
	public Vertex(final float x, final float y) {
		super(x, y);
		addImageWithBoundingBox(ResourceManager
				.getImage(TigressGame.VERTEX_IMG_RSC));
		neighbors = new HashSet<Vertex>();
	}
	
	public void addNeighbors(Vertex neighbor) {
		neighbors.add(neighbor);
	}
	
}
