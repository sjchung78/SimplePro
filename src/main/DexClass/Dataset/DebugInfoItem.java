package main.DexClass.Dataset;

public class DebugInfoItem {

	private int lineStart = 0;
	private int parametersSize = 0;
	private int[] parameterName = null;
	private ByteCode[] bc = null;
	public int getLineStart() {
		return lineStart;
	}
	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}
	public int getParametersSize() {
		return parametersSize;
	}
	public void setParametersSize(int parametersSize) {
		this.parametersSize = parametersSize;
	}
	public int[] getParameterName() {
		return parameterName;
	}
	public void setParameterName(int[] parameterName) {
		this.parameterName = parameterName;
	}
	public ByteCode[] getBc() {
		return bc;
	}
	public void setBc(ByteCode[] bc) {
		this.bc = bc;
	}
	
}
