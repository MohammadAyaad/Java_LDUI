package Core.SpecialTypes;

public class BitArray {
	//an array of single bits (saving RAM)
	private byte[] array;
	public BitArray(int length)
	{
		int add = ((length & 0x7) == 0)?0:1;
		int byteLength = (length >> 3) + add;
		this.array = new byte[byteLength]; //allocate enough bytes
	}
	public void fill(boolean value)
	{
		byte data = (byte)((value)?-1:0);
		for(int i = 0;i < this.array.length;i++)
			this.array[i] = data;
	}
	public boolean readBit(int index)
	{
		int bit_shift = index & 7;
        int i = (int)(index & 0xFFFFFFF8) >> 3;
        return (((this.array[i] >> bit_shift) & 1) > 0); 
	}
	public void writeBit(int index,boolean bit)
	{
		int bit_shift = index & 7;
        int i = (int)(index & 0xFFFFFFF8) >> 3;
        this.array[i] = (byte)((byte)(((bit) ? 1 : 0) << bit_shift) | (this.array[i] & ((1 << bit_shift) ^ 0xff)));
	}
}
