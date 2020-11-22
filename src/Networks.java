import java.util.ArrayList;
import java.util.Random;


public class Networks {
	public ArrayList<Link> NetworkLink(int nodeNumber) {
		ArrayList<Link> Links = new ArrayList<Link>();
		ArrayList<String> unvisited = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		
		//set nodes to the unvisited nodes
		for (int i = 1; i <= nodeNumber; i++) {
			String tempString = String.valueOf(i);
			unvisited.add(tempString);
		}
		
		//randomly generate the node
		Random random = new Random();
		int fst_node_index = random.nextInt(unvisited.size());
		
		//randomly select the first node to start
		visited.add(unvisited.get(fst_node_index));
		unvisited.remove(fst_node_index);
		
		
		while(unvisited.size() != 0) {
			Link generate_link = new Link();
			int visited_node = random.nextInt(visited.size());
			int unvisited_node = random.nextInt(unvisited.size());
			generate_link.source = visited.get(visited_node);
			generate_link.dest = unvisited.get(unvisited_node);
			generate_link.bandwidth = 50 + random.nextInt(100);
			generate_link.cost = 100/(double)generate_link.bandwidth;
			if(generate_link.cost < 1) generate_link.cost = 1;
			Links.add(generate_link);
			visited.add(unvisited.get(unvisited_node));
			unvisited.remove(unvisited_node);
		}
		
		for (int i = 0; i < visited.size(); i++) {
			for (int j = 0; j < visited.size(); j++) {
				if (i != j) {
					boolean exists = false;
					int k = 0;
					while (k < Links.size()) {
						if (visited.get(i).equals(Links.get(k).source) == true) {
							if (visited.get(j).equals(Links.get(k).dest) == true) {
								exists = true;
								break;
							}
						}
						if (visited.get(i).equals(Links.get(k).dest) == true) {
							if (visited.get(j).equals(Links.get(k).source) == true) {
								exists = true;
								break;
							}
						}
						k++;
					}
					if (!exists) {
						int choice = random.nextInt(2);
						if (choice == 1) {
							Link newlink = new Link();
							newlink.source = visited.get(i);
							newlink.dest = visited.get(j);
							newlink.bandwidth = 50 + random.nextInt(100);
							newlink.cost = 100/(double)newlink.bandwidth;
							if(newlink.cost < 1) newlink.cost = 1;
							Links.add(newlink);
						}
					}
				}
			}
		}
		for (int i = 0; i < Links.size(); i++) {
			System.out.print("Source: " + Links.get(i).source + "    ");
			System.out.println("Destination: " + Links.get(i).dest);
			System.out.println("Bandwidth: " + Links.get(i).bandwidth + " Mbps"
					+ "    Cost: " + Math.round(Links.get(i).cost*100)/100.0	);
			System.out.println(" ");
		}
		return Links;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Networks testing = new Networks();
		testing.NetworkLink(5);
	}

}
