package Exercise1;

class ListCell 
{
	char data;
	ListCell next;

	public ListCell(char x, ListCell c) 
	{
		data = x;
		next = c;
	}
}

class ListException extends RuntimeException 
{
	public ListException(String s) 
	{
		super(s);
	}
}

// interface for exercise 6
interface LListIterator 
{
	public boolean hasNext();

	public char next();

	public void remove();
}

public class LList 
{
	private ListCell front;

	public LList() 
	{
		front = null;
	}

	public void addToFront(char x) 
	{
		front = new ListCell(x, front);
	}

	public void addToBack(char x) 
	{
		if (front == null)
			front = new ListCell(x, front);
		else {
			ListCell c = front;
			while (c.next != null)
				c = c.next;
			c.next = new ListCell(x, null);
		}
	}

	public char elementAt(int n)
	{
		ListCell c = front;
		if (n < 0)
			throw new ListException("elementAt called with negative argument");
		for (int i = 0; i < n; i++) 
		{
			if (c == null)
				throw new ListException("no element at position " + n);
			c = c.next;
		}
		if (c == null)
			throw new ListException("no element at position " + n);
		return c.data;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer("<");
		ListCell c = front;
		while (c != null) 
		{
			sb.append(c.data);
			c = c.next;
		}
		return (sb + ">");
	}
	
	
	public int length()
	{
		ListCell cell;
		int count =0;
		
		for(cell = front; cell!=null; cell=cell.next)
		{
			count++;
			
		}
		return count;
	}
	
	
	public int occs(char c)
	{
		ListCell cell;
		int ans=0;
		
		for(cell= front; cell!=null; cell= cell.next)
		{
			if(c==cell.data)
			{
				ans++;
			}
		}
		
		
		return ans;
	}
	
	public void removeFront()
	{
		ListCell  cell = front; 
		if(cell==null)
		{
			throw new RuntimeException();
		}
		else if(cell!=null)
		{
			front = front.next;
		}
	}
	
	
	 public void removeBack()
	 {
		 ListCell cell= front;
		 if(cell==null)
		 {
			 throw new NullPointerException();
		 }
		 else if(cell!=null)
		 {
			 while(cell.next.next!=null)
			 {
				 cell = cell.next;
			 }
			 cell.next=null;
		 }
		 
	 }
	 
//	public boolean remove(char c) {
//		if (front == null)
//			return false;
//		else if (front.data == c) {
//			front = front.next;
//			return true;
//		}
//		ListCell ce = front;
//		while (ce.next != null && ce.next.data != c)
//			ce = ce.next;
//		if (ce.next == null)
//			return false;
//		else {
//			ce.next = ce.next.next;
//			return true;
//		}
//	}

	 public boolean remove(char c)
	 {
		 int count=0;
		 ListCell cell = front;
		 int rc =0;
		 if (cell != null && cell.data == c){
			 if (cell.next != null) front = cell.next;
		 }
		 else front = null;
		 while(cell!=null)
		 {
			 if(cell.next!=null && c==cell.next.data)
			 {
				 count++;
				 System.out.println("count = "+count);
				 System.out.println("cell.next "+ cell.next.data +"||"+ cell.next.next.data +"cell.next.next");
				 cell.next=cell.next.next;
			 }
			 cell=cell.next;
			 rc++;
			 System.out.println("rc= "+rc);
		 }
		 
		 return true;
	 }
	

	// main method to test the class - expected list contents shown as comments

	public static void main(String args[]) 
	{
		LList myList = new LList(); // <>
		System.out.println(myList);
		myList.addToFront('c'); // <c>
		myList.addToFront('b'); // <bc>
		myList.addToFront('a'); // <abc>
		System.out.println(myList);
		myList.addToBack('d'); // <abcd>
		myList.addToBack('e'); // <abcde>
		myList.addToBack('e'); // <abcde>
		
		System.out.println("the occerances is "+myList.occs('e'));
		System.out.println("length is "+myList.length());
		
		System.out.println("element before removing : "+ myList );
		myList.removeFront();
		System.out.println("element after removing : " +myList);
		
		myList.remove('b');
		//myList.removeBack();
		System.out.println("list after removing "+ myList);
		
		
		for (int i = -1; i < 7; i++)
			try 
			{
				System.out.println(myList.elementAt(i));
			} 
			catch (ListException e) 
			{
				System.out.println("ERROR: " + e);
			}
	}
}

