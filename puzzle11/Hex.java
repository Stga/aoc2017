public class Hex {
	private int x;
	private int y;
	private int z;
	private int maxDistance;
	
	Hex() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.maxDistance = 0;
	}
	
	Hex(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.maxDistance = 0;
	}
	
	public int distance() {
		return (Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z) ) /2;
	}
	
	public int getMaxDistance() {
		return this.maxDistance;
	}
	
	public void setMaxDistance(int distance) {
		this.maxDistance = distance;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public String toString() {
		return String.format("x: %d, y: %d, z: %d", this.x, this.y, this.z);
	}
	
	public void move_n() {
		this.y += 1;
		this.z -= 1;
	}
	
	public void move_s() {
		this.y -= 1;
		this.z += 1;
	}
	
	public void move_ne() {
		this.x += 1;
		this.z -= 1;
	}
	
	public void move_sw() {
		this.x -= 1;
		this.z += 1;
	}
	            
	public void move_se() {
		this.x += 1;
		this.y -= 1;
	}
	
	public void move_nw() {
		this.x -= 1;
		this.y += 1;
	}
}