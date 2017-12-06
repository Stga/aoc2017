import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemoryReallocator {

	private LinkedList<Integer> memBanks;
	private HashSet<String> bankAllocationSet;
	private ArrayList<String> bankAllocationList;
	private boolean bankAllocationsUnique;

	public MemoryReallocator(int[] banksBlockCounts) {
		this.memBanks = new LinkedList<Integer>();
		for(int blockCount : banksBlockCounts) {
			this.memBanks.add(blockCount);
		}
		this.bankAllocationSet = new HashSet<String>(this.memBanks.size());
		this.bankAllocationList = new ArrayList<String>(this.memBanks.size());
		this.bankAllocationsUnique = true;
	}
	
	private int getLargestBank() {
		int largestBank = -1;
		int maxBlockCount = 0;
		final int memBanksCount = this.memBanks.size();
		
		for(int i=0; i<memBanksCount; i++) {
			int currentBlockCount = this.memBanks.get(i);
			
			if(currentBlockCount>maxBlockCount) {
				maxBlockCount = currentBlockCount;
				largestBank = i;
			}
		}
		
		return largestBank;
	}
	
	public boolean saveAllocations() {
		String allocations = this.getAllocations();
		
		final boolean isUnique = this.bankAllocationSet.add(allocations);
		if(isUnique) {
			bankAllocationList.add(allocations);
		}
		
		return isUnique;
	}
	
	public String getAllocations() {
		String allocations = "";
		
		ListIterator<Integer> iter = this.memBanks.listIterator();
		while(iter.hasNext()) {
			allocations += iter.next();
		}
		
		return allocations;
	}
	
	public void reallocateBanks() {
		final int bankSize = this.memBanks.size();
		final int largestBank = this.getLargestBank();
		int distributableBlocks = this.memBanks.get(largestBank);
		ListIterator<Integer> iter = this.memBanks.listIterator(largestBank);
		iter.next();
		//Clean out the bank
		this.memBanks.set(largestBank, 0);
		
		//Redistribute blocks
		while(distributableBlocks > 0) {
			if(iter.hasNext()) {
				final int nextBank = iter.nextIndex();
				final int nextBanksBlockCount = iter.next();
				int newBlockCount = nextBanksBlockCount + 1;
				this.memBanks.set(nextBank, newBlockCount);
				distributableBlocks--;
			} else {
				iter = this.memBanks.listIterator();
			}
		}
	}
	
	public void printAllocations() {
		String allocations = "";
		
		for(int i=0; i<this.memBanks.size(); i++) {
			allocations += memBanks.get(i) + "\t";
		}
		
		System.out.println(allocations);
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("memBankSizes.txt"));
			String line = br.readLine();
			String[] lineValues = line.split("\\t");
			
			int[] banksBlockCounts = new int[lineValues.length];
			
			for(int i=0; i<lineValues.length; i++) {
				banksBlockCounts[i] = Integer.parseInt(lineValues[i]);
			}
			
			MemoryReallocator mr = new MemoryReallocator(banksBlockCounts);
			
			int j = -1;
			while(mr.bankAllocationsUnique) {
				mr.bankAllocationsUnique = mr.saveAllocations();
				mr.reallocateBanks();		
				j++;
			}
			
			System.out.println(j);
			mr.getAllocations();
			int previousAllocationLocation = mr.bankAllocationList.indexOf(mr.getAllocations())-1;
			System.out.println(j - previousAllocationLocation);
			br.close();
			
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
