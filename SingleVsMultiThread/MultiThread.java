public class MultiThread extends Thread
{
	private Object[] obj;
	long totalSum;

	public MultiThread(Object[] obj)
	{
		this.obj = obj;
	}

	public long getSum()
	{
		return this.totalSum;
	}

	public void run()
	{
		for (int x = 0; x < this.obj.length; x++)
		{
			this.totalSum += (Integer) this.obj[x];
		}
	}
}
