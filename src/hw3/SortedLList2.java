package hw3;

public class SortedLList2<T extends Comparable<T>> implements ListInterface<T>
{
	private Node2 firstDummyNode; // Reference to first node of chain
	private Node2 lastDummyNode;
	private int numberOfEntries;

	public SortedLList2()
	{
		firstDummyNode = new Node2(null);
		lastDummyNode = new Node2(null);
		initializeDataFields();
	} // end default constructor

	@Override
	public void add(T newEntry)
	{
		// CHANGE the LList2 version so it inserts in the correct place
		// so the list stays in order, BUT stop traversing at the lastDummyNode
		// OR
		// when the newEntry <= current Node's data (using compareTo)
		// (don't forget to increment the numberOfEntries)
		Node2 newNode = new Node2(newEntry);
		Node2 compareTo = firstDummyNode.getNextNode();
		while (compareTo != lastDummyNode && newEntry.compareTo(compareTo.getData()) > 0)
		{
			compareTo = compareTo.getNextNode();
		}
		newNode.setPrevNode(compareTo.getPrevNode());
		newNode.setNextNode(compareTo);
		compareTo.getPrevNode().setNextNode(newNode);
		compareTo.setPrevNode(newNode);
		numberOfEntries++;
	}

	@Override
	public boolean add(int newPosition, T newEntry)
	{
		// COMPLETELY REWRITE the LList2 version so you
		// call add(T) passing newEntry, and ignore newPosition
		// and return true
		//		if (newPosition > numberOfEntries + 1 || newPosition < 1)
		//		{
		//			return false;
		//		}
		//		Node2 newNode = new Node2(newEntry);
		//		Node2 at = firstDummyNode;
		//		for (int i = 1; i < newPosition; i++)
		//		{
		//			at = at.getNextNode();
		//		}
		//
		//		newNode.setNextNode(at.getNextNode());
		//		newNode.setPrevNode(at);
		//		at.getNextNode().setPrevNode(newNode);
		//		at.setNextNode(newNode);
		//
		//		numberOfEntries++;
		//		return true;
		add(newEntry);
		return true;
	}

	@Override
	public boolean remove(T anEntry)
	{
		// CHANGE from Lab Ex. 4.2 Answers so it STOPS traversing
		// when you reach the lastDummyNode
		// OR when you either find anEntry (and USE compareTo)
		// or if the currentNode's data is > anEntry (using compareTo)
		// //
		// if found, do as done in LList2 (including decrementing
		// numberOfEntries) and return true,
		// otherwise return false
		Node2 hold = firstDummyNode.getNextNode();
		while( hold != lastDummyNode && hold.getData().compareTo(anEntry) < 0)
		{
			hold = hold.getNextNode();
		}
		if (hold == lastDummyNode || hold.getData().compareTo(anEntry) != 0)
		{
			return false;
		}
		Node2 nodeBefore = hold.getPrevNode();
		Node2 nodeAfter = hold.getNextNode();
		nodeBefore.setNextNode(nodeAfter); // Remove entry
		nodeAfter.setPrevNode(nodeBefore);

		numberOfEntries--; // Update count
		return true;
	}

	@Override
	public T remove(int givenPosition)
	{
		T result = null; // Return value

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			// same code if givenPosition is 1 or > 1
			Node2 nodeToRemove = getNodeAt(givenPosition);
			Node2 nodeBefore = nodeToRemove.getPrevNode();
			result = nodeToRemove.getData(); // Save entry to be removed
			Node2 nodeAfter = nodeToRemove.getNextNode();
			nodeBefore.setNextNode(nodeAfter); // Remove entry
			nodeAfter.setPrevNode(nodeBefore);

			numberOfEntries--; // Update count
			return result; // Return removed entry
		} else
			return null;
	} // end remove(int)

	@Override
	public void clear()
	{
		initializeDataFields();
	}

	@Override
	public T getEntry(int givenPosition)
	{
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			return getNodeAt(givenPosition).getData();
		} else
			return null;
	}

	@Override
	public boolean contains(T anEntry)
	{
		// CHANGE the LList2 version so it traverses the
		// list like in remove(T) so it STOPS traversing
		// when you reach the lastDummyNode
		// OR when you either find anEntry (and USE compareTo)
		// or if the currentNode's data is > anEntry (using compareTo)
		//
		// return true if found, false otherwise
		Node2 currNode;

		currNode = firstDummyNode.getNextNode();
		while (currNode != lastDummyNode && currNode.getData().compareTo(anEntry) >= 0)
		{
			currNode = currNode.getNextNode();
		}
		if (currNode == lastDummyNode || currNode.getData().compareTo(anEntry) > 0 )
		{
			return false;
		}
		return true;
	}

	@Override
	public int getLength()
	{
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty()
	{
		return numberOfEntries == 0;
	}

	@Override
	public void display()
	{
		Node2 currNode;

		currNode = firstDummyNode.getNextNode(); // FOR LAB EXERCISE 4.2
		while (currNode != lastDummyNode) // FOR LAB EXERCISE 4.2
		{
			System.out.println(currNode.getData());
			currNode = currNode.getNextNode();
		}
	} // end display

	// YOU FINISH THIS METHOD SO IT DISPLAYS
	// ALL THE DATA IN THE LIST BACKWARDS
	// (remember to ignore the dummy nodes' data)
	public void displayBackwards()
	{
		Node2 currNode;

		currNode = lastDummyNode.getPrevNode(); // FOR LAB EXERCISE 4.2
		while (currNode != firstDummyNode) // FOR LAB EXERCISE 4.2
		{
			System.out.println(currNode.getData());
			currNode = currNode.getPrevNode();
		}
	}

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields()
	{
		firstDummyNode.setNextNode(lastDummyNode);
		lastDummyNode.setPrevNode(firstDummyNode);
		numberOfEntries = 0;
	} // end initializeDataFields

	// Returns a reference to the Node2 at a given position.
	// Precondition: The chain is not empty;
	// 1 <= givenPosition <= numberOfEntries.
	// Returns a reference to the node at a given position.
	// Precondition: The chain is not empty;
	// 1 <= givenPosition <= numberOfEntries.
	// CHANGE TO search forwards ONLY if givenPosition is between 1 and
	// numberOfEntries/2 (inclusive)
	private Node2 getNodeAt(int givenPosition)
	{
		if (givenPosition < 1 )
			return firstDummyNode;
		else if (givenPosition <= (numberOfEntries + 1) / 2)// CHANGE
		{
			Node2 currentNode = firstDummyNode.getNextNode();

			// Traverse the chain to locate the desired node
			// (skipped if givenPosition is 1)
			for (int counter = 1; counter < givenPosition; counter++)
				currentNode = currentNode.getNextNode();

			return currentNode;
		} 
		else if ( givenPosition <= numberOfEntries )
		{
			Node2 currentNode = lastDummyNode.getPrevNode();

			for (int counter = numberOfEntries; counter > givenPosition; counter--)
				currentNode = currentNode.getPrevNode();

			return currentNode;
		}
		return lastDummyNode;
	} // end getNodeAt

	private class Node2
	{
		private T data; // Entry in list
		private Node2 next; // Link to next Node2
		private Node2 prev; // Link to previous Node2

		private Node2(T dataPortion)
		{
			data = dataPortion;
			next = null;
			prev = null;
		} // end constructor

		private Node2(T dataPortion, Node2 nextNode)
		{
			data = dataPortion;
			next = nextNode;
			prev = null;
		} // end constructor

		private T getData()
		{
			return data;
		} // end getData

		private void setData(T newData)
		{
			data = newData;
		} // end setData

		private Node2 getNextNode()
		{
			return next;
		} // end getNextNode

		private void setNextNode(Node2 nextNode)
		{
			next = nextNode;
		} // end setNextNode

		private Node2 getPrevNode()
		{
			return prev;
		} // end getNextNode

		private void setPrevNode(Node2 prevNode)
		{
			prev = prevNode;
		} // end setNextNode
	} // end Node2
} // end SortedLList2 class
