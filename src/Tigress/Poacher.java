package Tigress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Poacher extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC/*,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC*/
		};
	
	private boolean trapped;
	private Vertex currentPos;
	private Vertex nextPos;
	private Vector movingDir;
	private String direction;
	private boolean firstPath;
	private boolean reset;

	public Poacher(final float x, final float y, Vertex pos) { 
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		trapped = false;
		currentPos = pos;
		firstPath = true;
		reset = false;
	}
	
	private void nearestTiger(ArrayList<Cub> cubs, Tigress tigress, ArrayList<Vertex> vertices) {
		Map<Vector, Entity> m = new HashMap<Vector, Entity>();
		m.put(tigress.getPosition(), tigress);
		for (Cub c : cubs)
			m.put(c.getPosition(), c);
		for (Vector pos : m.keySet()) {
			
		}
	}
	
	public void setMoving(TigressGame bg) {
		if (hasPassed()) System.out.println("passed");
		if (hasPassed() || firstPath || reset) {
			reset = false;
			Map<String, Integer> distances = getDistances(bg);
			int minDist = Integer.MAX_VALUE;
			Vertex closest = null;
			for (Cub c : bg.cubs) {
				Vertex pos = c.getVertex();
				if (distances.get(pos.toString()) < minDist) {
					minDist = distances.get(pos.toString());
					closest = pos;
				}
			}
			if (distances.get(bg.tigress.getVertex().toString()) < minDist) {
				minDist = distances.get(bg.tigress.getVertex().toString());
				closest = bg.tigress.getVertex();
			}
			
			LinkedList<Vertex> path;
			if (!firstPath) {
				path = search(getNextPos(), closest);
				currentPos = nextPos;
			} else {
				path = search(currentPos, closest);
				firstPath = false;
			}
			nextPos = path.get(0);
			if (nextPos.getX() > currentPos.getX()) {
				setVelocity(new Vector(.1f, 0f));
				direction = "right";
			} else if (nextPos.getX() < currentPos.getX()) {
				setVelocity(new Vector(-.1f, 0f));
				direction = "left";
			} else if (nextPos.getY() > currentPos.getY()) {
				setVelocity(new Vector(0f, .1f));
				direction = "below";
			} else {
				setVelocity(new Vector(0f, -.1f));
				direction = "above";
			}
			System.out.println("path: " + path);
			System.out.println("currentpos: " + currentPos.toString());
			System.out.println("nextpos: " + nextPos.toString());
			System.out.println("closest: " + closest);
			System.out.println("direction: " + direction);
			System.out.println("--------------------------------------------");
		}
	}	
	
	private boolean hasPassed() {
		if (direction != null && nextPos != null) {
			if (direction.equals("left"))
				return getPosition().getX() <= nextPos.getX();
			else if (direction.equals("right"))
				return getPosition().getX() >= nextPos.getX();
			else if (direction.equals("above"))
				return getPosition().getY() <= nextPos.getY();
			else
				return getPosition().getY() >= nextPos.getY();
		}
		return false;
	}
	
	/**
	 * Set whether the poacher is trapped in a vine or not.
	 * @param val: true if yes, false if not
	 */
	public void setTrapped(boolean val) {
		trapped = val;
	}
	
	/**
	 * @return trapped: true if trapped in vine, false if not
	 */
	public boolean isTrapped() {
		return trapped;
	}
	
	public Vertex getCurrentPos() {
		return currentPos;
	}
	
	public Vertex getNextPos() {
		return nextPos;
	}
	
	private Map<String, Integer> getDistances(TigressGame bg) {
		Queue<Vertex> frontier = new LinkedList<Vertex>();
		frontier.add(bg.poacher.getCurrentPos());
		Map<String, Integer> distance = new HashMap<String, Integer>();
		distance.put(bg.poacher.getCurrentPos().toString(), 0);
		
		while (!frontier.isEmpty()) {
			Vertex cur = frontier.poll();
			for (Vertex n : cur.getNeighbors()) {
				if (!distance.containsKey(n.toString())) {
					frontier.add(n);
					distance.put(n.toString(), 1 + distance.get(cur.toString()));
				}
			}
		}
		return distance;
	}
	
	public LinkedList<Vertex> search(Vertex start, Vertex goal) {
		LinkedList<Vertex> closedList = new LinkedList<Vertex>();
		LinkedList<Vertex> openList = new LinkedList<Vertex>();
		Map<String, Vertex> parents = new HashMap<String, Vertex>();
		openList.add(start);
		parents.put(start.toString(), null);
 
		while (!openList.isEmpty()) {
			Vertex v = openList.removeFirst();
			if (v.toString().equals(goal.toString())) {
				return constructPath(goal, parents);
			} else {
				closedList.add(v);
				  
				for (Vertex neighbor : v.getNeighbors()) {
					if (!closedList.contains(neighbor) && !openList.contains(neighbor)) {
						parents.put(neighbor.toString(), v);
						openList.add(neighbor);
					}
				}
			}
		}		    
		  
		return null;
	}
	
	protected LinkedList<Vertex> constructPath(Vertex v, Map<String, Vertex> parents) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		while (parents.get(v.toString()) != null) {
			path.addFirst(v);
			v = parents.get(v.toString());
		}
		return path;
	}
	
	protected void setReset() {
		reset = true;
		currentPos = new Vertex(50, 50);
	}
	
}
