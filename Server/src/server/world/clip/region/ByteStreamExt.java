package server.world.clip.region;

public final class ByteStreamExt {

	public byte buffer[];

	public int currentOffset;

	public ByteStreamExt(byte abyte0[]) {
		buffer = abyte0;
		currentOffset = 0;
	}

	public int read3Bytes() {
		currentOffset += 3;
		return ((buffer[currentOffset - 3] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public byte[] readBytes() {
		int i = currentOffset;
		while (buffer[currentOffset++] != 10)
			;
		byte abyte0[] = new byte[currentOffset - i - 1];
		System.arraycopy(buffer, i, abyte0, i - i, currentOffset - 1 - i);
		return abyte0;
	}

	public void readBytes(int i, int j, byte abyte0[]) {
		for (int l = j; l < j + i; l++)
			abyte0[l] = buffer[currentOffset++];
	}

	public int readDWord() {
		currentOffset += 4;
		return ((buffer[currentOffset - 4] & 0xff) << 24)
				+ ((buffer[currentOffset - 3] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public String readNewString() {
		int i = currentOffset;
		while (buffer[currentOffset++] != 0)
			;
		return new String(buffer, i, currentOffset - i - 1);
	}

	public long readQWord() {
		long l = (long) readDWord() & 0xffffffffL;
		long l1 = (long) readDWord() & 0xffffffffL;
		return (l << 32) + l1;
	}

	public int readR3Bytes() {
		currentOffset += 3;
		return ((buffer[currentOffset - 1] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 3] & 0xff);
	}

	public byte readSignedByte() {
		return buffer[currentOffset++];
	}

	public int readSignedWord() {
		currentOffset += 2;
		int i = ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
		if (i > 32767)
			i -= 0x10000;
		return i;
	}

	public String readString() {
		int i = currentOffset;
		while (buffer[currentOffset++] != 10)
			;
		return new String(buffer, i, currentOffset - i - 1);
	}

	public int readUnsignedByte() {
		return buffer[currentOffset++] & 0xff;
	}

	public int readUnsignedWord() {
		currentOffset += 2;
		return ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}
	public void skip(int length) {
		currentOffset += length;
	}

	// removed useless static initializer
}
