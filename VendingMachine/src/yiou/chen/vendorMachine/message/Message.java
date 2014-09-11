package yiou.chen.vendorMachine.message;

public abstract class Message implements IDisplayable{
	protected int mAutority;
	public static final int NO_AUTHORITY=0;
	public static final int CUSTOMER=1;
	public static final int OPERATOR=2;
	public Message(){
		mAutority=setAutorityLevel();
	}
	public int getAutority() {
		return mAutority;
	}
	@Override
	public String showDiscription() {
		return null;
	}
	protected abstract int setAutorityLevel();
}
